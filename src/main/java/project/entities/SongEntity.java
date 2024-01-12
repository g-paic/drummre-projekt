package project.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(collection = "songs")
public class SongEntity {

    @Id
    private String id;

    private String name;
    private String album;
    private String artist;

    private String release_date;

    private Integer popularity;
    private Integer length;
    private Integer danceability;
    private Integer acousticness;
    private Integer energy;
    private Integer instrumentalness;
    private Integer liveness;
    private Integer valence;
    private Integer loudness;
    private Integer speechiness;
    private String tempo;
    private String key;
    private String time_signature;
   private String mood;


}
