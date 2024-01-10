package project.lastfm;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
	private String name;
	private String duration;
	private String mbid;
	private String url;
	private Streamable streamable;
	private Artist artist;
	private Image[] image;
	private Attributes attributes;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDuration() {
		return this.duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getMbid() {
		return this.mbid;
	}
	
	public void setMbid(String mbid) {
		this.mbid = mbid;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Streamable getStreamable() {
		return this.streamable;
	}
	
	public void setStreamable(Streamable streamable) {
		this.streamable = streamable;
	}
	
	public Artist getArtist() {
		return this.artist;
	}
	
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public Image[] getImage() {
		return this.image;
	}
	
	public void setImage(Image[] image) {
		this.image = image;
	}
	
	public Attributes getAttributes() {
		return this.attributes;
	}
	
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String toString() {
		return "name=" + this.name + ", duration=" + this.duration + ", mbid=" + this.mbid + ", url=" + this.url + ", streamable="
				+ this.streamable + ", artist=" + this.artist + ", image=" + Arrays.toString(this.image) + ", attributes=" 
				+ this.attributes;
	}
}
