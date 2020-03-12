/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

Solution:
stack
Iterate string
    if closing bracket
        if top element not equals equivalenet opening bracket or stack is empty
            return false
        else
            remove top element
    else
        add character to the stack
return true if stack is empty else false;
*/

import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (map.containsKey(c))
            {
                if (stack.empty() || stack.peek() != map.get(c)){
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }
}