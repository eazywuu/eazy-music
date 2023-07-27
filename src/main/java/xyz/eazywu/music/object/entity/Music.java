package xyz.eazywu.music.object.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.eazywu.music.object.enums.MusicStatusType;

import javax.persistence.*;
import java.util.List;

/**
 * 音乐实体类
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music extends BaseEntity {
    /**
     * 音乐名
     */
    private String name;
    /**
     * 音乐简介
     */
    private String description;
    /**
     * 音乐上架状态
     */
    @Enumerated(EnumType.STRING)
    private MusicStatusType status;

    @OneToOne
    private File file;

    @ManyToMany
    @JoinTable(name = "music_artist",
            joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"))
    private List<Artist> artistList;
}
