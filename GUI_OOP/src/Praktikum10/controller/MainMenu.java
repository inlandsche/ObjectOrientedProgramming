package Praktikum10.controller;

import Praktikum10.Launcher.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;

public class MainMenu {

    public void klikMahasiswa(ActionEvent ae) throws IOException {
        Main main = new Main();

        main.changeScene("/Praktikum10/fxmlFiles/formMahasiswa.fxml");

    }

    public void klikDosen(ActionEvent ae) throws IOException {
        Main main = new Main();

        main.changeScene("/Praktikum10/fxmlFiles/formDosen.fxml");
    }

    public void klikExit(ActionEvent ae) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Konfirmasi");
        alert.setHeaderText(null);
        alert.setContentText("Yakin mau keluar?");
        var action = alert.showAndWait();

        if (action.get() == ButtonType.OK){
            System.exit(0);
        }
    }

}
