package com.master.linkedlists;

/**
 * A collection of integer numbers
 * Limits:
 *  1) Beyond Int.MAX_VALUE elements, countOccurrences, size and grab are wrong
 *  2) The slow linear algorithms of this class cause large bags to have poor
 *     performance
 * 
 * @author simon_000
 */
public class IntLinkedBag {
    
    /**
     * Initialize an empty bag
     */
    public IntLinkedBag(){
        
    }
    
    /**
     * Add a new element to this bag
     * @param element - the new element that is being added
     */
    public void add(int element){
        
    }
    
    /**
     * Add the contents of another bag to this bag
     * @param addend - a bag whose contents will be added to this bag
     */
    public void addAll(IntLinkedBag addend){
        
        // Check for illegal null reference at addend
        if(addend == null){
            throw new NullPointerException("addend is null");
        }
        
    }
    
    /**
     * Add a variable number of new elements to this bag.
     * If these new elements would take this bag beyond its current capacity,
     * then the capacity is increased before adding the new elements.
     * @param elements - a variable number of new elements that are all being
     *                   added
     */
    public void addMany(int... elements){
        
    }
    
    /**
     * Generate a copy of this bag
     * @return A copy of this bag. Subsequent changes to the copy will not
     *         affect the original, nor vice versa. The return value must be
     *         typecase to an IntLinkedBag before it is used.
     */
    public IntLinkedBag clone(){
        IntLinkedBag answer;
        
        try{
            answer = (IntLinkedBag) super.clone();
        }
        catch(CloneNotSupportedException e){
            
        }
        
        return answer;
    }
    
    /**
     * Accessor method to count the number of occurrences of a particular
     * element in this bag.
     * @param target - the element that needs to be counted
     * @return The number of times that target occurs in this bag
     */
    public int countOccurrences(int target){
        return 0;
    }
    
    /**
     * Accessor method to retrieve a random element from this bag
     * Note: This bag cannot be empty
     * @return A randomly selected element from this bag
     */
    public int grab(){
        return 0;
    }
    
    /**
     * Remove one copy of a specified element from this bag
     * @param target - the element to remove from this bag
     * @return True if the element has been removed, else false
     */
    public boolean remove(int target){
        
    }
    
    /**
     * Accessor method to determine the number of elements in this bag
     * @return The number of elements in this bag
     */
    public int size(){
        return 0;
    }
    
    /**
     * Create a new bag that contains all the elements from the two other bags
     * @param b1 - the first of two bags
     * @param b2 - the second of two bags
     * @return A new bag that is the union of b1 and b2
     */
    public static IntLinkedBag union(IntLinkedBag b1, IntLinkedBag b2){
        
        // Check for null reference in b1 and b2
        if(b1 == null){
            throw new NullPointerException("b1 is null");
        }
        if(b2 == null){
            throw new NullPointerException("b2 is null");
        }
    }
}
