import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

public class App {
    public static void main(String[] args) throws Exception {
        // Q1-------------------------
        EdgarAllanPoeHash EAPH = new EdgarAllanPoeHash();
        FileReader file = new FileReader(new File("src\\EdgarAllanPoeBellsB2022groomed.txt"));
        String poem = EAPH.getFileContents(file);
        String[] poemArr = EAPH.stringToWordArray(poem);
        String[] noDupeWords = EAPH.discardDuplicates(poemArr);
        ArrayList<Character> charArray = EAPH.toCharArr(noDupeWords);
        ArrayList<Integer> asciiArray = EAPH.toAsciiArray(charArray);
        ArrayList<Integer> hashArray = EAPH.toHashArray(asciiArray);
        // Q2------------------
        System.out.println("Q2-------------------------");
        ArrayList<Integer> table = EAPH.toHashTable(hashArray, noDupeWords);
        // Q3-------------------------
        System.out.println("Q3-------------------------");
        EAPH.questions(table);
        // Q4----------------------------
        System.out.println("Q4-------------------------");
        Dijkstras d = new Dijkstras();
        int[][] graph = { { 0, 53, 10, 12, 0, 0, 0, 0, 0, 0 },
                { 53, 0, 33, 0, 2, 0, 101, 0, 0, 0 },
                { 10, 33, 0, 9, 30, 18, 0, 0, 0, 0 },
                { 12, 0, 9, 0, 0, 17, 0, 0, 6, 0 },
                { 0, 2, 30, 0, 0, 14, 123, 122, 0, 0 },
                { 0, 0, 18, 17, 14, 0, 0, 137, 7, 0 },
                { 0, 101, 0, 0, 123, 0, 0, 8, 0, 71 },
                { 0, 0, 0, 0, 122, 137, 8, 0, 145, 66 },
                { 0, 0, 0, 6, 0, 7, 0, 145, 0, 212 },
                { 0, 0, 0, 0, 0, 0, 71, 66, 212, 0 } };
        int[] input = d.askForInput();
        d.printPath(d.shortestPath(graph, input[0], input[1]));
    }
}
