package csc325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

/**
 * The Geocode class facilitates API calls needed to generate geographical data used to populate weather forecasts.
 * @author Jayson Magee
 */
public class Geocode {
    @FXML
    private TextField searchText;
    
    /**
     * Returns weather periods as a String object.
     * @author Jayson Magee
     */
    public String getWeatherPeriods(String id, int x, int y) {
        String finalUrl = "https://api.weather.gov/gridpoints/" + id + "/" + x + "," + y + "/forecast";
        try {
            URL url = new URL("https://api.weather.gov/gridpoints/" + id + "/" + x + "," + y + "/forecast");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            if (con.getResponseCode() == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
            } else {
                System.out.println("Error: " + con.getResponseCode());
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return finalUrl;
    }

    /**
     * Returns weather values using latitude and longitude coordinates.
     * @param latitude
     * @param longitude
     * @return
     * @throws Exception 
     */
    public String getWeather(double latitude, double longitude) throws Exception {
        String string = "";

        URL url = new URL("https://api.weather.gov/points/" + latitude + "," + longitude);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        if (con.getResponseCode() == 200) {
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            String[] temperatures = response.toString().split("\"gridId\":");
            for (int i = 1; i < temperatures.length; i++) {
                String temperature = temperatures[i].replaceAll("\"", "")
                        .split("[,\"]")[0]
                        .trim();
                String[] gridx = response.toString().split("\"gridX\":");
                int gridX = Integer.parseInt(gridx[i].split(",")[0].trim());
                String[] gridy = response.toString().split("\"gridY\":");
                int gridY = Integer.parseInt(gridy[i].split(",")[0].trim());

                string = getWeatherPeriods(temperature, gridX, gridY);
                
            }
        } else {
            System.out.println("Error: " + con.getResponseCode());
        }
        return string;
    }

    /**
     * Handles API calls.
     * @param args
     */
    public String handleGeocoding() {
        String finalUrl = "";
        URLConnection connection = null;
        try {
            String apiKey = "6f8e9ba51ab74f6bb2614b10d88b2671";
            String address = "New York";
            String url = "https://api.opencagedata.com/geocode/v1/json?q="
                    + URLEncoder.encode(address, "UTF-8") + "&key=" + apiKey;
            
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
            for (int i = 0; i < 1; i++) {
                JSONObject result = results.getJSONObject(i);
                JSONObject geometry = result.getJSONObject("geometry");
                double lat = geometry.getDouble("lat");
                double lng = geometry.getDouble("lng");
                try {
                    finalUrl = getWeather(lat, lng);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
       return finalUrl;
    }
}
