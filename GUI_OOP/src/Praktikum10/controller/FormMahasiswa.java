package Praktikum10.controller;

import Praktikum10.classData.Data;
import Praktikum10.classData.Mahasiswa;
import Praktikum10.Launcher.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.io.IOException;


public class FormMahasiswa {

    @FXML
    private TextField inputNIM;
    @FXML
    private TextField inputNama;
    @FXML
    private TextField inputProdi;
    @FXML
    private TextField inputFakultas;

    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;


    @FXML
    private TableView<Mahasiswa> tableMhs;
    @FXML
    private TableColumn<Mahasiswa, String> colNim;
    @FXML
    private TableColumn<Mahasiswa, String> colNama;
    @FXML
    private TableColumn<Mahasiswa, String> colProdi;
    @FXML
    private TableColumn<Mahasiswa, String> colFakultas;

    @FXML
    public void initialize() throws IOException{

        colNim.setCellValueFactory(new PropertyValueFactory<>("Nim"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        colProdi.setCellValueFactory(new PropertyValueFactory<>("Prodi"));
        colFakultas.setCellValueFactory(new PropertyValueFactory<>("Fakultas"));

        tampilData();
    }

    public void tampilData(){
        tableMhs.getItems().removeAll(Data.mhs);
        tableMhs.getItems().addAll(Data.mhs);
    }

    public void tambahData(){
        String nim, nama, prodi, fakultas;

        for (Mahasiswa m : Data.mhs){
            if (inputNIM.getText().equals(m.getNim())){
                Alert alertNIM = new Alert(Alert.AlertType.ERROR);
                alertNIM.setTitle("Error");
                alertNIM.setHeaderText(null);
                alertNIM.setContentText("NIM sudah ada!");

                alertNIM.showAndWait();
                return;
            }
        }

        if (inputNIM.getText() == "" || inputNama.getText() == "" || inputProdi.getText() == ""
        || inputFakultas.getText() == ""){
            Alert alertNIM = new Alert(Alert.AlertType.ERROR);
            alertNIM.setTitle("Error");
            alertNIM.setHeaderText(null);
            alertNIM.setContentText("Field tidak boleh kosong!");

            alertNIM.showAndWait();
            return;
        }
        else {
            nim = inputNIM.getText();
            nama = inputNama.getText();
            prodi = inputProdi.getText();
            fakultas = inputFakultas.getText();

            try {
                Data.mhs.add(new Mahasiswa(nim, nama, prodi, fakultas));
                Data.simpanMhs();
                tampilData();
            } catch (Exception e) {
                e.printStackTrace();
            }

            inputNIM.setText("");
            inputNama.setText("");
            inputProdi.setText("");
            inputFakultas.setText("");

            inputNIM.requestFocus();
        }
    }

    public void edit(){
        Mahasiswa m = tableMhs.getSelectionModel().getSelectedItem();

        if (m != null) {
            inputNIM.setText(m.getNim());
            inputNama.setText(m.getNama());
            inputProdi.setText(m.getProdi());
            inputFakultas.setText(m.getFakultas());
        }
    }

    public void updateData() throws IOException{
        Mahasiswa updated = tableMhs.getSelectionModel().getSelectedItem();

        String nim = inputNIM.getText();
        String nama = inputNama.getText();
        String prodi = inputProdi.getText();
        String fakultas = inputFakultas.getText();

        if (nim.equals("") || nama.equals("") || prodi.equals("")
                || fakultas.equals("")){
            Alert alertNIM = new Alert(Alert.AlertType.ERROR);
            alertNIM.setTitle("Error");
            alertNIM.setHeaderText(null);
            alertNIM.setContentText("Field tidak boleh kosong!");

            alertNIM.showAndWait();
            return;
        }

        if (updated != null) {
            for (Mahasiswa m : Data.mhs) {
                if (nim.equals(m.getNim()) && m != updated) {
                    Alert alertNIM = new Alert(Alert.AlertType.ERROR);
                    alertNIM.setTitle("Error");
                    alertNIM.setHeaderText(null);
                    alertNIM.setContentText("NIM sudah ada!");

                    alertNIM.showAndWait();
                    return;
                }
            }

            for (Mahasiswa m : Data.mhs) {
                if (m.getNim().equals(updated.getNim())) {
                    m.setNim(nim);
                    m.setNama(nama);
                    m.setProdi(prodi);
                    m.setFakultas(fakultas);

                    break;
                }
            }
        }

        tampilData();
        Data.simpanMhs();
    }

    public void hapusData() throws IOException{
        Mahasiswa deleted = tableMhs.getSelectionModel().getSelectedItem();

        if (deleted != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi");
            alert.setHeaderText("");
            alert.setContentText("Apakah Anda yakin untuk menghapus?");

            var action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                for (Mahasiswa m : Data.mhs) {
                    if (m.getNim().equals(deleted.getNim())) {
                        Data.mhs.remove(m);
                        tableMhs.getItems().remove(m);
                        break;
                    }
                }

            }

            Data.simpanMhs();
        }


    }

    public void reset(){
        inputNIM.setText("");
        inputNama.setText("");
        inputProdi.setText("");
        inputFakultas.setText("");

        inputNIM.requestFocus();
    }

    public void klikBack(ActionEvent ae) throws IOException{
        Main main = new Main();

        main.changeScene("/Praktikum10/fxmlFiles/MainMenu.fxml");
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        edit();
    }


}
