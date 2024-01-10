package project.deezer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	private String id;
	private boolean readable;
	private String title;
	private String title_short;
	private String title_version;
	private String link;
	private String duration;
	private String rank;
	private boolean explicit_lyrics;
	private int explicit_content_lyrics;
	private int explicit_content_cover;
	private String preview;
	private String md5_image;
	private Artist artist;
	private Album album;
	private String type;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isReadable() {
		return this.readable;
	}
	
	public void setReadable(boolean readable) {
		this.readable = readable;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle_short() {
		return this.title_short;
	}
	
	public void setTitle_short(String title_short) {
		this.title_short = title_short;
	}
	
	public String getTitle_version() {
		return this.title_version;
	}
	
	public void setTitle_version(String title_version) {
		this.title_version = title_version;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getDuration() {
		return this.duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getRank() {
		return this.rank;
	}
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public boolean isExplicit_lyrics() {
		return this.explicit_lyrics;
	}
	
	public void setExplicit_lyrics(boolean explicit_lyrics) {
		this.explicit_lyrics = explicit_lyrics;
	}
	
	public int getExplicit_content_lyrics() {
		return this.explicit_content_lyrics;
	}
	
	public void setExplicit_content_lyrics(int explicit_content_lyrics) {
		this.explicit_content_lyrics = explicit_content_lyrics;
	}
	
	public int getExplicit_content_cover() {
		return this.explicit_content_cover;
	}
	
	public void setExplicit_content_cover(int explicit_content_cover) {
		this.explicit_content_cover = explicit_content_cover;
	}
	
	public String getPreview() {
		return this.preview;
	}
	
	public void setPreview(String preview) {
		this.preview = preview;
	}
	
	public String getMd5_image() {
		return this.md5_image;
	}
	
	public void setMd5_image(String md5_image) {
		this.md5_image = md5_image;
	}
	
	public Artist getArtist() {
		return this.artist;
	}
	
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public Album getAlbum() {
		return this.album;
	}
	
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "id=" + this.id + ", readable=" + this.readable + ", title=" + this.title + ", title_short=" + this.title_short
				+ ", title_version=" + this.title_version + ", link=" + this.link + ", duration=" + this.duration 
				+ ", rank=" + this.rank + ", explicit_lyrics=" + this.explicit_lyrics 
				+ ", explicit_content_lyrics=" + this.explicit_content_lyrics 
				+ ", explicit_content_cover=" + this.explicit_content_cover + ", preview=" + this.preview 
				+ ", md5_image=" + this.md5_image + ", artist=" + this.artist + ", album=" + this.album + ", type=" + this.type;
	}
}
