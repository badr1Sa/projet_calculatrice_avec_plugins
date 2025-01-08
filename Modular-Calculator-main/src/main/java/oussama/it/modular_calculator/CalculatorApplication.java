package oussama.it.modular_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Calculator-view.fxml"));
        Scene mainScene = new Scene(loader.load(), 600, 460);


        primaryStage.setTitle("RSI Calculator!");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}



//package oussama.it.modular_calculator;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import java.io.IOException;
//public class CalculatorApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApplication.class.getResource("Calculator-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 460);
//        stage.setTitle("RSI Calculator!");
//        stage.setScene(scene);
//        stage.show();
//    }
//    public static void main(String[] args) {
//        launch();
//    }
//}