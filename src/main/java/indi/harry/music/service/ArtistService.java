package indi.harry.music.service;


import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Artist;
import indi.harry.music.entity.DTO.ArtistInfoDTO;

import java.util.List;

public interface ArtistService {

    // 查询艺人信息
    ServerResponseResult<List<Artist>> list(ArtistInfoDTO artistInfoDTO);

    // 添加艺人
    ServerResponseResult<Artist> add(Artist artist);

    // 删除艺人
    ServerResponseResult<Boolean> delete(Artist artist);

    // 修改艺人信息
    ServerResponseResult<Artist> modify(Artist artist);
}
