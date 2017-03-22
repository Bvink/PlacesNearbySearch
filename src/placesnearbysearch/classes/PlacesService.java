package placesnearbysearch.classes;

import placesnearbysearch.constants.Constants;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlacesService {

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_NEARBY_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json";

    private String businessType;
    private int radius;

    public PlacesService(String businessType, int radius) {
        this.businessType = businessType;
        this.radius = radius;
    }

    public StringBuilder nearbySearch(double latitude, double longitude) {

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            String s = PLACES_API_BASE + TYPE_NEARBY_SEARCH + OUT_JSON
                    + "?location=" + latitude + "," + longitude
                    + "&radius=" + radius
                    + "&type=" + businessType
                    + "&key=" + Constants.GOOGLE_PLACES_API_KEY;

            URL url = new URL(s);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResults;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return jsonResults;
    }
}