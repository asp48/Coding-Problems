/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Input: [1,8,6,2,5,4,8,3,7]
Output: 49

Time Complexity: O(n)

The problem is to find a rectangle whose area is maximum. 
Since area = length * width; strategy is to maximize both.
Two pointer approach. If one is getting minimized, try to max other one. 
Intialize pointers to start and end of the given array. Move the pointers towards the side of maximizing area.
Note that moving pointers reduces width and so maximizing height is the only option available. 
*/
class Solution {
    public int maxArea(int[] height) {
        int p1=0, p2=height.length-1;
        int max=0;
        while (p1 < p2){
            int w = p2-p1;
            int l = Math.min(height[p1],height[p2]);
            int area = l*w;
            if (area>max){
                max=area;
            }
            if (height[p1] < height[p2]){
                p1++;
            } else {
                p2--;
            }
        }
        return max;
    }
}