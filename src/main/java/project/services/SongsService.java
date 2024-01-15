package project.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.dto.SpotifyResp;
import project.entities.SongEntity;

import java.io.IOException;
import java.util.List;

public interface SongsService {


    String checkLyricsMoodDetection(String songLyrics);

    List<SongEntity> getSocialDataForMoodDetection(List<SongEntity> songs) throws IOException;

     List<SongEntity> fetchSongsForGivenUserMoodData(List<String> mood) throws IOException;

     List<SongEntity> getRelatedSongsForLikedSongs(List<SongEntity> likedSongList);


}
