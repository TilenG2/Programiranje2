public class DN02 {
    public static void main(String[] args) {
        String besedilo = "";
        for (String s : args) {
            besedilo = besedilo + " " + s;
        }
        besedilo = besedilo.trim();
        besedilo = "* " + besedilo + " *";
        String zvezdice = "";
        for (int i = 0; i < besedilo.length(); i++) {
            zvezdice += "*";
        }
        System.out.println(zvezdice);
        System.out.println(besedilo);
        System.out.println(zvezdice);
    }
}