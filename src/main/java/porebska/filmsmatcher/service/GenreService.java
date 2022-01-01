package porebska.filmsmatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import porebska.filmsmatcher.model.Genre;
import porebska.filmsmatcher.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre addGenre(Genre genre) {
        if (genreRepository.existsById(genre.getGenreId())) {
            return null;
        }
        return genreRepository.save(genre);
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
