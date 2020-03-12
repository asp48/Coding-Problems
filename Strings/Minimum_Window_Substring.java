import java.util.*;
import javafx.util.Pair;
/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

O(n)
*/
class Solution1 {
    public String minWindow(String s, String t) {
        int m=s.length();
        int n=t.length();
        if (n > m || m==0 || n==0){
            return "";
        }
        int[] tArray = new int[128];
        char[] ca = s.toCharArray();
        for(char c: t.toCharArray()) tArray[c]++;
        int mc=0; // matched count in a window
        int l=0,h=0; //two pointers for sliding window
        int start=-1, end=0; // to return window string
        while(h<m){
            if(tArray[ca[h]]>0) mc++;
            tArray[ca[h]]--;
            while(mc==n){
                if(start==-1 || h-l+1 < end-start){
                    start = l;
                    end = h+1;
                }
                tArray[ca[l]]++;
                if(tArray[ca[l]]>0) mc--;
                l++;
            }
            h++;
        }
        if(start==-1){
            return "";
        }
        return s.substring(start, end);
    }
}

//solution2

class Solution2 {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()){
            return "";
        }
        Map<Character,Integer> mapT = new HashMap<>(); // indexing of t string
        for(int i=0;i<t.length();i++){
            mapT.put(t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1);
        }
        char[] ca = s.toCharArray();
        int muc = 0; // matched unique char count
        int l=0, h=0; // two pointers for sliding window
        int si=-1; // starting index
        int minLength=Integer.MAX_VALUE; //minLength
        int required_uc=mapT.size();
        
        List<Pair<Integer, Character>> shortS = new ArrayList<>();
        for(int i=0; i<ca.length;i++){
            if(mapT.containsKey(ca[i])){
                shortS.add(new Pair<Integer, Character>(i, ca[i]));
            }
        }
        // sliding window map to maintain unique characters
        Map<Character, Integer> windowMap = new HashMap<>(); 
        while(h<shortS.size()){
            char c = shortS.get(h).getValue();
            if(mapT.containsKey(c)){
                int count = windowMap.getOrDefault(c,0);
                count++;
                windowMap.put(c, count);
                if(count == mapT.get(c).intValue()){
                    muc++;
                }
            }
            while(l<=h && muc == required_uc){
                int start = shortS.get(l).getKey();
                int end = shortS.get(h).getKey();
                if (end-start+1 < minLength){
                    minLength = end-start+1;
                    si=start;
                }
                c = shortS.get(l).getValue();
                if (windowMap.containsKey(c)){
                    int count = windowMap.get(c).intValue();
                    count--;
                    windowMap.put(c, count);
                    if(count < mapT.get(c).intValue()){
                        muc--;
                    }
                }
                l++;
            }
            h++;
        }
        if(si==-1){
            return "";
        }
        return s.substring(si, si+minLength);
    }
}