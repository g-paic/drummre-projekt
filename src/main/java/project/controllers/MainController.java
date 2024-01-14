package project.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import project.dto.SongRespDto;
import project.entities.SongEntity;
import project.entities.SongSocialDataEntity;
import project.forms.CustomOAuth2User;
import project.mapper.SongMapper;
import project.services.SongSocialDataService;
import project.services.SongsService;
import project.services.UserService;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final SongMapper songMapper = new SongMapper();

	@Autowired
	private final SongsService songsService;

	@Autowired
	private final UserService userService;

	@Autowired
	private final SongSocialDataService songSocialDataService;

	//getting user_data
	@GetMapping("/dashboard")
	public String getDashboard(Model model, Principal principal) throws Exception {
		if(principal != null) {
			CustomOAuth2User auth2User = (CustomOAuth2User) ((Authentication) principal).getPrincipal();
			List<SongEntity> likedSongs = userService.getFavouritedSongs(auth2User.getEmail());
			System.out.println(auth2User.getName());
			System.out.println(likedSongs.size());
			List<SongRespDto> likedSongsDto = likedSongs.stream().map(songMapper::toSongResp).collect(Collectors.toList());
			model.addAttribute("likedSongs", likedSongsDto);
			model.addAttribute("name", auth2User.getName());
			model.addAttribute("gmail", auth2User.getEmail());
			model.addAttribute("pictureLink", auth2User.getAttributes().get("picture"));
			return "dashboardPage";
		} else {
			return "redirect:login";
		}
	}

	@GetMapping("/dashboard/survey-results")
	public String getSongsBasedOnSurveyReuslts(@RequestParam("mood") String mood, Model model) throws IOException {
		List<SongEntity> songList = songsService.fetchSongsForGivenUserMoodData(mood);
		List<SongRespDto> songListDto = songList.stream()
				.map(songMapper::toSongResp).collect(Collectors.toList());
		songListDto.forEach(song -> {
			System.out.println("---------------------------");
			System.out.println(song.getLyrics());
		});
		//todo: send 10 songs
		model.addAttribute("suggestedSongs",songListDto);
		System.out.println(songList.size());
		return "surveyResultsPage";
	}

	@GetMapping("/dashboard/survey")
	public String getMoodSurvey(Model model, Principal principal) {
		return "surveyPage";
	}

	@GetMapping("/dashboard/social-data")
	public String getDashBoardWithSocialData(Model model, Principal principal) {
		List<SongSocialDataEntity> top50ByStreams = songSocialDataService.getSongSocialData();
		top50ByStreams.forEach(song -> System.out.println(song.getName() + " streamed " + song.getStreams()));
		return "dashboardPage";
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

}
