package classes;
import java.util.ArrayList;

    public class Transaksi {
        private String noTransaksi;
        private String namaPemesanan;
        private String tanggal;
        private String noMeja;
        private ArrayList<Pesanan> pesanan;
        private double uangBayar;
        private double pajak;
        private double totalbayar;

        public Transaksi(String no_transaksi, String nm_pemesan, String tanggal, String no_meja) {}
}
