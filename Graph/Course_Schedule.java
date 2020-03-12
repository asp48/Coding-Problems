package Graph;

import java.util.*;

/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

Solution Logic:
Convert the given edgeList into Adjacency list.
For each vertex in the adjacency list, check if there is a loop by applying dfs.
Loop can be detected by checking if there exists a neighbour vertex which is already visited but partally. 
If a neighbour vertex is completely exlored, that means there is no loop between the current vertex and neighbour vertex. So visited array can be stored as below to distinguish different vertices.
0 -> not visited at all
1 -> partially explored, or ongoing dfs
2 -> completely explored
*/

//time: O(|V|) and space: O(|E|+|V|)
class Solution {
    public boolean canFinish(int numCourses, int[][] preq) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<preq.length;i++){
            graph[preq[i][0]].add(preq[i][1]);
        }
        int[] visited = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            if(visited[i]!=2 && hasLoop(graph, visited, i)){
                return false;
            }
        }
        return true;
    }
    
    public boolean hasLoop(ArrayList<Integer>[] graph, int[] visited, int course){
        visited[course]=1;
        for(int preq: graph[course]){
            if(visited[preq]==1){
                return true;
            } else if(visited[preq]==0){
                if(hasLoop(graph, visited, preq)){
                    return true;
                }
            }
        }
        visited[course]=2;
        return false;
    }
}
//