package xyz.eazywu.music.object.entity;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import xyz.eazywu.music.object.enums.ArtistStatusType;

        import javax.persistence.*;
        import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private ArtistStatusType status;
}
