package project.dto;

import lombok.Getter;
import lombok.Setter;
import project.entities.SongEntity;

import java.util.List;

@Getter
@Setter
public class SpotifyResp {

    public List<SongEntity> audio_features;
}
