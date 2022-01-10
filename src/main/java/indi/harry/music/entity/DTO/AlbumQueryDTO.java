package indi.harry.music.entity.DTO;

/**
 * 专辑信息查询入参
 * Created by Avicii4 at 2022/1/10.
 */
public class AlbumQueryDTO extends AlbumInfoDTO {

    private Integer startYear;

    private Integer endYear;

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }
}
