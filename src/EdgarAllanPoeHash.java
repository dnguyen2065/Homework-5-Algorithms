import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        return stringBuilder.toString();
    }

    public String[] stringToWordArray(String poem) {
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
        wordHashs.remove(228);
        wordHashs.remove(227);
        wordHashs.remove(226);
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

    public ArrayList<Integer> toHashTable(ArrayList<Integer> hashArray, String[] words) {
        ArrayList<Integer> filterList = new ArrayList<>();
        ArrayList<String> wordList = new ArrayList<>();
        for (int i = 0; i < 293; i++) {
            filterList.add(-1);
            wordList.add(" ");
        }
        System.out.println(hashArray.size());
        for (int i = 0; i < hashArray.size(); i++) {
            int j = hashArray.get(i);
            if (filterList.get(i) == -1) {
                filterList.set(j, j);
                wordList.set(j, words[i]);
            } else {
                int temp = 0;
                boolean exit = false;
                while (!exit) {
                    if (j + temp + 1 >= 293) {
                        temp = j * -1;
                    } else {
                        temp++;
                    }
                    if (filterList.get(j + temp) == -1) {
                        filterList.set(j + temp, j);
                        wordList.set(j + temp, words[i]);

                        exit = true;
                    }
                }
            }

        }
        for (int i = 0; i < filterList.size(); i++) {
            System.out.println(i + " " + wordList.get(i) + " " + filterList.get(i));
        }
        return filterList;

    }

    public void questions(ArrayList<Integer> thing) {

        int accum = 0;

        for (int i : thing) {
            if (i != -1) {
                accum++;
            }
        }
        System.out.println("There are " + accum + " non-empty addresses. This makes the load factor "
                + (float) accum / 293.0);
        int max = 0;
        int temp = 0;
        int curr = 0;
        int maxStarts = 0;
        int maxEnd = 0;
        for (int i = 0; i < thing.size(); i++) {
            if (thing.get(i) == -1) {
                curr++;
            } else {

                temp = curr;
                curr = 0;
            }
            if (max < temp) {

                maxEnd = i;
                max = temp;
                maxStarts = maxEnd - max;
            }
        }
        System.out.println("The longest empty area in the table is length " + max + " starting at address " + maxStarts
                + " and ending at address " + maxEnd);
        int max2 = 0;
        int temp2 = 0;
        int curr2 = 0;
        int maxStarts2 = 0;
        int maxEnd2 = 0;
        for (int i = 0; i < thing.size(); i++) {
            if (thing.get(i) != -1) {
                curr2++;
            } else {
                temp2 = curr2;
                curr2 = 0;
            }
            if (max2 < temp2) {
                maxEnd2 = i;
                max2 = temp2;
                maxStarts2 = maxEnd2 - max2;
            }
        }
        System.out
                .println(
                        "The longest cluster area in the table is length " + max2 + " starting at address " + maxStarts2
                                + " and ending at address " + maxEnd2);
        ArrayList<Integer> nonneg1thing = new ArrayList<>();
        for (int i : thing) {
            if (i != -1) {
                nonneg1thing.add(i);
            }
        }
        Integer maxOccurredElement = nonneg1thing.stream()
                .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(nonneg1thing, o1) -
                        Collections.frequency(nonneg1thing, o2)))
                .orElse(null);
        int amount = Collections.frequency(nonneg1thing, maxOccurredElement);
        System.out.println("The hash value with the greatest number of distinct words is " + maxOccurredElement
                + " this many words have this hash value " + amount);

        int endval = 0;
        int actualVal = 0;
        int max3 = 0;
        for (int i = 0; i < thing.size(); i++) {
            if (thing.get(i) != -1) {
                endval = i - thing.get(i);
                if (max3 < endval) {
                    actualVal = thing.get(i);
                    max3 = endval;
                }
            }

        }
        System.out.println("Word with value " + actualVal + " is placed " + max3 + " spaces away from its hash value.");
    }

}