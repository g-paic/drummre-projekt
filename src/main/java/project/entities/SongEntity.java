package project.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(collection = "song_lyrics")
public class SongEntity {

    @Id
    private String _id;

    @Override
    public String toString() {
        return "SongEntity{" +
                "id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", release_date='" + release_date + '\'' +
                ", popularity='" + popularity + '\'' +
                ", length='" + length + '\'' +
                ", danceability='" + danceability + '\'' +
                ", acousticness='" + acousticness + '\'' +
                ", energy='" + energy + '\'' +
                ", instrumentalness='" + instrumentalness + '\'' +
                ", liveness='" + liveness + '\'' +
                ", valence='" + valence + '\'' +
                ", loudness='" + loudness + '\'' +
                ", speechiness='" + speechiness + '\'' +
                ", tempo='" + tempo + '\'' +
                ", key='" + key + '\'' +
                ", time_signature='" + time_signature + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }

    private String name;
    private String album;
    private String artist;

    private String spotifyId;

    private String release_date;
    private String lyrics;

    private String popularity;
    private String length;
    private String danceability;
    private String acousticness;
    private String energy;
    private String instrumentalness;
    private String liveness;
    private String valence;
    private String loudness;
    private String speechiness;
    private String tempo;
    private String key;
    private String time_signature;
    private String mood;


}
