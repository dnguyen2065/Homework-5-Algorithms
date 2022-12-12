import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

public class App {
    public static void main(String[] args) throws Exception {
        EdgarAllanPoeHash EAPH = new EdgarAllanPoeHash();
        FileReader file = new FileReader(new File("src\\EdgarAllanPoeBellsB2022groomed.txt"));
        String poem = EAPH.getFileContents(file);
        String[] poemArr = EAPH.stringToCharArray(poem);
        String[] noDupeWords = EAPH.discardDuplicates(poemArr);
        ArrayList<Character> charArray = EAPH.toCharArr(noDupeWords);
        ArrayList<Integer> asciiArray = EAPH.toAsciiArray(charArray);
        ArrayList<Integer> hashArray = EAPH.toHashArray(asciiArray);
        Hashtable<Integer, Integer> hashTable = EAPH.toHashTable(hashArray, poemArr);
        System.out.println(hashArray);
        // EAPH.printHashTable(hashTable, hashArray);
    }
}
