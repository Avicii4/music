package indi.harry.music.service.impl;

import indi.harry.music.common.ResponseCode;
import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Artist;
import indi.harry.music.entity.DTO.AlbumInfoDTO;
import indi.harry.music.entity.DTO.AlbumQueryDTO;
import indi.harry.music.entity.DTO.ArtistInfoDTO;
import indi.harry.music.mapper.AlbumMapper;
import indi.harry.music.mapper.ArtistMapper;
import indi.harry.music.service.ArtistService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 艺人信息管理服务
 * Created by Avicii4 at 2022/1/9.
 */
@Service("artistService")
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public ServerResponseResult<List<Artist>> list(ArtistInfoDTO artistInfoDTO) {
        List<Artist> queryResult = artistMapper.query(artistInfoDTO);
        if (CollectionUtils.isEmpty(queryResult)) {
            return ServerResponseResult.responseSuccessMessage(ResponseCode.NO_QUERY_RESULT);
        } else {
            return ServerResponseResult.responseSuccess(ResponseCode.QUERY_SUCCESS, queryResult);
        }
    }

    @Override
    public ServerResponseResult<Artist> add(Artist artist) {
        if (StringUtils.isBlank(artist.getName())) {
            return ServerResponseResult.responseErrorMessage(ResponseCode.ARTIST_NAME_BLANK);
        }
        Artist checkResult = artistMapper.checkByName(artist.getName());
        if (checkResult != null) {
            return ServerResponseResult.responseErrorMessage(ResponseCode.ARTIST_NAME_DUPLICATE);
        }
        artist.setId(null);

        int insertResult = artistMapper.insertSelective(artist);
        if (insertResult == 1) {
            return ServerResponseResult.responseSuccess(ResponseCode.ADD_ARTIST_SUCCESS, artist);
        }
        return ServerResponseResult.responseErrorMessage(ResponseCode.ADD_ARTIST_FAIL);
    }

    @Override
    public ServerResponseResult<Boolean> delete(Artist artist) {
        Artist checkArtist = artistMapper.selectByPrimaryKey(artist.getId());
        if (checkArtist == null) {
            return ServerResponseResult.responseErrorMessage(ResponseCode.ARTIST_UNKNOWN);
        }
        AlbumQueryDTO dto = new AlbumQueryDTO();
        dto.setArtistName(checkArtist.getName());
        List<AlbumInfoDTO> checkAlbums = albumMapper.query(dto);
        if (!CollectionUtils.isEmpty(checkAlbums)) {
            return ServerResponseResult.responseErrorMessage(ResponseCode.ARTIST_CANNOT_DELETE);
        }
        artistMapper.deleteByPrimaryKey(artist.getId());
        return ServerResponseResult.responseSuccess(ResponseCode.ARTIST_DELETE_SUCCESS, true);
    }

    @Override
    public ServerResponseResult<Artist> modify(Artist artist) {
        Artist checkArtist = artistMapper.selectByPrimaryKey(artist.getId());
        if (checkArtist == null) {
            return ServerResponseResult.responseErrorMessage(ResponseCode.ARTIST_UNKNOWN);
        }
        Artist modifiedArtist = new Artist();
        modifiedArtist.setId(checkArtist.getId());
        modifiedArtist.setName(artist.getName());
        modifiedArtist.setRegion(artist.getRegion());
        modifiedArtist.setBirthday(artist.getBirthday());
        int updateResult = artistMapper.updateByPrimaryKeySelective(artist);
        if (updateResult == 1) {
            return ServerResponseResult.responseSuccess(ResponseCode.ARTIST_MODIFY_SUCCESS, modifiedArtist);
        }
        return ServerResponseResult.responseErrorMessage(ResponseCode.ARTIST_MODIFY_FAIL);
    }

}
