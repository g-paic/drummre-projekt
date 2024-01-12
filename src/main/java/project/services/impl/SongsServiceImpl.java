package project.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.dto.OpenAiReq;
import project.dto.OpenAiResp;
import project.services.SongsService;

@Service
@RequiredArgsConstructor
public class SongsServiceImpl implements SongsService {


    private final RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String url;

    @Value("${openai.model}")
    private String model;

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
}
