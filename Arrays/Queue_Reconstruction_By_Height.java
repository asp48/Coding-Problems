import java.util.*;

/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

 
Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/
/*
Logic:
1. Sort the array such that
    if heights are same, sort in the increasing order of k.
    if heights are different, sort in the decreasing order of height.
2. Build a new list by inserting each element at its corresponding position k.

Sample I/O
Input:[[7,0],[4,4],[7,1],[5,1],[6,1],[5,0]]

After Step1: [[7,0],[7,1],[6,1],[5,0],[5,1],[4,4]]

Step2:
[7,0]
[7,0], [7,1]
[7,0], [6,1], [7,1]
[5,0], [7,0], [6,1], [7,1]
[5,0], [5,1], [7,0], [6,1], [7,1]
[5,0], [5,1], [7,0], [6,1], [4,4], [7,1]

After Step2: [[5,0],[5,1],[7,0],[6,1],[4,4],[7,1]]

Ouput: [[5,0],[5,1],[7,0],[6,1],[4,4],[7,1]]

Here sorting is needed because, while inserting, greater h elements will be at the start and smaller h elements will be at the end.
Inserting smaller h element in front of greater h does not impact anything, however the reverse is not true.
By the time element e needs to be inserted, all the elements having h greater than this element would have been inserted and the given criteria is thus ensured.
*/
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // Step 1
        Arrays.sort(
            people, 
            (p1, p2)->( (p1[0]==p2[0]) ? p1[1]-p2[1] : p2[0]-p1[0] )
        );
        // Step 2
        List<int[]> result = new LinkedList<>();
        for(int[] p: people){
            result.add(p[1], p);
        }
        return result.toArray(new int[people.length][2]);
    }
}