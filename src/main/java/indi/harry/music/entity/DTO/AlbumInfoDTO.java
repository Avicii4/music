package indi.harry.music.entity.DTO;

/**
 * 音乐专辑信息展示
 * Created by Avicii4 at 2022/1/7.
 */
public class AlbumInfoDTO {

    // 专辑ID
    private Integer id;

    // 专辑名称
    private String name;

    // 艺人名称
    private String artistName;

    // 流派
    private String genre;

    // 年代
    private Integer year;

    // 发行商
    private String publisher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
