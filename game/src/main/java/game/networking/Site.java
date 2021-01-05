package game.networking;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Site {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static final String URL = "http://localhost:63342/web-api/src/api/signMatch.php";
    static OkHttpClient client = new OkHttpClient();
    private static boolean loggedIn;
    private static String userName = "";
    private static String enemyUsername;

    public static String getUserName() {
        return userName;
    }

    public static Boolean isLoggedIn() {
        return loggedIn;
    }

    public static String getEnemyUsername() {
        return enemyUsername;
    }

    public static void setEnemyUsername(String enemyUsername) {
        Site.enemyUsername = enemyUsername;
    }

    public static void sendResult(String json) {
        // Send results to Website
        System.out.println(json);
    }

    public static void login(String email, String password) {
        try {
            //send login request
            URL url = new URL("http://localhost:63343/web-api/src/api/login.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\n" +
                    "    \"email\": \"" + email + "\",\n" +
                    "    \"password\": \"" + password + "\"\n" +
                    "}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            BufferedReader br = null;

            //if logged in get profile info
            if (con.getResponseCode() == 200) {
                //set the sessionId from the response
                String cookie = con.getHeaderField("Set-Cookie");
                cookie = cookie.split(";")[0].split("=")[1];

                //get profile with the sessionID
                url = new URL("http://localhost:63343/web-api/src/api/profile.php");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Cookie", "PHPSESSID=" + cookie);
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);

                //if valid token
                if (con.getResponseCode() == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String responseString = br.readLine();
                    JSONObject obj = new JSONObject(responseString);

                    userName = obj.getString("username");
                } else {
                    return;
                }
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String responseString = br.readLine();
                JSONObject obj = new JSONObject(responseString);
                return;
            }
        } catch (Exception ex) {
            return;
        }
    }

    public static void setLoggedIn(boolean b) {
        loggedIn = b;
    }

    public static void setUsername(String userText) {
        Site.userName = userText;
    }
}