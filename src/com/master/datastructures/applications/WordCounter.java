
package com.master.datastructures.applications;

import java.util.*; // Provides TreeMap, Iterator, and Scanner
import java.io.*;   // Provides FileReader and FileNotFoundException

/**
 * A simple program that shows the use of TreeMaps and Iterators.
 * The program opens and reads a file called words.txt.
 * Each line in this file should consist of one or more English words separated by spaces.
 * The nd of each line must not have any extra spaces after the last word.
 * The program reads the file, and then a table is printed of all words and their counts.
 * Credit goes to Michael Main.
 * 
 * @author simon_000
 */
public class WordCounter {
    public static void main(String[] args){
        TreeMap<String, Integer> frequencyData = new TreeMap<>();
        
        readWordFile(frequencyData);
        printAllCounts(frequencyData);
    }
    
    /**
     * Get counts for each word
     * @param word - A specific word instances
     * @param frequencyData - TreeMap object that holds words and their counts
     * @return The key for the specific word
     */
    private static int getCount(String word, TreeMap<String, Integer> frequencyData){
        if(frequencyData.containsKey(word)){
            // The word has occurred before, so get its count from the map
            return frequencyData.get(word); // auto-unboxed
        }
        else{
            // No occurrences of this word
            return 0;
        }
    }
    
    /**
     * Print counts of specific words in table format
     * @param frequencyData - The number of occurrences for a word
     */
    private static void printAllCounts(TreeMap<String, Integer> frequencyData){
        System.out.println("----------------------------");
        System.out.println("    Occurrences    Word");
        
        for(String word : frequencyData.keySet()){
            System.out.printf("%15d    %s\n", frequencyData.get(word), word);
        }
        
        System.out.println("----------------------------");
    }
    
    /**
     * Reads a text file, gets the current count for a particular word, increments the count and
     * puts the new count back into the TreeMap
     * @param frequencyData TreeMap object containing words and their counts
     */
    private static void readWordFile(TreeMap<String, Integer> frequencyData){
        Scanner wordFile;
        String word;    // A word read from the file
        Integer count;  // The number of occurrences of the word
        
        try{
            // Try to open the words.txt file:
            wordFile = new Scanner(new FileReader("words.txt"));
        }
        catch(FileNotFoundException e){
            // If the file failed, then print an error message and return without counting words
            System.err.println(e);
            return;
        }
        
        while(wordFile.hasNext()){
            // Read the next word and get rid of the end-of-line marker if needed
            word = wordFile.next();
            
            // Get the current count of this word, add 1, and then store the new count
            count = getCount(word, frequencyData) + 1;  // autobox
            frequencyData.put(word, count);
        }
    }
}
