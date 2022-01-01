package porebska.filmsmatcher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movie_preference")
public class MoviePreference {

    @Id
    @SequenceGenerator(
            name = "preference_sequence",
            sequenceName = "preference_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "preference_sequence"
    )
    private Long preferenceId;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    private User user;

    @OneToOne
    @JoinColumn(
            name = "movie_id",
            referencedColumnName = "movie_id"
    )
    private Movie movie;

    @Column(name = "status")
    private Status status;
}
