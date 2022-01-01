package porebska.filmsmatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "title")
    private String title;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "vote")
    private Double vote;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "movie_genre_map",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "genre_id",
                    referencedColumnName = "genre_id"
            )
    )
    private List<Genre> genres;

    public void addGenre(Genre genre) {
        if (genres == null) genres = new ArrayList<>();
        genres.add(genre);
    }
}
