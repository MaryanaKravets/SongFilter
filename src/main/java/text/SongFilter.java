package text;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

final class SongFilter {
    /**
     * The length of words .
     */
    private static final int LENGTH_CONDITION = 3;

    /**
     * By default, the value {@value SongFilter#LENGTH_CONDITION}.
     */
    List<String> convertFileToListOfWords(File file) throws IOException {
        String s;
        String s1 = "";
        try (
                BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((s = br.readLine()) != null) {
                s1 = s1.concat(s);
            }
        }
        return Arrays
                .asList(s1.split("[[\\p{Punct}&&[^']]\\p{Space}]+"));
    }

    void printFilteredSong(final List<String> list) {
        list.stream()
                .filter(i -> ((i.length() >= LENGTH_CONDITION)
                        && !i.equalsIgnoreCase("fucking")))
                .forEach(System.out::println);
    }

    void printExceptionalWords(final List<String> list) {
        List<String> list1 = list.stream()
                .filter(i -> ((i.length() < LENGTH_CONDITION)
                        || i.equalsIgnoreCase("fucking")))
                .collect(Collectors.toList());
        System.out.println("Words with length less"
                + " three or bad words:\n" + list1);
        System.out.println("Size:" + list1.size());
    }

    void printFrequencyOfWords(final List<String> list) {
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

