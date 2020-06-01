/*
https://leetcode.com/problems/evaluate-reverse-polish-notation/

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
*/

/*
Using explicit stack
Time: O(N)
Space: O(number of numeric strings)
*/
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Integer num1, num2;
        for(int i=0;i<tokens.length;i++){
            switch(tokens[i]){
                case "+":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1+num2);
                    break;
                case "-":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1-num2);
                    break;
                case "*":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1*num2);
                    break;
                case "/":
                    num2 = stack.pop();
                    num1= stack.pop();
                    stack.push(num1/num2);
                    break;
                default:
                    stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}

/*
Recursion with implicit function stack
Time: O(N)
Space: O(number of numeric strings)
*/
class Solution2 {
    // we need a global cursor to retain its latest value over recursion calls
    int cur = 0;
    public int evalRPN(String[] tokens) {
        this.cur = tokens.length-1;
        if(cur==0) return toInteger(tokens[cur]);
        return evaluate(tokens);
    }
    
    public int evaluate(String[] tokens){
        String op = tokens[cur];
        Integer num2 = toInteger(tokens[--cur]);
        if(null==num2) {
            num2 = evaluate(tokens);
        }
        Integer num1 = toInteger(tokens[--cur]);
        if(null==num1){
            num1 = evaluate(tokens);
        }
        Integer ans = 0;
        switch(op){
            case "+": ans = num1+num2;break;
            case "-": ans = num1-num2;break;
            case "*": ans = num1*num2;break;
            case "/": ans = num1/num2;break;
        }
        return ans;
    }
    
    // Integer.parseInt() turned out to be slower in Leetcode. So implemented from scratch.
    public Integer toInteger(String s){
        if (s.length()==1 && (s.charAt(0) > '9' || s.charAt(0) < '0')){
            return null;
        }
        boolean neg = false;
        int i=0, res=0;
        if(s.charAt(0)=='-'){
            neg = true;
            i++;
        }
        for(;i<s.length();i++){
            res *= 10;
            res += s.charAt(i)-'0';
        }
        return neg ? -res: res;
    }
}