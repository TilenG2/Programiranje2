public class DN04 {
    public static void main(String[] args) throws Exception {
        // args = new String[] { "010000010100001001000011" };

        String message = toAsci(args[0]);
        System.out.println(message);
    }

    public static String toAsci(String bin) {
        StringBuffer sb = new StringBuffer(bin);
        for (int i = sb.length(); i > 0; i = i - 8) {
            sb.insert(i, " ");
        }
        String[] chars = sb.toString().trim().split(" ");
        String message = "";
        int pow, stevilo, character;
        for (String string : chars) {
            pow = 0;
            character = 0;
            stevilo = Integer.parseInt(string);
            while (stevilo != 0) {
                character += (stevilo % 10) * Math.pow(2, pow);
                stevilo = stevilo / 10;
                pow++;
            }
            message += (char) character;
        }
        return message;
    }
}