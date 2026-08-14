package ArrayOrStrings;

// ms practice
// 1. The Core Concept:
// Design a data structure that supports insert, remove, and getRandom in average O(1) time complexity.
// - insert(val): Inserts an item val if not already present.
// - remove(val): Removes an item val if present.
// - getRandom(): Returns a random element from the current set of elements with equal probability.

// 2. The Strategy: ArrayList + HashMap (Swap-with-Last Trick)
// - Why not just HashMap / HashSet?
//   A HashSet provides O(1) insert and delete, but does NOT support true O(1) random access by index.
// - Why not just ArrayList?
//   An ArrayList provides O(1) random access via index, but removing an arbitrary element takes O(n) due to shifting.
// - Combination:
//   1. ArrayList stores the actual elements to allow O(1) access by random index: `list.get(randIndex)`.
//   2. HashMap stores `element -> index in list` to allow O(1) lookup.
//   3. O(1) Deletion Trick: Instead of deleting from the middle of the ArrayList, swap the element to delete with the LAST element in the list, update the HashMap with the new index of the swapped element, and then remove the last element from the list in O(1) time.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomO1 {

    private final List<Integer> list;
    private final Map<Integer, Integer> map; // value -> index in list
    private final Random random;

    public InsertDeleteGetRandomO1() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        // Add to the end of list and record its index in the map
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int indexToRemove = map.get(val);
        int lastElement = list.get(list.size() - 1);

        // Move the last element to the place of the element to delete
        list.set(indexToRemove, lastElement);
        map.put(lastElement, indexToRemove);

        // Remove the last element from both list and map
        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    // Optional main method for local verification
    public static void main(String[] args) {
        InsertDeleteGetRandomO1 randomizedSet = new InsertDeleteGetRandomO1();

        System.out.println("Insert 1: " + randomizedSet.insert(1)); // Returns true
        System.out.println("Remove 2: " + randomizedSet.remove(2)); // Returns false (not present)
        System.out.println("Insert 2: " + randomizedSet.insert(2)); // Returns true
        System.out.println("GetRandom: " + randomizedSet.getRandom()); // Returns 1 or 2
        System.out.println("Remove 1: " + randomizedSet.remove(1)); // Returns true
        System.out.println("Insert 2: " + randomizedSet.insert(2)); // Returns false (already present)
        System.out.println("GetRandom: " + randomizedSet.getRandom()); // Returns 2
    }
}
