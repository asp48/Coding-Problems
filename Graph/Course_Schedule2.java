package Graph;

import java.util.*;

/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/

//Using DFS, time: O(|V|+|E|) and space: O(|E|+|V|)
class Solution {
    public int[] findOrder(int numCourses, int[][] preq) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<preq.length;i++){
            graph[preq[i][0]].add(preq[i][1]);
        }
        int[] visited = new int[numCourses];
        List<Integer> order = new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            if(visited[i]==0 && hasLoop(graph, visited, i, order)){
                return new int[0];
            }
        }
        int[] result = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            result[i] = order.get(i).intValue();
        }
        return result;
    }
    
    public boolean hasLoop(ArrayList<Integer>[] graph, int[] visited, int course, List<Integer> order){
        visited[course]=1;
        for(int preq: graph[course]){
            if(visited[preq]==1){
                return true;
            } else if(visited[preq]==0){
                if(hasLoop(graph, visited, preq, order)){
                    return true;
                }
            } 
        }
        visited[course]=2;
        order.add(course);
        return false;
    }
}
/*
Kahn’s algorithm for Topological Sorting

Intuition:
The first node in the topological ordering will be the node that doesn't have any incoming edges. Essentially, any node that has an in-degree of 0 can start the topologically sorted order. If there are multiple such nodes, their relative order doesn't matter and they can appear in any order.

Algorithm
1. Initialize a queue, Q to keep a track of all the nodes in the graph with 0 in-degree.
2. Iterate over all the edges in the input and create an adjacency list and also a map of node v/s in-degree.
3. Add all the nodes with 0 in-degree to Q.
4. The following steps are to be done until the Q becomes empty.
    1. Pop a node from the Q. Let's call this node, N.
    2. For all the neighbors of this node, N, reduce their in-degree by 1. If any of the nodes' in-degree reaches 0, add it to the Q.
    3. Add the node N to the list maintaining topologically sorted order.
    4. Continue from step 4.1.

time: O(|V|+|E|), space O(|V|)
*/
class Solution2 {
    public int[] findOrder(int numCourses, int[][] preq) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<preq.length;i++){
            graph[preq[i][1]].add(preq[i][0]);
            indegree[preq[i][0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        int[] result = new int[numCourses];
        int i=0;
        while(!q.isEmpty()){
            //System.out.println(q);
            int dependency = q.remove();
            result[i++] = dependency;
            for(int course : graph[dependency]){
                indegree[course]--;
                if(indegree[course]==0){
                    q.add(course);
                }
            }
        }
        if(i==numCourses){
            return result;
        }
        return new int[0];
    }
}