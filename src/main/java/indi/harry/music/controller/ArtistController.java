package indi.harry.music.controller;

import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Artist;
import indi.harry.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 艺人信息管理Controller
 * Created by Avicii4 at 2022/1/8.
 */
@RestController
@RequestMapping("/artist/")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    /**
     * 艺人信息查询
     *
     * @param artist 查询参数
     * @return 查询结果
     */
    @GetMapping("list")
    public ServerResponseResult<List<Artist>> list(@RequestBody Artist artist) {
        return artistService.list(artist);
    }

    /**
     * 艺人信息添加
     *
     * @param artist 新增信息
     * @return 添加成功/失败结果
     */
    @PostMapping("add")
    public ServerResponseResult<Artist> add(@RequestBody Artist artist) {
        return artistService.add(artist);
    }

    /**
     * 艺人信息删除
     *
     * @param artist 删除入参
     * @return 删除结果
     */
    @PostMapping("delete")
    public ServerResponseResult<Boolean> delete(@RequestBody Artist artist) {
        return artistService.delete(artist);
    }

    /**
     * 艺人信息修改
     *
     * @param artist 修改内容
     * @return 修改结果
     */
    @PostMapping("modify")
    public ServerResponseResult<Artist> modify(@RequestBody Artist artist) {
        return artistService.modify(artist);
    }

}
