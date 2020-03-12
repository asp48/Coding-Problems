/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

Solution:
exhaustive dfs aproach, without visited array. Move only forward.
recursion.
O(3^n*4^m)  n->digits with 3 chars, m-> digits with 4 chars
function calls can eat up the stack for big inputs.
I don't think there can be further optimization, as all combinations are required.
*/

import java.util.List;

class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> table = new ArrayList<>();
        for(int i=0; i<digits.length(); i++){
            if (map.containsKey(digits.charAt(i))){
                table.add(map.get(digits.charAt(i)));
            }
        }
        List<String> result = new ArrayList<>();
        if (!table.isEmpty()){
            dfs(table, "", -1, result);
        }  
        return result;
    }

    public void dfs(List<String> table, String prefix, int row, List<String> result)
    {
        if (row==table.size()-1){
            result.add(prefix);
            return;
        }
        int start=row+1;
        for(int i=0;i<table.get(start).length();i++){
            dfs(table, prefix+table.get(start).charAt(i), start, result);
        }
    }
}