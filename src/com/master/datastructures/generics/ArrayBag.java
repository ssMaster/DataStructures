/*
  An ArrayBag<E> is a collection of references to E objects
    
  Limitations:
    1. The capacity of one of these bags can change after it's created, but the maximum capacity is limited
       by the amount of free memory on the machine. The constructors, add, clone, and union will result in
       an OutOfMemoryError when free memory is exhausted.
    2. A bag's capacity cannot exceed the largest integer, 2,147,483,647 (Integer.MAX_VALUE).
       Any attempt to create a larger capacity results in failure due to an arithmetic overflow.
    3. Because of the slow linear algorithms of this class, large bags will have poor performance.
  */
package com.master.datastructures.generics;

/**
 * A generic ArrayBag
 * @author simon_000
 * @param <E> Generic bag return
 */
public class ArrayBag<E> implements Cloneable{
    /*
    Invariant of the ArrayBag<E? generic class:
        1. The number of elements in the bag is in the instance variable manyItems
        2. For an empty bag, we do not care what is stored in any of data;
           for a non-empty bag, the elements in the bag are stored in data[0] through data[manyItems-1], and
           we don't care what's in the rest of data
    */
    private Object data[];  // The bag stores an array of Objects. At run time, each Object will actually
                            // have type E.
    private int manyItems;
    
    /**
     * Initialize an empty bag with an initial capacity of 10.
     * Note that the add method works efficiently (without needing more memory) until the capacity is reached.
     */
    public ArrayBag(){
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = new Object[INITIAL_CAPACITY];
    }
    
    /**
     * Initialize an empty bag with a specified initial capacity.
     * Note that the add method works efficiently (without needing more memory) until this capacity is reached.
     * Note: initialCapacity must be non-negative
     * @param initialCapacity - The initial capacity of this bag
     * @exception IllegalArgumentException
     *      Indicates the initialCapacity is negative
     */
    public ArrayBag(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException("initialCapacity is negative: " + initialCapacity);
        }
        manyItems = 0;
        data = new Object[initialCapacity];
    }
    
    /**
     * Add a new element to this bag. If this new element would take this bag beyond its current capacity, then the 
     * capacity is increased before adding the new element.
     * @param element - the new element that is being added
     */
    public void add(E element){
        if(manyItems == data.length){
            // Double the capacity and add 1 - this works even if manyItems is 0
            // In the case that manyItems*2+1 is beyond Integer.MAX_VALUE, there will be an arithmetic overflow
            ensureCapacity(manyItems * 2 + 1);
        }
        data[manyItems] = element;
        manyItems++;
    }
    
    /**
     * Add the contents of another bag to this bag
     * @param addend 
     */
    public void addAll(ArrayBag<E> addend){
        // If addend is null, then a NullPointerException is thrown
        ensureCapacity(manyItems + addend.manyItems);
        
        System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
        manyItems += addend.manyItems;
    }
    
    /**
     * Add a variable number of new elements to this bag. If these new elements would take this bag beyond its current
     * capacity, then the capacity is increased before adding the new elements.
     * Note: Creating a bag with capacity beyond Integer.MAX_VALUE causes arithmetic overflow
     * @param elements - a variable number of new elements that are being added
     */
    @SuppressWarnings("unchecked")
    public void addMany(E... elements){
        if(manyItems + elements.length > data.length){
            // Ensure twice as much space as we need
            ensureCapacity((manyItems + elements.length) * 2);
        }
        System.arraycopy(elements, 0, data, manyItems, elements.length);
        manyItems += elements.length;
    }
    
    /**
     * Generate a copy of this bag.
     * @return The return value is a copy of this bag. Subsequent changes to the copy will not affect the original.
     */
    @SuppressWarnings("unchecked")
    public ArrayBag<E> clone(){
        ArrayBag<E> answer;
        
        try{
            answer = (ArrayBag<E>) super.clone();
        }
        catch(CloneNotSupportedException e){
            throw new RuntimeException("This class does not implement Cloneable");
        }
        
        answer.data = data.clone();
        return answer;
    }
    
    /**
     * Accessor method to count the number of occurrences of a particular element in this bag
     * @param target - the reference to an E object to be counted
     * @return The number of times that target occurs in this bag
     *         If target is non-null, then the occurrences are found using the target.equals method
     */
    public int countOccurrences(E target){
        int answer;
        int index;
        
        answer = 0;
        
        if(target == null){
            // Count how many times null appears in the bag
            for(index = 0; index < manyItems; index++){
                if(data[index] == null){
                    answer++;
                }
            }
        }
        else{
            // Use target.equals to determine how many times the target appears
            for(index = 0; index < manyItems; index++){
                if(target.equals(data[index])){
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    /**
     * Change the current capacity of this bag.
     * @param minimumCapacity - the new capacity for this bag
     */
    public void ensureCapacity(int minimumCapacity){
        Object[] biggerArray;
        
        if(data.length < minimumCapacity){
            biggerArray = new Object[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }
    
    /**
     * Accessor method to determine the current capacity of this bag.
     * The add method works efficiently (without needing more memory) until this capacity is reached.
     * @return The current capacity of this bag
     */
    public int getCapacity(){
        return data.length;
    }
    
    /**
     * Accessor method to retrieve a random element from this bag.
     * @return A randomly selected element form this bag
     */
    public E grab(){
        int i;
        
        if(manyItems == 0){
            throw new IllegalStateException("Bag size is zero.");
        }
        
        i = (int) (Math.random() * manyItems);  // from 0 to manyItems-1
        
        return (E) data[i];
    }
    
    /**
     * Remove one copy of a specified element from this bag.
     * @param target- the element to remove from this bag
     * @return True if target was found in this bag and one copy of target was removed, otherwise this
     *         bag remains unchanged and returns false
     */
    public boolean remove(E target){
        int index;  // The location of target in the data array
        
        // First, set index to the location of target in the data array
        // If target is not in the array, then index will be set equal to manyItems
        if(target == null){
            // Find the first occurrence of the null reference in the bag
            index = 0;
            while((index < manyItems) && (data[index] != null)){
                index++;
            }
        }
        else{
            // Use target.equals to find the first occurrence of the target
            index = 0;
            while((index < manyItems) && (!target.equals(data[index]))){
                index++;
            }
        }
        
        if(index == manyItems){
            return false;   // The target was not found, so nothing is removed
        }
        else{
            // The target was found at data[index]
            manyItems--;
            data[index] = data[manyItems];
            data[manyItems] = null; // The unused array location is set to null to
                                    // allow Java to collect the unused memory
            return true;
        }
    }
    
    /**
     * Accessor method to determine the number of elements in this bag
     * @return The number of elements in this bag
     */
    public int size(){
        return manyItems;
    }
    
    /**
     * Reduce the current capacity of this bag to its size (the number of elements it contains).
     */
    public void trimToSize(){
        Object[] trimmedArray;
        
        if(data.length != manyItems){
            trimmedArray = new Object[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }
    
    /**
     * Create a new bag that contains all the elements from two other bags.
     * Note: An attempt to create a bag with capacity beyond Integer.MAX_VALUE will cause the bag
     *       to fail with an arithmetic overflow.
     * @param <E> Indicates a generic bag return
     * @param b1 - The first bag
     * @param b2 - The second bag
     * @return A new bag that is the union of b1 and b2
     * @exception NullPointerException
     *      Indicates that one of the arguments is null
     */
    public static <E> ArrayBag<E> union(ArrayBag<E> b1, ArrayBag<E> b2){
        // If either b1 or b2 is null, then a NullPointerException is thrown
        if(b1 == null || b2 == null){
            throw new NullPointerException("b1 or b2 is null");
        }
        
        ArrayBag<E> answer = new ArrayBag<E>(b1.getCapacity() + b2.getCapacity());
        
        System.arraycopy(b1.data, 0, answer.data, 0, b1.manyItems);
        System.arraycopy(b2.data, 0, answer.data, b1.manyItems, b2.manyItems);
        answer.manyItems = b1.manyItems + b2.manyItems;
        return answer;
    }
    
}
