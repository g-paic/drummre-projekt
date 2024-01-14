package project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongMoodData {

            private String name;
            private String album;
            private String artist;
            private String release_date;
            private Integer popularity;
            private Integer length;

            private String danceability;//
            private String energy;//
            private String key;//
            private String loudness;//
            private String speechiness;//
            private String acousticness;//
            private String instrumentalness;//
            private String liveness;//
            private String valence;//
            private String tempo;//
            private String id;//
            private String time_signature;//

            private String mood;



}
