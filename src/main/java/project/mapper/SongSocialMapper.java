package project.mapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import project.dto.SongSocialRespDto;
import project.entities.SongSocialDataEntity;

@Configuration
public class SongSocialMapper {

    public SongSocialRespDto toSongSocialRespDto(SongSocialDataEntity songSocialDataEntity) {
        SongSocialRespDto songSocialRespDto = new SongSocialRespDto();
        songSocialRespDto.setSongName(songSocialDataEntity.getName());
        songSocialRespDto.setStreams(songSocialDataEntity.getStreams());
        songSocialRespDto.setIn_apple_charts(songSocialDataEntity.getIn_apple_charts());
        songSocialRespDto.setIn_apple_playlists(songSocialDataEntity.getIn_apple_playlists());
        songSocialRespDto.setIn_deezer_playlists(songSocialDataEntity.getIn_deezer_playlists());
        songSocialRespDto.setIn_deezer_charts(songSocialDataEntity.getIn_deezer_charts());
        songSocialRespDto.setIn_shazam_charts(songSocialDataEntity.getIn_shazam_charts());
        songSocialRespDto.setIn_spotify_charts(songSocialDataEntity.getIn_spotify_charts());
        songSocialRespDto.setIn_spotify_playlists(songSocialDataEntity.getIn_spotify_playlists());
        return songSocialRespDto;
    }
}
