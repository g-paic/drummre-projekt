package project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongMoodData {

    private Integer danceability;
            private String energy;
           private String  key;
            private String loudness;
            private String mode;
            private String speechiness;
            private String acousticness;
            private String instrumentalness;
            private String liveness;
            private String valence;
            private String tempo;
            private String type;
            private String id;
            private String uri;
            private String track_href;
            private String analysis_url;
            private String duration_ms;
            private String time_signature;

}
