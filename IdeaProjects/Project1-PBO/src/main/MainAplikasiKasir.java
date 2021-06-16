package main;
import classes.*;
import com.sun.tools.javac.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

    public class MainAplikasiKasir {
        public DaftarMenu daftarMenu;
        // Mulai inisiasi pajak
        public static double PAJAK_PPN = 0.10;
        public static double BIAYA_SERVICE = 0.05;
        // Sudah inisiasi pajak

        // Daftar Menu (generate method)
        public void generateDaftarMenu(){
            daftarMenu = new DaftarMenu();
            daftarMenu.tambahMenu(new Ramen("Ramen Original", 18000));
            daftarMenu.tambahMenu(new Ramen("Ramen Seafood", 25000));
            daftarMenu.tambahMenu(new Ramen("Ramen Vegetarian", 22000));
            daftarMenu.tambahMenu(new Ramen("Ramen Karnivor", 28000));
            daftarMenu.tambahMenu(new Kuah("Kuah Orisinil"));
            daftarMenu.tambahMenu(new Kuah("Kuah TomYum"));
            daftarMenu.tambahMenu(new Kuah("Kuah Extra Hot"));
            daftarMenu.tambahMenu(new Kuah("Kuah Susu"));
            daftarMenu.tambahMenu(new Toping("Baby Crab", 6000));
            daftarMenu.tambahMenu(new Toping("Chicken Katsu", 8000));
            daftarMenu.tambahMenu(new Toping("Jamur Goreng", 4000));
            daftarMenu.tambahMenu(new Toping("Bakso Goreng", 7000));
            daftarMenu.tambahMenu(new Toping("Sliced Beef", 5000));
            daftarMenu.tambahMenu(new Minuman("Jus Mangga", 10000));
            daftarMenu.tambahMenu(new Minuman("Jus Stroberi", 11000));
            daftarMenu.tambahMenu(new Minuman("Capucino Coffee", 15000));
            daftarMenu.tambahMenu(new Minuman("V60", 14000));
            daftarMenu.tambahMenu(new Minuman("Tea", 14000));

            daftarMenu.tampilDaftarMenu();
        }

        public double checkInputNumber(String label){
            try{
                Scanner get = new Scanner(System.in);
                System.out.println(label);
                double nilai = get.nextDouble();

                return nilai;
            } catch (InputMismatchException e){
                System.out.println("Harap Masukan angka : \t\t" );
                return checkInputNumber(label);
            }
        }

        public static void main(String[] args) {

            Scanner input = new Scanner(System.in);

            String no_transaksi, nama_pemesan, tanggal, no_meja = "";
            String transaksi_lagi = "",pesan_lagi = "", keterangan = "", makan_ditempat;
            int jumlah_pesanan, no_menu;


            MainAplikasiKasir app = new MainAplikasiKasir();
            app.generateDaftarMenu();

            do {
                //Mulai Transaksi
                System.out.println("=======>>> TRANSAKSI DISINI <<<=======");

                System.out.print("No Transaksi : ");
                no_transaksi = input.next();
                System.out.print("Pemesan : ");
                nama_pemesan = input.next();
                System.out.print("Tanggal : [dd-mm-yyyy] ");
                tanggal = input.next();
                System.out.print("Makan ditempat? [Y/N] ");
                makan_ditempat = input.next();

                if (makan_ditempat.equalsIgnoreCase("Y")) {
                    System.out.print("Nomor Meja : ");
                    no_meja = input.next();
                }

                Transaksi trans = new Transaksi(no_transaksi, nama_pemesan,tanggal, no_meja);
                System.out.println("--------- PESANAN ----------");
                int no_kuah;
                do{
                    Menu menu_yang_dipilih = app.daftarMenu.pilihMenu();

                    jumlah_pesanan = (int) app.checkInputNumber("Jumlah : ");

                    Pesanan pesanan = new Pesanan(menu_yang_dipilih, jumlah_pesanan);
                    trans.tambahPesanan(pesanan);

                    if(menu_yang_dipilih.getKategori().equals("Ramen")){
                        int jumlah_ramen = jumlah_pesanan;

                        do{
                            Menu kuah_yang_dipilih = app.daftarMenu.pilihKuah();

                            System.out.println("Level : [0-5] : ");
                            String level = input.next();

                            int jumlah_kuah = 0;

                            do{
                                jumlah_kuah = (int) app.checkInputNumber("Jumlah : ");

                                if(jumlah_kuah > jumlah_ramen){
                                    System.out.println("Jumlah Kuah Harus Sesuai Dengan Jumlah Ramen");
                                } else{
                                    break;
                                }
                            } while(jumlah_kuah > jumlah_ramen);
                            Pesanan pesanan_kuah = new Pesanan(kuah_yang_dipilih, jumlah_kuah);
                            pesanan_kuah.setKeterangan("Level" + level);

                            trans.tambahPesanan(pesanan_kuah);

                            jumlah_ramen -= jumlah_kuah;
                        }while(jumlah_ramen>0);
                    } else{
                        System.out.println("Keterangan : [- saja jika tidak ada] : ");
                        keterangan = input.next();
                    }

                    if(!keterangan.equals("-")){
                        pesanan.setKeterangan(keterangan);
                    }

                    System.out.println("Tambah Pesanan? (y/n) : ");
                    pesan_lagi = input.next();
                } while (pesan_lagi.equalsIgnoreCase("y"));

                trans.cetakStruk();

                double totalPemesanan = trans.hitungTotalPesanan();
                System.out.println("-------------------------------");
                System.out.println("Total : \t\t" + totalPemesanan);

                trans.setPajak(PAJAK_PPN);
                double ppn = trans.hitungPajak();
                System.out.println("Pajak 10% : \t\t" + ppn);

                double biayaService = 0;
                if(makan_ditempat.equalsIgnoreCase("y")){
                    trans.setBiayaService(BIAYA_SERVICE);
                    biayaService = trans.hitungBiayaService();
                    System.out.println("Service 5% : \t\t" + biayaService);
                }

                System.out.println("Total : \t\t"+ trans.hitungTotalBayar(ppn, biayaService));

                double uangKembali = 0;
                do{
                    double uangBayar = app.checkInputNumber("Uang Bayar : \t\t");
                    uangKembali = trans.hitungKembalian(uangBayar);
                    if(uangKembali <0){
                        System.out.println("Uang anda kurang");
                    } else {
                        System.out.println("Kembalian : \t\t" + uangKembali);
                        break;
                    }
                }while (uangKembali < 0);
                System.out.println("Lakukan Transaksi Lagi?");
                transaksi_lagi = input.next();
            } while (transaksi_lagi.equalsIgnoreCase("y"));
            System.out.println(">>>>>>>> TERIMA KASIH <<<<<<<<");
        }

    }

