import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DN11 {

    public static void main(String[] args) {
        // args = new String[] { "5", "Domace_Naloge/viri/kraji.txt",
        // "Domace_Naloge/viri/povezave1.bin" };

        EuroRail network = new EuroRail();
        int operation = Integer.parseInt(args[0]);
        switch (operation) {
            case 1:
                network.preberiKraje(args[1]);
                network.preberiPovezave(args[2]);
                network.izpisiKraje();
                System.out.println();
                network.izpisiPovezave();
                break;
            case 2:
                network.preberiKraje(args[1]);
                network.preberiPovezave(args[2]);
                network.izpisiNetwork();
                break;
            case 3:
                network.preberiKraje(args[1]);
                network.preberiPovezave(args[2]);
                network.izpisiNetworkSorted();
                break;
            case 4:
                network.preberiKraje(args[1]);
                network.preberiPovezave(args[2]);
                StringBuilder kraj = new StringBuilder("");
                for (int i = 4; i < args.length; i++) {
                    kraj.append(args[i]);
                    kraj.append(" ");
                }
                int prestopi = Integer.parseInt(args[3]);
                network.potujmo(kraj.toString().trim(), prestopi);
                break;
            case 5:
                network.preberiKraje(args[1]);
                network.preberiPovezaveOld(args[2]);
                network.izpisiPovezave();
                break;
            default:
                break;
        }

    }
}

class EuroRail {
    private Map<String, Kraj> krajiMap;
    private List<Kraj> kraji;
    private List<Vlak> vlaki;

    public EuroRail() {
        krajiMap = new HashMap<>();
        kraji = new ArrayList<>();
        vlaki = new ArrayList<>();
    }

    boolean preberiKraje(String imeDatoteke) {
        File kraji_txt = new File(imeDatoteke);
        String[] tab;
        String ime, okrajsava;
        try (Scanner sc = new Scanner(kraji_txt)) {
            while (sc.hasNextLine()) {
                try {
                    tab = sc.nextLine().split(";");
                    ime = tab[0];
                    okrajsava = tab[1];
                    if (krajiMap.keySet().contains(ime))
                        continue;
                    krajiMap.put(ime, new Kraj(ime, okrajsava));
                    kraji.add(krajiMap.get(ime));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    boolean preberiPovezave(String imeDatoteke) {
        File povezave_txt = new File(imeDatoteke);
        String id;
        Kraj origin, destination;
        double doplacilo;
        float casPotovanja;
        int casPotovanjaInt;
        String[] tab;
        Vlak vlak;
        try (Scanner sc = new Scanner(povezave_txt)) {
            while (sc.hasNextLine()) {
                tab = sc.nextLine().split(";");
                try {
                    id = tab[0];
                    origin = krajiMap.get(tab[1]);
                    destination = krajiMap.get(tab[2]);
                    if (origin == null || destination == null || origin.equals(destination))
                        continue;
                    casPotovanja = Float.parseFloat(tab[3]);
                    int number = (int) casPotovanja;
                    int decimal = Math.round((casPotovanja - number) * 100);
                    if (tab[3].contains("."))
                        number *= 60;
                    casPotovanjaInt = number + decimal;
                    if (tab.length == 5) {
                        doplacilo = Double.parseDouble(tab[4]);
                        vlak = new EkspresniVlak(id, origin, destination, casPotovanjaInt, doplacilo);
                        vlaki.add(vlak);
                        origin.dodajOdhod(vlak);
                    } else {
                        vlak = new RegionalniVlak(id, origin, destination, casPotovanjaInt);
                        vlaki.add(vlak);
                        origin.dodajOdhod(vlak);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    void preberiPovezaveOld(String imeDatoteke) {
        File povezave_txt = new File(imeDatoteke);
        byte[] id;
        int origin, destination;
        byte[] casPotovanja, doplacilo;
        int casPotovanjaInt;
        double doplaciloDouble;
        String idString;
        ByteBuffer wrapped;
        try (FileInputStream fs = new FileInputStream(povezave_txt)) {
            while (fs.available() > 0) {
                try {
                    id = fs.readNBytes(6);
                    origin = fs.read();
                    destination = fs.read();
                    origin--;
                    destination--;
                    casPotovanja = fs.readNBytes(2);
                    doplacilo = fs.readNBytes(2);
                    idString = new String(id, "UTF-8");
                    wrapped = ByteBuffer.wrap(casPotovanja);
                    casPotovanjaInt = wrapped.getShort();
                    wrapped = ByteBuffer.wrap(doplacilo);
                    doplaciloDouble = wrapped.getShort();
                    doplaciloDouble /= 100;
                    if (origin == destination)
                        continue;
                    if (doplaciloDouble == 0) {
                        vlaki.add(new RegionalniVlak(idString, kraji.get(origin), kraji.get(destination),
                                casPotovanjaInt));
                    } else {
                        vlaki.add(
                                new EkspresniVlak(idString, kraji.get(origin), kraji.get(destination), casPotovanjaInt,
                                        doplaciloDouble));
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void izpisiKraje() {
        System.out.println("Kraji, povezani z vlaki:");
        for (Kraj kraj : kraji) {
            System.out.println(kraj);
        }
    }

    void izpisiPovezave() {
        System.out.println("Vlaki, ki povezujejo kraje:");
        for (Vlak vlak : vlaki) {
            System.out.println(vlak);
        }
    }

    void izpisiNetwork() {
        System.out.println("Kraji in odhodi vlakov:");
        for (Kraj kraj : kraji) {
            kraj.izpisAll();
        }
    }

    void izpisiNetworkSorted() {
        System.out.println("Kraji in odhodi vlakov (po abecedi):");
        Set<Kraj> krajiSet = new TreeSet<>();
        krajiSet.addAll(kraji);
        for (Kraj kraj : krajiSet) {
            kraj.izpisAllSorted();
        }
    }

    boolean potujmo(String krajString, int prestopi) {
        Set<Kraj> destinations = new TreeSet<>();
        if (krajiMap.get(krajString) == null) {
            System.out.printf("NAPAKA: podanega kraja (%s) ni na seznamu krajev.\n", krajString);
            return false;
        }
        destinations.add(krajiMap.get(krajString));
        destinations.addAll(potujmoRek(destinations, prestopi));
        destinations.remove(krajiMap.get(krajString));
        if (destinations.size() == 0) {
            System.out.printf("Iz kraja %s ni nobenih povezav.\n", krajiMap.get(krajString));
            return false;
        } else {
            System.out.printf("Iz kraja %s lahko z max %d prestopanji pridemo do naslednjih krajev:\n",
                    krajiMap.get(krajString), prestopi);
            for (Kraj kraj : destinations) {
                System.out.println(kraj);
            }
        }
        return true;
    }

    Set<Kraj> potujmoRek(Set<Kraj> destinations, int prestopi) {
        if (prestopi < 0)
            return destinations;
        Set<Kraj> temp = new TreeSet<>();
        temp.addAll(destinations);
        for (Kraj kraj : temp) {
            destinations.addAll(kraj.povezave());
        }
        return potujmoRek(destinations, --prestopi);
    }

}

class Kraj implements Comparable<Kraj> {
    private String ime;
    private String drzava;
    private List<Vlak> odhodi;

    public Kraj(String ime, String drzava) {
        this.ime = ime;
        this.drzava = drzava;
        this.odhodi = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", ime, drzava);
    }

    boolean dodajOdhod(Vlak vlak) {
        if (!odhodi.contains(vlak))
            return odhodi.add(vlak);
        return false;
    }

    void izpisAll() {
        System.out.println(this);
        System.out.printf("odhodi vlakov (%d):\n", this.odhodi.size());
        for (Vlak vlak : odhodi) {
            System.out.printf(" - %s\n", vlak);
        }
        System.out.println();
    }

    void izpisAllSorted() {
        System.out.println(this);
        System.out.printf("odhodi vlakov (%d):\n", this.odhodi.size());
        Set<Vlak> odhodiSet = new TreeSet<>();
        odhodiSet.addAll(odhodi);
        for (Vlak vlak : odhodiSet) {
            System.out.printf(" - %s\n", vlak);
        }
        System.out.println();
    }

    @Override
    public int compareTo(Kraj k) {
        return this.drzava.compareTo(k.drzava) == 0 ? this.ime.compareTo(k.ime) : this.drzava.compareTo(k.drzava);
    }

    TreeSet<Kraj> povezave() {
        TreeSet<Kraj> krajiSet = new TreeSet<>();
        for (Vlak vlak : odhodi) {
            krajiSet.add(vlak.getDestination());
        }
        return krajiSet;
    }

}

abstract class Vlak implements Comparable<Vlak> {
    private String id;
    private Kraj origin;
    private Kraj destination;
    private int casPotovanja;

    public Vlak(String id, Kraj origin, Kraj destination, int casPotovanja) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.casPotovanja = casPotovanja;
    }

    @Override
    public String toString() {
        return String.format("Vlak %s (%s, %.2f EUR)", this.opis(),
                casPotovanja > 60 ? String.format("%d.%02dh", (casPotovanja / 60), (casPotovanja % 60))
                        : casPotovanja + " min",
                this.cenaVoznje());
    }

    abstract String opis();

    abstract double cenaVoznje();

    public String getId() {
        return id;
    }

    public Kraj getOrigin() {
        return origin;
    }

    public Kraj getDestination() {
        return destination;
    }

    public int getCasPotovanja() {
        return casPotovanja;
    }

}

class RegionalniVlak extends Vlak {
    private double hitrost = 50;
    private double cenaNaKm = 0.068;

    public RegionalniVlak(String id, Kraj origin, Kraj destination, int casPotovanja) {
        super(id, origin, destination, casPotovanja);
    }

    @Override
    double cenaVoznje() {
        return hitrost * (getCasPotovanja() / 60.0) * cenaNaKm;
    }

    @Override
    String opis() {
        return String.format("%s (regionalni) %s -- %s", getId(), getOrigin(), getDestination());
    }

    @Override
    public int compareTo(Vlak v) {
        double cena = v.cenaVoznje() - this.cenaVoznje();
        if (cena > 0)
            return 1;
        if (cena < 0)
            return -1;
        return 0;
    }

}

class EkspresniVlak extends Vlak {
    private double hitrost = 110;
    private double cenaNaKm = 0.154;
    private double doplacilo;

    public EkspresniVlak(String id, Kraj origin, Kraj destination, int casPotovanja, double doplacilo) {
        super(id, origin, destination, casPotovanja);
        this.doplacilo = doplacilo;
    }

    @Override
    double cenaVoznje() {
        return hitrost * (getCasPotovanja() / 60.0) * cenaNaKm + doplacilo;
    }

    @Override
    String opis() {
        return String.format("%s (ekspresni) %s -- %s", getId(), getOrigin(), getDestination());
    }

    @Override
    public int compareTo(Vlak v) {
        double cena = v.cenaVoznje() - this.cenaVoznje();
        if (cena > 0)
            return 1;
        if (cena < 0)
            return -1;
        return 0;
    }
}