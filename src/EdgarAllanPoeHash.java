import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.*;
import java.util.*;

public class EdgarAllanPoeHash {
    public String getFileContents(FileReader file) throws IOException {
        BufferedReader reader = new BufferedReader(file);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        return stringBuilder.toString();
    }

    public String[] stringToCharArray(String poem) {
        poem = poem.replaceAll(
                "[^a-zA-Z0-9\\s'-]", "");
        poem = poem.replaceAll("\n", " ").replaceAll("\r", "");
        String[] stringArr = poem.split(" ");
        return stringArr;
    }

    public ArrayList<Character> toCharArr(String[] poemArr) {
        ArrayList<Character> charList = new ArrayList<>();
        for (int j = 0; j < poemArr.length; j++) {
            for (int i = 0; i < poemArr[j].length(); i++) {

                char letter = poemArr[j].charAt(i);
                if (i == 0) {
                    charList.add(' ');
                }
                charList.add(letter);
            }
        }
        return charList;
    }

    public ArrayList<Integer> toAsciiArray(ArrayList<Character> charArray) {
        ArrayList<Integer> asciiArray = new ArrayList<>();

        for (char c : charArray) {
            asciiArray.add((int) c);
        }
        return asciiArray;
    }

    public ArrayList<Integer> toHashArray(ArrayList<Integer> asciiArray) {
        ArrayList<Integer> hashArray = new ArrayList<>();
        int hash = 0;
        int C = 123;
        int m = 293;
        for (int i : asciiArray) {
            if (i == 32) {
                hash = 0;
            } else {
                hash = (hash * C + i) % m;
                hashArray.add(hash);
            }

        }
        return hashArray;

    }

    public HashTable<Integer, String> toHashTable(ArrayList<Integer> hashArray, String[] words) {
        Hashtable<Integer, String> poemTable = new Hashtable<>(293);
        for (int i = 0; i < hashArray.size();i++)
        poemTable.put()
    }

}