package text;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.Arrays;

final class Main {
    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        File file = new File("data.txt");
        try (
                FileWriter fw = new FileWriter(file)) {
            fw.write("You float like a feather \n In a beautiful world"
                    + "\n And I wish I was special \n You're so fucking"
                    + " special \n I want you to notice \n When I'm not around"
                    + "\n You are so fucking special \n I wish I was special");
        }
        SongFilter songFilter = new SongFilter();
        List<String> listOfWords = songFilter.convertFileToListOfWords(file);
        System.out.println("A number of words in song is: "
                + listOfWords.size());
        System.out.println("Song without bad words and all"
                + " words with length more than 3: ");
        songFilter.printFilteredSong(listOfWords);
        songFilter.printExceptionalWords(listOfWords);
        songFilter.printFrequencyOfWords(listOfWords);
    }
}


