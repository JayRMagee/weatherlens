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
 *
 * @author jayson
 */
public class Geocode {
    @FXML
    private TextField searchText;
    /**
     *
     * @author jayson
     */
    public String getWeatherPeriods(String id, int x, int y) {
        String finalUrl = "https://api.weather.gov/gridpoints/" + id + "/" + x + "," + y + "/forecast";
        try {
            //public void getWeather() throws IOException {
            // Construct the API URL using the latitude and longitude
            URL url = new URL("https://api.weather.gov/gridpoints/" + id + "/" + x + "," + y + "/forecast");
//            String finalUrl = "https://api.weather.gov/gridpoints/" + id + "/" + x + "," + y + "/forecast";
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

//                String[] temperatures = response.toString().split("\"temperature\":");
//                for (int i = 1; i < temperatures.length; i++) {
//                    int temperature = Integer.parseInt(temperatures[i].split(",")[0].trim());
//                    System.out.println("Temperature " + i + ": " + temperature);
//                }

            } else {
                System.out.println("Error: " + con.getResponseCode());
            }
        } catch (MalformedURLException ex) {
            System.out.println("wrong");
        } catch (IOException ex) {
            System.out.println("wrong");
        }
        return finalUrl;
    }

    public String getWeather(double lat, double longe) throws Exception {
        String s = "";
        //String latitude = "38.9072";
        //String longitude = "-77.0369";

        // Construct the API URL using the latitude and longitude
        URL url = new URL("https://api.weather.gov/points/" + lat + "," + longe);//https://api.weather.gov/gridpoints/OKX/33,37/forecast
//40.0757384, -74.4041622       https://api.weather.gov/points/40.0757384,-74.4041622
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

            /*
            String[] gridID = response.toString().split("\"gridId\":");
            for (int i = 1; i < gridID.length; i++) {
                int temperature = Integer.parseInt(gridID[i].split(",")[0].trim());
                System.out.println("Temperature " + i + ": " + temperature);
            }
             */
            String[] temperatures = response.toString().split("\"gridId\":");
            for (int i = 1; i < temperatures.length; i++) {
                //int temperature = Integer.parseInt(temperatures[i].split(",")[0].trim());
                //String temperature = temperatures[i].split(",")[0].trim();
                String temperature = temperatures[i].replaceAll("\"", "")
                        .split("[,\"]")[0]
                        .trim();
                //System.out.println(temperature);
                //}

                String[] gridx = response.toString().split("\"gridX\":");
                //for (int i = 1; i < gridx.length; i++) {
                int gridX = Integer.parseInt(gridx[i].split(",")[0].trim());
                //System.out.println(gridX);
                //}

                String[] gridy = response.toString().split("\"gridY\":");
                //for (int i = 1; i < gridx.length; i++) {
                int gridY = Integer.parseInt(gridy[i].split(",")[0].trim());
                //System.out.println(gridY);

                s = getWeatherPeriods(temperature, gridX, gridY);
                
            }

            //getShortForecast(temperature, gridX, gridY);
        } else {
            System.out.println("Error: " + con.getResponseCode());
        }
        return s;
    }

    /**
     *
     * @param args
     */
    public String geocoding() {
        String finalUrl = "";
        URLConnection connection = null;
        try {
            //6f8e9ba51ab74f6bb2614b10d88b2671

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
            for (int i = 0; i < 1;/*results.length();*/ i++) {
                JSONObject result = results.getJSONObject(i);
                JSONObject geometry = result.getJSONObject("geometry");
                double lat = geometry.getDouble("lat");
                double lng = geometry.getDouble("lng");
                //System.out.println(lat + " " + lng);
                try {
                    //System.out.println(result.getString("formatted") + " (" + lat + ", " + lng + ")");
                    finalUrl = getWeather(lat, lng);
                } catch (Exception ex) {
                    System.out.println("error getting lat long");
                }

            }
        } catch (MalformedURLException ex) {
            System.out.println("error");
        } catch (IOException ex) {
            System.out.println("erroe");
        } catch (JSONException ex) {
            System.out.println("error");
        }
       return finalUrl;
    }
}
