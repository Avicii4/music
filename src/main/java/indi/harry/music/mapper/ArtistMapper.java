package indi.harry.music.mapper;

import indi.harry.music.entity.Artist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Artist record);

    // 添加艺人信息
    int insertSelective(Artist record);

    Artist selectByPrimaryKey(Integer id);

    // 根据姓名查询艺人
    Artist checkByName(@Param("name") String name);

    // 艺人多条件模糊查询
    List<Artist> query(@Param("artist") Artist artist);

    int updateByPrimaryKeySelective(Artist record);

    int updateByPrimaryKey(Artist record);
}