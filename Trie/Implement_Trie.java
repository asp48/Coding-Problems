/*
https://leetcode.com/problems/implement-trie-prefix-tree/

Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true

Note:
You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
*/

/*
Explaination: https://leetcode.com/problems/implement-trie-prefix-tree/solution/
*/
class TrieNode {
    
    TrieNode links[];
    int maxLinks = 26;
    boolean isEnd = false;
    
    public TrieNode(){
        links = new TrieNode[maxLinks];    
    }
    
    public boolean containsKey(char ch){
        return links[ch-'a'] != null;
    }
    
    public TrieNode get(char ch){
        return links[ch-'a'];
    }
    
    public TrieNode insert(char ch){
        TrieNode node = new TrieNode();
        links[ch-'a'] = node;
        return node;
    }
    
    public boolean isEnd(){
        return isEnd;
    }
    
    public void setIsEnd(boolean isEnd){
        this.isEnd = isEnd;
    }
}

/*
Time Complexity:
Insertion: O(m), where m is the key length
Search Prefix: O(m)

Space Complexity:
Insertion: O(m) when current word does not share any prefix
Search Prefix: O(1)
*/
class Trie {
    TrieNode root = null;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for(char ch: word.toCharArray()){
            if(!node.containsKey(ch)){
                node = node.insert(ch);
            } else {
                node = node.get(ch);
            }
        }
        node.setIsEnd(true);
    }
    
    private TrieNode searchPrefix(String word){
        TrieNode node  = root;
        for(char ch: word.toCharArray()){
            if (node.containsKey(ch)){
                node = node.get(ch);
            } else {
                return null;
            }
        }
        return node;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node!=null && node.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node!=null;
    }
}