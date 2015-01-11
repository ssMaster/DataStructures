/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.datastructures.applications;

import com.master.datastructures.linkedlists.IntNode;

/**
 *
 * @author simon_000
 */
public class IntNodeApp {
    public static void main(String[] args){
        IntNode head = new IntNode(1, null);
        
        for(int i = 20; i > 10; i--){
            head.addNodeAfter(i);
            System.out.println("New element: " + i);
        }
        
        System.out.println("================");
        
        IntNode cursor;
        for(cursor = head; cursor != null; cursor = cursor.getLink()){
            System.out.println("Data: " + cursor.getData());
        }
        
        System.out.println("============");
        System.out.println("Max: " + IntNode.listSort(head).getData());
    }
}
