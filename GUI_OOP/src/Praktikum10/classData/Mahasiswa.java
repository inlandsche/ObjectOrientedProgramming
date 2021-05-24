package Praktikum10.classData;

public class Mahasiswa extends Civitas {
    private String nim;

    public Mahasiswa(String nim, String nama, String prodi, String fakultas) {
        super(nama, prodi, fakultas);
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
