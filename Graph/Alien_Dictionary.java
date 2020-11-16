/*
https://practice.geeksforgeeks.org/problems/alien-dictionary/1#

Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary. Find the order of characters in the alien language.
Note: Many orders may be possible for a particular test case, thus you may return any valid order.

Input:
The first line of input contains an integer T denoting the no of test cases. Then T test cases follow. Each test case contains an integer N and k denoting the size of the dictionary. Then in the next line are sorted space separated values of the alien dictionary.

Output:
For each test case in a new line output will be 1 if the order of string returned by the function is correct else 0 denoting incorrect string returned.

Your Task:
You don't need to read input or print anything. Your task is to complete the function findOrder() which takes the string array dict[], its size N and the integer K as inputs and returns a string denoting the order of characters in the alien language.

Expected Time Complexity: O(N + K).
Expected Auxiliary Space: O(K).

Constraints:
1 <= T <= 1000
1 <= N <= 300
1 <= k <= 26
1 <= Length of words <= 50

Example:
Input:
2
5 4
baa abcd abca cab cad
3 3
caa aaa aab

Output:
1
1

*/

// { Driver Code Starts
/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import java.math.*;

class GFG {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = Integer.parseInt(sc.next());
		while(t-- > 0)
		{
		    int n = Integer.parseInt(sc.next());
		    int k = Integer.parseInt(sc.next());
		    
		    String[] words = new String[n];
		    
		    for(int i=0;i<n;i++)
		    {
		        words[i] = sc.next();
		    }
		    
		    Solution T = new Solution();
		  //  System.out.println(T.findOrder(words,k));
		    String order = T.findOrder(words,n,k);
		    
		    String temp[] = new String[n];
		    for(int i=0;i<n;i++)
		        temp[i] = words[i];
		    
		    Arrays.sort(temp, new Comparator<String>(){
		    
		      @Override
                public int compare(String a, String b) {
                    int index1 = 0;
                    int index2 = 0;
                    for(int i = 0; i < Math.min(a.length(), b.length()) 
                                        && index1 == index2; i++) {
                        index1 = order.indexOf(a.charAt(i));
                        index2 = order.indexOf(b.charAt(i));
                    }
                
                    if(index1 == index2 && a.length() != b.length()) 
                    {
                        if(a.length() < b.length())
                            return 1;
                        else
                            return 0;
                    }
                
                    if(index1 < index2)
                        return 1;
                    else
                        return 0;
                        
                }
		    });
		    
		    int flag = 1;
		    for(int i=0;i<n;i++)
		    {
		        if(!words[i].equals(temp[i]))
	            {
	                flag = 0;
	                break;
	            }
		    }
		    
		    System.out.println(flag);
		}
	}
	
}
// } Driver Code Ends


//User function Template for Java

/*
dict : array of strings denoting the words in alien langauge
N : Size of the dictionary
K : Number of characters
*/

/*
Using Topological Sort.
Time: O(K + number_of_edges)
Space: O(Number_of_Edges + K)
*/
class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        
        for(int i=0;i<(N-1);i++){
            // take two words at a time
            String word1 = dict[i], word2 = dict[i+1];
            for(int j=0;j<Math.min(word1.length(), word2.length());j++){
                // create edge from word1(j) to word2(j), if they are different.
                // this edge implies word1(j) should come before word2(j) in the final order.
                char parent=word1.charAt(j), child=word2.charAt(j);
                if(parent != child){
                    if(graph.containsKey(parent)){
                        graph.get(parent).add(child);
                    } else {
                        List<Character> children = new ArrayList<>();
                        children.add(child);
                        graph.put(parent, children);
                    }
                    indegree.put(child, indegree.getOrDefault(child,0)+1);
                }
            }
        }
        // Add vertices with 0 indegree to the queue
        Queue<Character> q = new LinkedList<>();
        for(Map.Entry<Character, Integer> entry: indegree.entrySet()){
            if(entry.getValue()==0){
                q.add(entry.getKey());
            }
        }
        // Topologica Sort
        StringBuilder result = new StringBuilder();
        while(!q.isEmpty()){
            Character v = q.poll();
            result.append(v);
            for(Character child: graph.get(v)){
                indegree.put(child, indegree.get(child)-1);
                if(indegree.get(child)==0){
                    q.add(child);
                }
            }
        }
        return result.toString();
    }
}