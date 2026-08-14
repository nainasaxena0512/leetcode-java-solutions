package linkedlist;

// ms practice
// 1. The Core Concept:
// An LRU Cache is a "smart" memory buffer with a fixed capacity. When the cache is full and you want to add a new item, it must evict (remove) the item that hasn't been used for the longest time.
// Rule 1: When you "get" or "put" an item, it becomes the most recently used.
// Rule 2: When capacity is exceeded, remove the least recently used item.
// Requirement: Both get and put operations must be O(1) (constant time).

// 2. The Strategy: Why Two Data Structures?
// To achieve O(1), a single data structure isn't enough:
// - HashMap: Provides O(1) lookup for the keys.
// - Doubly Linked List: Provides O(1) removal and addition at the head/tail to maintain the "order of use."

// On a get(key): We locate the node via the HashMap. We then 'detach' it from its current position in the Doubly Linked List and move it to the Head. This marks it as recently used.
// On a put(key, value):
// - If the key exists, we update the value and move it to the Head.
// - If it’s a new key, we add it to the Head.
// - If the cache is full, we remove the node at the Tail (the oldest item) and delete its corresponding entry from the HashMap.

import java.util.*;

public class LRUCache {
    // 1. Define a Doubly Linked List Node to store key-value pairs
    // We use a Doubly Linked List so we can remove a node in O(1) time
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node(int k, int v) { this.key = k; this.value = v; }
    }

    private final int capacity;      // Maximum items the cache can hold
    private final Map<Integer, Node> map; // HashMap for O(1) lookups of nodes
    private final Node head;         // Dummy head (Most Recently Used side)
    private final Node tail;         // Dummy tail (Least Recently Used side)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        // Initialize dummy nodes to avoid null checks during add/remove
        head = new Node(0, 0); 
        tail = new Node(0, 0);
        
        // Connect head and tail initially
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // Step 1: Check if the key exists in our HashMap
        if (!map.containsKey(key)) {
            return -1; // Not found
        }
        
        // Step 2: If found, the item is now "Recently Used"
        Node node = map.get(key);
        remove(node); // Pick it up from its current position
        insertToHead(node); // Move it to the front (right after dummy head)
        
        return node.value;
    }

    public void put(int key, int value) {
        // Step 1: If key already exists, remove the old node
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        
        // Step 2: If we are at capacity, evict the Least Recently Used item
        if (map.size() == capacity) {
            // The LRU item is always the one right before the dummy tail
            remove(tail.prev);
        }
        
        // Step 3: Insert the new (or updated) node at the head
        insertToHead(new Node(key, value));
    }

    // Helper: Removes a node from its current position in the Doubly Linked List
    private void remove(Node node) {
        // Remove the key from the HashMap lookup
        map.remove(node.key);
        
        // Re-link the neighbor nodes to bypass the current node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper: Always inserts a node at the "Most Recently Used" position (after dummy head)
    private void insertToHead(Node node) {
        // Add the node to the HashMap for future O(1) lookups
        map.put(node.key, node);
        
        // Logic to place node between head and head.next
        Node headNext = head.next;
        
        head.next = node;
        node.prev = head;
        
        node.next = headNext;
        headNext.prev = node;
    }

    // Optional main method for testing
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println("get(1): " + lru.get(1)); // returns 1
        lru.put(3, 3);                               // evicts key 2
        System.out.println("get(2): " + lru.get(2)); // returns -1
        lru.put(4, 4);                               // evicts key 1
        System.out.println("get(1): " + lru.get(1)); // returns -1
        System.out.println("get(3): " + lru.get(3)); // returns 3
        System.out.println("get(4): " + lru.get(4)); // returns 4
    }
}
