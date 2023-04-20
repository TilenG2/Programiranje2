package Vaje;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Vaje8 {

    // Iz podane datoteke prebere podatke o računih in ustvari račune v podani
    // banki.
    private static void dodajRacune(String vir, Banka banka) throws FileNotFoundException {
        Scanner vhod = new Scanner(new File(vir));
        while (vhod.hasNextLine()) {
            String[] podatki = vhod.nextLine().split(";");
            if (podatki[0].equalsIgnoreCase("tekoci")) { // ustvari tekoči račun
                banka.dodajTekociRacun(podatki[1], Double.parseDouble(podatki[2]));
            } else { // ustvari varčevalni račun
                banka.dodajVarcevalniRacun(podatki[1], Double.parseDouble(podatki[2]));
            }
            banka.polog(podatki[1], Double.parseDouble(podatki[3]));
        }
        vhod.close();
    }

    public static void main(String[] args) throws FileNotFoundException {

        // ustvarimo novo banko
        Banka bankaFRI = new Banka();

        // v banki naredimo račune z določenimi stanji, vse podatke preberemo iz
        // datoteke
        dodajRacune("racuni.txt", bankaFRI);

        bankaFRI.izpisiRacune();

        System.out.println();
        bankaFRI.izpisiRacune(true);

        String stevilkaRacuna = "SI56 1234 4321 1234 126"; // tekoči z limitom 100 €
        // String stevilkaRacuna = "SI56 7823 4563 8346 123"; // varčevalni z 0,1%
        // obrestmi

        // TODO: položi znesek na račun in preveri novo stanje z izpisom računov

        // TODO: dvigni znesek z računa in preveri novo stanje z izpisom računov

        // TODO: preveri še ostale metode banke

    }
}

class Banka {
    private static final int MAX_RACUNOV = 500;
    private Racun[] racuni = new Racun[MAX_RACUNOV];
    private int steviloRacunov = 0;

    public Banka() {

    }

    boolean dodajTekociRacun(String stevilka, double limit) {
        if (steviloRacunov >= MAX_RACUNOV)
            return false;
        if (vrniRacun(stevilka) != null)
            return false;
        racuni[steviloRacunov++] = new TekociRacun(stevilka, limit);
        return true;
    }

    boolean dodajVarcevalniRacun(String stevilka, double obresti) {
        if (steviloRacunov >= MAX_RACUNOV)
            return false;

        if (vrniRacun(stevilka) != null)
            return false;

        racuni[steviloRacunov++] = new VarcevalniRacun(stevilka, obresti);
        return true;
    }

    boolean dvig(String stevilka, double znesek) {
        Racun racun = vrniRacun(stevilka);
        if (racun == null)
            return false;

        racun.dvig(znesek);

        return true;
    }

    private Racun vrniRacun(String stevilka) {
        for (Racun racun : racuni) {
            if (racun == null)
                break;
            if (stevilka.equals(racun.getStevilka())) {
                return racun;
            }
        }
        return null;
    }

    boolean polog(String stevilka, double znesek) {

        Racun racun = vrniRacun(stevilka);
        if (racun == null)
            return false;

        racun.polog(znesek);

        return true;
    }

    public void dodajObresti() {
        for (Racun racun : racuni) {
            if (racun == null)
                break;
            if (racun instanceof VarcevalniRacun)
                ((VarcevalniRacun) racun).dodajObresti();
        }
    }

    void izpisiRacune() {
        System.out.println(racuni[0]);
        for (Racun racun : racuni) {
            if (racun == null)
                break;
            System.out.println(racun.toString());
        }
        System.out.println("Stevilo racunov: " + steviloRacunov);
    }

    void izpisiRacune(boolean varcevalni) {
        int st_racun = 0;
        for (Racun racun : racuni) {
            if (racun == null)
                break;
            if (varcevalni && (racun instanceof VarcevalniRacun)) {
                System.out.println(racun.toString());
                st_racun++;
            } else if (!varcevalni && racun instanceof TekociRacun) {
                System.out.println(racun.toString());
                st_racun++;
            }
        }
        System.out.println("Stevilo racunov: " + st_racun);
    }
}

abstract class Racun {
    private double stanje; // stanje na računu
    private final String stevilka; // številka računa

    /**
     * Konstruktor: ustvari račun s podano številko računa in začetnim stanjem 0,00
     * EUR.
     *
     * @param stevilka - številka računa
     */
    Racun(String stevilka) {
        this.stanje = 0.0;
        this.stevilka = stevilka;
    }

    /**
     * Položi znesek "znesek" na račun.
     *
     * @param znesek - položen znesek, pozitivno število
     * @return true, če je polog uspešno izveden, sicer false (v primeru
     *         nepozitivnega pologa)
     */
    public boolean polog(double znesek) {
        if (znesek < 0)
            return false;
        stanje += znesek;
        return true;
    }

    /**
     * Dvigne znesek "znesek" z računa. Stanje računa je lahko tudi negativno.
     *
     * @param znesek - dvignjen znesek, pozitivno število
     * @return true, če je dvig uspešno izveden, sicer false (v primeru
     *         nepozitivnega zneska)
     */
    public boolean dvig(double znesek) {
        if (znesek < 0)
            return false;
        stanje -= znesek;
        return true;
    }

    /**
     * Vrne trenutno stanje na računu (getter).
     *
     * @return stanje na računu
     */
    public double getStanje() {
        return stanje;
    }

    /**
     * Vrne številko računa (getter).
     *
     * @return številka računa
     */
    public String getStevilka() {
        return stevilka;
    }

    /**
     * Abstraktna metoda, ki vrne opis računa glede na njegov tip.
     *
     * @return niz z opisom računa
     */
    abstract String opisRacuna();

    /**
     * Pripravi niz, ki opisuje račun in njegovo trenutno stanje.
     * Niz je naslednje oblike: "Račun <stevilka> (<opis>): <stanje> EUR",
     * kjer je <stevilka> številka računa, <stanje> je stanje na računu, <opis> pa
     * je opis tipa računa.
     *
     * @return niz, ki opisuje račun in stanje na njem
     */
    public String toString() {
        return String.format("Račun %s (%s): %.2f EUR", this.stevilka, this.opisRacuna(), this.stanje);
    }
}

class TekociRacun extends Racun {
    private double limit;

    public TekociRacun(String stevilka, double limit) {
        super(stevilka);
        this.limit = limit;
    }

    @Override
    public boolean dvig(double znesek) {
        if (znesek > limit)
            return false;
        return super.dvig(znesek);
    }

    @Override
    String opisRacuna() {
        // TODO Auto-generated method stub
        return String.format("tekoči, limit: %.2f EUR", this.limit);
    }
}

class VarcevalniRacun extends Racun {
    private double obresti;

    public VarcevalniRacun(String stevilka, double obresti) {
        super(stevilka);
        this.obresti = obresti;
    }

    void dodajObresti() {
        super.polog(super.getStanje() * (obresti));
    }

    @Override
    String opisRacuna() {
        // TODO Auto-generated method stub
        return String.format("varčevalni, obrestna mera: %.2f%%", obresti * 100);
    }
}