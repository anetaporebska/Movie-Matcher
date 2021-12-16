package porebska.filmsmatcher;

import org.springframework.beans.factory.annotation.Autowired;
import porebska.filmsmatcher.service.MovieService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MoviesExtractor {

    private static final String getUrl = "https://api.themoviedb.org/3/movie/popular?api_key=97c23dc2af1e261a3024c0bb5b4f3060&language=en-US&page=1";
    private static final String USER_AGENT = "Mozilla/5.0";

    @Autowired
    private MovieService movieService;

    public static void getMovies() throws IOException {
        URL url = new URL(getUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response);
        } else {
            System.out.println("GET request not worked");
        }



    }


}
