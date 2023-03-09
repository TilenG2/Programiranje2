package Vaje;

public class Neki {
    public static void main(String[] args) {
        System.out.println("hello");
    }

    public static void pravokotnikStevil(int sirina, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= sirina; j++) {
                System.out.print(j % 10);
            }
            System.out.println();
        }
    }

    public static void pravokotnik(int odmik, int sirina, int visina) {
        for (int i = 0; i < visina; i++) {
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < sirina; j++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }

    public static void trikotnikStevil(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j % 10);
            }
            System.out.println();
        }
    }

    public static void trikotnikStevilObrnjen(int visina) {
        for (int i = visina; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j % 10);
            }
            System.out.println();
        }
    }

    public static void trikotnikStevil_e(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 0; j < visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j < i * 2; j++) {
                System.out.print(j % 10);
            }
            System.out.println();
        }
    }

    public static void trikotnik(int odmik, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 0; j < odmik + visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j < i * 2; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void trikotnikObrnjen(int odmik, int visina) {
        for (int i = visina; i > 0; i--) {
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j < i * 2; j++) {
                System.out.print("*");
            }
            System.out.println();
            odmik++;
        }
    }

    public static void romb(int odmik, int velikost) {
        trikotnik(odmik, velikost);
        trikotnikObrnjen(odmik + 1, velikost - 1);
    }

    public static void smreka(int velikost) {
        int odmik = velikost * 2 - 2;
        for (int i = 1; i <= velikost; i++) {
            trikotnik(odmik, i * 2);
            odmik -= 2;
        }
        int sirina = velikost % 2 == 1 ? velikost : velikost + 1;
        pravokotnik((2 * (2 * velikost) - sirina - 1) / 2, sirina, velikost * 2);
    }
}
