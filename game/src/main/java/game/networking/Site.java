package game.networking;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class Site {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static final String URL = "http://localhost:63342/web-api/src/api/signMatch.php";
    static OkHttpClient client = new OkHttpClient();

    public static void sendResult(String json) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
