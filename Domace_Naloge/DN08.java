import java.io.File;
import java.util.Scanner;

public class DN08 {

    public static void main(String[] args) throws Exception {
        // args = new String[] { "./Domace_Naloge/planeti.txt", "Zemlja" };
        Planet[] planeti = new Planet[8];
        Scanner sc = new Scanner(new File(args[0]));
        String[] niz;
        int index = 0;
        while (sc.hasNextLine()) {
            niz = sc.nextLine().split(":");
            planeti[index] = new Planet(niz[0], Integer.parseInt(niz[1]));
            index++;
        }
        sc.close();

        String[] planeti_args = args[1].split("\\+");
        double radij = 0;
        for (String string : planeti_args) {
            for (Planet planet : planeti) {
                if (planet.getName().equalsIgnoreCase(string)) {
                    radij += planet.povrsina();
                    break;
                }
            }
        }
        radij = Math.round(radij / 1000000);
        System.out.printf("Povrsina planetov \"%s\" je %.0f milijonov km2\n", args[1], radij);
    }
}

class Planet {
    private String name;
    private int radius;

    public Planet(String name, int radius) {
        this.name = name;
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public int getRadius() {
        return radius;
    }

    public double povrsina() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name + " " + radius;
    }
}