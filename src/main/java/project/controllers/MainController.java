package project.controllers;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.deezer.Data;
import project.deezer.DataSet;
import project.entities.DeezerEntity;
import project.entities.TrackEntity;
import project.forms.CustomOAuth2User;
import project.lastfm.Track;
import project.lastfm.TracksResponse;
import project.repositories.DeezerRepository;
import project.repositories.TrackRepository;
import project.services.DeezerService;
import project.services.LastFmService;

@Controller
public class MainController {
	@Autowired
	private TrackRepository trackRepo;
	
	@Autowired
	private DeezerRepository deezerRepo;
	
	@Autowired
	private LastFmService lastFmService;
	
	@Autowired
	private DeezerService deezerService;

	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Dobro došli");
		return "welcomePage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {
		CustomOAuth2User auth2User = (CustomOAuth2User) ((Authentication) principal).getPrincipal();
		
		model.addAttribute("email", auth2User.getEmail());
		model.addAttribute("name", auth2User.getName());
		
		return "profilPage";
	}
	
	@RequestMapping(value = "/lastfm/{tag}", method = RequestMethod.GET)
    public String getTopTracksForTag(Model model, @PathVariable("tag") String tag) {
    	TracksResponse tracksResponse = lastFmService.getTracksFromUrl(tag);
    	Track[] tracks = tracksResponse.getTracks().getTrack();
    	Set<TrackEntity> set = new LinkedHashSet<>();
    	
    	for(Track track: tracks) {
    		TrackEntity existTrackEntity = null;
    		
    		try {
    			existTrackEntity = trackRepo.getTrackByName(track.getName()).get();
    		} catch(NoSuchElementException no) {
    			existTrackEntity = null;
    		}
    		
    		if(existTrackEntity == null) {
	    		TrackEntity trackEntity = new TrackEntity();
	    		trackEntity.setName(track.getName());
	    		trackEntity.setDuration(track.getDuration());
	    		trackEntity.setMbid(track.getMbid());
	    		trackEntity.setUrl(track.getUrl());
	    		trackEntity.setArtist(track.getArtist().getName());
	    		trackEntity.setTag(tag);
	    		
	    		trackRepo.save(trackEntity);
	    		set.add(trackEntity);
    		} else {
    			set.add(existTrackEntity);
    		}
    	}
    	
    	model.addAttribute("tag", tag);
    	model.addAttribute("list", set);
    	
    	return "tracks";
    }
	
	@RequestMapping(value = "/deezer/{artist}", method = RequestMethod.GET)
    public String getTheSongsOfTheSinger(Model model, @PathVariable("artist") String artist) {
		DataSet dataSet = deezerService.getDataFromUrl(artist);
		Data[] data = dataSet.getData();
    	Set<DeezerEntity> set = new LinkedHashSet<>();
    	
    	for(Data song: data) {
    		DeezerEntity existDeezerEntity = null;
    		try {
    			existDeezerEntity = deezerRepo.getDeezerByTitle(song.getTitle()).get();
    		} catch(NoSuchElementException no) {
    			existDeezerEntity = null;
    		}
    		
    		if(existDeezerEntity == null) {
    			DeezerEntity deezerEntity = new DeezerEntity();
    			deezerEntity.setTitle(song.getTitle());
    			deezerEntity.setDuration(song.getDuration());
    			deezerEntity.setPreview(song.getPreview());
    			deezerEntity.setMd5_image(song.getMd5_image());
    			deezerEntity.setAlbum(song.getAlbum().getTitle());
    			deezerEntity.setArtist(artist);
	    		
    			deezerRepo.save(deezerEntity);
    			set.add(deezerEntity);
    		} else {
    			set.add(existDeezerEntity);
    		}
    	}
    	
    	model.addAttribute("artist", artist);
    	model.addAttribute("list", set);
    	
    	return "deezer";
    }
}
