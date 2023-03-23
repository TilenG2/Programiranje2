package Kvizi;

public class Kviz2 {
    public static void main(String[] args) {

        System.out.println(binarnoSestej("10", "11"));
        System.out.println(binarnoSestej("10011010010", "1000011100001"));

    }

    static String binarnoSestej(String s, String b) {
        s = "0" + s;
        b = "0" + b;
        int sBin = s.length() - 1;
        int bBin = b.length() - 1;
        String sestevek = "";
        char bit1, bit2, carry = '0';
        while (sBin > 0 || bBin > 0) {
            bit1 = s.charAt(sBin);
            bit2 = b.charAt(bBin);
            if (carry == '0') {
                if (bit1 == '0' && bit2 == '0')
                    sestevek = "0" + sestevek;
                else if (bit1 == '1' && bit2 == '1') {
                    sestevek = "0" + sestevek;
                    carry = '1';
                } else
                    sestevek = "1" + sestevek;
            } else {
                if (bit1 == '0' && bit2 == '0') {
                    sestevek = "1" + sestevek;
                    carry = '0';

                } else if (bit1 == '1' && bit2 == '1')
                    sestevek = "1" + sestevek;
                else
                    sestevek = "0" + sestevek;
            }
            sBin = sBin > 0 ? --sBin : sBin;
            bBin = bBin > 0 ? --bBin : bBin;
        }
        return carry == '0' ? sestevek : carry + sestevek;
    }

    static double koren(int x, int d) {
        double decimal = 1, c = 1;
        for (int i = 0; i <= d; i++) {
            while (c * c < x)
                c += 1 / decimal;
            c -= 1 / decimal;
            decimal *= 10;
        }
        return c;
    }

    static int[] duplikati(int[] tabela) {
        java.util.ArrayList<Integer> brezponovitev = new java.util.ArrayList<Integer>();
        boolean vsebuje;
        for (int stev : tabela) {
            vsebuje = false;
            for (Integer test : brezponovitev) {
                if (test == stev)
                    vsebuje = true;
            }
            if (!vsebuje)
                brezponovitev.add(stev);
        }
        int[] neki = new int[brezponovitev.size()];
        for (int i = 0; i < neki.length; i++) {
            neki[i] = brezponovitev.get(i);
        }
        return neki;
    }

    static void rotiraj(int[] tabela, int k) {
        int[] kopija = tabela.clone();
        k = tabela.length - k;
        while (k < 0)
            k += k;
        for (int i = 0; i < kopija.length; i++) {
            tabela[(i + k) % tabela.length] = kopija[i];
        }
    }

    static int[] range(int a, int b, int c) {
        int[] zaporedje = new int[(int) Math.ceil((b - a) / (double) c)];
        for (int i = 0; i < zaporedje.length; i++) {
            zaporedje[i] = a + i * c;
        }
        return zaporedje;
    }

    static int vsotaStevk(String str) {
        int vsota = 0;
        for (int i = 0; i < str.length(); i++) {
            try {
                vsota += Integer.parseInt(str.substring(i, i + 1));
            } catch (Exception e) {
                // Ma tud ce nc ne nardim ;)
            }
        }
        return vsota;
    }

    static boolean preveriRep(String a, String b) {
        a = a.toUpperCase();
        b = b.toUpperCase();
        return a.lastIndexOf(b) == a.length() - b.length() || b.lastIndexOf(a) == b.length() - a.length();
    }
}
