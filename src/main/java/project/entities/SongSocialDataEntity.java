package project.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("songs_social_data")
public class SongSocialDataEntity {

    @Id
    private String _id;
    private String name;
    private String artists;
    private String artist_count;

    private String in_spotify_playlists;

    private String in_spotify_charts;

    private String streams;
    private String in_apple_playlists;

    private String in_apple_charts;

    private String in_deezer_playlists;

    private String  in_deezer_charts;

    private String in_shazam_charts;

    private String bpm;

    private String mode;

}

