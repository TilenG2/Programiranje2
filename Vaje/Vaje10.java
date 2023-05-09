package Vaje;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Vaje10 {
    public static void main(String[] args) {
        Dolgovi dolgovi = new Dolgovi();
        dolgovi.preberiPrijatelje(new File("Vaje/viri/prijatelji.txt"));
        dolgovi.preberiDolgove(new File("Vaje/viri/dolgovi.txt"));
        dolgovi.izpis();
    }
}

class Prijatelj implements Comparable<Prijatelj> {
    private static int st_prijateljev = 0;
    private int zapSt;
    private String ime;
    private TreeMap<Prijatelj, Double> dolguje;

    public Prijatelj(String ime) {
        this.zapSt = ++st_prijateljev;
        this.ime = ime;
        this.dolguje = new TreeMap<>();
    }

    @Override
    public String toString() {
        return String.format("[%03d] %s", this.zapSt, this.ime);
    }

    public void izpisDolg() {
        for (Prijatelj p : dolguje.keySet()) {
            System.out.printf("  --> %s (%.2f EUR)\n", p, dolguje.get(p));
        }
    }

    public void addDolg(Prijatelj p, Double d) {
        if (dolguje.containsKey(p))
            dolguje.put(p, dolguje.get(p) + d);
        else
            dolguje.put(p, d);
    }

    public boolean imaDolgove() {
        return !dolguje.isEmpty();
    }

    public static int getSt_prijateljev() {
        return st_prijateljev;
    }

    public int getZapSt() {
        return zapSt;
    }

    public String getIme() {
        return ime;
    }

    @Override
    public int compareTo(Prijatelj p) {
        return this.zapSt - p.getZapSt();
    }

}

class Dolgovi {
    private ArrayList<Prijatelj> prijatelji = new ArrayList<>();

    void preberiPrijatelje(File datoteka) {
        Scanner sc;
        try {
            sc = new Scanner(datoteka);
            while (sc.hasNextLine())
                prijatelji.add(new Prijatelj(sc.nextLine()));
            sc.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    void preberiDolgove(File datoteka) {
        Scanner sc;
        String[] vrsica;
        Double dolg;
        Prijatelj p1 = null, p2 = null;
        boolean testp1, testp2;
        try {
            sc = new Scanner(datoteka);
            while (sc.hasNextLine()) {
                vrsica = sc.nextLine().split(";");
                testp1 = false;
                testp2 = false;
                if (vrsica.length == 3) {
                    dolg = Double.parseDouble(vrsica[2]);
                    if (dolg > 0) {
                        for (Prijatelj p : prijatelji) {
                            if (p.getIme().equals(vrsica[0])) {
                                p1 = p;
                                testp1 = true;
                            } else if (p.getIme().equals(vrsica[1])) {
                                p2 = p;
                                testp2 = true;
                            }
                        }
                    } else {
                        for (Prijatelj p : prijatelji) {
                            if (p.getIme().equals(vrsica[0])) {
                                p2 = p;
                                testp2 = true;
                            } else if (p.getIme().equals(vrsica[1])) {
                                p1 = p;
                                testp1 = true;
                            }
                        }
                        dolg *= -1;
                    }
                    if (testp1 && testp2)
                        p1.addDolg(p2, dolg);
                }
            }

        } catch (

        Exception e) {
            // TODO: handle exception
        }
    }

    public void izpis() {
        for (Prijatelj p : prijatelji) {
            System.out.print(p);
            if (p.imaDolgove())
                System.out.println(" ima dolgove do naslednjih prijateljev:");
            else
                System.out.println(" nima dolgov.");
            p.izpisDolg();
        }
    }
}