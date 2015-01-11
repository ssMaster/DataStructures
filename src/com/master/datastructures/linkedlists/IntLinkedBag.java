/**
 * Invariant of the IntLinkedBag ADT 1. The elements in the bag are stored in a
 * linked list 2. The head reference of the list is stored in the instance
 * variable head 3. The total number of elements in the list is stored in the
 * instance variable manyNodes
 */
package com.master.datastructures.linkedlists;

/**
 * A collection of integer numbers Limits: 1) Beyond Int.MAX_VALUE elements,
 * countOccurrences, size and grab are wrong 2) The slow linear algorithms of
 * this class cause large bags to have poor performance
 *
 * @author simon_000
 */
public class IntLinkedBag {

    private IntNode head;   // Head reference for the list
    private int manyNodes;  // Number of nodes in the list

    /**
     * Initialize an empty bag
     */
    public IntLinkedBag() {
        head = null;
        manyNodes = 0;
    }

    /**
     * Add a new element to this bag
     *
     * @param element - the new element that is being added
     */
    public void add(int element) {
        head = new IntNode(element, head);
        manyNodes++;
    }

    /**
     * Add the contents of another bag to this bag
     *
     * @param addend - a bag whose contents will be added to this bag
     */
    public void addAll(IntLinkedBag addend) {
        IntNode copyInfo[];

        // Check for illegal null reference at addend
        if (addend == null) {
            throw new NullPointerException("addend is null");
        }

        if (addend.manyNodes > 0) {
            copyInfo = IntNode.listCopyWithTail(addend.head);
            copyInfo[1].setLink(head);  // Link the tail of the copy to my own head...
            head = copyInfo[0];         // ...and set my own head to the head of the copy.
            manyNodes += addend.manyNodes;
        }
    }

    /**
     * Add a variable number of new elements to this bag. If these new elements
     * would take this bag beyond its current capacity, then the capacity is
     * increased before adding the new elements.
     *
     * @param elements - a variable number of new elements that are all being
     * added
     */
    public void addMany(int... elements) {
        // Activate the ordinary add method for each integer in the elements array
        for (int i : elements) {
            add(i);
        }
    }

    /**
     * Generate a copy of this bag
     *
     * @return A copy of this bag. Subsequent changes to the copy will not
     * affect the original, nor vice versa. The return value must be typecase to
     * an IntLinkedBag before it is used.
     */
    public IntLinkedBag clone() {
        IntLinkedBag answer;

        try {
            answer = (IntLinkedBag) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable.");
        }

        answer.head = IntNode.listCopy(head);   // purpose is to create a new linked list for the clone's head instance variable to refer to
        return answer;
    }

    /**
     * Accessor method to count the number of occurrences of a particular
     * element in this bag.
     *
     * @param target - the element that needs to be counted
     * @return The number of times that target occurs in this bag
     */
    public int countOccurrences(int target) {
        IntNode cursor;
        int answer;

        answer = 0;
        cursor = IntNode.listSearch(head, target);
        while (cursor != null) {
            // Each time that cursor is not null, we have another occurrence of target
            // Add 1 to answer and then move cursor to the next occurrence of the target
            answer++;
            cursor = cursor.getLink();
            cursor = IntNode.listSearch(cursor, target);
        }

        return answer;
    }

    /**
     * Accessor method to retrieve a random element from this bag Note: This bag
     * cannot be empty
     *
     * @return A randomly selected element from this bag
     */
    public int grab() {
        IntNode cursor;
        int i;  // A random value between 1 and the size of the bag

        if (manyNodes == 0) {
            throw new IllegalStateException("Bag size is zero.");
        }

        // Get a random number from 1 to the size of the bag and go to that position
        i = (int) (Math.random() * manyNodes) + 1;
        cursor = IntNode.listPosition(head, i);

        return cursor.getData();
    }

    /**
     * Remove one copy of a specified element from this bag
     *
     * @param target - the element to remove from this bag
     * @return True if the element has been removed, else false
     */
    public boolean remove(int target) {
        IntNode targetNode; // the node that contains the target

        targetNode = IntNode.listSearch(head, target);
        if (targetNode == null) {
            // The target was not found, so nothing is removed
            return false;
        } else {
            // The target was found at targetNode. 
            // Copy the head data to targetNode and then remove the extra copy of the head data
            targetNode.setData(head.getData());
            head = head.getLink();
            manyNodes--;
            return true;
        }
    }

    /**
     * Accessor method to determine the number of elements in this bag
     *
     * @return The number of elements in this bag
     */
    public int size() {
        return manyNodes;
    }

    /**
     * Create a new bag that contains all the elements from the two other bags
     *
     * @param b1 - the first of two bags
     * @param b2 - the second of two bags
     * @return A new bag that is the union of b1 and b2
     */
    public static IntLinkedBag union(IntLinkedBag b1, IntLinkedBag b2) {
        IntLinkedBag answer = new IntLinkedBag();

        // Check for null reference in b1 and b2
        if (b1 == null) {
            throw new NullPointerException("b1 is null");
        }
        if (b2 == null) {
            throw new NullPointerException("b2 is null");
        }

        answer.addAll(b1);
        answer.addAll(b2);
        return answer;
    }
}
