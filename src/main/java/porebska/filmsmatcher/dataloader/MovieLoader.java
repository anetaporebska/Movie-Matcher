package porebska.filmsmatcher.dataloader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import porebska.filmsmatcher.model.Genre;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.service.GenreService;
import porebska.filmsmatcher.service.MovieService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieLoader extends DataLoader {
    private final String getUrl = "https://api.themoviedb.org/3/movie/popular?api_key=97c23dc2af1e261a3024c0bb5b4f3060&language=en-US&page=1";

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieService movieService;

    // @PostConstruct
    public void run() {
        try {
            String response = loadData(getUrl);
            parseResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void parseResponse(String response) {
        System.out.println(response);

        JSONObject jsonObject = new JSONObject(response);
        JSONArray array = jsonObject.getJSONArray("results");

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonEntry = array.getJSONObject(i);
            Long id = jsonEntry.getLong("id");
            String title = jsonEntry.getString("title");
            JSONArray genreArray = jsonEntry.getJSONArray("genre_ids");
            String releaseDate = jsonEntry.getString("release_date");
            Double vote = jsonEntry.getDouble("vote_average");

            List<Genre> genreList = new ArrayList<>();

            for (int j = 0; j < genreArray.length(); j++) {
                Long genreId = genreArray.getLong(j);
                Optional<Genre> genre = genreService.getGenreById(genreId);
                genre.ifPresent(genreList::add);
            }

            Movie movie = new Movie(id, title, releaseDate, vote, genreList);
            movieService.addMovie(movie);
        }
    }
}
