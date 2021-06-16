package classes;

public class Toping extends Menu{

    public Toping(String nama_toping, double harga){
        setNama_menu(nama_toping);
        setHarga(harga);
        setKategori("Toping");
    }

    public Toping(String crah_Stick_Bakar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}