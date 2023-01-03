module main.weatherapp {
   requires javafx.controls;
   requires javafx.fxml;
   requires org.json;


   opens main.weatherapp to javafx.fxml;
   exports main.weatherapp;
}