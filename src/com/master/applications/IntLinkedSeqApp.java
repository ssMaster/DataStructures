package com.master.applications;

import com.master.linkedlists.IntLinkedSeq;

/**
 *
 * @author simon_000
 */
public class IntLinkedSeqApp {
    public static void main(String[] args){
        IntLinkedSeq seq = new IntLinkedSeq();
        
        System.out.println("Number of nodes in seq: " + seq.size());
        
        // add the first element to the sequence
        // this should now be the current element
        seq.addBefore(55);
        System.out.println("Number of nodes in seq: " + seq.size());
        System.out.println("Data in cursor: " + seq.getCurrent());
        
        System.out.println("===============");
        
        // add a new element
        // new elements always become the new current, so check
        // also check to make sure the new element is added before the previous one
        seq.addBefore(42);
        System.out.println("Number of nodes in seq: " + seq.size());
        System.out.println("Data in cursor: " + seq.getCurrent());
    }
    
}
