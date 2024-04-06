package at.ac.fhcampuswien.fhmdb.utils;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class MovieAPI {
    public static final String API_URL = "https://prog2.fh-campuswien.ac.at";

    public static String getAllMovies(String api_url) {
        Request request = new Request.Builder()
                .url(api_url + "/movies")
                .header("User-Agent", "http.agent")
                .build();

        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);
        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(getAllMovies(API_URL));    }


}