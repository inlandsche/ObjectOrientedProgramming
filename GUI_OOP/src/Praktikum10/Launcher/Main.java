package Praktikum10.Launcher;

import Praktikum10.classData.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        //inisialisasi Mahasiswa dan Dosen pada main class
        Data.initMahasiswa();
        Data.initDosen();

        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/Praktikum10/fxmlFiles/MainMenu.fxml"));
        primaryStage.setTitle("Praktikum GUI");
        primaryStage.setScene(new Scene(root, 600, 430));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxml));

        stage.getScene().setRoot(parent);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
