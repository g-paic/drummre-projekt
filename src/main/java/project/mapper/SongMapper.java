package project.mapper;

import project.dto.SongRespDto;
import project.entities.SongEntity;

public class SongMapper {

    public SongRespDto toSongResp(SongEntity songEntity) {
        SongRespDto songRespDto = new SongRespDto();
        songRespDto.setArtist(songEntity.getArtist());
        songRespDto.setName(songEntity.getName());
        songRespDto.setLyrics(songEntity.getLyrics());
        songRespDto.setRelease_date(songEntity.getRelease_date());
        return songRespDto;
    }
}