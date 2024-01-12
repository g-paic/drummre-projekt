package project.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

public interface SongsService {


    String checkLyricsMoodDetection(String songLyrics);

     void getSocialDataForMoodDetection(List<String> songIds) throws IOException;


}
