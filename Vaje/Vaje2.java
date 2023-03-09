package Vaje;

public class Vaje2 {
    public static void main(String[] args) {
        // izpisFak();
        izpisFakDub();
    }

    public static void izpisFakDub() {
        int Top = 100;
        double fact;
        double factS;
        double error;
        System.out.println("  n         n!            Stirling(n)     napaka (%)");
        System.out.println("----------------------------------------------------");
        for (int i = 1; i <= Top; i++) {
            fact = fakultetaD(i);
            factS = stirlingD(i);
            error = (fact - factS) * 100.0 / fact;
            System.out.printf("%3d %17.9e %17.9e   %.7f\n", i, fact, factS, error);
        }
    }

    static double fakultetaD(double n) {
        if (n == 0)
            return 1.0;
        return fakultetaD(n - 1) * n;
    }

    static double stirlingD(double n) {
        double PIn2 = 2.0 * Math.PI * n;
        double n_divide_e = n / Math.E;
        double rezultat = Math.sqrt(PIn2) * Math.pow(n_divide_e, n);
        return rezultat;
    }

    public static void izpisFak() {
        int Top = 20;
        long fact;
        long factS;
        System.out.println(" n n! Stirling(n) napaka (%)");
        System.out.println("----------------------------------------------------------");
        for (int i = 1; i <= Top; i++) {
            fact = fakultetaL(i);
            factS = stirlingL(i);
            System.out.printf("%3d %20d %20d %.7f\n", i, fact, factS, ((1.0 * fact -
                    factS) / fact) * 100.0);
        }
    }

    public static long fakultetaL(int n) {
        if (n == 0)
            return 1;
        return fakultetaL(n - 1) * n;
    }

    public static long stirlingL(int n) {
        return (long) Math.round(Math.sqrt(2 * Math.PI * n) * Math.pow((n / Math.E),
                n));
    }

}