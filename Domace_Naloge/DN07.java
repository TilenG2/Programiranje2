import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DN07 {
    public static File file_dir;

    public static void main(String[] args) throws Exception {
        // args = new String[] { "5", "./Domace_Naloge/naloga_datoteke", "izpis.txt",
        // "mapa1/besedilo1_kopija.txt" };
        int naloga = Integer.parseInt(args[0]);
        file_dir = new File(args[1]);
        switch (naloga) {
            case 1:
                izpisi_datoteke(file_dir);
                break;
            case 2:
                najvecja_datoteka(file_dir);
                break;
            case 3:
                izpis_vsebin(file_dir, Integer.parseInt(args[2]));
                break;
            case 4:
                kopiraj_datoteko(args[2], args[3]);
                break;
            case 5:
                zdruzi_datoteko(file_dir, args[2]);
                break;
            case 6:
                najdiVDatotekah(file_dir, args[2]);
                break;
            case 7:
                drevo(file_dir);
                break;
            case 8:
                resiMatematicneIzraze(file_dir);
                break;
            case 9:
                nNajvecjih(file_dir, Integer.parseInt(args[2]));
                break;

            default:
                break;
        }
    }

    public static void izpisi_datoteke(File f) {
        String[] datoteke = f.list();
        File dat;
        double size;
        for (String datoteka : datoteke) {
            dat = new File(f.toString(), datoteka);
            size = dat.length();
            size = size / 1000;
            System.out.printf("%20s%20s%10.3f\n", datoteka, dat.isDirectory() ? "Mapa" : "Datoteka",
                    size);
        }
    }

    public static void najvecja_datoteka(File f) {
        String[] datoteke = f.list();
        File dat;
        Datoteka min = new Datoteka();
        Datoteka max = new Datoteka();

        for (int i = 0; i < datoteke.length; i++) {
            dat = new File(f.toString() + "/" + datoteke[i]);
            if (dat.isFile())
                if (i == 0) {
                    min.setAll(datoteke[i], dat.length());
                    max.setAll(datoteke[i], dat.length());
                } else {
                    if (min.getSize() > dat.length())
                        min.setAll(datoteke[i], dat.length());
                    else if (max.getSize() < dat.length())
                        max.setAll(datoteke[i], dat.length());
                }
        }
        System.out.printf("%s %.3f\n", max.getName(), max.getSize() / 1000.0);
        System.out.printf("%s %.3f\n", min.getName(), min.getSize() / 1000.0);

    }

    public static void izpis_vsebin(File f, int n) throws Exception {
        String[] datoteke = f.list();
        File dat;
        Scanner sc;
        for (String datoteka : datoteke) {
            dat = new File(f.toString(), datoteka);
            if (dat.isFile()) {
                if (isTxtFile(datoteka)) {
                    sc = new Scanner(dat);
                    System.out.println(datoteka);
                    for (int i = 1; i <= n && sc.hasNextLine(); i++) {
                        System.out.println("    " + sc.nextLine());
                    }
                } else {
                    System.out.printf("%s (ni tekstovna datoteka)\n", datoteka);
                }
            }
        }
    }

    public static void kopiraj_datoteko(String vhodnaDatoteka, String izhodnaDatoteka) throws Exception {
        File dat1 = new File(file_dir.toString(), vhodnaDatoteka);
        File dat2 = new File(file_dir.toString(), izhodnaDatoteka);
        Scanner sc = new Scanner(dat1);
        PrintWriter pw = new PrintWriter(dat2);
        if (dat2.exists() && dat2.length() == 0) {
            while (sc.hasNextLine())
                pw.println(sc.nextLine());
        } else {
            System.out.println("Napaka pri kopiranju, datoteka že vsebuje besedilo");
        }
        pw.close();
        sc.close();
    }

    public static void zdruzi_datoteko(File direktorij, String izhodnaDatoteka) throws Exception {
        String[] datoteke = direktorij.list();
        File dat;
        Scanner sc;
        boolean noFiles = true;
        PrintWriter pw = new PrintWriter(direktorij.toString() + "/" + izhodnaDatoteka);
        for (String datoteka : datoteke) {
            dat = new File(direktorij.toString(), datoteka);
            if (dat.isFile()) {
                if (isTxtFile(datoteka)) {
                    sc = new Scanner(dat);
                    while (sc.hasNextLine()) {
                        pw.println(sc.nextLine());
                    }
                    noFiles = false;
                }
            }
        }
        pw.close();
        if (noFiles)
            System.out.println("Direktorij ne vsebuje tekstovnih datotek.");
    }

    public static void najdiVDatotekah(File f, String iskanNiz) throws Exception {
        String[] datoteke = f.list();
        File dat;
        Scanner sc;
        String vrstica;
        int countVrstice;
        for (String datoteka : datoteke) {
            dat = new File(f.toString(), datoteka);
            if (dat.isFile() && isTxtFile(datoteka)) {
                sc = new Scanner(dat);
                countVrstice = 1;
                while (sc.hasNextLine()) {
                    vrstica = sc.nextLine();
                    if (vrstica.indexOf(iskanNiz) != -1) {
                        System.out.printf("%s %d: %s\n", datoteka, countVrstice, vrstica);
                    }
                    countVrstice++;
                }
            }
        }
    }

    public static void drevo(File f) {
        drevoRek(f, 1);
    }

    public static void drevoRek(File f, int depth) {
        String[] datoteke = f.list();
        System.out.println("    ".repeat(depth - 1) + "/" + f.getName());
        File dat;
        for (String datoteka : datoteke) {
            dat = new File(f.toString(), datoteka);
            if (dat.isDirectory()) {
                drevoRek(dat, depth + 1);
            } else {
                System.out.println("    ".repeat(depth) + datoteka);
            }
        }
    }

    public static void resiMatematicneIzraze(File f) throws Exception {
        String[] datoteke = f.list();
        File dat;
        Scanner sc, vr;
        String vrstica;
        int cifra, vsota;
        boolean izraz;
        for (String datoteka : datoteke) {
            dat = new File(f.toString(), datoteka);
            if (dat.isFile() && isTxtFile(datoteka)) {
                sc = new Scanner(dat);
                System.out.println(datoteka);
                while (sc.hasNextLine()) {
                    vrstica = sc.nextLine().replaceAll("-", " -").replaceAll("\\+", " +");
                    vr = new Scanner(vrstica);
                    vsota = 0;
                    izraz = false;
                    while (vr.hasNextInt()) {
                        cifra = vr.nextInt();
                        vsota += cifra;
                        izraz = true;
                        if (cifra > 9) {
                            izraz = false;
                            break;
                        }
                    }
                    if (izraz) {
                        vrstica = vrstica.replaceAll(" ", "");
                        System.out.println("  " + vrstica + "=" + vsota);
                    }
                }
            }
        }
    }

    public static void nNajvecjih(File f, int n) {
        ArrayList<Datoteka> datoteke = directoryDig(f);
        int maxSize, maxIndex;
        for (int i = 0; i < n; i++) {
            maxSize = (int) datoteke.get(0).getSize();
            maxIndex = 0;
            for (int j = 1; j < datoteke.size(); j++) {
                if (maxSize < datoteke.get(j).getSize()) {
                    maxIndex = j;
                    maxSize = (int) datoteke.get(j).getSize();
                }
            }
            System.out.println(datoteke.get(maxIndex).toString());
            datoteke.remove(maxIndex);

        }
    }

    public static boolean isTxtFile(String datoteka) {
        return datoteka.indexOf(".txt", datoteka.length() - 4) != -1;
    }

    public static ArrayList<Datoteka> directoryDig(File f) {
        ArrayList<Datoteka> datoteke = new ArrayList<Datoteka>();
        String[] datetekeStrings = f.list();
        File dat;
        for (String datoteka : datetekeStrings) {
            dat = new File(f.toString(), datoteka);
            if (dat.isDirectory())
                datoteke.addAll(directoryDig(dat));
            else
                datoteke.add(new Datoteka(datoteka, dat.length()));
        }
        return datoteke;
    }
}

class Datoteka {
    String name;
    long size;

    public Datoteka() {
        this.name = null;
        this.size = 0;
    }

    public Datoteka(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public void setAll(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return name + " " + size;
    }
}