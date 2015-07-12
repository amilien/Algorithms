package cache;
import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int MAX_CAPACITY;
    private Map<Integer, Node> map = new HashMap<Integer, Node>();
    private Node head, tail;

    static class Node {
        int key, value;
        Node prev, next;
        
        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }
    
    public LRUCache(int capacity) {
        MAX_CAPACITY = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        remove(key, node);
        insert(key, node.value);
        return node.value;
    }
    
    public void set(int key, int value) {
        if (map.containsKey(key))
            remove(key, map.get(key));
        else if (map.size() == MAX_CAPACITY)
            remove(tail.key, tail);
        insert(key, value);
    }
    
    private void remove(int key, Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null)
            prev.next = next;
        else
            tail = next;
        if (next != null)
            next.prev = prev;
        else
            head = prev;
        map.remove(key);
    }
    
    private void insert(int key, int value) {
        Node node = new Node(key, value);
        if (tail == null)
            tail = node;
        if (head != null) {
            head.next = node;
            node.prev = head;
        }
        head = node;
        map.put(key, node);
    }
    
    private void print() {
        Node node = tail;
        System.out.print("List: ");
        while (node != null) {
            System.out.print("(" + node.key + ", " + node.value + ") ");
            node = node.next;
        }
        System.out.print("\nMap: ");
        for (Map.Entry<Integer, Node> entry : map.entrySet())
            System.out.print("(" + entry.getKey() + ", " + entry.getValue().value + ") ");
    }
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.print(cache.get(2) + " ");
        cache.set(2,6);
        System.out.print(cache.get(1) + " ");
        cache.set(1,5);
        cache.set(1,2);
        System.out.print(cache.get(1) + " ");
        System.out.print(cache.get(2) + "\n");
        cache.print();
    }

}
