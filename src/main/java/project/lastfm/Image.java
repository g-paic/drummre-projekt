package project.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	@JsonProperty("#text")
	private String text;
	private String size;
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getSize() {
		return this.size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "(text=" + this.text + ", size=" + this.size + ")";
	}
}
