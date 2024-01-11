package project.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public interface SongsService {


    String checkLyricsMoodDetection(String songLyrics);
}
