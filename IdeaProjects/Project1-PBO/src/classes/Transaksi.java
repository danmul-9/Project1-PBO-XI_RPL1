package classes;
import java.util.ArrayList;
import java.util.List;

public class Transaksi {

    private String noTransaksi;
    private String namaPemesanan;
    private String tanggal;
    private String noMeja;
    private List<Pesanan> pesanan;
    private double uangBayar;
    private double pajak;
    private double totalbayar;
    private double biayaService;

    public Transaksi(String no_transaksi, String nm_pemesan, String tanggal, String no_meja) {
        this.noTransaksi = no_transaksi;
        this.namaPemesanan = nm_pemesan;
        this.tanggal = tanggal;
        this.noMeja = no_meja;

        pesanan = new ArrayList<>();
    }

    public void tambahPesanan(Pesanan pesanan) {
        this.pesanan.add(pesanan);
    }

    public List<Pesanan> getSemuaPesanan() {
        return pesanan;
    }

    public double hitungTotalPesanan() {
        for(int i=0; i<pesanan.size();i++){
            Pesanan psn = pesanan.get(i);
            double harga = psn.getMenu().getHarga();
            totalbayar += (harga * psn.getJumlah());
        }
        return totalbayar;
    }

    public double hitungPajak(){
        return totalbayar * pajak;
    }

    public double hitungBiayaService(){
        return totalbayar * biayaService;
    }

    public double hitungTotalBayar(double pajak, double biayaService){
        totalbayar = totalbayar + pajak + biayaService;
        return totalbayar;
    }

    public double hitungKembalian(double uangBayar) {
        return uangBayar - totalbayar;
    }

    public void cetakStruk() {
        System.out.println("\n>>>>>>>>> !!!!! DM RAMEN !!!!! <<<<<<<<<");
        System.out.println("No Transaksi : " + noTransaksi);
        System.out.println("Pemesan : " + namaPemesanan);
        System.out.println("Tanggal : " +tanggal);

        if(noMeja.equals("")){
            noMeja = "Take Away";
        }

        System.out.println("Meja : " + noMeja);
        System.out.println("========================================");
        for(int i = 0; i<pesanan.size(); i++){
            Pesanan psn = pesanan.get(i);
            Menu m = psn.getMenu();
            String pesanan = psn.getJumlah() + " " + m.getNama_menu() + "\t"+
                    (m.getHarga()* psn.getJumlah());

            if(m.getKategori().equals("Kuah")){
                pesanan = " "+pesanan;
            }

            System.out.println(pesanan);
        }

    }

    public void setPajak(double pajak) {
        this.pajak = pajak;
    }

    public void setBiayaService(double biayaService) {
        this.biayaService = biayaService;
    }
}