package project.services;

import project.dto.SongSocialRespDto;
import project.entities.SongSocialDataEntity;

import java.util.List;
import java.util.Map;

public interface SongSocialDataService {

    public List<SongSocialRespDto> getTop50BasedOnPlatform(String platform);

    public Map<String, List<SongSocialRespDto>> getSongSocialData();
}
