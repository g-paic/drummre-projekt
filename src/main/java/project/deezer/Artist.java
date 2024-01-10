package project.deezer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
	private String id;
	private String name;
	private String link;
	private String picture;
	private String picture_small;
	private String picture_medium;
	private String picture_big;
	private String picture_xl;
	private String tracklist;
	private String type;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getPicture() {
		return this.picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getPicture_small() {
		return this.picture_small;
	}
	
	public void setPicture_small(String picture_small) {
		this.picture_small = picture_small;
	}
	
	public String getPicture_medium() {
		return this.picture_medium;
	}
	
	public void setPicture_medium(String picture_medium) {
		this.picture_medium = picture_medium;
	}
	
	public String getPicture_big() {
		return this.picture_big;
	}
	
	public void setPicture_big(String picture_big) {
		this.picture_big = picture_big;
	}
	
	public String getPicture_xl() {
		return this.picture_xl;
	}
	
	public void setPicture_xl(String picture_xl) {
		this.picture_xl = picture_xl;
	}
	
	public String getTracklist() {
		return this.tracklist;
	}
	
	public void setTracklist(String tracklist) {
		this.tracklist = tracklist;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "(id=" + this.id + ", name=" + this.name + ", link=" + this.link + ", picture=" + this.picture + ", picture_small="
				+ this.picture_small + ", picture_medium=" + this.picture_medium + ", picture_big=" + this.picture_big
				+ ", picture_xl=" + this.picture_xl + ", tracklist=" + this.tracklist + ", type=" + this.type + ")";
	}
}
