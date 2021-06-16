package classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DaftarMenu {

    private ArrayList<Menu> daftarMenu;

    public DaftarMenu(){
        daftarMenu = new ArrayList<>();
    }

    public void tambahMenu(Menu menu){
        daftarMenu.add(menu);
    }

    public void getMenuByKategori(String kategori){
        System.out.println("========" + kategori + "========");

        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu m = daftarMenu.get(i);
            if (m.getKategori().equals(kategori)) {
                System.out.println((i + 1) + ". " + m.getNama_menu() + "\t" + m.getHarga());
            }
        }
    }

    public void tampilDaftarMenu(){
        System.out.println("========>>> DM RAMEN!!!!! <<<========");
        getMenuByKategori("Ramen");
        getMenuByKategori("Kuah");
        getMenuByKategori("Toping");
        getMenuByKategori("Minuman");
    }

    public Menu pilihMenu(){
        try{
            Scanner input = new Scanner(System.in);

            System.out.println("Nomor menu yang dipesan : ");
            int no_menu = input.nextInt();

            Menu m = daftarMenu.get(no_menu-1);
            if(!m.getKategori().equalsIgnoreCase("Ramen")){
                return m;
            } else {
                System.out.println("Pesan Ramen terlebih dahulu");
                return pilihMenu();
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Pesanan Tidak tersedia");
            return pilihMenu();
        }catch (InputMismatchException i){
            System.out.println("Mohon Masukan Nomor Menu");
            return pilihMenu();
        }
    }

    public Menu pilihKuah(){
        try{
            Scanner input = new Scanner(System.in);

            System.out.println("Kuah : ");
            int no_menu = input.nextInt();

            Menu m = daftarMenu.get(no_menu-1);
            if(!m.getKategori().equalsIgnoreCase("Kuah")){
                return m;
            } else {
                System.out.println("Kuah Tidak Tersedia");
                return pilihKuah();
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Pesanan Tidak tersedia");
            return pilihKuah();
        }catch (InputMismatchException i){
            System.out.println("Mohon Masukan Nomor Menu");
            return pilihKuah();
        }
    }


}