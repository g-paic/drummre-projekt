package project.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import project.forms.Provider;

import java.util.List;

@Document(collection = "users")
public class User {
	@Id
	private String id;

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean enabled;


	private List<String> likedSongs;

	public List<String> getLikedSongs() {
		return likedSongs;
	}

	public void setLikedSongs(List<String> likedSongs) {
		this.likedSongs = likedSongs;
	}

	@Enumerated(EnumType.STRING)
	private Provider provider;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return this.id + " - " + this.username;
	}
}
