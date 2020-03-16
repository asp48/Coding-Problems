/*
https://leetcode.com/problems/generate-parentheses/

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/

/*
Using backtracking

Approach 1: 
Maintain a count such that adding a open parentheses increments the count and adding a closed parentheses decrements the count. If length of the generated string equals 2*n and count is 0, that means we have generated a valid combination and can be added to the result.

Time and Space: Exponential : 4^N (how?)
*/
class Solution {
    List<String> result = null;
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        helper(new StringBuilder(), n, 0);
        return result;
    }
    
    private void helper(StringBuilder curSb, int n, int c){
        // if reached max length: 2*n
        if(curSb.length()==2*n){
            // if valid combination, add it to the result
            if (c==0) {
                result.add(curSb.toString());
            } // else, cannot proceed further, return to backtrack
            return;
        }
        // add open paranthese only if they are less than the max allowed
        if (c<n){
            curSb.append('(');
            helper(curSb, n, c+1);
            curSb.setLength(curSb.length() - 1);
        }
        // add close parantheses only if there is atleast one open parentheses
        if (c>0){
            curSb.append(')');
            helper(curSb, n, c-1);
            curSb.setLength(curSb.length() - 1);
        }
    }
}
/*
Approach 2:
Maintain a separate count of open parentheses and close parentheses. Recurse only if adding a paranthesis can result in a valid combination. This has overall same complexity as previous solution but has lesser backtracking operations and hence it converges faster. This solution greedily moves towards valid comination, therefore when the current string length reaches max length, it is surely a valid sequence.
*/
class Solution {
    List<String> result = null;
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        helper(new StringBuilder(), n, 0, 0);
        return result;
    }
    
    private void helper(StringBuilder curSb, int n, int ob, int cb){
        // if reached max length: 2*n, it should be a valid combination
        if(curSb.length()==2*n){
            result.add(curSb.toString());
            return;
        }
        // add open paranthese only if they are less than the max allowed
        if (ob<n){
            curSb.append('(');
            helper(curSb, n, ob+1, cb);
            curSb.deleteCharAt(curSb.length() - 1);
        }
        // add close parantheses only if they are less than the current open parentheses 
        if (cb<ob){
            curSb.append(')');
            helper(curSb, n, ob, cb+1);
            curSb.deleteCharAt(curSb.length() - 1);
        }
    }
}
