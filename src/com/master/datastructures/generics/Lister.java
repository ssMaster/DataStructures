
package com.master.datastructures.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import com.master.datastructures.linkedlists.Node;

/**
 *
 * @author simon_000
 */
public class Lister<E> implements Iterator<E> {
    private Node<E> current;
    
    public Lister(Node<E> head){
        current = head;
    }
    
    public boolean hasNext(){
        return (current != null);
    }
    
    public E next(){
        E answer;
        
        if(!hasNext()) {
            throw new NoSuchElementException("The Lister is empty.");
        }
        
        answer = current.getData();
        current = current.getLink();
        
        return answer;
    }
    
    public void remove(){
        throw new UnsupportedOperationException("Lister has no remove method.");
    }
}
