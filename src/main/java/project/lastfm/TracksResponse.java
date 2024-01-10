package project.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TracksResponse {
    private Tracks tracks;

	public Tracks getTracks() {
		return this.tracks;
	}

	public void setTracks(Tracks tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return this.tracks.toString();
	}
}
