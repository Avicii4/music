package indi.harry.music.service;

import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Album;
import indi.harry.music.entity.DTO.AlbumInfoDTO;

import java.util.List;

/**
 * 音乐专辑服务类
 */
public interface AlbumService {

    // 专辑信息条件搜索展示
    ServerResponseResult<List<AlbumInfoDTO>> list(AlbumInfoDTO albumInfoDTO);

    // 专辑信息添加
    ServerResponseResult<Album> add(AlbumInfoDTO albumInfoDTO);

    // 专辑删除
    ServerResponseResult<Boolean> delete(AlbumInfoDTO albumInfoDTO);

    // 专辑信息修改
    ServerResponseResult<Album> modify(AlbumInfoDTO albumInfoDTO);
}
