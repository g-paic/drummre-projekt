package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.entities.SongSocialDataEntity;
import project.repositories.SongSocialDataRepository;
import project.services.SongSocialDataService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class SongSocialDataImpl implements SongSocialDataService {

    @Autowired
    private SongSocialDataRepository songSocialDataRepository;

    @Override
    public List<SongSocialDataEntity> getSongSocialData() {
//        List<SongSocialDataEntity> songs = songSocialDataRepository.findAll();
//        System.out.println(songs.size());
//        List<SongSocialDataEntity> songsToRemove = new ArrayList<>();
//        Set<String> songNameSet = new HashSet<>();
//        songs.forEach(song -> {
//            if(songNameSet.contains(song.getName())) {
//                songsToRemove.add(song);
//            } else {
//
//                songNameSet.add(song.getName());
//            }
//        });
//
//        songsToRemove.forEach(song -> {
//            System.out.println("removed " + song.getName());
//            songSocialDataRepository.deleteById(song.get_id());}
//        );


        Page<SongSocialDataEntity> inSpotifyPLaylists = songSocialDataRepository.findAll(PageRequest.of(0, 50, Sort.by(Sort.Order.desc("in_spotify_playlists"))));
        System.out.println("top 50 spotify");
        inSpotifyPLaylists.getContent().forEach(song -> System.out.println(song.getName()));
        Page<SongSocialDataEntity> inApplePlaylists = songSocialDataRepository.findAll(PageRequest.of(0, 50, Sort.by(Sort.Order.desc("in_apple_playlists"))));
        System.out.println("top 50 apple");
        inApplePlaylists.getContent().forEach(song -> System.out.println(song.getName()));
        Page<SongSocialDataEntity> inDeezerPlaylists = songSocialDataRepository.findAll(PageRequest.of(0, 50, Sort.by(Sort.Order.desc("in_deezer_playlists"))));
        System.out.println("top 50 deezer");
        inDeezerPlaylists.getContent().forEach(song -> System.out.println(song.getName()));
        Page<SongSocialDataEntity> songPage =  songSocialDataRepository.findAll(PageRequest.of(0, 50, Sort.by(Sort.Order.desc("streams"))));

        return songPage.getContent();
    }
}
