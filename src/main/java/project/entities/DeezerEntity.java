package project.entities;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deezer")
public class DeezerEntity {
	@Id
	private String id;
	
	private String title;
	private String duration;
	private String preview;
	private String md5_image;
	private String album;
	private String artist;
	
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
	
	public String getDuration() {
		return this.duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
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
	
	public String getAlbum() {
		return this.album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((md5_image == null) ? 0 : md5_image.hashCode());
		result = prime * result + ((preview == null) ? 0 : preview.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeezerEntity other = (DeezerEntity) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (md5_image == null) {
			if (other.md5_image != null)
				return false;
		} else if (!md5_image.equals(other.md5_image))
			return false;
		if (preview == null) {
			if (other.preview != null)
				return false;
		} else if (!preview.equals(other.preview))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
