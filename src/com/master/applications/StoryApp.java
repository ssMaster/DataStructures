/*
This program reads some words into bags. A silly story is written using these words.
*/
package com.master.applications;

import com.master.generics.ArrayBag;
import java.util.Scanner;

/**
 *
 * @author simon_000
 */
public class StoryApp {
    private static Scanner stdin = new Scanner(System.in);
    
    public static void main(String[] args){
        final int WORDS_PER_BAG = 4;    // Number of items per bag
        final int MANY_SENTENCES = 3;   // Number of sentences in story
        
        ArrayBag<String> good = new ArrayBag<String>(WORDS_PER_BAG);
        ArrayBag<String> bad = new ArrayBag<String>(WORDS_PER_BAG);
        ArrayBag<String> names = new ArrayBag<String>(WORDS_PER_BAG);
        int line;
        
        // Fill the three bags with items typed by the program's user
        System.out.println("Help me write a story!");
        
        getWords(good, WORDS_PER_BAG, "adjectives that describe a good mood");
        getWords(bad, WORDS_PER_BAG, "adjectives that describe a bad mood");
        getWords(names, WORDS_PER_BAG, "first names");
        
        System.out.println("Thanks for your help! Now, let me work my magic.");
        
        // Use the items to write a silly story
        System.out.println("LIFE");
        System.out.println("by A. Computer\n");
        for(line = 1; line <= MANY_SENTENCES; line++){
            System.out.print((String) names.grab());
            System.out.print(" was feeling ");
            System.out.print((String) bad.grab());
            System.out.print(", yet he/ she was also ");
            System.out.print((String) good.grab());
            System.out.println(".");
        }
        
        System.out.println();
        System.out.println("Life is " + (String) bad.grab() + ".");
        System.out.println("The " + (String) good.grab() + " end.");
    }
    
    public static void getWords(ArrayBag<String> b, int n, String prompt){
        String userInput;
        int i;
        
        System.out.print("Please type " + n + " " + prompt);
        System.out.println(", separated by spaces.");
        System.out.println("Press the <return> key after the final entry.");
        for(i = 1; i <= n; i++){
            userInput = stdin.next();
            b.add(userInput);
        }
        System.out.println();
    }
    
}
