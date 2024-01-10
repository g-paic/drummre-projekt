package project.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Streamable {
	@JsonProperty("#text")
	private String text;
	private String fulltrack;
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getFulltrack() {
		return this.fulltrack;
	}
	
	public void setFulltrack(String fulltrack) {
		this.fulltrack = fulltrack;
	}

	@Override
	public String toString() {
		return "(text=" + this.text + ", fulltrack=" + this.fulltrack + ")";
	}
}
