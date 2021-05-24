package Praktikum10.classData;

public class Dosen extends Civitas {
    private String nip;

    public Dosen(String nip, String nama, String prodi, String fakultas) {
        super(nama, prodi, fakultas);
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
