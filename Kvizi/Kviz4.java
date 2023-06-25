package Kvizi;

public class Kviz4 {
    public static void main(String[] args) throws Exception {
        poisciInIzpisiBarve("/home/tileng/Documents/GitHub/Programiranje2/Kvizi/files/style.css");
    }

    static void poisciInIzpisiBarve(String imeDatoteke) {
        java.util.regex.Pattern MY_PATTERN = java.util.regex.Pattern.compile(".?color: #[0-9A-Fa-f]{6}");
        java.util.regex.Matcher m;
        try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(imeDatoteke))) {
            while (sc.hasNextLine()) {
                m = MY_PATTERN.matcher(sc.nextLine());
                while (m.find()) {
                    if (!m.group(0).contains("-"))
                        hexLong(m.group(0).strip());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void hexLong(String hexString) {
        hexString = hexString.replaceAll("color: #", "");
        int R = Integer.valueOf(hexString.substring(0, 2), 16);
        int G = Integer.valueOf(hexString.substring(2, 4), 16);
        int B = Integer.valueOf(hexString.substring(4, 6), 16);
        float[] hsl = new float[3];
        RgbToHsl(R, G, B, hsl);
        System.out.printf("#%s -> rgb(%d, %d, %d) -> hsl(%.0f, %.0f, %.0f)\n", hexString, R, G, B, hsl[0], hsl[1],
                hsl[2]);
    }

    public static void RgbToHsl(int red, int green, int blue, float hsl[]) {
        float r = (float) red / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;
        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float f;
        if (max == min)
            f = 0;
        else if (max == r && g >= b)
            f = (60 * (g - b)) / (max - min);
        else if (max == r && g < b)
            f = 360 + (60 * (g - b)) / (max - min);
        else if (max == g)
            f = 120 + (60 * (b - r)) / (max - min);
        else if (max == b)
            f = 240F + (60F * (r - g)) / (max - min);
        else
            f = 0;
        float f1 = (max + min) / 2;
        float f2;
        if (f1 != 0 && max != min) {
            if (0 < f1 && f1 <= 0.5) {
                f2 = (max - min) / (max + min);
            } else if (f1 == 0.5) {
                f2 = (max - min) / (2.0F - (max + min));
            } else {
                f2 = 0;
            }
        } else {
            f2 = 0.0F;
        }
        hsl[0] = f;
        hsl[1] = f2 * 100;
        hsl[2] = f1 * 100;
    }

    static void dvojnaNagrada(String igralkeFilename, String igralciFilename) {
        String tmp;
        int dolzina;
        java.util.ArrayList<Object[]> vrstice = new java.util.ArrayList<>();
        Object[] zapis;
        java.util.Scanner sc = null;

        try (java.util.Scanner scF = new java.util.Scanner(new java.io.File(igralkeFilename));
                java.util.Scanner scM = new java.util.Scanner(new java.io.File(igralciFilename))) {
            while (scF.hasNext()) {
                zapis = new Object[5];
                tmp = scF.nextLine().trim();
                sc = new java.util.Scanner(tmp);
                sc.useDelimiter(", ");
                zapis[0] = sc.nextInt(); // indeks
                zapis[1] = sc.nextInt(); // leto
                zapis[2] = sc.nextInt(); // starost
                zapis[3] = sc.next(); // ime_priimek
                zapis[4] = sc.nextLine().substring(2); // naslov
                vrstice.add(zapis);

            }
            dolzina = vrstice.size();
            vrstice.sort(new java.util.Comparator<Object[]>() {
                @Override
                public int compare(Object[] arg0, Object[] arg1) {
                    return ((String) arg0[4]).compareTo((String) arg1[4]);
                }
            });
            while (scM.hasNext()) {
                zapis = new Object[5];
                tmp = scM.nextLine().trim();
                sc = new java.util.Scanner(tmp);
                sc.useDelimiter(", ");
                zapis[0] = sc.nextInt(); // indeks
                zapis[1] = sc.nextInt(); // leto
                zapis[2] = sc.nextInt(); // starost
                zapis[3] = sc.next(); // ime_priimek
                zapis[4] = sc.nextLine().substring(2); // naslov
                vrstice.add(zapis);
            }
            for (int indexF = 0; indexF < dolzina; indexF++) {
                for (int indexM = dolzina + 1; indexM < vrstice.size(); indexM++) {
                    if (vrstice.get(indexF)[4].equals(vrstice.get(indexM)[4])) {
                        System.out.printf("Film: %s, Leto: %d, Igralka: %s, Igralec: %s\n", vrstice.get(indexF)[4],
                                vrstice.get(indexF)[1], vrstice.get(indexF)[3], vrstice.get(indexM)[3]);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}