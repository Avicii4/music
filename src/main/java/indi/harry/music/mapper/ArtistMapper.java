package indi.harry.music.mapper;

import indi.harry.music.entity.Artist;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Artist record);

    int insertSelective(Artist record);

    Artist selectByPrimaryKey(Integer id);

    // 根据姓名查询艺人
    Artist checkByName(String name);

    int updateByPrimaryKeySelective(Artist record);

    int updateByPrimaryKey(Artist record);
}