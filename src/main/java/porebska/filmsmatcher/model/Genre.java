package porebska.filmsmatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genres")
public class Genre {

    @Id
    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "name")
    private String name;

}
