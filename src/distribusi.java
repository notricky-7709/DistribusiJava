import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class distribusi {
    static Scanner input = new Scanner(System.in);
    static int[][] gudang;
    static int[][] toko;
    static String[] label_produk;

    //Dummy data
    /*static int[][] gudang = {
            {1 , 3, 5, 7},
            {6, 8, 4, 5},
            {9, 1, 3, 2},
            {7, 6, 5, 4},
    };
    static int[][] toko = {
            {2, 4, 9, 4},
            {5, 6, 4, 1},
            {7, 8, 1, 2},
            {2, 3, 1, 4},
    };
    static String[] label_produk = {"Lifebuoy","Dettol","Garnier","Ponds"};*/

    //Fungsi untuk data persediaan gudang dan keperluan toko, dan nama toko dan produk
    static void inputData(){
        //Mengisi Tabel awal

        //Input data
        int n_gudang, n_toko, n_produk;

        //Input produk
        System.out.print("Masukkan jumlah jenis produk yang tersedia: ");
        n_produk = input.nextInt();
        label_produk = new String[n_produk];
        input.nextLine();
        for(int i = 0; i < label_produk.length; i++){
            System.out.printf("Masukkan nama produk %d: ", i + 1);
            label_produk[i] = input.nextLine();
        }
        transition();

        //Input Gudang

        System.out.print("Masukkan jumlah gudang: ");
        n_gudang = input.nextInt();
        gudang = new int[n_gudang][n_produk];
        input.nextLine();
        for(int i = 0; i < gudang.length; i++){
            System.out.printf("Masukkan stok gudang %d: \n", i + 1);
            for(int j = 0; j < gudang[0].length; j++){
                System.out.printf("Stok %s: ", label_produk[j]);
                gudang[i][j] = input.nextInt();
            }
            input.nextLine();
        }
        transition();

        //Input toko

        System.out.print("Masukkan jumlah toko: ");
        n_toko = input.nextInt();
        toko = new int[n_toko][n_produk];
        input.nextLine();
        for(int i = 0; i < toko.length; i++){
            System.out.printf("Masukkan stok toko %d: \n", i + 1);
            for(int j = 0; j < toko[0].length; j++){
                System.out.printf("Stok %s: ", label_produk[j]);
                toko[i][j] = input.nextInt();
            }
            input.nextLine();
        }
        transition();
        menuPage();
    }
    static void menuPage(){
        int pick;
        System.out.println("Menu:");
        System.out.println("1. Read Data");
        System.out.println("2. Distribusikan stok");
        System.out.println("3. Input Ulang Data");
        System.out.println();
        System.out.println("4. Keluar");
        System.out.println("\n\n");
        System.out.println("Masukkan apa yang ingin dilakukan: ");
        pick = input.nextInt();
        transition();
        switch (pick){
            case 1:
                readData();
            case 2:
                StockDistribution();
            case 3:
                inputData();
            case 4:
                endApp();
            default:
                System.out.println("Input Salah");
                pause();
                transition();
                menuPage();
        }

    }
    static void readData(){
        int pick;
        System.out.println("Menu - Read:");
        System.out.println("1. Stok Gudang");
        System.out.println("2. Permintaan Toko");
        System.out.println("3. Daftar Produk");
        System.out.println();
        System.out.println("4. Kembali ke menu");
        System.out.println("\n\n");
        System.out.println("Masukkan apa yang ingin dilakukan: ");
        pick = input.nextInt();
        transition();
        switch (pick){
            case 1:
                readGudang();
            case 2:
                readToko();
            case 3:
                listProduk();
            case 4:
                menuPage();
            default:
                System.out.println("Input Salah");
                pause();
                transition();
                readData();
        }
    }
    //Menampilkan tabel stok gudang
    static void printGudang(){
        System.out.printf("|| %-15s ||", "Nama gudang");
        for(int i = 0; i < label_produk.length; i++){
            System.out.printf(" %-10s||", label_produk[i]);
        }
        System.out.println();
        //Konten tabel
        for(int i = 0; i < gudang.length; i++){
            System.out.printf("|| %-15s ||", ("gudang" + (i + 1)));
            for(int j = 0; j < gudang[0].length; j++){
                System.out.printf(" %-10d||", gudang[i][j]);
            }
            System.out.println();
        }
    }
    static void printToko(){
        System.out.printf("|| %-15s ||", "Nama toko");
        for(int i = 0; i < label_produk.length; i++){
            System.out.printf(" %-10s||", label_produk[i]);
        }
        System.out.println();
        //Konten tabel
        for(int i = 0; i < toko.length; i++){
            System.out.printf("|| %-15s ||", ("toko" + (i + 1)));
            for(int j = 0; j < toko[0].length; j++){
                System.out.printf(" %-10d||", toko[i][j]);
            }
            System.out.println();
        }
    }
    static void printProduk(){
        System.out.println("List Produk:");
        for(int i = 0; i < label_produk.length; i++){
            System.out.printf("%d. %s\n", (i+1), label_produk[i]);
        }
    }
    static void readGudang(){
        printGudang();
        pause();
        transition();
        readData();
    }
    //Menampilkan tabel permintaan toko
    static void readToko(){
        printToko();
        pause();
        transition();
        readData();
    }
    static void pause(){
        System.out.print("Tekan ENTER untuk kembali...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void listProduk(){
        printProduk();
        transition();
        readData();
    }
    static void transition(){
        System.out.println("=================");
        System.out.println("=================");
    }
    //Fungsi untuk menunjukkan proses distribusi
    static void StockDistribution(){
        int[][] sisa_gudang = gudang;

        for(int i = 0; i < toko[0].length; i++){
            int n_gudang = 0;
            System.out.println("=================");
            System.out.printf("Pengiriman %s \n", label_produk[i]);
            System.out.println("=================");
            for(int j = 0; j < toko.length; j++){
                int temp_request = toko[j][i];
                System.out.printf("Toko %d (%d buah):\n", (j+1), temp_request);

                if(n_gudang < sisa_gudang.length){
                    while (temp_request > sisa_gudang[n_gudang][i]) {
                        System.out.println("Gudang " + (n_gudang + 1));
                        temp_request = temp_request - sisa_gudang[n_gudang][i];
                        sisa_gudang[n_gudang][i] = 0;
                        if (n_gudang < (sisa_gudang.length - 1)) {
                            n_gudang++;
                        } else if (n_gudang == sisa_gudang.length) {
                            System.out.println("Gudang kehabisan stok");
                            j = toko.length;
                            temp_request = 0;
                        }
                    }
                    if (temp_request < sisa_gudang[n_gudang][i]) {
                        System.out.println("Gudang " + (n_gudang + 1));
                        sisa_gudang[n_gudang][i] -= temp_request;
                    }
                    //Jika request dan sisa di gudang sama2 0
                    else if (temp_request == sisa_gudang[n_gudang][i]) {
                        System.out.println("Gudang " + (n_gudang + 1));
                        n_gudang++;
                    }
                }
                else{
                    System.out.println("Gudang kehabisan stok");
                }

            }
        }
        pause();
        transition();
        menuPage();
    }
    static void endApp(){
        System.out.println("Terima kasih telah menggunakan");
        System.exit(0);
    }
    public static void main(String[] args){
        inputData();
    }
}
