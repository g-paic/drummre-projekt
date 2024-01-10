package project.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.deezer.DataSet;

@Service
public class DeezerService {
    private RestTemplate restTemplate;

    public DeezerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DataSet getDataFromUrl(String artist) {
    	String link = "https://api.deezer.com/search/track?q=" + artist;
        ResponseEntity<DataSet> responseEntity = this.restTemplate.getForEntity(link, DataSet.class);
        return responseEntity.getBody();
    }
}
