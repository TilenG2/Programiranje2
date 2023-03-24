public class DN06 {
    public static void main(String[] args) throws Exception {
        String kontrola = "a".repeat(args[0].length());
        while (bsdChecksum(kontrola) != bsdChecksum(args[0]))
            kontrola = povecaj(kontrola);
        System.out.println(kontrola);

    }

    static int bsdChecksum(String niz) {
        int checksum = 0;
        for (int i = 0; i < niz.length(); i++) {
            checksum = (checksum >> 1) + ((checksum & 1) << 15);
            checksum += niz.charAt(i);
            checksum &= 0xffff;
        }
        return checksum;
    }

    static String povecaj(String niz) {
        if (niz.charAt(niz.length() - 1) == 'z')
            return povecaj(niz.substring(0, niz.length() - 1)) + "a";
        return niz.substring(0, niz.length() - 1) + (char) (niz.charAt(niz.length() - 1) + 1);
    }
}