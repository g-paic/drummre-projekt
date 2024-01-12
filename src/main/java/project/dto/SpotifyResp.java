package project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpotifyResp {

    public List<SongMoodData> audio_features;
}
