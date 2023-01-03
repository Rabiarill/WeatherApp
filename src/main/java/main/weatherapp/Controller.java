package main.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class Controller {

   @FXML
   private TextField CityTextField;

   @FXML
   private Button GetWeatherButton;

   @FXML
   private Label Location;

   @FXML
   private Label Precipitation;

   @FXML
   private Label Temp;

   @FXML
   private Label TempFeelsLike;


   @FXML
   void initialize() {

      GetWeatherButton.setOnAction(actionEvent -> {
         String city = CityTextField.getText();
         JSONObject json = WeatherApi.getJson(city);

         System.out.println(json.toString());

         Location.setText(WeatherApi.getCityName(json) + ", " + WeatherApi.getCityCode(json));
         Precipitation.setText(WeatherApi.getPrecipitation(json));
         Temp.setText(WeatherApi.getTemp(json));
         TempFeelsLike.setText("По ощущениям:" + WeatherApi.getFeelsLike(json));
      });

   }

}



