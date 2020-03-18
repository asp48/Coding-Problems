/*
https://leetcode.com/problems/word-search-ii

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:
Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
 
Note:
All inputs are consist of lowercase letters a-z.
The values of words are distinct.
*/

/*
For this problem, a trie can help in two ways.
1. It tells us whether our current word is a valid dictionary word
2. It tells us whether our current prefix is part of valid dictionary word. That way, at each character we can make a decision whether to go forward or backtrack and change the direction.

Complexity Analysis: (Needs more research)
M, N = number of rows and colums in board
Y = length of longest word in the dictionary
Time: O(MN*(4^min(Y, M*N))
Space: O(MN*(4^min(Y, M*N))
References:
https://stackoverflow.com/questions/36479640/time-space-complexity-of-depth-first-search
Here branching factor is 4.
Depth = min(Y, M*N)
Time complexity of dfs function: 4^min(Y, M*N)
dfs is applied for each element in the 2D grid.
Total Time complexity: MN*4^min(Y, M*N)
*/
class Solution {
    Set<String> result = null;
    public List<String> findWords(char[][] board, String[] words) {
        // use set to remove duplicates
        result = new HashSet<>();
        // Create a trie data structure
        Trie trie = new Trie();
        // Insert all given dictionary words
        for(String word: words){
            trie.insert(word);
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                //apply dfs from each position in the grid
                dfs(board, i, j, trie.getRootNode(), new StringBuilder());
            }
        }
        // convert set to list as requested
        List<String> resList = new ArrayList<String>(); 
        resList.addAll(result); 
        return resList;
    }
    
    private void dfs(char[][] board, int i, int j, TrieNode node, StringBuilder curSb){
        // character is already visited or not in the trie, return to backtrack
        if(board[i][j] == '#' || !node.containsKey(board[i][j])){
            return;
        }
        char origChar = board[i][j];
        curSb.append(origChar);
        //get the next node from trie
        node = node.get(origChar);
        // if reached end, add current word to result
        if(node.isEnd()){
            result.add(curSb.toString());
            /*
            Here we are using set to remove duplications.
            Other approach is, we can modify the trie itself to remove the current word from dictionary.
            It can be done by changing this node from end node to non-end node.
            node.setIsEnd(false);
            */
        }
        // mark the current position as visited
        board[i][j] = '#';
        // check in 4 possible directions
        if(i<board.length-1){
            dfs(board, i+1, j, node, curSb);
        }
        if(i>0){
            dfs(board, i-1, j, node, curSb);
        }
        if(j<board[0].length-1){
            dfs(board, i, j+1, node, curSb);
        }
        if(j>0){
            dfs(board, i, j-1, node, curSb);
        }
        // restore back
        curSb.setLength(curSb.length()>0?curSb.length()-1:0);
        board[i][j] = origChar;
    }
}

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
    
    public TrieNode getRootNode(){
        return root;
    }
}