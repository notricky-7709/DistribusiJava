import java.util.Scanner;
import java.util.ArrayList;
public class distribusi {
    static Scanner input = new Scanner(System.in);
    /*static int[][] gudang;
    static int[][] toko;
    static String[] label_produk;*/

    //Dummy data
    static int[][] gudang = {
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
    static String[] label_produk = {"Lifebuoy","Dettol","Garnier","Ponds"};

    //Fungsi untuk data persediaan gudang dan keperluan toko, dan nama toko dan produk
    static void inputData(){ //Mengisi Tabel awal

        //Input data
        int n_gudang, n_toko, n_produk;

        //Input produk
        System.out.print("Masukkan jumlah jenis produk yang tersedia: ");
        n_produk = input.nextInt();
        label_produk = new String[n_produk];
        transition();
        for(int i = 0; i < label_produk.length; i++){
            System.out.printf("Masukkan nama produk %d: ", i + 1);
            label_produk[i] = input.nextLine();
            System.out.println();
        }
        transition();

        //Input Gudang

        System.out.print("Masukkan jumlah gudang: ");
        n_gudang = input.nextInt();
        gudang = new int[n_gudang][n_produk];
        transition();
        for(int i = 0; i < gudang.length; i++){
            System.out.printf("Masukkan stok gudang %d: \n", i + 1);
            for(int j = 0; j < gudang[0].length; j++){
                System.out.printf("Stok %s: ", label_produk[j]);
                gudang[i][j] = input.nextInt();
            }
            transition();
        }
        transition();

        //Input toko

        System.out.print("Masukkan jumlah toko: ");
        n_toko = input.nextInt();
        toko = new int[n_toko][n_produk];
        transition();
        for(int i = 0; i < toko.length; i++){
            System.out.printf("Masukkan stok toko %d: \n", i + 1);
            for(int j = 0; j < toko[0].length; j++){
                System.out.printf("Stok %s: ", label_produk[j]);
                toko[i][j] = input.nextInt();
            }
            transition();
        }
        transition();
        menuPage();
    }
    static void menuPage(){
        int pick;
        System.out.println("Menu:");
        System.out.printf("%1. Read Data");
        System.out.printf("%2. Update Data");
        System.out.printf("%3. Delete Data");
        System.out.println();
        System.out.printf("%4. Distribusikan stok");
        System.out.println("\n\n");
        System.out.println("Masukkan apa yang ingin dilakukan: ");
        pick = input.nextInt();
        transition();
        switch (pick){
            case 1:
                readData();
            case 2:
                editData();
            case 3:
                deleteData();
            case 4:
                StockDistribution();
            default:
                System.out.println("Input Salah");
                System.out.println("Tekan ENTER untuk kembali...");
                input.nextLine();
                transition();
                menuPage();
        }

    }
    static void readData(){
        int pick;
        System.out.println("Menu - Read:");
        System.out.println("1. Tabel Gudang");
        System.out.println("2. Tabel Toko");
        System.out.println("3. Daftar Produk");
        System.out.println();
        System.out.println("4. Kembali ke menu");
        System.out.println("\n\n");
        System.out.println("Masukkan apa yang ingin dilakukan: ");
        pick = input.nextInt();
        transition();
        switch (pick){
            case 1:
                readData();
            case 2:
                editData();
            case 3:
                deleteData();
            case 4:
                menuPage();
            default:
                System.out.println("Input Salah");
                System.out.println("Tekan ENTER untuk kembali...");
                input.nextLine();
                transition();
                readData();
        }
    }
    static void editData(){

    }
    static void deleteData() {

    }
    static void readGudang(){
        //Membuat format tabel
        String tableformat = "| %-15s |"; // "| %-15s | $-4d|%n"
        for(int i = 0; i < gudang[0].length; i++){
            tableformat += ("$-4d|");
        }
        tableformat += "%n";
    }
    static void readToko(){

    }
    static void readProduk(){

    }
    static void transition(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    //Fungsi untuk menunjukkan proses distribusi
    static void StockDistribution(){
        //
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
    }
    public static void main(String[] args){
        //
        StockDistribution();



    }
}
