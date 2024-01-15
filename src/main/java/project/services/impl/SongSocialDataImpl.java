package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.dto.SongSocialRespDto;
import project.entities.SongSocialDataEntity;
import project.mapper.SongSocialMapper;
import project.repositories.SongSocialDataRepository;
import project.services.SongSocialDataService;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


@Service
@RequiredArgsConstructor
public class SongSocialDataImpl implements SongSocialDataService {

    @Autowired
    private SongSocialMapper songSocialMapper;

    @Autowired
    private SongSocialDataRepository songSocialDataRepository;


    @Override
    public List<SongSocialRespDto> getTop50BasedOnPlatform(String platform) {
        Page<SongSocialDataEntity> playlist = songSocialDataRepository.findAll(PageRequest.of(0, 50, Sort.by(Sort.Order.desc("in_"+ platform + "_playlists"))));
        List<SongSocialRespDto> returnList = playlist.getContent().stream()
                .map(songSocial -> songSocialMapper.toSongSocialRespDto(songSocial)).collect(Collectors.toList());
        return returnList;

    }

    @Override
    public Map<String, List<SongSocialRespDto>> getSongSocialData() {

        Map<String, List<SongSocialDataEntity>> map = songSocialDataRepository.findAll().stream()
                .collect(groupingBy(SongSocialDataEntity::getArtists));

        Map<String, List<SongSocialDataEntity>> filteredMap = map.entrySet().stream().filter(el ->
            el.getKey().split(", ").length == 1
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));

        Map<String, List<SongSocialRespDto>> returnMap = new LinkedHashMap<>();

        filteredMap.forEach((key, value) -> returnMap.put(key, value.stream().map(song -> songSocialMapper.toSongSocialRespDto(song)).collect(Collectors.toList())));

        return returnMap;
    }
}
