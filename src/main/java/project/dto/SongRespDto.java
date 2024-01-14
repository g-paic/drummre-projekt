package project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SongRespDto {

    private String name;
    private String artist;

    private String release_date;

    private String popularity;

    private String lyrics;
}
