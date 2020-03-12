/*
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Solution:
Backtracking
*/
class Solution1 {
    public int minDistance(String word1, String word2) {
        return getNumberOfEdits(word1, word2, 0, 0, word1.length(), word2.length(), 0);
    }
    
    public int getNumberOfEdits(String s1, String s2, int i, int j, int m, int n, int count){
        System.out.println("before while" + i + " " + j + " " + count);
        if(i>n || j>n){
            return count;
        }
        while(i < m && j < n && s1.charAt(i) == s2.charAt(j)){
            i++;
            j++;
        }
        System.out.println("after while" + i + " " + j + "\n");
        if(i==m && j<n) {
            count+= n-i;
            return count;
        }
        if(j==n && i<m) {
            count+= m-i;
            return count;
        }
        count+=1;
        return min(getNumberOfEdits(s1, s2, i+1, j+1, m, n, count),
                        getNumberOfEdits(s1, s2, i+1, j, m, n, count),
                        getNumberOfEdits(s1, s2, i, j+1, m, n, count)
                       );
    }
    
    public int min(int a, int b, int c){
        return Math.min(Math.min(a,b), c);
    }
}

/*
Backtracking with memorization
*/
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] table = new int[m][n];
        return getNumberOfEdits(word1, word2, 0, 0, m, n, table);
    }
    
    public int getNumberOfEdits(String s1, String s2, int i, int j, int m, int n, int[][] table){
        //System.out.println("before: " + i + " " + j);
        if(i==m){
            return n-j;
        }
        if(j==n){
            return m-i;
        }
        if (table[i][j] == 0){
            if(s1.charAt(i) == s2.charAt(j)){
                table[i][j] = getNumberOfEdits(s1, s2, i+1, j+1, m, n, table); // no edit
            } else {
                int edits = min(getNumberOfEdits(s1, s2, i+1, j+1, m, n, table), //replace
                                getNumberOfEdits(s1, s2, i+1, j, m, n, table), // delete
                                getNumberOfEdits(s1, s2, i, j+1, m, n, table) //insert
                               );
                table[i][j]=1+edits;
            }
        }
        //System.out.println("Returning2: " + table[i][j]);
        return table[i][j];
    }
    
    public int min(int a, int b, int c){
        return Math.min(Math.min(a,b), c);
    }
}

/*
dp
O(m*n)
*/
class Solution3 {
    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] table = new int[m+1][n+1];
        char[] ca1 = s1.toCharArray();
        char[] ca2 = s2.toCharArray();
        for(int j=0;j<n;j++){
            table[m][j] = n-j;
        }
        for(int i=0;i<m;i++){
            table[i][n] = m-i;
        }
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(ca1[i]==ca2[j]){
                    table[i][j] = table[i+1][j+1];
                }else {
                    table[i][j] = 1 + min(table[i+1][j+1],table[i+1][j],table[i][j+1]);
                }
            }
        }
        return table[0][0];
    }
    
    public int min(int a, int b, int c){
        return (a<b)? ((a<c)?a:c) : ((b<c)?b:c);
    }
}