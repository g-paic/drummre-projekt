package project.lastfm;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracks {
    private Track[] track;
    
	public Track[] getTrack() {
		return this.track;
	}

	public void setTrack(Track[] track) {
		this.track = track;
	}

	@Override
	public String toString() {
		return Arrays.toString(this.track);
	}
}
