import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        TreeSet<Integer> neki = new TreeSet<>();
        neki.add(1);
        for (Integer integer : neki) {
            neki.add(++integer);
        }
        System.out.println(neki);
    }
}
