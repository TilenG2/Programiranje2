package Kvizi;

import java.io.FileNotFoundException;

public class Kviz3 {
    public static void main(String[] args) throws Exception {
        preberiInIzpisi("i1");

    }

    int countWords(String fileName) {
        java.util.Scanner sc;
        int stBesed = 0;
        try {
            sc = new java.util.Scanner(new java.io.File(fileName));
            while (sc.hasNext()) {
                sc.next();
                stBesed++;
            }
        } catch (FileNotFoundException e) {
            return -1;
        } catch (Exception e) {

        }
        return stBesed;
    }

    public static void preberiInIzpisi(String oznaka) {
        try {

            java.util.HashMap<String, String> studenti = new java.util.HashMap<>();
            java.util.Scanner sc = new java.util.Scanner(new java.io.File(oznaka + "-prijave.txt"));
            java.io.File[] files = { new java.io.File(oznaka + "-n1.txt"), new java.io.File(oznaka + "-n2.txt"),
                    new java.io.File(oznaka + "-n3.txt"), new java.io.File(oznaka + "-n4.txt") };
            String[] vr, student;
            while (sc.hasNextLine()) {
                vr = sc.nextLine().split(":");
                if (vr.length > 1)
                    studenti.put(vr[0], vr[1] + ":" + vr[0] + ":" + "VP");
            }
            for (java.io.File file : files) {
                sc = new java.util.Scanner(file);
                while (sc.hasNextLine()) {
                    String neki = sc.nextLine();
                    vr = neki.split(":");
                    student = studenti.get(vr[0]).split(":");
                    try {
                        studenti.put(vr[0], student[0] + ":" + student[1] + ":"
                                + (Integer.parseInt(student[2]) + Integer.parseInt(vr[1])));
                    } catch (Exception e) {
                        studenti.put(vr[0], student[0] + ":" + student[1] + ":" + Integer.parseInt(vr[1]));
                    }
                }
            }
            java.util.ArrayList<String> studentiArr = new java.util.ArrayList<>();
            for (String s : studenti.values()) {
                // System.out.println(s);
                studentiArr.add(s);
            }
            studentiArr.sort(null);
            for (String s : studentiArr) {
                student = s.split(":");
                System.out.printf("%s:%s:%s\n", student[1], student[0], student[2]);
            }
        } catch (Exception e) {
        }
    }
}

class Student {
    int vpisnast;
    String imePriimek;
    int tocke = -1;

    public Student(int vpisnast, String imePriimek) {
        this.imePriimek = imePriimek;
        this.vpisnast = vpisnast;
    }

    public void addTocke(int tocke) {
        if (this.tocke == -1) {
            this.tocke = tocke;
        } else {
            this.tocke += tocke;
        }
    }

    public String getTocke() {
        if (tocke == -1)
            return "VP";
        return "" + tocke;
    }

    public String getImePriimek() {
        return imePriimek;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return vpisnast + ":" + imePriimek + ":" + getTocke();
    }

    public static java.util.Comparator<Student> StudentComparator = new java.util.Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            String StudentName1 = s1.getImePriimek().toUpperCase();
            String StudentName2 = s2.getImePriimek().toUpperCase();
            return StudentName1.compareTo(StudentName2);
        }
    };
}

class Teserakt {
    private double x;

    public Teserakt(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getHiperVolumen() {
        return Math.pow(x, 4);
    }

    public double getPovrsinskiVolumen() {
        return Math.pow(x, 3) * 8;
    }

    public double getStranicnoDiagonalo() {
        return Math.sqrt(2) * x;
    }

    public double getTelesnoDiagonalo() {
        return Math.sqrt(3) * x;
    }

    public double get4DDiagonalo() {
        return 2 * x;
    }

    public String toString() {
        return String.format("[ Tesarakt: x=%.1f ]", x);
    }
}