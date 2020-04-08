/*
https://leetcode.com/problems/excel-sheet-column-number/

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701
*/
/*
Finding pattern and converting it into mathematical expression
Take few examples and find the connection.
columNumber = columnNumber + vi * 26^(n-1-i)

Time: O(n) n: string length
Space: O(1)
*/
class Solution {
    public int titleToNumber(String s) {
        int ans = 0, n = s.length();
        for(int i=0;i<n;i++){
            int v = s.charAt(i)-'A'+1;
            ans += v * Math.pow(26, n-1-i); 
        }
        return ans;
    }
}