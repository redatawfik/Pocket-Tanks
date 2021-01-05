package game.networking;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import org.json.*;

public class Site {
    private  static Boolean loggedIn = false;
    private static String userName = "";

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Site.userName = userName;
    }

    public static void setLoggedIn(Boolean loggedIn) {
        Site.loggedIn = loggedIn;
    }

    public static Boolean getLoggedIn() {
        return loggedIn;
    }

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

    public static JSONObject login(String email, String password){
        try{
            //send login request
            URL url = new URL("http://localhost:63342/web-api/src/api/login.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\n" +
                    "    \"email\": \""+email+"\",\n" +
                    "    \"password\": \""+password+"\"\n" +
                    "}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            } catch (Exception ex) {
                return null;
            }
            BufferedReader br = null;

            //if logged in get profile info
            if (con.getResponseCode()  == 200) {
                //set the sessionId from the response
                String cookie = con.getHeaderField("Set-Cookie");
                cookie = cookie.split(";")[0].split("=")[1];

                //get profile with the sessionID
                url = new URL("http://localhost:63342/web-api/src/api/profile.php");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Cookie", "PHPSESSID="+cookie);
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);

                //if valid token
                if (con.getResponseCode()  == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String responseString =   br.readLine();
                    JSONObject obj = new JSONObject(responseString);
                    return  obj;
                } else {
                    return null;
                }
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String responseString =   br.readLine();
                JSONObject obj = new JSONObject(responseString);
                return  obj;
            }
        }
        catch (Exception ex){
            return null;
        }
    }
}
