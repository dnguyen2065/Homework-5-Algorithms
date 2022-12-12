import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
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
        ArrayList<Integer> fullHashArray = new ArrayList<>();
        ArrayList<Integer> wordHashs = new ArrayList<>();
        int hash = 0;
        int C = 123;
        int m = 293;
        for (int i : asciiArray) {
            if (i == 32) {
                hash = 0;
                fullHashArray.add(hash);
            } else {
                hash = (hash * C + i) % m;
                fullHashArray.add(hash);
            }

        }
        for (int i = 0; i < fullHashArray.size(); i++) {
            if (i == 0) {
            } else if (fullHashArray.get(i) == 0) {
                wordHashs.add(fullHashArray.get(i - 1));
            }
        }

        return wordHashs;
    }

    public String[] discardDuplicates(String[] list) {
        ArrayList<String> newList = new ArrayList<>();
        ArrayList<String> finList = new ArrayList<>();

        for (String s : list) {
            newList.add(s);
        }
        for (String i : newList) {
            if (!finList.contains(i)) {
                finList.add(i);
            }
        }
        String[] wordarr = new String[finList.size()];
        for (int i = 0; i < finList.size(); i++) {
            wordarr[i] = finList.get(i);
        }

        return wordarr;
    }

    public Hashtable<Integer, Integer> toHashTable(ArrayList<Integer> hashArray, String[] words) {
        Hashtable<Integer, Integer> hashTable = new Hashtable<>(293);
        for (int i = 1; i < 293; i++) {
            for (int j : hashArray)
                if (i == j) {
                    hashTable.put(i, j);
                }
        }
        return hashTable;

    }

    public void printHashTable(Hashtable<Integer, Integer> hashTable, ArrayList<Integer> hashArray) {
        int intvalue = 0;
        for (int i = 0; i < hashTable.size(); i++) {
            System.out.println(hashTable.get(i));
            if (hashTable.get(i) != null) {
                intvalue = hashTable.get(i);
            }

            if (intvalue == hashArray.get(i)) {
                System.out.println("if entered");
                System.out.println(" " + hashArray.get(i));
            }
        }
    }

}