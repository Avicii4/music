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
            return ServerResponseResult.responseSuccess("查询成功", resultDTOList);
        }
    }

    @Override
    public ServerResponseResult<Album> add(AlbumInfoDTO albumInfoDTO) {
        Artist artist = artistMapper.checkByName(albumInfoDTO.getArtistName());
        ServerResponseResult result = checkParam(albumInfoDTO, artist);
        if (result.isSuccessful()) {
            Album newAlbum = new Album();
            newAlbum.setName(albumInfoDTO.getName());
            newAlbum.setArtistId(artist.getId());
            newAlbum.setGenre(albumInfoDTO.getGenre());
            newAlbum.setYear(albumInfoDTO.getYear());
            newAlbum.setPublisher(albumInfoDTO.getPublisher());
            int insertResult = albumMapper.insertSelective(newAlbum);
            if (insertResult == 1) {
                return ServerResponseResult.responseSuccess("专辑信息添加成功", newAlbum);
            }
        }
        return ServerResponseResult.responseErrorMessage(result.getMsg());
    }

    @Override
    public ServerResponseResult<Boolean> delete(AlbumInfoDTO albumInfoDTO) {
        List<AlbumInfoDTO> resultDTOList = albumMapper.query(albumInfoDTO);
        if (resultDTOList.size() == 1) { // 仅当查询得到一个结果时才进行删除
            albumMapper.deleteByPrimaryKey(resultDTOList.get(0).getId());
            return ServerResponseResult.responseSuccess("专辑信息删除成功", true);
        } else {
            return ServerResponseResult.responseErrorMessage("专辑信息删除失败");
        }
    }

    @Override
    public ServerResponseResult<Album> modify(AlbumInfoDTO albumInfoDTO) {
        Artist artist = artistMapper.checkByName(albumInfoDTO.getArtistName());
        ServerResponseResult result = checkParam(albumInfoDTO, artist);
        if (result.isSuccessful()) {
            Album modifiedAlbum = new Album();
            modifiedAlbum.setId(albumInfoDTO.getId());
            modifiedAlbum.setName(albumInfoDTO.getName());
            modifiedAlbum.setArtistId(artist.getId());
            modifiedAlbum.setGenre(albumInfoDTO.getGenre());
            modifiedAlbum.setYear(albumInfoDTO.getYear());
            modifiedAlbum.setPublisher(albumInfoDTO.getPublisher());
            int updateResult = albumMapper.updateByPrimaryKeySelective(modifiedAlbum);
            if (updateResult == 1) {
                return ServerResponseResult.responseSuccess("专辑信息修改成功", modifiedAlbum);
            }
            return ServerResponseResult.responseErrorMessage("修改时发生未知错误");
        }
        return ServerResponseResult.responseErrorMessage(result.getMsg());
    }

    // 抽离部分逻辑
    private ServerResponseResult<AlbumInfoDTO> checkParam(AlbumInfoDTO albumInfoDTO, Artist artist) {
        if (StringUtils.isBlank(albumInfoDTO.getName())) {
            return ServerResponseResult.responseErrorMessage("专辑名称不能为空");
        } else if (StringUtils.isBlank(albumInfoDTO.getArtistName())) {
            return ServerResponseResult.responseErrorMessage("艺人名称不能为空");
        }
        if (artist == null) {
            return ServerResponseResult.responseErrorMessage("关联艺人不存在，请重新输入");
        }
        return ServerResponseResult.responseSuccess();
    }

}
