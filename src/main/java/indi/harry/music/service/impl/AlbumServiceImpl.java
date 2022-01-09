package indi.harry.music.service.impl;

import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Album;
import indi.harry.music.entity.Artist;
import indi.harry.music.entity.DTO.AlbumInfoDTO;
import indi.harry.music.mapper.AlbumMapper;
import indi.harry.music.mapper.ArtistMapper;
import indi.harry.music.service.AlbumService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 专辑信息管理服务
 * Created by Avicii4 at 2022/1/7.
 */
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private ArtistMapper artistMapper;

    @Override
    public ServerResponseResult<List<AlbumInfoDTO>> list(AlbumInfoDTO albumInfoDTO) {
        List<AlbumInfoDTO> resultDTOList = albumMapper.query(albumInfoDTO);
        if (CollectionUtils.isEmpty(resultDTOList)) {
            return ServerResponseResult.responseSuccessMessage("没有找到符合的结果");
        } else {
            return ServerResponseResult.responseSuccess(resultDTOList);
        }
    }

    @Override
    public ServerResponseResult<Album> add(AlbumInfoDTO albumInfoDTO) {
        if (StringUtils.isBlank(albumInfoDTO.getName())) {
            return ServerResponseResult.responseErrorMessage("专辑名称不能为空");
        } else if (StringUtils.isBlank(albumInfoDTO.getArtistName())) {
            return ServerResponseResult.responseErrorMessage("艺人名称不能为空");
        }
        Artist artist = artistMapper.checkByName(albumInfoDTO.getArtistName());
        if (artist == null) {
            return ServerResponseResult.responseErrorMessage("关联艺人不存在，请重新输入");
        }
        Album newAlbum  = new Album();
        newAlbum.setName(albumInfoDTO.getName());
        newAlbum.setArtistId(artist.getId());
        newAlbum.setGenre(albumInfoDTO.getGenre());
        newAlbum.setYear(albumInfoDTO.getYear());
        newAlbum.setPublisher(albumInfoDTO.getPublisher());
        int insertResult = albumMapper.insertSelective(newAlbum);
        if(insertResult ==1){
            return ServerResponseResult.responseSuccess("专辑信息添加成功",newAlbum);
        } else {
            return ServerResponseResult.responseErrorMessage("专辑信息添加失败");
        }
    }


}
