import java.util.ArrayList;
import java.util.Scanner;

public class DN09 {
    public static ArrayList<Lik> liki = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc;
        for (String lik : args) {
            sc = new Scanner(lik).useDelimiter(":");
            switch (sc.next().toLowerCase()) {
                case "pravokotnik":
                    liki.add(new Pravokotnik(sc.nextInt(), sc.nextInt()));
                    break;
                case "kvadrat":
                    liki.add(new Kvadrat(sc.nextInt()));
                    break;
                case "nkotnik":
                    liki.add(new NKotnik(sc.nextInt(), sc.nextInt()));
                    break;
                default:
                    // How did we get here;
                    break;
            }
        }
        System.out.println(skupniObseg());
    }

    public static int skupniObseg() {
        int obseg = 0;
        for (Lik lik : liki) {
            obseg += lik.obseg();
        }
        return obseg;
    }
}

class Pravokotnik implements Lik {
    private int a, b;

    public Pravokotnik(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double obseg() {
        return 2 * a + 2 * b;
    }
}

class Kvadrat extends Pravokotnik {

    public Kvadrat(int a) {
        super(a, a);
    }

    @Override
    public double obseg() {
        return super.obseg();
    }
}

class NKotnik implements Lik {
    private int n, a;

    public NKotnik(int n, int a) {
        this.n = n;
        this.a = a;
    }

    @Override
    public double obseg() {
        return n * a;
    }
}

interface Lik {
    public double obseg();
}