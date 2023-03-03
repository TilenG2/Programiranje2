import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class DN03 {
    public static void main(String[] args) throws Exception {
        File datoteka = new File(args[0]);
        int n = Integer.parseInt(args[1]);
        int seme = Integer.parseInt(args[2]);
        Scanner sc = new Scanner(datoteka);
        int vrstice = Integer.parseInt(sc.nextLine());
        Random rnd = new Random(seme);

        String[] sifre = new String[vrstice];

        for (int i = 0; i < sifre.length; i++) {
            sifre[i] = sc.nextLine();
        }
        String geslo = "";
        int index;
        for (int i = 0; i < n; i++) {
            index = rnd.nextInt(sifre.length);
            int indexcharat = rnd.nextInt(sifre[index].length());
            geslo = geslo + sifre[index].charAt(indexcharat);
        }
        System.out.println(geslo);
        sc.close();
    }
}