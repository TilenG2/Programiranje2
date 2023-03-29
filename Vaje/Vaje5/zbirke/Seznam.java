package Vaje.Vaje5.zbirke;

public class Seznam {
    private static String[] nakupovalniSeznam;
    private static int elementi = 0;

    public static boolean narediSeznam(int n) {
        try {
            nakupovalniSeznam[0] = nakupovalniSeznam[0];
            return false;
        } catch (Exception e) {
            nakupovalniSeznam = new String[n];
        }
        return true;
    }

    public static boolean dodajNaKonecSeznama(String element) {
        try {
            nakupovalniSeznam[elementi] = element;
            elementi++;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void izpisiSeznam() {
        try {
            if (nakupovalniSeznam[0] != null) {
                System.out.println("Na seznamu so naslednji elementi:");
                for (int i = 0; i < nakupovalniSeznam.length; i++) {
                    System.out.printf("%3d: %s", i + 1, nakupovalniSeznam[i]);
                }
            } else
                System.out.println("Seznam je prazen (nima elementov).");
        } catch (Exception e) {
            System.out.println("NAPAKA: Seznam ne obstaja.");
        }
    }

    public static String odstraniIzSeznama(int mesto) {
        String element = null;
        try {
            element = nakupovalniSeznam[mesto - 1];
            elementi--;
            for (int i = mesto - 1; i < nakupovalniSeznam.length - 1; i++) {
                nakupovalniSeznam[i] = nakupovalniSeznam[i + 1];
            }
            nakupovalniSeznam[nakupovalniSeznam.length - 1] = null;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return element;
    }
}
