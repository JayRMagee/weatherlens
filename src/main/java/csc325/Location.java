package csc325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

/**
 *
 * @author Jonathan Crain & Jayson Magee
 */
public class Location {

    double latitude;
    double longitude;
    String gridID;
    int gridX;
    int gridY;
    String locationSearchString;

    public Location(String locationSearchString) {
        this.locationSearchString = locationSearchString;
        geocode();
        fetchGridPoints();
    }

    /**
     * Retrieves the first available latitude and longitude for the
     * locationSearchString, setting this location's coordinates.
     */
    private void geocode() {
        String finalUrl;
        URLConnection connection;
        try {
            String apiKey = "6f8e9ba51ab74f6bb2614b10d88b2671";
            String url = "https://api.opencagedata.com/geocode/v1/json?q="
                    + URLEncoder.encode(locationSearchString, "UTF-8") + "&key=" + apiKey;
            connection = new URL(url).openConnection();
            InputStream response = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray results = jsonObject.getJSONArray("results");
            //Could loop through but right now we don't know what result to pick - so just take the first one (index 0).
            JSONObject result = results.getJSONObject(0);
            JSONObject geometry = result.getJSONObject("geometry");
            latitude = geometry.getDouble("lat");
            longitude = geometry.getDouble("lng");
        } catch (IOException | JSONException ex) {
            System.out.println("error");
        }
    }

    private void fetchGridPoints() {
        try {
            // Construct the API URL using the latitude and longitude
            URL url = new URL("https://api.weather.gov/points/" + latitude + "," + longitude);
            // Make a request to the NWS API
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            // Check if the request was successful
            if (con.getResponseCode() == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                
                String[] splitResponse = response.toString().split("\"gridId\":");
                for (int i = 1; i < splitResponse.length; i++) {
                    gridID = splitResponse[i].replaceAll("\"", "")
                        .split("[,\"]")[0]
                        .trim();
                    
                    String[] gridx = response.toString().split("\"gridX\":");
                    gridX = Integer.parseInt(gridx[i].split(",")[0].trim());
                    
                    String[] gridy = response.toString().split("\"gridY\":");
                    gridY = Integer.parseInt(gridy[i].split(",")[0].trim());
                }
            } else {
                System.out.println("Error: " + con.getResponseCode());
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex);;
        }
    }
}
