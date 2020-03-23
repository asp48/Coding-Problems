/*
https://leetcode.com/problems/roman-to-integer/

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

/*
Usually roman numbers will be in the decreasing order of values. But in between, there can be increasing subarray and it should be handled differently.
Ex: IXIMM = 2008
Increasing subarrays: IX(9), IMM(1999)
Final Result = Sum of integer values of all increasing subarrays.
where, integer value of an increasing subarray = Value of the last element - sum of the remaining elements

Time: O(N), where N is the length of string s
Space: O(1)
5 ms, faster than 60.94%
*/
class Solution {
    public int romanToInt(String s) {
        // create a mapping of roman character to its interger value
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char ca[] = s.toCharArray();
        int j = 0, num=0, curVal=0, n = ca.length;
        while(j<n){
            curVal = map.get(ca[j]);
            // Decrease num until you reach the end of an increasing subarray.
            // At the end of this loop, curVal will hold the last element of the increasing subarray.
            while(j<n-1 && curVal < map.get(ca[j+1])){
                num -= curVal;
                j++;
                curVal = map.get(ca[j]);
            }
            // add current value to num
            num += curVal;
            j++;
        }
        return num;
    }
}

/*
Processing the string from right to left.
Shorter solution with same complexity as above.
*/
class Solution2 {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char ca[] = s.toCharArray();
        int num=0, curVal=0, prevVal=0;
        for(int i=ca.length-1; i>=0; i--){
            curVal = map.get(ca[i]);
            if(curVal < prevVal){
                num -= curVal;
            } else {
                num += curVal;
            }
            prevVal = curVal;
        }
        return num;
    }
}