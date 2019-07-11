package text;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.InputMismatchException;

final class SongFilter { /** The length of words .*/
    private static final int LENGTH_CONDITION = 3;
/** By default, the value {@value SongFilter#LENGTH_CONDITION}.*/
    private static int lenthCond = LENGTH_CONDITION;
    private static int getLengthCond() {
        return lenthCond;
    }
    private SongFilter() {
    }

    static int countOfWords(final List<String> list) {
        return list.size();
    }

    static void wordFilter(final List<String> list) {
        list.stream()
                .filter(i -> ((i.length() > getLengthCond())
                        && !i.equalsIgnoreCase("fucking")))
                .forEach(System.out::println);
    }

    static void wordByCondition(final List<String> list) {
        List<String> list1 = list.stream()
                .filter(i -> ((i.length() <= getLengthCond())
                        || i.equalsIgnoreCase("fucking")))
                .collect(Collectors.toList());
        System.out.println("Words with length less or equals"
                + " three or bad words:\n" + list1);
        System.out.println("Size:" + list1.size());
    }

    static void freqOfWords(final List<String> list) {
        Map<String, Integer> freq = new TreeMap<>();
        list.forEach(word -> freq.merge(word, 1, Integer::sum));
        System.out.println("How many times a word occur:\n " + freq);
        System.out.println("How many frequency words to print: ");
        try {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            freq.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue()
                            .reversed())
                    .limit(n)
                    .forEach(System.out::println);
        } catch (InputMismatchException ex) {
            System.err.println("You entered incorrect data!");
        }
    }

}

