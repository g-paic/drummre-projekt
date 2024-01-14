package project.services.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import project.dto.AccessTokenSpotify;
import project.dto.OpenAiReq;
import project.dto.OpenAiResp;
import project.dto.SpotifyResp;
import project.entities.SongEntity;
import project.repositories.SongRepository;
import project.services.SongsService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
            answer me on this question. What is the mood of this song lyrics %s ? A: Happy, B: Sad, C: Calm, D: Energetic ?, one word answer""";


    @Autowired
    private SongRepository songRepository;
    public List<SongEntity> fetchSongsForGivenUserMoodData(String mood) throws IOException {
        List<SongEntity> songs = songRepository.findAll();
        //first of all check if exist songs which mood is not calculated
        List<SongEntity> songsWithoutMoodCalculated = songs.stream().filter(song -> song.getMood() == null).collect(Collectors.toList());
        if(!songsWithoutMoodCalculated.isEmpty()) {
            // check spotify social data if they are fetched form spotify api
            songs = songs.stream().filter(song -> song.getAcousticness() == null).collect(Collectors.toList());
            //fetched song data for mood calculation and stored to database
            songRepository.saveAll(getSocialDataForMoodDetection(songs));

            songs = songRepository.findAll();
            songs =  songs.stream().filter(song -> song.getMood() == null).collect(Collectors.toList());
            //todo: calculate mood for new songs in database
        }
        //fetch songs with given mood
        songs = songRepository.findAllByMood(mood);
//      songs.forEach(song -> System.out.println(song.toString()));
        return songs;


    }

    //function that returns mood of given lyrics song
    @Override
    public String checkLyricsMoodDetection(String songLyrics) {
        String prompt = String.format(QUESTION, songLyrics);
        OpenAiReq openAiReq = new OpenAiReq(model, prompt);
        OpenAiResp response = restTemplate.postForObject(url, openAiReq, OpenAiResp.class);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        System.out.println("Mood of this song is " + response.getChoices().get(0).getMessage().getContent().split(": ")[1]);

        return response.getChoices().get(0).getMessage().getContent().split(": ")[1];
    }


    //connect to spotify api
    public List<SongEntity> getSocialDataForMoodDetection(List<SongEntity> songs) throws IOException {
        restTemplateSpotify = new RestTemplate();
        String accessToken = getSpotifyAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " +  accessToken);
        HttpEntity<String> req = new HttpEntity<String>(headers);
        List<String> songIds = songs.stream()
                .map(SongEntity::getSpotifyId).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < songIds.size(); i++) {
            if(i < 100) {
                sb.append(songIds.get(i));
                if(i != Math.min(songIds.size(), 100) - 1 ) {
                    sb.append(",");
                }
            } else {
                break;
            }

        }
        String endpoint = "https://api.spotify.com/v1/audio-features/?ids=" + sb.toString();
        ResponseEntity<String> response = restTemplateSpotify.exchange(endpoint, HttpMethod.GET, req, String.class);
        System.out.println(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SpotifyResp songResp = objectMapper.readValue( response.getBody(), SpotifyResp.class);
        songResp.getAudio_features().forEach(el -> System.out.println(el.getAcousticness()));
        List<SongEntity> returnList = songResp.getAudio_features();
        for(int i = 0 ; i < songResp.getAudio_features().size(); i++) {
            returnList.get(i).setArtist(songs.get(i).getArtist());
            returnList.get(i).setAlbum(songs.get(i).getAlbum());
            returnList.get(i).setLength(songs.get(i).getLength());
            returnList.get(i).setName(songs.get(i).getName());
            returnList.get(i).setRelease_date(songs.get(i).getRelease_date());
            returnList.get(i).setPopularity(songs.get(i).getPopularity());

        }

        System.out.println("songs after fetching mood data");
        System.out.println(returnList.get(0).toString());
//        returnList.forEach(el -> System.out.println(el.toString()));

        return returnList;

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
