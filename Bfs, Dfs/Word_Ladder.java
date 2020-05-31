
/*
https://leetcode.com/problems/word-ladder/

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

/*
BFS
https://leetcode.com/problems/word-ladder/solution/
365 ms, faster than 24.10%
Time: O(M^2 * N), M = length of each word, N = total number of words in the list 
M*N to generate the hashmap, extra M to generate regEx
M*N for BFS, extra M to generate regEx

Space: O(M^2 * N)
HashMap -> O(M^2 * N)
Visited -> O(M*N)
Queue -> O(M*N)
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        int L = beginWord.length();
        //Using regular expression, store all possible words in a map.
        wordList.forEach(word ->{
           for(int i=0;i<L;i++){
               String regEx = word.substring(0, i) + "*" + word.substring(i+1, L);
               List<String> possibleWords = map.getOrDefault(regEx, new ArrayList<>());
               possibleWords.add(word);
               map.put(regEx, possibleWords);
           } 
        });
        // Queue for BFS
        Queue<String> q = new LinkedList<>();
        // visited array, since it is a bidirectional graph
        Set<String> visited = new HashSet<>();
        q.add(beginWord);
        int level = 1;
        while(!q.isEmpty()){
            int size = q.size();
            // get all children at a depth 'level'
            for(int i=0;i<size;i++){
                String word = q.poll();
                // mark as visited
                visited.add(word);
                // generate all children and add it to queue
                for(int j=0;j<L;j++){
                    String regEx = word.substring(0, j) + "*" + word.substring(j+1, L);
                    if(map.containsKey(regEx)){
                        for(String w: map.get(regEx)) {
                            // reached required end word
                           if(w.equals(endWord)){
                                return level+1;
                           }
                           if(!visited.contains(w)){
                               q.add(w);
                           } 
                        }
                    }
                }
            }
            // increment the depth
            level++;
        }
        return 0;
    }
}

/*
Space optimization and converges faster
BFS
66ms, faster than 54.37%
Time: O(26*M^2 * N) considering extra M for building intermediate word
Space: O(M*N)
*/
class Solution2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int level = 1;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)){
            return 0;
        }
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                char[] carr = q.poll().toCharArray();
                // generate all possible intermediate words
                for(int j=0;j<carr.length;j++){
                    char orig = carr[j];
                    for(int k='a';k<='z';k++){
                        carr[j] = (char)k;
                        String str = String.valueOf(carr);
                        if(!wordSet.contains(str)){
                            continue;
                        }
                        if(str.equals(endWord)){
                            return level+1;
                        }
                        if(!visited.contains(str)){
                            q.add(str);
                            visited.add(str);
                        }
                    }
                    carr[j]=orig;
                }
            }
            level++;
        }
        return 0;
    }
}

/*
Bidirectional BFS
10 ms, faster than 99%
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> forwardSet = new HashSet<>();
        Set<String> backwardSet = new HashSet<>();
        // visited nodes on one end
        forwardSet.add(beginWord);
        // visited nodes at the other end
        backwardSet.add(endWord);
        Set<String> wordSet = new HashSet<>(wordList);
        // remove, to reduce search space
        wordSet.remove(beginWord);
        if(!wordSet.contains(endWord)){
            return 0;
        }
        // remove, to reduce search space
        wordSet.remove(endWord);
        return transform(forwardSet, backwardSet, wordSet);
    }
    
    public int transform(Set<String> forwardSet, Set<String> backwardSet, Set<String> wordSet){
        // nothing to explore either at one end or both ends
        // if one of the list is empty => graph is disconnected
        if(forwardSet.isEmpty() || backwardSet.isEmpty()) return 0;
        // to store all children at the current level
        Set<String> newSet = new HashSet<>();
        // traverse from one end
        for(String str: forwardSet){
            char[] carr = str.toCharArray();
            // generate all possible children
            for(int j=0;j<carr.length;j++){
                char tmp = carr[j];
                for(int k='a';k<='z';k++){
                    carr[j] = (char)k;
                    String cstr = String.valueOf(carr);
                    // if cstr is common, we reached end, return 2 (because length of this transform is 2)
                    if(backwardSet.contains(cstr)){
                        return 2;
                    }
                    // cstr is valid string and is not visited
                    if(wordSet.contains(cstr) && !forwardSet.contains(cstr)){
                        // mark as visited
                        newSet.add(cstr);
                        // remove to reduce search space
                        wordSet.remove(cstr);
                    }
                }
                // revert back
                carr[j] = tmp;
            }
        }
        // store only the possible children at the current level
        forwardSet = newSet;
        // alternate between two ends based on number of nodes to process ==> This is why it is bidirectional bfs
        int result = forwardSet.size()>backwardSet.size()?transform(backwardSet, forwardSet, wordSet): transform(forwardSet, backwardSet, wordSet);
        // in each iteration, we are processing one complete level, therefore increase the returned level by 1
        return result == 0? 0: result+1;
    }
}