
package com.master.datastructures.linkedlists;

/**
 * Node data structure specification Creates a linked lists for any data type
 *
 * @param <E>
 * @author simon_000
 */
public class Node<E> {

    private E data;       // holds the data of a node
    Node<E> link;   // reference to the next node in the linked list

    /**
     * Constructor for the IntNode
     *
     * @param initialData - the initial data of this new node
     * @param initialLink - a reference to the node after this new node (this
     * reference may be null to indicate that there is no node after this new
     * node)
     */
    public Node(E initialData, Node<E> initialLink) {
        data = initialData;
        link = initialLink;
    }

    /**
     * Modification method to add a new node after this node
     *
     * @param element - the data to be placed in the new node
     */
    public void addNodeAfter(E element) {
        link = new Node(element, link);
    }

    public E getData() {
        return (E) data;
    }

    public Node getLink() {
        return link;
    }

    /**
     * Copy a list
     *
     * @param source - the head reference for a linked list that will be copied
     * @param <E>
     * (which may be an empty list where source is null)
     * @return The head reference for the copy
     */
    public static <E> Node listCopy(Node<E> source) {
        Node copyHead;
        Node copyTail;

        // Handle the special case of an empty list
        if (source == null) {
            return null;
        }

        // Make the first part of the newly created list
        copyHead = new Node<>(source.data, null);
        copyTail = copyHead;

        // Make the rest of the nodes for the newly created list
        while (source.link != null) {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }

        // Return the head reference for the new list
        return copyHead;
    }

    public static <E> Node[] listCopyWithTail(Node<E> source) {
        Node<E> copyHead;
        Node<E> copyTail;
        Node<E> answer[] = new Node[2];

        // Handle the special case of an empty list
        if (source == null) {
            return null;
        }

        // Make the first part of the newly created list
        copyHead = new Node<>(source.data, null);
        copyTail = copyHead;

        // Make the rest of the nodes for the newly created list
        while (source.link != null) {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }

        // Return the head and tail references for the new list
        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    /**
     * Compute the number of nodes in a linked list
     *
     * @param head - the head reference for a linked list (which may be an empty
     * list with a null head)
     * @return The number of nodes in the list with the given head
     */
    public static int listLength(Node head) {
        Node cursor;
        int answer;

        answer = 0;
        for (cursor = head; cursor != null; cursor = cursor.link) {
            answer++;
        }

        return answer;
    }

    /**
     * Copy part of a list, providing a head and tail reference for the new copy
     * Note: The return type must now be an array of Objects since generic arrays are forbidden.
     *
     * @param <E>
     * @param start - Reference to the start of a list
     * @param end - Reference to the end of a list
     * @return An array where [0] is the head reference for the copy and the [1]
     * component is a reference to the tail of the copy
     */
    public static <E> Object[] listPart(Node<E> start, Node<E> end) {
        Node<E> copyHead;
        Node<E> copyTail;
        Node<E> cursor;
        Object answer[] = new Object[2];

        // Check for illegal null reference at start or end
        if (start == null) {
            throw new IllegalArgumentException("start cannot be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("end cannot be null");
        }

        // Make the first node for t he newly created list
        copyHead = new Node<>(start.data, null);
        copyTail = copyHead;
        cursor = start;

        // Make the rest of the nodes for the newly created list
        while (cursor != end) {
            cursor = cursor.link;
            if (cursor == null) {
                throw new IllegalArgumentException("end node was not found on the list");
            }
            copyTail.addNodeAfter(cursor.data);
            copyTail = copyTail.link;
        }

        // Return the head and tail reference for the new list
        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    /**
     * Find a node at a specified position in a linked list
     *
     * @param <E>
     * @param head - the head reference for a linked list (which may be an empty
     * list with a null head)
     * @param pos - a node number
     * @return A reference to the node at the specified position in the list or
     * null if there is no such position
     */
    public static <E> Node<E> listPosition(Node<E> head, int pos) {
        Node<E> cursor;

        if (pos <= 0) {
            throw new IllegalArgumentException("pos must be positive");
        }

        cursor = head;
        for (int i = 0; (i < pos) && (cursor != null); i++) {
            cursor = cursor.link;
        }

        return cursor;
    }

    /**
     * Search for a particular piece of data in a linked list
     *
     * @param <E>
     * @param head - The head reference for a linked list (which may be an empty
     * list with a null head)
     * @param target - a piece of data to search for
     * @return A reference to the first node that contains the specified target
     * or null if there is no such node
     */
    public static <E> Node<E> listSearch(Node<E> head, E target) {
        Node<E> cursor;
        
        if(target == null){
            // Search for a node in which the data is the null reference
            for(cursor = head; cursor != null; cursor = cursor.link){
                if(cursor.data == null){
                    return cursor;
                }
            }
        }
        else{
            // Search for a node that contains the non-null target
            for (cursor = head; cursor != null; cursor = cursor.link) {
                if (target.equals(cursor.data)) {
                    return cursor;
                }
            }
        }
        
        // Target not found in list
        return null;
    }

    /**
     * Modification method to remove the node after this node Note: This node
     * must not be the tail node of the list
     */
    public void removeNodeAfter() {
        link = link.link;
    }

    /**
     * Modify the data of this node
     *
     * @param newData - the new data for this node
     */
    public void setData(E newData) {
        data = newData;
    }

    /**
     * Modify the link of this node
     *
     * @param newLink - the new link reference for this node
     */
    public void setLink(Node<E> newLink) {
        link = newLink;
    }
    
    // =========================================================================
    
}
