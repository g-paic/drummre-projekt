package project.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.lastfm.TracksResponse;

@Service
public class LastFmService {

    @Value("${lastfm.api.key}")
    private String apiKey;

    private String apiUrl = "http://ws.audioscrobbler.com/2.0";
    private RestTemplate restTemplate;

    public LastFmService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TracksResponse getTracksFromUrl(String tag) {
    	String link = this.apiUrl + "/?method=tag.gettoptracks&tag=" + tag + "&api_key=" + this.apiKey + "&format=json";
        ResponseEntity<TracksResponse> responseEntity = this.restTemplate.getForEntity(link, TracksResponse.class);
        return responseEntity.getBody();
    }
}
