import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        EdgarAllanPoeHash EAPH = new EdgarAllanPoeHash();
        FileReader file = new FileReader(new File("src\\EdgarAllanPoeBellsB2022groomed.txt"));
        String poem = EAPH.getFileContents(file);
        String[] poemArr = EAPH.stringToCharArray(poem);
        ArrayList<Character> charArray = EAPH.toCharArr(poemArr);
        ArrayList<Integer> asciiArray = EAPH.toAsciiArray(charArray);
        ArrayList<Integer> hashArray = EAPH.toHashArray(asciiArray);
        System.out.println(poemArr.length);

    }
}
