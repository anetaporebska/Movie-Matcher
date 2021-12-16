package porebska.filmsmatcher.dataloader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import porebska.filmsmatcher.model.Genre;
import porebska.filmsmatcher.service.GenreService;

import java.io.IOException;

@Component
public class GenreLoader extends DataLoader {

    private final String getUrl = "https://api.themoviedb.org/3/genre/movie/list?api_key=97c23dc2af1e261a3024c0bb5b4f3060&language=en-US";

    @Autowired
    private GenreService genreService;

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
        JSONObject jsonObject = new JSONObject(response);
        JSONArray array = jsonObject.getJSONArray("genres");
        for (int i = 0; i < array.length(); i++) {
            Long id = array.getJSONObject(i).getLong("id");
            String name = array.getJSONObject(i).getString("name");
            Genre genre = new Genre(id, name);
            genreService.addGenre(genre);
        }
    }
}
