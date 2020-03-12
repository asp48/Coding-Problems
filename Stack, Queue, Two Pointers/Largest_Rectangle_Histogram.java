import java.util.Stack;

/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
https://leetcode.com/problems/largest-rectangle-in-histogram/

Example:
Input: [2,1,5,6,2,3]
Output: 10

Logic:
First Step: To find the global optimal of the largest rectangle, you have to find the local optimal and compare all the local optimals

Second Step: As the local optimal for each bar, is to utilize the bar as much as possible. Which means you have to find the left and right bars larger than the current selected bar to fully utilize this bar. In other words,
area = height of current bar * ( difference between the position of left minimum and right minimum bar)
Now, problem is reduced to finding minimum bar to the left and right of current bar.

Third Step: Compare all the local optimals that utilize each whole bar

O(n)
*/

/*
Stack to store the position of the left minimum. While doing so, your current processing bar will be the right minimum.
while array has elmement
    If element in the array is greater than top element in the stack
        push the index of elememt to the stack
    Else
        pop elements from stack until the current element is greater than top element. Then push it.
        While popping, find the local max area.

if stack is not empty
    pop elements one by one and calculate local maximum parallelly.

Note:
Idea is to push index of the elements to the stack, so that we can calculate distance between two bars.
After popping, if stack is empty, it means the elements below the popped element are bigger and hence have been popped from the stack already.
Imagine stack as holding indices of increasing subsequence.
At any time, immediately below element in the stack holds the position of the left minimum and if there are none, current element is the minimum and will be at the lowest point of stack. 
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> s = new Stack<>();
        int maxArea = 0, i;
        for(i=0;i<heights.length;i++){
            while(!s.isEmpty() && heights[i]<heights[s.peek()]){
                int top = s.pop();
                if(s.isEmpty()){
                    maxArea = Math.max(maxArea, heights[top]*i);
                } else{
                    maxArea = Math.max(maxArea, heights[top]*(i-s.peek()-1));
                }
            }
            s.push(i);
            
        }
        while(!s.isEmpty()){
            int top = s.pop();
            if(s.isEmpty()){
                maxArea = Math.max(maxArea, heights[top]*i);
            } else{
                maxArea = Math.max(maxArea, heights[top]*(i-s.peek()-1));
            }
        }
        return maxArea;
    }
}

/*
Stack does not perform better compared to array. So inspite of taking more iterations, below solution performs better compared to the above. 

Maintain two arrays left and right to hold the index of left minimum and right minimum correspondingly.
Then find area wrt each element in the array and calculate the maximum area.

*/
class Solution2 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n==0) return 0;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n-1] = n;
        int maxArea = 0;
        for(int i=1;i<n;i++){
            int temp=i-1;
            /*
            Instead of calculating minimum by traversing all elements below it, we can use concept of dp to reuse the existing minimum calculated for each index.
            */
            while(temp>=0 && heights[temp]>=heights[i]){
                temp=left[temp];                
            }
            left[i]=temp;
        }
        for(int i=n-2;i>=0;i--){
            int temp=i+1;
            // Same as above.
            while(temp<n && heights[temp]>=heights[i]){
                temp=right[temp];                
            }
            right[i]=temp;
        }
        for(int i=0;i<n;i++){
            maxArea = Math.max(maxArea, heights[i]*(right[i]-left[i]-1));  
        }
        return maxArea;
    }
}