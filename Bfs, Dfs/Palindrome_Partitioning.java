
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
*/

/*
Time: O(N^2 + 2^N), N^2 -> to record all the palindromes, 2^N for dfs (worst case, all substrings are palindromes) 
Space: O(N^2)
*/
class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        char[] arr = s.toCharArray();
        // record all possible palindromic substrings
        for(int i=0;i<n;i++){
            int l = i;
            int r = i;
            // expand around center for odd length strings
            while(l>=0 && r<n && arr[l]==arr[r]){
                isPalindrome[l][r] = true;
                l--;
                r++;
            }
            l = i;
            r = i+1;
            if(r==n){
                continue;
            }
            // expand around center for even length strings
            while(l>=0 && r<n && arr[l]==arr[r]){
                isPalindrome[l][r] = true;
                l--;
                r++;
            }
        }
        List<List<String>> results = new ArrayList<>();
        // dfs to generate all possible partition paths
        dfs(s, 0, new ArrayList<>(), isPalindrome, results);
        return results;
    }
    
    public void dfs(String s, int start, List<String> currentPartition, boolean[][] isPalindrome, List<List<String>> results){
        // reached end of the string, add current partition so far to result set
        if(start==s.length()){
            if(!currentPartition.isEmpty()){
                results.add(new ArrayList<>(currentPartition));
            }
            return;
        }
        // explore by choosing different endIndex every time
        for(int i=start; i<s.length(); i++){
            if(isPalindrome[start][i]){
                currentPartition.add(s.substring(start, i+1));
                dfs(s, i+1, currentPartition, isPalindrome, results);
                // by removing last element, we can backtrack
                currentPartition.remove(currentPartition.size()-1);
            }
        }
    }
}