package project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.services.SongsService;

@RestController(value = "/detect-song-mood")
@AllArgsConstructor
public class TestOpenAiController {

    private final SongsService songsService;

    @GetMapping(value = "")
    public String getSongMood() {
        //send test lyrics
        String  lyrics = " ";
        return songsService.checkLyricsMoodDetection(lyrics);
    }
}
