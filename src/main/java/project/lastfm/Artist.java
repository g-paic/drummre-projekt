package project.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
	private String name;
	private String mbid;
	private String url;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "(name=" + this.name + ", mbid=" + this.mbid + ", url=" + this.url + ")";
	}
}
