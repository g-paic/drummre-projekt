package project.services;

import java.util.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.entities.SongEntity;
import project.entities.User;
import project.forms.CustomOAuth2User;
import project.forms.Provider;
import project.repositories.SongRepository;
import project.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private SongRepository songRepository;
	
	public void processOAuthPostLogin(CustomOAuth2User oauthUser) {
		User existUser = null;
		
		try {
			existUser = repo.getUserByUsername(oauthUser.getEmail()).get();
		} catch(NoSuchElementException no) {
			existUser = null;
		}
		
		if(existUser == null) {
			String[] name = oauthUser.getName().split(" ");
			String firstName = name[0];
			String lastName = name[1];
			
			User newUser = new User();
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setUsername(oauthUser.getEmail());
			newUser.setEnabled(true);
			newUser.setProvider(Provider.GOOGLE);
			
			repo.save(newUser);
			
			System.out.println("Created new user: " + oauthUser.getEmail());
		}
	}

    public List<SongEntity> getFavouritedSongs(String userName) {
		User user = repo.findByUsername(userName);
		List<SongEntity> returnList = new ArrayList<SongEntity>();
		if(user.getLikedSongs() != null) {
			user.getLikedSongs().forEach(songId -> {
				try {
					System.out.println(songId);
					SongEntity song = songRepository.findBySpotifyId(songId).orElseThrow(() -> new Exception("wrong song id"));
					returnList.add(song);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			});
		} else {
			new ArrayList<>();
		}
		//reverse list, last liked first showed
		List<SongEntity> reversedList = new ArrayList<>();
		for(int j = returnList.size()- 1 ; j >= 0; j--) {
			reversedList.add(returnList.get(j));
			System.out.println(returnList.get(j).getSpotifyId());
		}

		return reversedList;

    }

	public void updateUserLikeSongs(List<String> songIds, String email) {
		List<String> filteredList =songIds.stream().filter(song -> song.length() ==22).collect(Collectors.toList());
		System.out.println(filteredList.size());
		User user = repo.findByUsername(email);
		if(user.getLikedSongs() == null) {
			user.setLikedSongs(filteredList);
		} else {
			List<String> concatList = new ArrayList<>();
			concatList.addAll(user.getLikedSongs());
			concatList.addAll(filteredList);
			user.setLikedSongs(concatList);
		}
		user.setLikedSongs(user.getLikedSongs());
		repo.save(user);
	}
}
