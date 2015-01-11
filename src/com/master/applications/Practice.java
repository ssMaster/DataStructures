/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.applications;

import com.master.linkedlists.IntNode;

/**
 *
 * @author simon_000
 */
public class Practice {
    public static void main(String[] args){
        IntNode head = new IntNode(3, null);
        IntNode tail = head;
        IntNode cursor = head;
        
        
        
        tail.addNodeAfter(5);
        tail = tail.getLink();
        tail.addNodeAfter(2);
        tail = tail.getLink();
        tail.addNodeAfter(7);
        tail = tail.getLink();
        
        cursor = head;  // set cursor back to the beginning
        
        //System.out.println("head: " + IntNode.listLength(head));
        
        // list should now be: 3, 5, 3, 7, null
        for(int i = 0; i < IntNode.listLength(head); i++){
            System.out.println(i + " is: " + cursor.getData());
            cursor = cursor.getLink();
        }
        
        System.out.println("\n===============\n");
        
        // =================================================
        
        tail.setLink(head); // set the link of tail to be its own head
                            // this causes the list to be a circular link
        
        cursor = head;  // set cursor back to head
        for(int i = 0; i < 10; i++){
            System.out.println(i + " is: " + cursor.getData());
            cursor = cursor.getLink();
        }
        
        // =================================================
        
        System.out.println("\n===============\n");
        
        cursor = head;  // set cursor back to head
        for(int i = 0; i < 5; i++){
            System.out.println(i + " is: " + cursor.getData());
            cursor = cursor.getLink();
        }
    }
    
}
