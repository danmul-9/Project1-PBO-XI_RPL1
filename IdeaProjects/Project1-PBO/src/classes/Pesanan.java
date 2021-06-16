package classes;

public class Pesanan {

    private Menu menu;
    private int jumlah;
    private String keterangan;

//    public Pesanan(Menu menu, int jumlah) {
//        this.menu = menu;
//        this.jumlah = jumlah;
//    }

    public Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }

    public Pesanan() {
    }

    public void setKeterangan(String Keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeterangan() { return keterangan; }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
