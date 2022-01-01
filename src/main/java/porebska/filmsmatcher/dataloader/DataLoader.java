package porebska.filmsmatcher.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class DataLoader {

    protected static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    protected final String USER_AGENT = "Mozilla/5.0";

    protected String loadData(String getUrl) throws IOException {
        URL url = new URL(getUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
        StringBuilder response = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            logger.info("Successfully loaded data");
        } else {
            logger.warn("GET request not worked");
        }
        return response.toString();
    }

    abstract void parseResponse(String response);
}
