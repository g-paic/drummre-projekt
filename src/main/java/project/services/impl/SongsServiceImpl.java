package project.services.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import project.dto.AccessTokenSpotify;
import project.dto.OpenAiReq;
import project.dto.OpenAiResp;
import project.dto.SpotifyResp;
import project.entities.SongEntity;
import project.services.SongsService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongsServiceImpl implements SongsService {


    @Qualifier("openaiRestTemplate")
    private final RestTemplate restTemplate;

    private RestTemplate restTemplateSpotify;

    @Value("${openai.api.url}")
    private String url;

    @Value("${openai.model}")
    private String model;

    @Value("${spotify.client.id}")
    private String spotify_client_id;

    @Value("${spotify.client.secret}")
    private String spotify_client_secret;

    @Value("${spotify.url.token}")
    private String spotifyUrlToken;

    //common question for openai api
    private static final String QUESTION = """ 
            answer me on this question. What is the mood of this song lyrics %s ? A: happy, B:sad, C:calm, D:energetic ?, one word answer""";

    public void determineMoodsOfSongs() {

    }

    //function that returns mood of given lyrics song
    @Override
    public String checkLyricsMoodDetection(String songLyrics) {
        String prompt = String.format(QUESTION, songLyrics);
//        System.out.println("---Song lyrics is---");
//        System.out.println(songLyrics);
        OpenAiReq openAiReq = new OpenAiReq(model, prompt);
        OpenAiResp response = restTemplate.postForObject(url, openAiReq, OpenAiResp.class);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        System.out.println("Mood of this song is " + response.getChoices().get(0).getMessage().getContent().split(": ")[1]);

        return response.getChoices().get(0).getMessage().getContent().split(": ")[1];
    }


    //connect to spotify api
    public void getSocialDataForMoodDetection(List<String> songIds) throws IOException {
        restTemplateSpotify = new RestTemplate();
        String accessToken = getSpotifyAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " +  accessToken);
        HttpEntity<String> req = new HttpEntity<String>(headers);
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < songIds.size(); i++) {
            sb.append(songIds.get(i));
            if(i != songIds.size() - 1) {
                sb.append(",");
            }
        }
        String endpoint = "https://api.spotify.com/v1/audio-features/?ids=" + sb.toString();
        ResponseEntity<String> response = restTemplateSpotify.exchange(endpoint, HttpMethod.GET, req, String.class);
        System.out.println(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SpotifyResp songs = objectMapper.readValue( response.getBody(), SpotifyResp.class);
        songs.getAudio_features().forEach(s -> System.out.println("energy " + s.getEnergy()));
    }


    //returns access token for spotify api
    private String getSpotifyAccessToken() throws JsonProcessingException {
        restTemplateSpotify = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String credentials = spotify_client_id + ":" + spotify_client_secret;
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes()));
        MultiValueMap<String, String> bodyParamMap = new LinkedMultiValueMap<String, String>();
        bodyParamMap.add( "grant_type", "client_credentials");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyParamMap, headers);
        ResponseEntity<String> res = restTemplateSpotify.postForEntity(spotifyUrlToken, request, String.class);
        if(res.getStatusCode().value() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            AccessTokenSpotify accessToken = objectMapper.readValue(res.getBody(), AccessTokenSpotify.class);
            return accessToken.getAccess_token();

        }

        return null;
    }
}
