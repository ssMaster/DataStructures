package com.master.linkedlists;

/**
 *
 * @author simon_000
 */
public class IntLinkedSeq implements Cloneable {
    /**
     * Invariant of the IntLinkedSeq class:
     *      1. The elements in the bag are stored in a linked list.
     *      2. The first reference of the list is in the instance variable head.
     *      3. The last reference of the list is in the instance variable tail.
     *      4. The current position reference of the list is in the instance
     *         variable cursor.
     *      5. The position just before the current position is in the instance
     *         variable precursor.
     *      6. The total number of elements in the list is in the instance
     *         variable manyNodes.
     */

    private int manyNodes;  // keeps track of the number of nodes in the list
    private IntNode head;   // reference to the first node
    private IntNode tail;   // reference to the last node
    private IntNode cursor; // refers to the node with the current element (or null)
    private IntNode precursor;  // refers to the node before the current element (or null)

    /**
     * Constructor for the IntLinkedSeq class
     * Initialize an empty sequence
     */
    public IntLinkedSeq() {
        head = null;
        tail = null;
        cursor = null;
        precursor = head;
        manyNodes = 0;
    }

    /**
     * Adds a new element to this sequence before the current element
     *
     * @param element - the new element that is being added
     */
    public void addBefore(int element) {
        if (isCurrent()) {  // if there is a current element
            if (cursor == head) {  // where cursor is the head
                precursor = new IntNode(element, cursor);  // add new node
                head = precursor;  // move head;
            } else {  // if cursor is not the head
                precursor.addNodeAfter(element);  // add new node
                cursor = precursor.getLink();  // move cursor
            }
        }
        else{  // if no current element
            if(head == null){
                head = new IntNode(element, null);
                cursor = head;  // move cursor
                precursor = head;  // move precursor
                tail = head;  // move tail
            }
            else{  // otherwise, add the node after the precursor, before cursor
                precursor.addNodeAfter(element);  // add after precursor
            }
        }

        manyNodes++;  // update the invariant

    }

    /**
     * Adds a new element to this sequence after the current element
     *
     * @param element - the new element that is being added
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for a new node
     */
    public void addAfter(int element) {
        if (isCurrent()) {  // if there is a current element, add this element after
            cursor.addNodeAfter(element);  // create new node
            precursor = cursor;         // move precursor 
            cursor = cursor.getLink();  // move cursor
        }
        else{  // if no current element
            if(tail == null){  // when there is no tail reference, there's no head reference either
                tail = new IntNode(element, null);  // create a new node and point tail to this node
                cursor = tail;      // move cursor to the new node
                precursor = tail;   // move precursor to tail
                head = tail;        // move head to the same node as tail
            }
            else{  // when tail is not null, there is a head so do nothing here
                tail.addNodeAfter(element);  // create new node and point tail to it
                precursor = tail;       // point precursor to original tail
                tail = tail.getLink();  // tail gets updated link to new node
                cursor = tail;          // update cursor to the new node
            }
            manyNodes++;  // update invariant
        }
    }
    
    /**
     * Place the contents of another sequence at the end of this sequence
     * @param addend - A sequence that will be placed at the end of this sequence
     */
    public void addAll(IntLinkedSeq addend){
        IntNode[] copy;
        
        if(addend == null){
            throw new IllegalArgumentException("addend is null");
        }
        
        if(addend.size() > 0){
            copy = IntNode.listCopyWithTail(addend.head);
            tail.getLink().setLink(copy[0]);  // set what used to be the last node to the start of the new list
            copy[1].setLink(null);  // set link of last item to null
            tail.setLink(copy[0]);  // set tail link to the first element of the copy
            manyNodes += addend.size();  // update the invariant
        }
    }

    /**
     * Determine the number of elements in this sequence
     * @return 
     */
    public int size() {
        return manyNodes;
    }

    /**
     * Accessor method to determine whether this sequence has a specified
     * current element that can be retrieved with the getCurrent method
     *
     * @return True if there is a current element, otherwise false
     */
    public boolean isCurrent() {
        if (cursor == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Accessor method to determine the current element of the sequence Note:
     * isCurrent must return true
     *
     * @return The current element of the sequence
     */
    public int getCurrent() {
        if(!isCurrent()){
            throw new IllegalStateException("isCurrent() is null: there is no current element");
        }
        
        // return the integer value of data at cursor
        return cursor.getData();
    }

    /**
     * Move forward, so that the current element is now the next element in this sequence
     * Note: isCurrent() must return true
     */
    public void advance() {
        if (isCurrent()) {
            precursor = cursor;         // move the precursor to where the cursor is
            cursor = cursor.getLink();  // advance the current element forward to the next link
        } else {
            // at end of sequence, do nothing
        }
    }
    
    /**
     * Generate a copy of this sequence
     * @return A copy of this sequence. Subsequent changes to the copy will not affect the original and vice versa.
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        IntLinkedSeq answer;
        
        try{
            answer = (IntLinkedSeq) super.clone();
        }
        catch(CloneNotSupportedException e){
            throw new RuntimeException("This class does not implement Cloneable.");
        }
        
        answer.head = IntNode.listCopy(head);
        
        return answer;
    }
    
    public static IntLinkedSeq concatenation(IntLinkedSeq s1, IntLinkedSeq s2){
        if( (s1 == null) || (s2 == null) ){
            throw new IllegalArgumentException("s1 or s2 is null");
        }
        
        IntLinkedSeq answer = new IntLinkedSeq();
        
        answer.addAll(s1);  // add all from the first sequence
        answer.addAll(s2);  // add all from the second sequence
        
        // Return the union IntLinkedSeq object
        return answer;
    }
    
    /**
     * Remove the current element from this sequence
     * Note: if there is no current element, removeCurrent must not be called
     */
    public void removeCurrent(){
        if(!isCurrent()){
            throw new IllegalStateException("isCurrent() is null");
        }
        
        if(tail == head){  // only one node case
            head = null;
            tail = null;
            precursor = head;
            manyNodes--;  // update invariant
            return;
        }
        
        if(cursor == tail){  // if cursor is at the last node
            tail = precursor;   // remove the last node
            tail.setLink(null);
            cursor = tail;      // move cursor
            precursor = head;   // move precursor to beginning
            while(precursor.getLink() != cursor){
                // search for a link for precursor
                if(precursor.getLink() == null){
                    break;
                }
                precursor = precursor.getLink();
            }
            
            manyNodes--;  // update invariant
            return;
        }
        
        if(cursor == head){  // if cursor is at the first node
            head = head.getLink();
            cursor = head;
            precursor = head;
            manyNodes--;  // update invariant
            return;
        }
        
        // regular case
        cursor = cursor.getLink();
        precursor.setLink(cursor);
        manyNodes--;  // update invariant
    }
    
    /**
     * Set the current element at the front of this sequence
     */
    public void start(){
        if(head == null){  // if there are no elements in the sequence
            cursor = null; // no cursor
        }
        
        cursor = head;  // move cursor to the first node
        precursor = head;
    }

}
