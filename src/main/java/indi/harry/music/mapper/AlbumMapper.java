package indi.harry.music.mapper;

import indi.harry.music.entity.Album;
import indi.harry.music.entity.DTO.AlbumInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlbumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Album record);

    // 添加专辑信息
    int insertSelective(Album record);

    Album selectByPrimaryKey(Integer id);

    // 多条件模糊查询专辑信息
    List<AlbumInfoDTO> query(@Param("dto") AlbumInfoDTO albumInfoDTO);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}