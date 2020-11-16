/*
https://leetcode.com/problems/minimum-height-trees/
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
Return a list of all MHTs' root labels. You can return the answer in any order.
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Example 3:
Input: n = 1, edges = []
Output: [0]

Example 4:
Input: n = 2, edges = [[0,1]]
Output: [0,1]

Constraints:
1 <= n <= 2 * 104
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.
*/


/*
Using topological sorting approach.
Explaination:
https://leetcode.com/problems/minimum-height-trees/

Time: O(V+E)=>O(n + n-1)=>O(n)
Space: O(V+E)=>O(n + n-1)=>O(n)
*/
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n==1){
            return Collections.singletonList(0);
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int i=0;i<edges.length;i++){
            int node1 = edges[i][0], node2 = edges[i][1];
            if(graph.containsKey(node1)){
                graph.get(node1).add(node2);
            } else {
                List<Integer> children = new ArrayList<>();
                children.add(node2);
                graph.put(node1, children);
            }
            if(graph.containsKey(node2)){
                graph.get(node2).add(node1);
            } else {
                List<Integer> children = new ArrayList<>();
                children.add(node1);
                graph.put(node2, children);
            }
            indegree.put(node1, indegree.getOrDefault(node1, 0)+1);
            indegree.put(node2, indegree.getOrDefault(node2, 0)+1);
        }
        
        Queue<Integer> leaves = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: indegree.entrySet()){
            if(entry.getValue()==1){
                leaves.add(entry.getKey());
            }
        }
        int totalNodes = n;
        while(totalNodes>2){
            int leavesSize = leaves.size(); 
            totalNodes -= leavesSize;
            for(int i=0;i<leavesSize;i++){
                int leaf = leaves.poll();
                for(int neighbour: graph.get(leaf)){
                    indegree.put(neighbour, indegree.get(neighbour)-1);
                    if(indegree.get(neighbour)==1){
                        leaves.add(neighbour);
                    }
                }
            }
        }
        List<Integer> result = new LinkedList<>();
        result.addAll(leaves);
        return result;
    }
}