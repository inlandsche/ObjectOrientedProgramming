package Praktikum10.controller;

import Praktikum10.classData.Data;
import Praktikum10.classData.Dosen;
import Praktikum10.Launcher.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class FormDosen {

    @FXML
    private TextField inputNIP;
    @FXML
    private TextField inputNama;
    @FXML
    private TextField inputProdi;
    @FXML
    private TextField inputFakultas;

    @FXML
    private TableView<Dosen> tabelDsn;
    @FXML
    private TableColumn<Dosen, String> colNip;
    @FXML
    private TableColumn<Dosen, String> colNama;
    @FXML
    private TableColumn<Dosen, String> colProdi;
    @FXML
    private TableColumn<Dosen, String> colFakultas;

    @FXML
    public void initialize(){
        colNip.setCellValueFactory(new PropertyValueFactory<>("Nip"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        colProdi.setCellValueFactory(new PropertyValueFactory<>("Prodi"));
        colFakultas.setCellValueFactory(new PropertyValueFactory<>("Fakultas"));

        tampilData();
    }

    public void tampilData(){
        tabelDsn.getItems().removeAll(Data.dsn);
        tabelDsn.getItems().addAll(Data.dsn);
    }

    public void tambahData(){
        String nip, nama, prodi, fakultas;

        for (Dosen dosen : Data.dsn){
            if (inputNIP.getText().equals(dosen.getNip())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("NIP sudah ada!");

                alert.showAndWait();
                return;
            }
        }

        if (inputNIP.getText().equals("") || inputNama.getText().equals("") || inputProdi.getText().equals("")
                || inputFakultas.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Field tidak boleh kosong!");

            alert.showAndWait();
        }
        else {
            nip = inputNIP.getText();
            nama = inputNama.getText();
            prodi = inputProdi.getText();
            fakultas = inputFakultas.getText();

            try {
                Data.dsn.add(new Dosen(nip, nama, prodi, fakultas));
                Data.simpanDsn();
                tampilData();
            } catch (Exception e) {
                e.printStackTrace();
            }

            inputNIP.setText("");
            inputNama.setText("");
            inputProdi.setText("");
            inputFakultas.setText("");

            inputNIP.requestFocus();
        }
    }

    public void edit(){
        Dosen d = tabelDsn.getSelectionModel().getSelectedItem();

        if (d != null){
            inputNIP.setText(d.getNip());
            inputNama.setText(d.getNama());
            inputProdi.setText(d.getProdi());
            inputFakultas.setText(d.getFakultas());
        }
    }

    public void updateData() throws IOException{
        Dosen updated = tabelDsn.getSelectionModel().getSelectedItem();

        String nip = inputNIP.getText();
        String nama = inputNama.getText();
        String prodi = inputProdi.getText();
        String fakultas = inputFakultas.getText();

        if (nip.equals("") || nama.equals("") || prodi.equals("")
                || fakultas.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Field tidak boleh kosong!");

            alert.showAndWait();
            return;
        }

        if (updated != null){
            for (Dosen dosen : Data.dsn){
                if (nip.equals(dosen.getNip()) && dosen != updated){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("NIP sudah ada!");
                    alert.showAndWait();
                    return;
                }
            }

            for (Dosen dosen : Data.dsn){
                if (dosen.getNip().equals(updated.getNip())){
                    dosen.setNip(nip);
                    dosen.setNama(nama);
                    dosen.setProdi(prodi);
                    dosen.setFakultas(fakultas);

                    break;
                }
            }

            tampilData();
            Data.simpanDsn();
        }
    }

    public void hapusData() throws IOException{
        Dosen deleted = tabelDsn.getSelectionModel().getSelectedItem();

        if (deleted != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi");
            alert.setHeaderText("");
            alert.setContentText("Apakah Anda yakin untuk menghapus?");

            var action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                for (Dosen dosen : Data.dsn) {
                    if (dosen.getNip().equals(deleted.getNip())) {
                        Data.dsn.remove(dosen);
                        tabelDsn.getItems().remove(dosen);
                        break;
                    }
                }
            }

            tampilData();
            Data.simpanDsn();
        }
    }

    public void reset(){
        inputNIP.setText("");
        inputNama.setText("");
        inputProdi.setText("");
        inputFakultas.setText("");

        inputNIP.requestFocus();
    }

    public void klikBack(ActionEvent ae) throws IOException {
        Main main = new Main();

        main.changeScene("/Praktikum10/fxmlFiles/MainMenu.fxml");
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        edit();
    }
}
