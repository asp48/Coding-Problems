import java.util.Arrays;

/*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
*/

/*
Intuition:
From the figure:
https://leetcode.com/problems/task-scheduler/Figures/621_Task_Scheduler_new.PNG

Idea is to find idle slots in the grid and then add it to total number of tasks to get the required answer.

Algorithm;
1. Maintain an array of number of instances for each unique task.
2. Sort the array in increasing order so that if we traverse backwards from the end, tasks are in the decreasing order of instance count.
    Array can be sorted in descending order to avoid traversing backwards. But Arrays.sort(int[], Collections.reverseOrder()) will not work for primitive datatypes such as int. To overcome this, if we convert our int[26] array to Integer[26], there can be null values and it will throw null pointer exception. So the solution is to provide a custom comparator to sort in descending order
3. In the image, height of the rectangular grid is max_count-1 and width is n+1. 
    Therefore, Initialize max_val = map[25]-1, idle_slots = max_val*n (here n instead of n+1 because column containing max task is skipped)
4. for i in the range of 24 upto map[i]==0 in the decreasing order,
        deduct occupied slots from idle_slots
5. If idle_slots < 0, return total number of tasks, else return idle_slots + total number of tasks
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Step 1
        int[] taskCount = new int[26];
        for(char c: tasks){
            taskCount[c-'A']++;
        }
        // Step 2
        // To sort in descending order, Arrays.sort(taskCount, (n1,n2)->( n1==null ? (n2==null? 0 : 1) : (n2==null? -1 : n2-n1) ));
        Arrays.sort(taskCount);
        // Step 3
        int max_val = taskCount[25]-1;
        int idle_slots = max_val*n;
        // Step 4
        for(int i=24;i>=0 && taskCount[i]>0;i--){
            idle_slots -= Math.min(max_val, taskCount[i]);
        }
        // Step 5
        return idle_slots < 0 ? tasks.length : idle_slots + tasks.length;
    }
}