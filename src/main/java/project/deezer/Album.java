package project.deezer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
	private String id;
	private String title;
	private String cover;
	private String cover_small;
	private String cover_medium;
	private String cover_big;
	private String cover_xl;
	private String md5_image;
	private String tracklist;
	private String type;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCover() {
		return this.cover;
	}
	
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public String getCover_small() {
		return this.cover_small;
	}
	
	public void setCover_small(String cover_small) {
		this.cover_small = cover_small;
	}
	
	public String getCover_medium() {
		return this.cover_medium;
	}
	
	public void setCover_medium(String cover_medium) {
		this.cover_medium = cover_medium;
	}
	
	public String getCover_big() {
		return this.cover_big;
	}
	
	public void setCover_big(String cover_big) {
		this.cover_big = cover_big;
	}
	
	public String getCover_xl() {
		return this.cover_xl;
	}
	
	public void setCover_xl(String cover_xl) {
		this.cover_xl = cover_xl;
	}
	
	public String getMd5_image() {
		return this.md5_image;
	}
	
	public void setMd5_image(String md5_image) {
		this.md5_image = md5_image;
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
		return "(id=" + this.id + ", title=" + this.title + ", cover=" + this.cover + ", cover_small=" + this.cover_small
				+ ", cover_medium=" + this.cover_medium + ", cover_big=" + this.cover_big + ", cover_xl=" + this.cover_xl
				+ ", md5_image=" + this.md5_image + ", tracklist=" + this.tracklist + ", type=" + this.type + ")";
	}
}
