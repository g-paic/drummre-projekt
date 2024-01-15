package project.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import project.dto.SongRespDto;
import project.dto.SongSocialRespDto;
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
			List<SongEntity> relatedsongs = songsService.getRelatedSongsForLikedSongs(likedSongs);
//			likedSongs.forEach(el -> System.out.println(el.getEnergy() + " " + el.getMood() +  " " + el.getName()));
//			System.out.println("-------------------");
//			relatedsongs.forEach(el -> System.out.println(el.getEnergy() + " " + el.getMood() + " " + el.getName()));
//			System.out.println(auth2User.getName());
			List<SongRespDto> relatedSongsDto = relatedsongs.stream().map(songMapper::toSongResp).collect(Collectors.toList());
			List<SongRespDto> likedSongsDto = likedSongs.stream().map(songMapper::toSongResp).collect(Collectors.toList());
			model.addAttribute("relatedSongs", relatedSongsDto);
			model.addAttribute("likedSongs", likedSongsDto);
			model.addAttribute("name", auth2User.getName());
			model.addAttribute("gmail", auth2User.getEmail());
			model.addAttribute("pictureLink", auth2User.getAttributes().get("picture"));
			return "dashboardPage";
		} else {
			return "redirect:login";
		}
	}

	@GetMapping("/dashboard/liked-songs")
	public String updateLikedSongs(@RequestParam("id") List<String> songIds, Principal principal) {
		if(songIds != null &&  principal != null) {
			CustomOAuth2User auth2User = (CustomOAuth2User) ((Authentication) principal).getPrincipal();
			userService.updateUserLikeSongs(songIds, auth2User.getEmail());
		}
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard/survey-results")
	public String getSongsBasedOnSurveyReuslts(@RequestParam("mood") List<String> mood, Model model) throws IOException {
//		mood.forEach(el -> System.out.println(el));
		List<SongEntity> songList = songsService.fetchSongsForGivenUserMoodData(mood);
		List<SongRespDto> songListDto = songList.stream()
				.map(songMapper::toSongResp).collect(Collectors.toList());
		songListDto.forEach(song -> {
//			System.out.println("---------------------------");
//			System.out.println(song.getLyrics());
		});
		songList.forEach(el -> System.out.println(el.getMood()));
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
		List<SongSocialRespDto> spotifyList = songSocialDataService.getTop50BasedOnPlatform("spotify");
		List<SongSocialRespDto> appleList = songSocialDataService.getTop50BasedOnPlatform("apple");
		List<SongSocialRespDto> deezerList = songSocialDataService.getTop50BasedOnPlatform("deezer");

		//map that contains artist name as key and list of songs with social data as value
		Map<String, List<SongSocialRespDto>> socialDataMap = songSocialDataService.getSongSocialData();

		model.addAttribute("socialDataMap", socialDataMap);
		model.addAttribute("spotifyList", spotifyList);
		model.addAttribute("appleList", appleList);
		model.addAttribute("deezerList", deezerList);
		return "socialPage";
	}

	//:todo get meta data controller
	public String getMetaData() {

		return "metaDataPage";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}

	@RequestMapping(value = "/dashboard/info", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {
		CustomOAuth2User auth2User = (CustomOAuth2User) ((Authentication) principal).getPrincipal();
		
		return "infoPage";
	}

}
