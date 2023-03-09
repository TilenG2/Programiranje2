package Kvizi;

public class Kviz1 {
    public static void main(String[] args) {
        veckratnikDelitelj(12, -24);
        veckratnikDelitelj(12, 8);
        veckratnikDelitelj(44, 0);

    }

    public static void veckratnikDelitelj(int a, int b) {
        if (a == 0 || b == 0)
            System.out.println("Napaka: obe števili morata biti različni od nič.");
        else {
            a = Math.abs(a);
            b = Math.abs(b);
            System.out.printf("Največji skupni delitelj je %d.\n", gcd(a, b));
            System.out.printf("Najmanjši skupni večkratnik je %d.\n", lcm(a, b));

        }
    }

    public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static void praDvojcek(int n) {
        for (int i = 2; i < n; i++) {
            if (jePrastevilo(i - 2) && jePrastevilo(i))
                System.out.printf("(%d, %d)\n", i - 2, i);
        }
    }

    public static String izracunajRazliko(String prviCas, String drugiCas) {
        String[] cas1 = prviCas.split(":");
        String[] cas2 = drugiCas.split(":");
        int prviCassec = Integer.parseInt(cas1[0]) * 3600 + Integer.parseInt(cas1[1]) * 60 + Integer.parseInt(cas1[2]);
        int drugiCassec = Integer.parseInt(cas2[0]) * 3600 + Integer.parseInt(cas2[1]) * 60 + Integer.parseInt(cas2[2]);
        return pretvoriSekunde(Math.abs(drugiCassec - prviCassec));
    }

    public static int[] presek(int[] tabela1, int[] tabela2) {
        String stevila = "";
        for (int i = 0; i < tabela1.length; i++) {
            for (int j = 0; j < tabela2.length; j++) {
                if (tabela1[i] == tabela2[j] && !stevila.contains(tabela1[i] + " "))
                    stevila += tabela1[i] + " ";
            }
        }
        String[] tab = stevila.trim().split(" ");
        int[] interesct = new int[tab.length];
        for (int i = 0; i < interesct.length; i++) {
            interesct[i] = Integer.parseInt(tab[i]);
        }
        return interesct;
    }

    public static int[] unija(int[] tabela1, int[] tabela2) {
        int[] union = new int[tabela1.length + tabela2.length];
        int indexunion = 0;
        for (int i = 0; i < tabela1.length; i++) {
            union[indexunion] = tabela1[i];
            indexunion++;
        }
        for (int i = 0; i < tabela2.length; i++) {
            union[indexunion] = tabela2[i];
            indexunion++;
        }
        return union;
    }

    public static void pitagoroviTrojcki(int x) {
        for (int a = 1; a <= x; a++) {
            for (int b = a; b <= x; b++) {
                for (int c = b; c <= x; c++) {
                    if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2))
                        System.out.printf("%d %d %d\n", a, b, c);
                }
            }
        }
    }

    public static int vsotaPrvih(int n) {
        int vsota = 0, stevilo = 2, stevec = 0;
        while (true) {
            if (jePrastevilo(stevilo)) {
                vsota += stevilo;
                stevilo++;
                stevec++;
            } else
                stevilo++;
            if (!(stevec < n))
                break;
        }
        return vsota;
    }

    public static String pretvoriVDesetisko(String n, int b) {
        java.util.Map<String, Integer> pretvorbe = new java.util.HashMap<String, Integer>();
        pretvorbe.put("0", 0);
        pretvorbe.put("1", 1);
        pretvorbe.put("2", 2);
        pretvorbe.put("3", 3);
        pretvorbe.put("4", 4);
        pretvorbe.put("5", 5);
        pretvorbe.put("6", 6);
        pretvorbe.put("7", 7);
        pretvorbe.put("8", 8);
        pretvorbe.put("9", 9);
        pretvorbe.put("A", 10);
        pretvorbe.put("B", 11);
        pretvorbe.put("C", 12);
        pretvorbe.put("D", 13);
        pretvorbe.put("E", 14);
        pretvorbe.put("F", 15);
        pretvorbe.put("G", 16);

        String[] stevke = n.trim().split("");
        int desetisko = 0;
        int ponovitev = 0;
        int vrednost;
        for (int i = stevke.length - 1; i >= 0; i--) {
            vrednost = pretvorbe.get(stevke[i]);
            if (vrednost >= b)
                return String.format("Napaka pri pretvorbi sistema - števka %s", stevke[i]);
            desetisko += vrednost * Math.pow(b, ponovitev);
            ponovitev++;
        }
        return String.format("%s(%d)=%d(10)", n, b, desetisko);
    }

    public static void vDesetisko(int n) {
        int coppy = n;
        int desetisko = 0;
        int ponovite = 0;
        if ((n + "").contains("9"))
            System.out.printf("Število %d ni število v osmiškem sistemu (števka 9)\n", n);
        else if ((n + "").contains("8"))
            System.out.printf("Število %d ni število v osmiškem sistemu (števka 8)\n", n);
        else {
            while (coppy != 0) {
                desetisko += (int) (coppy % 10 * Math.pow(8, ponovite));
                coppy = coppy / 10;
                ponovite++;

            }
            System.out.printf("%d(8) = %d(10)\n", n, desetisko);
        }
    }

    public static void izrisiZastavo(int n) {
        for (int i = 1; i <= 5 * n; i++) {
            for (int j = 1; j <= 15 * n + 1; j++) {
                if (j < 4 * n && i <= 3 * n) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0)
                            System.out.print("*");
                        else
                            System.out.print(" ");
                    } else {
                        if (j % 2 == 0)
                            System.out.print(" ");
                        else
                            System.out.print("*");
                    }
                } else if (j == 4 * n && i <= 3 * n) {
                    System.out.print(" ");
                } else {
                    System.out.print("=");
                }
            }
            System.out.println();
        }
    }

    public static boolean jePrastevilo(int n) {
        if (n < 2)
            return false;
        for (int i = 2; i <= n / 2; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static int fibonacijeva(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        return fibonacijeva(n - 1) + fibonacijeva(n - 2);
    }

    public static boolean jeFibonaccijevo(int n) {
        int i = 1;
        while (fibonacijeva(i) <= n) {
            if (fibonacijeva(i) == n)
                return true;
            i++;
        }
        return false;
    }

    public static void javaJavaJava(int n) {
        if (n > 0) {
            String[] izpis = { "J    a   v     v  a   ", "J   a a   v   v  a a  ",
                    "J  J  aaaaa   V V  aaaaa ",
                    "JJ  a     a   V  a     a" };
            for (int i = 0; i < izpis.length; i++) {
                for (int j = 0; j < n; j++)
                    System.out.printf("%27s", izpis[i]);
                System.out.println();
            }
        } else
            System.out.println("Napaka: negativen n");

    }

    public static String pretvoriSekunde(int sekunde) {
        if (sekunde < 0)
            return "Število sekund ne more biti negativno";
        int hour = sekunde / 3600;
        sekunde = sekunde % 3600;
        int minute = sekunde / 60;
        sekunde = sekunde % 60;
        return String.format("%02d:%02d:%02d", hour, minute, sekunde);
    }

    public static void krog(double r, int d) {
        if (r < 0)
            System.out.println("Napaka: negativen polmer");
        else if (d < 0)
            System.out.println("Napaka: negativen d");
        else {
            System.out.printf("Obseg kroga s polmerom r=%.2f je %." + d + "f\n", r, 2 * Math.PI * r);
            System.out.printf("Ploscina kroga s polmerom r=%.2f je %." + d + "f\n", r, Math.PI * Math.pow(r, 2));
        }
    }

    public static void nicli(int a, int b, int c) {
        double D = Math.pow(b, 2) - (4 * a * c);
        if (D >= 0) {
            double x1 = (-b + Math.sqrt(D)) / (2.0 * a);
            double x2 = (-b - Math.sqrt(D)) / (2.0 * a);

            System.out.printf("x1=%.2f, x2=%.2f\n", x1, x2);
        } else
            System.out.println("Napaka: nicli enacbe ne obstajata");
    }

    public static void kalkulator(int a, int b) {
        if (b != 0) {
            System.out.println(a + " + " + b + " = " + (a + b));
            System.out.println(a + " - " + b + " = " + (a - b));
            System.out.println(a + " x " + b + " = " + (a * b));
            System.out.println(a + " / " + b + " = " + (a / b));
            System.out.println(a + " % " + b + " = " + (a % b));
        } else
            System.out.println("Napaka: deljenje z 0");
    }

}
