import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        vBin("1025");
    }

    public static void vBin(String stev) {
        String orig = stev;
        String bin = "";
        while (!(stev.equals("1") || stev.equals("0"))) {
            switch (stev.charAt(stev.length() - 1)) {
                case '1':
                case '3':
                case '5':
                case '7':
                case '9':
                    bin = "1" + bin;
                    break;
                default:
                    bin = "0" + bin;
                    break;
            }
            stev = polovica(stev);
        }
        bin = stev + bin;
        int start = 0;
        int count = 0;
        while (bin.indexOf("1", start) != -1) {
            start = bin.indexOf("1", start) + 1;
            count++;
        }
        System.out.printf("%s(10) = %s(2) -> stevilo bitov: %d\n", orig, bin, count);
    }

    // Ta metoda ni dew programe je ze napisana (ignore this)
    public static String polovica(String stev) {
        int neki = Integer.parseInt(stev);
        return neki / 2 + "";
    }
}
