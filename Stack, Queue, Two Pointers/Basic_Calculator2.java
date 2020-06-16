
/*
https://leetcode.com/problems/basic-calculator-ii/
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
*/

/*
Using Stack
Time: O(2*N)
Space: O(N)
*/
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + c-'0';
            }
            if((!Character.isDigit(c) && c!=' ') || i==s.length()-1) {
                switch(sign){
                    case '+':stack.push(num);break;
                    case '-':stack.push(-num);break;
                    case '*':stack.push(stack.pop()*num);break;
                    case '/':stack.push(stack.pop()/num);break;
                }
                sign = c;
                num = 0;
            }
        }
        int ans = 0;
        while(!stack.isEmpty()){
            ans += stack.pop();
        }
        return ans;
    }
}

/*
Without using stack
https://leetcode.com/problems/basic-calculator-ii/discuss/63088/Explanation-for-Java-O(n)-time-and-O(1)-space-solution
Time: O(N)
Space: O(1)
*/
class Solution2 {
    public int calculate(String s) {
        int cur = 0, pre=0, sign=1, num=0, op=0; 
        
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + c-'0';
                if(i==s.length()-1 || !Character.isDigit(s.charAt(i+1))){
                    cur = (op==0)?num:((op==1)?cur*num:cur/num);
                }
            } else if(c=='*' || c=='/') {
                op=(c=='*')?1:-1;
                num=0;
            } else if(c=='+' || c=='-') {
                pre = pre + sign*cur;
                sign = (c=='+')?1:-1;
                num=0;
                op=0;
            }
        }
        return pre + sign*cur;
    }
}

/*
Using Recursion
Time: O(N)
Space: O(N) function stack
*/
class Solution3 {
    public int calculate(String s) {
        return helper(s.toCharArray(), 0, 1);
    }
    
    public int helper(char[] arr, int i, int sign){
        if(i==arr.length){
            return 0;
        }
        int op=0, num=0, pre=0;
        while(i<arr.length && arr[i]!='+' && arr[i]!='-'){
            char c = arr[i++];
            if(c=='*'){
                op=1;
            } else if(c=='/'){
                op=-1;
            } else if(c==' '){
                continue;
            } else {
                num = c-'0';
                while(i<arr.length && Character.isDigit(arr[i])){
                    num = num*10 + arr[i]-'0';
                    i++;
                }
                pre = (op==0)?num:((op==1)?pre*num:pre/num);
                pre *= sign;
                sign = 1;
            }
        }
        if(i==arr.length){
            return pre;
        } else {
            sign = (arr[i]=='+')?1:-1;
            return pre + helper(arr, i+1, sign);
        }
    }
}