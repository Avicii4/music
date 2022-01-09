package indi.harry.music.controller;

import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Album;
import indi.harry.music.entity.DTO.AlbumInfoDTO;
import indi.harry.music.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专辑信息管理Controller
 * Created by Avicii4 at 2022/1/8.
 */
@RestController
@RequestMapping("/album/")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /**
     * 多条件模糊查询专辑信息
     *
     * @param albumInfoDTO 查询入参
     * @return 查询结果
     */
    @GetMapping("list")
    public ServerResponseResult<List<AlbumInfoDTO>> list(@RequestBody AlbumInfoDTO albumInfoDTO) {
        return albumService.list(albumInfoDTO);
    }

    /**
     * 专辑信息添加
     *
     * @param albumInfoDTO 新专辑信息
     * @return 添加成功/失败结果
     */
    @PostMapping("add")
    public ServerResponseResult<Album> add(@RequestBody AlbumInfoDTO albumInfoDTO) {
        return albumService.add(albumInfoDTO);
    }

    /**
     * 专辑信息删除
     *
     * @param albumInfoDTO 删除入参
     * @return 删除成功/失败结果
     */
    @PostMapping("delete")
    public ServerResponseResult<Boolean> delete(@RequestBody AlbumInfoDTO albumInfoDTO) {
        return albumService.delete(albumInfoDTO);
    }

    /**
     * 专辑信息修改
     *
     * @param albumInfoDTO 修改入参
     * @return 修改结果
     */
    @PostMapping("modify")
    public ServerResponseResult<Album> modify(@RequestBody AlbumInfoDTO albumInfoDTO) {
        return albumService.modify(albumInfoDTO);
    }

}
