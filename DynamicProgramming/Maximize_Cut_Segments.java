/*
Given an integer N denoting the Length of a line segment. you need to cut the line segment in such a way that the cut length of a line segment each time is integer either x , y or z. and after performing all cutting operation the total number of cutted segments must be maximum. 


Input
First line of input contains of an integer 'T' denoting number of test cases. First line of each testcase contains N . Second line of each testcase contains 3 space separated integers x , y and z.

Output
For each test case print in a new line an integer corresponding to the answer .


Constraints
1<=t<=70
1<=N,x,y,z<=4000
 

Example

Input

2
4
2 1 1
5
5 3 2


Output
4
2

In first test case, total length is 4, and cut lengths are 2, 1 and 1.  We can make maximum 4 segments each of length 1. In secon test case, we can make two segments of lengths 3 and 2.
*/

/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while(t-->0){
		    int N = s.nextInt();
		    int[] arr = new int[3];
		    arr[0] = s.nextInt();
		    arr[1] = s.nextInt();
		    arr[2] = s.nextInt();
		    int[] dp = new int[N+1];
		    for(int i=1;i<=N;i++){
		        dp[i] = Integer.MIN_VALUE;
		    }
		    for(int i=0;i<3;i++){
		        for(int j=1;j<=N;j++){
		            if(arr[i]<=j){
		                dp[j] = Math.max(dp[j], 1 + dp[j-arr[i]]);
		            } 
		        }
		    }
		    int ans = dp[N];
		    //int ans = topDownDp(arr, 0, N, new Integer[3][N+1]);
		    //int ans = bottomUp(arr, arr.length-1, N, new Integer[3][N+1]);
		    System.out.println(ans);
		}
	}
	
	public static int topDownDp(int[] arr, int i, int tsum, Integer[][] dp){
	    if(i==arr.length){
	        return Integer.MIN_VALUE;
	    }
	    if(tsum==0){
	        return 0;
	    }
	    if(dp[i][tsum]!=null){
	        return dp[i][tsum];
	    }
	    int exclude=Integer.MIN_VALUE, include=Integer.MIN_VALUE;
	    if(arr[i]<=tsum){
	        include = 1 + topDownDp(arr, i, tsum-arr[i], dp);
	    }
	    exclude = topDownDp(arr, i+1, tsum, dp);
	    dp[i][tsum] = Math.max(include, exclude); 
	    return dp[i][tsum];
	}
	
	public static int bottomUp(int[] arr, int i, int tsum, Integer[][] dp){
	    if(i==-1){
	        return Integer.MIN_VALUE;
	    }
	    if(tsum == 0){
	        return 0;
	    }
	    if(dp[i][tsum]!=null){
	        return dp[i][tsum];
	    }
	    int include = Integer.MIN_VALUE, exclude = Integer.MIN_VALUE;
	    if(arr[i]<=tsum){
	        include = 1 + bottomUp(arr, i, tsum - arr[i], dp);
	    }
	    exclude = bottomUp(arr, i-1, tsum, dp);
	    dp[i][tsum] = Math.max(include, exclude);
	    return dp[i][tsum];
	}
}
