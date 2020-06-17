
/*
https://leetcode.com/problems/flatten-nested-list-iterator/

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
*/

/*
Space: O(level of nesting)
*/
public class NestedIterator implements Iterator<Integer> {
    
    // a stack to maintain the state of traversal as we go down the nesting hierarchy
    // a pair of list and index from where to start
    Stack<Pair<List<NestedInteger>, Integer>> stack = new Stack<>();
    // value to be returned by the iterator
    Integer curVal = null;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        // push the root list
        stack.push(new Pair<>(nestedList, 0));
    }

    @Override
    public Integer next() {
        return curVal;
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            // pop to traverse the parent list
            Pair<List<NestedInteger>, Integer> entry = stack.pop();
            // traverse downside until you find an integer value
            curVal = getInteger(entry.getKey(), entry.getValue());
            // if an integer is found, return true
            if(curVal != null){
                return true;
            }
            // else, keep looping to traverse upside
        }    
        curVal = null;
        return false;
    }
    
    private Integer getInteger(List<NestedInteger> list, int i){
        // corner case
        if (i > list.size()){
            return null;
        }
        Integer val = null;
        for(; i<list.size(); i++) {
            // store the current state, i+1 because ith element is under process and next time start from i+1
            stack.push(new Pair<>(list, i+1));
            NestedInteger nint = list.get(i);
            // return the value if it is an integer
            if (nint.isInteger()){
                val = nint.getInteger();
                break;
            } else {
                // recurse until you find a value
                val = getInteger(nint.getList(), 0);
                // if found, return the value
                if (null != val){
                    break;
                }
            }
            // pop so that we can push new state in the next iteration
            stack.pop();
        }
        return val;
    }
}