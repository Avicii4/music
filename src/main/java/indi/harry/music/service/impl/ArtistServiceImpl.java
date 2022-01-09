package indi.harry.music.service.impl;

import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Artist;
import indi.harry.music.entity.DTO.AlbumInfoDTO;
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
    public ServerResponseResult<List<Artist>> list(Artist artist) {
        List<Artist> queryResult = artistMapper.query(artist);
        if (CollectionUtils.isEmpty(queryResult)) {
            return ServerResponseResult.responseSuccessMessage("没有找到符合的结果");
        } else {
            return ServerResponseResult.responseSuccess("查询成功", queryResult);
        }

    }

    @Override
    public ServerResponseResult<Artist> add(Artist artist) {
        if (StringUtils.isBlank(artist.getName())) {
            return ServerResponseResult.responseErrorMessage("艺人姓名不能为空");
        }
        Artist checkResult = artistMapper.checkByName(artist.getName());
        if (checkResult != null) {
            return ServerResponseResult.responseErrorMessage("艺人重名，新增失败");
        }
        // TODO 日期前台传String 后台存Date
        artist.setId(null);
        int insertResult = artistMapper.insertSelective(artist);
        if (insertResult == 1) {
            return ServerResponseResult.responseSuccess("新增艺人信息成功", artist);
        }
        return ServerResponseResult.responseErrorMessage("新增艺人信息失败");
    }

    @Override
    public ServerResponseResult<Boolean> delete(Artist artist) {
        Artist checkArtist = artistMapper.selectByPrimaryKey(artist.getId());
        if (checkArtist == null) {
            return ServerResponseResult.responseErrorMessage("删除的艺人信息不存在");
        }
        AlbumInfoDTO dto = new AlbumInfoDTO();
        dto.setArtistName(checkArtist.getName());
        List<AlbumInfoDTO> checkAlbums = albumMapper.query(dto);
        if (!CollectionUtils.isEmpty(checkAlbums)) {
            return ServerResponseResult.responseErrorMessage("尚存在专辑信息的艺人无法删除");
        }
        artistMapper.deleteByPrimaryKey(artist.getId());
        return ServerResponseResult.responseSuccess("删除艺人信息成功", true);
    }

    @Override
    public ServerResponseResult<Artist> modify(Artist artist) {
        Artist checkArtist = artistMapper.selectByPrimaryKey(artist.getId());
        if (checkArtist == null) {
            return ServerResponseResult.responseErrorMessage("修改的艺人信息不存在");
        }
        Artist modifiedArtist = new Artist();
        modifiedArtist.setId(checkArtist.getId());
        modifiedArtist.setName(artist.getName());
        modifiedArtist.setRegion(artist.getRegion());
        modifiedArtist.setBirthday(artist.getBirthday());
        int updateResult = artistMapper.updateByPrimaryKeySelective(artist);
        if (updateResult == 1) {
            return ServerResponseResult.responseSuccess("艺人信息修改成功", modifiedArtist);
        }
        return ServerResponseResult.responseErrorMessage("艺人信息修改失败");
    }

}
