package main.weatherapp;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class WeatherApi {
   private static final String key = "65b7c06bcbe741297d9fac0db4d65836";

   public static JSONObject getJson (String city){
      return new JSONObject(getWeather(city));
   }

   private static String getWeather (String city){
      StringBuffer content = new StringBuffer();
      try {
         URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid=" + key);
         URLConnection urlConnection = url.openConnection();
         BufferedReader bufferedReader = new BufferedReader(
                 new InputStreamReader(urlConnection.getInputStream()));

         String readLine;
         while (( readLine = bufferedReader.readLine()) != null) {
            content.append(readLine);
         }
         bufferedReader.close();
      }catch (Exception e){
         System.out.println("Error getting data");
      }
      return content.toString();
   }

   public static  String getCityName(JSONObject jsonObject){
      return jsonObject.getString("name");
   }

   public static String getTemp(JSONObject jsonObject) {
      int temp = (int) Math.round(jsonObject.getJSONObject("main").getDouble("temp") - 273);
      String strTemp = null;
      if (temp == 0) {
         strTemp = temp + "°С";
      } else if (temp > 0) {
         strTemp = "+" + temp + "°С";
      } else if (temp < 0) {
         strTemp = "-" + temp + "°С";
      }
      return strTemp;
   }

   public static String getFeelsLike(JSONObject jsonObject)  {
      double feelsLike = Math.round((jsonObject.getJSONObject("main").getDouble("feels_like")-273)*100)/100.0;
      String strFeelsLike = null;
      if (feelsLike == 0) {
         strFeelsLike = feelsLike + "°С";
      } else if (feelsLike > 0) {
         strFeelsLike = "+" + feelsLike + "°С";
      } else if (feelsLike < 0) {
         strFeelsLike = "-" + feelsLike + "°С";
      }
      return strFeelsLike;
   }

   public static String getCityCode(JSONObject jsonObject){
      return jsonObject.getJSONObject("sys").getString("country");
   }

   public static String getPrecipitation(JSONObject jsonObject){
      return (String) jsonObject.getJSONArray("weather").getJSONObject(0).get("main");
   }

}
