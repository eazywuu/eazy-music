package xyz.eazywu.music.object.entity;

import lombok.Data;
import xyz.eazywu.music.object.enums.ArtistStatusType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Artist extends TraceableEntity {

    private String name;

    private String remark;

    @OneToOne
    private File photo;

    @ManyToMany
    @JoinTable(name = "artist_music",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
    private List<Music> musicList;

    private ArtistStatusType status;
}
