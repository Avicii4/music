package indi.harry.music.controller;

import indi.harry.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 艺人信息管理Controller
 * Created by Avicii4 at 2022/1/8.
 */
@RestController
@RequestMapping("/artist/")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

}
