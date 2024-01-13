package project.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.entities.SongEntity;
import project.forms.CustomOAuth2User;
import project.services.SongSocialDataService;
import project.services.SongsService;
import project.services.UserService;

@Controller
@RequiredArgsConstructor
public class MainController {

	@Autowired
	private final SongsService songsService;

	@Autowired
	private final UserService userService;

	@Autowired
	private final SongSocialDataService songSocialDataService;

	//fetching list of songs based on mood
	@GetMapping(value= "/dashboard")
	public String getSocialSongData(@RequestParam("mood") String mood, RedirectAttributes redirectAttributes, Model model) throws IOException {
		List<SongEntity> songList = songsService.fetchSongsForGivenUserMoodData(mood);
		redirectAttributes.addFlashAttribute("suggestedList", songList);
		//todo: what to send on frontend, mapping and add to model
		return "redirect:dashboard";
	}

	@GetMapping("/dashboard")
	public String getDashboard(Model model, Principal principal) throws Exception {
		if(principal != null) {
			CustomOAuth2User auth2User = (CustomOAuth2User) ((Authentication) principal).getPrincipal();
			List<SongEntity> favouritedSongs = userService.getFavouritedSongs(auth2User.getEmail());
			//todo: mapping to DTO object
			model.addAttribute("likedSongs", favouritedSongs);
			model.addAttribute("name", auth2User.getName());
			model.addAttribute("gmail", auth2User.getEmail());
			return "dashboardPage";
		} else {
			return "redirect:login";
		}
	}

	@GetMapping("/dashboard/social-data")
	public String getDashBoardWithSocialData(Model model, Principal principal) {
		return "dashboardPage";
	}

//	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
//	public String welcomePage(Model model) {
//		model.addAttribute("title", "Dobro došli");
//		return "welcomePage";
//	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {
		CustomOAuth2User auth2User = (CustomOAuth2User) ((Authentication) principal).getPrincipal();
		
		model.addAttribute("email", auth2User.getEmail());
		model.addAttribute("name", auth2User.getName());
		
		return "profilPage";
	}

}
