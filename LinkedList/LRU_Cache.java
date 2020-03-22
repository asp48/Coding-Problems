/*
https://leetcode.com/problems/lru-cache/

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache(2);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

/*
Use doubly linked list to maintain most recently used n (capacity) values.
Use a HashMap to fetch the target node based on given key in O(1) time.

Time: O(1) for both get and set
Space: O(2*N), (N) for hashmap, (N) for DLL
*/
// DLL node
class ListNode{
    ListNode prev;
    ListNode next;
    int key;
    int val;
    public ListNode(int key, int val){
        this.key = key;
        this.val=val;
    }
}
class LRUCache {
    // mapping of key to corresponding node in the list
    Map<Integer, ListNode> map = new HashMap<>();
    /* 
    *  dummy nodes which point to head and tail of the list, 
    *  help in moving most recently used node to the top/start and removing least recently used node from the list.
    */
    ListNode head = new ListNode(-1, -1);
    ListNode tail = new ListNode(-1, -1);
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity; 
        // join head and tail
        join(head, tail);
    }
    
    public int get(int key) {
        ListNode node = map.get(key);
        if(node != null){
            // move the node to the top as it is recently accessed
            remove(node);
            moveToHead(node);
            return node.val;
        }
        // key not found
        return -1;
    }
    
    public void put(int key, int value) {
        ListNode node  = map.get(key);
        if(node == null){
            // reached max capacity
            if(map.size() == capacity){
                // remove the tail data node from list and map
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            // create a new node and add to the map
            node = new ListNode(key, value);
            map.put(key, node);
        } else {
            // update the existing node
            node.val = value;
            remove(node);
        }
        // move the new/existing node to the top
        moveToHead(node);
    }
    
    // joins two nodes
    private void join(ListNode node1, ListNode node2) {
        node1.next = node2;
        node2.prev = node1;
    }
    
    // removes a node from its location in the list
    private void remove(ListNode node){
        join(node.prev, node.next);
    }
    
    // moves the node to the head of the list
    private void moveToHead(ListNode node){
        join(node, head.next);
        join(head, node);
    }
}
