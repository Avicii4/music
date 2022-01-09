package indi.harry.music.service;


import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.Artist;

import java.util.List;

public interface ArtistService {

    //
    ServerResponseResult<List<Artist>> list(Artist artist);

    ServerResponseResult<Artist> add(Artist artist);
}
