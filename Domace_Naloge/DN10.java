import java.util.TreeSet;

public class DN10 {

    public static void main(String[] args) {
        // args = new String[] { "abcabc", "ababcd" };
        TreeSet<String> podnizi = getVsiPodnizi(args[0]);
        for (int i = 1; i < args.length; i++) {
            podnizi.retainAll(getVsiPodnizi(args[i]));
        }
        String podniz = "";
        for (String string : podnizi) {
            if (string.length() > podniz.length())
                podniz = string;
        }
        System.out.println(podniz);
    }

    public static TreeSet<String> getVsiPodnizi(String niz) {
        TreeSet<String> podnizi = new TreeSet<>();
        String podniz;
        for (int i = 0; i <= niz.length(); i++) {
            podniz = niz.substring(i, niz.length());
            for (int j = 1; j <= podniz.length(); j++) {
                podnizi.add(podniz.substring(0, j));
            }
        }
        return podnizi;
    }
}