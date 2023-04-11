package xyz.eazywu.music.object.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.eazywu.music.object.enums.PlaylistStatusType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist extends BaseEntity{
    private String name;

    private String description;

    @OneToOne
    private File cover;

    @Enumerated(EnumType.STRING)
    private PlaylistStatusType status;

    @OneToOne
    private User creator;

    @ManyToMany
    @JoinTable(name = "playlist_music",
            joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
    private List<Music> musicList;
}
