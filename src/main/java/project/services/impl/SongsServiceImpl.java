package project.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.dto.OpenAiReq;
import project.services.SongsService;

@Service
@RequiredArgsConstructor
public class SongsServiceImpl implements SongsService {

    private final RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String url;

    @Value("${openai.model}")
    private String model;

    @Override
    public String checkLyricsMoodDetection(String songLyrics) {

        String question = """ 
                           answer me on this question. What is the mood of this song lyrics %s ? A: happy, B:sad, C:calm, D:energetic ?"""; //this is question for open ai
        String prompt = String.format(question, songLyrics);
        OpenAiReq openAiReq = new OpenAiReq(model, prompt);
        String mood = restTemplate.postForObject(url, openAiReq, String.class);

        return mood;
    }
}
