/*
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
*/

/*
Intuition:
Use a stack to maintain decreasing order of temperature starting from the end of the array.

Time Complexity: O(2*n), (n) for pushing and (n) for popping elements from the stack
Space Complexity: O(n), all elements in the stack when given sequence is in increasing order
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] result = new int[n];
        Stack<Integer> s = new Stack<>();

        for(int i=n-1;i>=0;i--){
            // Pop until the current element is smaller than the top element of stack.
            while(!s.isEmpty() && T[i]>=T[s.peek()]){
                s.pop();
            }
            // Store the difference between indices in the result array
            result[i] = s.isEmpty()?0:s.peek()-i;
            
            // Push only index into the stack
            s.push(i);
        }
        return result;
    }
}

/*
Motive:
Java stack implementation can be slow at times. If we can analyze how stack is helping us, we can optimize the previous solution to not use stack and thereby achieve constant space.
Intuition:
As we are traversing backward, when finding nearest higher temperature, we can use already calculated values to jump instead of walking. 

Time Complexity: O(2*n) (n) for iterating and (n) for jumping to the nearest higher temperature
Space Complexity: O(1)
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] days = new int[n];
        days[n-1] = 0;

        for(int i=n-2;i>=0;i--){
            int k = i+1;
            // use days array to jump instead of traversing linearly. Converges faster.
            while(T[i]>=T[k] && days[k]!=0){
                   k+=days[k];
            }
            // store the difference
            days[i] = (T[i]<T[k])?k-i:0;
        }
        return days;
    }
}