
package com.master.datastructures.applications;

import com.master.datastructures.linkedlists.Node;
import com.master.datastructures.generics.Lister;

/**
 *
 * @author simon_000
 */
public class ListerTest {
    public static void main(String[] args){
        Node<String> head;      // Head node of a small linked list
        Node<String> middle;    // Second node of the same list
        Node<String> tail;      // Tail node of the same list
        Lister<String> print;   // Used to print the small linked list
        
        // Create a small linked list
        tail = new Node<>("Larry", null);
        middle = new Node<>("Curly", tail);
        head = new Node<>("Moe", middle);
        
        // The list noew has "Moe", "Curly", and "Larry". Print these strings
        print = new Lister<>(head);
        while(print.hasNext()){
            System.out.println(print.next());
        }
    }
    
}
