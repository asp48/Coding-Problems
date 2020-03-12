/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/
/*
DFS
time: O(2*n)
space: O(n)
2 ms faster than 39%
*/
class Solution {
    public String decodeString(String s) {
        return helper(s, 0, s.length()-1);
    }
    
    public String helper(String s, int i, int j){
        //Base Condition
        if(i==j){
            return "" + s.charAt(i);
        }
        StringBuilder result = new StringBuilder();
        while(i<=j){
            char curr = s.charAt(i);
            //if current character is digit
            if(Character.isDigit(curr)){
                int n=0;
                // loop until u ger the complete number
                while(Character.isDigit(curr)){
                    n = n*10 + (curr-'0');
                    i++;
                    curr = s.charAt(i);
                }

                // skip the index of '['
                i+=1;

                // find the index of ']'
                int e = findEnd(s, i)-1;

                // get the decoded string by recursing for substring from index i to e
                String dstr = helper(s, i, e);

                // repeat the decoded string for n times and add it to result
                for(int k=0;k<n;k++){
                    result.append(dstr);
                }
                // skip the processed substring part
                i=e+2;
            // If not digit, append the character to result
            }else{
                result.append(curr);
                i++;
            }
        }
        return result.toString();
    }
    // find the position of odd right bracket without corresponding left bracket from index i
    public int findEnd(String s, int i){
        int opensqb = 0;
        int k=i;
        for(;k<s.length();k++){
            if(s.charAt(k)=='['){
                // increase square bracket count if you find left bracket
                opensqb++;
            } else if(s.charAt(k)==']'){
                //If there is no corresponding left bracket, return the current position.
                if(opensqb==0){
                    break;
                }
                // Else, decrease square bracket count.
                opensqb--;
            }
        }
        return k;
    }
}

/*
Solution1 can be modified to work with single pass, by removing findEnd function.

time: O(n)
space: O(n)
0ms faster than 100%
*/
class Solution2 {
    // stores position returned by findEnd as in Solution1
    int processed_until=0;
    public String decodeString(String s) {
        processed_until=0;
        return helper(s, 0);
    }
    
    public String helper(String s, int i){
        StringBuilder result = new StringBuilder();
        int repeat=0;
        while(i<s.length()){
            char curr = s.charAt(i);
            //get the complete number
            if(Character.isDigit(curr)){
                int num=0;
                while(Character.isDigit(curr)){
                    num = num*10 + (curr-'0');
                    i++;
                    curr = s.charAt(i);
                }
                repeat = num;
            //recurse to get the decoded version of substring next to '[' until ']'
            } else if(s.charAt(i)=='['){
                i++;
                String dstr = helper(s, i);
                for(int j=0;j<repeat;j++){
                    result.append(dstr);
                }
                // skip the processed substring part
                i = processed_until+1;
            // store the current position and return the result
            } else if(s.charAt(i)==']'){
                processed_until=i;
                break;
            //If it is none of the above characters, add the current character to result 
            } else {
               result.append(curr);
               i++; 
            }
        }
        return result.toString();
    }
}