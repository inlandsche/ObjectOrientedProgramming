package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login {

    @FXML
    private Button button;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label statusLogin;


    public void userLogin(ActionEvent event) throws IOException {
        check();
    }

    public void check() throws IOException{
        Main main = new Main();

        if (username.getText().equalsIgnoreCase("admin") && password.getText().equalsIgnoreCase("admin")){
            statusLogin.setText("Login Success!");

            main.changeScene("Menu.fxml");
        }

        else {
            statusLogin.setText("Username atau Password Anda Salah!");
        }
    }

}
