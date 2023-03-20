public class DN05 {
    public static void main(String[] args) throws Exception {
        // args = new String[] { "Niz", "Brez", "Stevk" };
        int[] stevila = new int[10];
        String niz = "";
        for (int i = 0; i < args.length; i++) {
            niz += args[i] + " ";
        }
        niz = "'" + niz.trim() + "'";

        for (int i = 0; i < niz.length(); i++) {
            try {
                stevila[Integer.parseInt(niz.substring(i, i + 1))]++;
            } catch (Exception e) {
                // Ma tud ce nc ne nardim ;)
            }
        }

        int pojavitve = 0;
        String izpis = "";
        for (int i = 0; i < stevila.length; i++) {
            if (stevila[i] > pojavitve) {
                pojavitve = stevila[i];
                izpis = "-> " + i;
            } else if (stevila[i] == pojavitve) {
                izpis = izpis + " " + i;
            }
        }
        if (pojavitve == 0)
            izpis = "V nizu " + niz + " ni stevk";
        else
            izpis = niz + izpis + " (" + pojavitve + ")";
        System.out.println(izpis);
    }
}