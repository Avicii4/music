package indi.harry.music.service.impl;

import indi.harry.music.common.ResponseCode;
import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Album;
import indi.harry.music.entity.Artist;
import indi.harry.music.entity.DTO.AlbumInfoDTO;
import indi.harry.music.entity.DTO.AlbumQueryDTO;
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
    public ServerResponseResult<List<AlbumInfoDTO>> list(AlbumQueryDTO queryDTO) {
        List<AlbumInfoDTO> resultDTOList = albumMapper.query(queryDTO);
        if (CollectionUtils.isEmpty(resultDTOList)) {
            return ServerResponseResult.responseSuccessMessage(ResponseCode.NO_QUERY_RESULT);
        } else {
            return ServerResponseResult.responseSuccess(ResponseCode.QUERY_SUCCESS, resultDTOList);
        }
    }

    @Override
    public ServerResponseResult<Album> add(AlbumInfoDTO albumInfoDTO) {
        Artist artist = artistMapper.checkByName(albumInfoDTO.getArtistName());
        ResponseCode result = checkParam(albumInfoDTO, artist);
        // 校验通过
        if (result.getCode() == ResponseCode.SUCCESS.getCode()) {
            Album newAlbum = new Album();
            newAlbum.setName(albumInfoDTO.getName());
            newAlbum.setArtistId(artist.getId());
            newAlbum.setGenre(albumInfoDTO.getGenre());
            newAlbum.setYear(albumInfoDTO.getYear());
            newAlbum.setPublisher(albumInfoDTO.getPublisher());
            int insertResult = albumMapper.insertSelective(newAlbum);
            if (insertResult == 1) {
                return ServerResponseResult.responseSuccess(ResponseCode.ALBUM_ADD_SUCCESS, newAlbum);
            } else {
                return ServerResponseResult.responseErrorMessage(ResponseCode.ALBUM_ADD_FAIL);
            }
        }
        // 否则返回校验错误的信息
        return ServerResponseResult.responseErrorMessage(result);
    }

    @Override
    public ServerResponseResult<Boolean> delete(AlbumInfoDTO albumInfoDTO) {
        AlbumQueryDTO queryDTO = new AlbumQueryDTO();
        queryDTO.setId(albumInfoDTO.getId());
        List<AlbumInfoDTO> resultDTOList = albumMapper.query(queryDTO);
        if (resultDTOList.size() == 1) { // 仅当查询得到一个结果时才进行删除
            albumMapper.deleteByPrimaryKey(resultDTOList.get(0).getId());
            return ServerResponseResult.responseSuccess(ResponseCode.ALBUM_DELETE_SUCCESS, true);
        } else {
            return ServerResponseResult.responseErrorMessage(ResponseCode.ALBUM_DELETE_FAIL);
        }
    }

    @Override
    public ServerResponseResult<Album> modify(AlbumInfoDTO albumInfoDTO) {
        Artist artist = artistMapper.checkByName(albumInfoDTO.getArtistName());
        ResponseCode result = checkParam(albumInfoDTO, artist);
        if (result.getCode() == ResponseCode.SUCCESS.getCode()) {
            Album modifiedAlbum = new Album();
            modifiedAlbum.setId(albumInfoDTO.getId());
            modifiedAlbum.setName(albumInfoDTO.getName());
            modifiedAlbum.setArtistId(artist.getId());
            modifiedAlbum.setGenre(albumInfoDTO.getGenre());
            modifiedAlbum.setYear(albumInfoDTO.getYear());
            modifiedAlbum.setPublisher(albumInfoDTO.getPublisher());
            int updateResult = albumMapper.updateByPrimaryKeySelective(modifiedAlbum);
            if (updateResult == 1) {
                return ServerResponseResult.responseSuccess(ResponseCode.ALBUM_MODIFY_SUCCESS, modifiedAlbum);
            }
            return ServerResponseResult.responseErrorMessage(ResponseCode.ALBUM_MODIFY_FAIL);
        }
        return ServerResponseResult.responseErrorMessage(result);
    }

    // 抽离部分逻辑
    private ResponseCode checkParam(AlbumInfoDTO albumInfoDTO, Artist artist) {
        if (StringUtils.isBlank(albumInfoDTO.getName())) {
            return ResponseCode.ALBUM_NAME_BLANK;
        } else if (StringUtils.isBlank(albumInfoDTO.getArtistName())) {
            return ResponseCode.ARTIST_NAME_BLANK;
        }
        if (artist == null) {
            return ResponseCode.ARTIST_UNKNOWN;
        }
        return ResponseCode.SUCCESS;
    }

}
