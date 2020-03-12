import java.util.*;

/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

The problem is a modified version of 'minimum window substring problem.
Algo:
1. Store the frequency count of each unique character of string p in a hashmap or an ASCII array of length 128: cArray
2. Initialize two pointers l and h to 0 for sliding window.
3. Iterate until h reaches end of string s
    3.1 If frequency of s[h] in cArray is positive, it means there is a match. So increase the matched count. Immediately decrease the frequency count in the cArray to account for the increased match count. Frequency becomes negative if there is no match in string p or repeated more than its number of occurences in string p.
    3.2 Unitl mc==n, increment l, // we have reached the required match count, its time to move l.
        3.2.1 if length of the window == n, add starting index l to the result
        3.2.2 Increment the frequency of cArray[s[h]]. If frequency becomes positive, it means the current character has a match in string p,       so decrement the matched count.
4. return result
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int m = s.length();
        int n = p.length();
        if(n>m || m==0 || n==0){
            return result;
        }
        char[] ca = s.toCharArray();
        
        // Step 1
        int[] cArray = new int[128];
        for(char c: p.toCharArray()){
            cArray[c]++;
        }

        // Step 2
        int l=0,h=0,mc=0; // mc: matched count in the current window
        
        // Step 3
        while(h<m){
            
            // Step 3.1
            if(cArray[ca[h]]>0) mc++;
            cArray[ca[h]]--;
            
            // Step 3.2
            while(mc==n){
                // Step 3.2.1
                if(h-l+1 == n){
                    result.add(l);
                }
                //  Step 3.2.2
                cArray[ca[l]]++;
                if(cArray[ca[l]]>0)mc--;

                l++;
            }
            h++;
        }

        // Step 4
        return result;
    }
}