package project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SongSocialRespDto {
    @Override
    public String toString() {
        return "SongSocialRespDto{" +
                "songName='" + songName + '\'' +
                ", in_spotify_playlists='" + in_spotify_playlists + '\'' +
                ", in_spotify_charts='" + in_spotify_charts + '\'' +
                ", streams='" + streams + '\'' +
                ", in_apple_playlists='" + in_apple_playlists + '\'' +
                ", in_apple_charts='" + in_apple_charts + '\'' +
                ", in_deezer_playlists='" + in_deezer_playlists + '\'' +
                ", in_deezer_charts='" + in_deezer_charts + '\'' +
                ", in_shazam_charts='" + in_shazam_charts + '\'' +
                '}';
    }

    private String songName;

    private String in_spotify_playlists;

    private String in_spotify_charts;
    private String streams;

    private String in_apple_playlists;
    private String in_apple_charts;

    private String in_deezer_playlists;

    private String in_deezer_charts;

    private String in_shazam_charts;
}
