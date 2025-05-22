import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MaximoAPIClient {
    private static final String MAXIMO_URL = "https://bportaluri.com/wp-content/MaximoJavaDocs76/";

    public static void main(String[] args) {
        try {
            URL url = new URL(MAXIMO_URL + "maxrest/rest/mbo/asset");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + encodeCredentials("username", "password"));

            if (conn.getResponseCode() != 200) {
                System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String encodeCredentials(String username, String password) {
        return java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
