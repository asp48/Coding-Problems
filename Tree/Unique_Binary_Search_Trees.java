/*
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/
/*
After finding output for few n's, I could observe following pattern.
Ex; [1,2] has two unique BSTs.
case1: If we add 3 at the start, i.e as a root, we can have two unique trees. Fig 2,3 in the explaination.
case2: If we add 3 at the end, we can have two unique trees. Fig 4,5 in the explaination.
case3: If we add 3 in the middle, we can have one unique tree. Fig 1 in the explaination.

Case1 and case2 can be described as: T(n-1) + T(n-1)
For case3;
Consider inserting 4 in [1,2,3]
There are two possibilities, one between 1 and 2, another between 2 and 3.
Possibility1: [1---4----2,3]. One element on the left and two elements on the right. Here 4 will be fixed and other elemenets can have different combinations. ie. [1,4,2,3] and [1,4,3,2]
Possibility2: [1,2----4---3]. Unique BSTs [1,2,4,3] and [2,1,4,3]
So it can be describer as T[k]*T[n-1-k] foeach k which lies between 0 and n-1 exclusively.
Hence all cases combined,
T[n] = T[n-1]*2 + (T[k]*T[n-1-k] for k in range(1,n-2))
O(n^2)
*/
class Solution1 {
    public int numTrees(int n) {
        int[] table = new int[n+2];
        table[0]=0;
        table[1]=1;
        table[2]=2;
        for(int i=3;i<=n;i++){
            table[i] = table[i-1]*2;
            int ans=0;
            for(int k=1;k<(i-1);k++){
                ans+=table[k] * table[i-1-k];
            }
            table[i]+=ans;
        }
        return table[n];
    }
}
/*
Simplified Code.
table[0] is set to 1 for easier calculation.
Logic:
    Take eache element as root
        Multiply the number of unique BSTs possible with the elements on the left and elements on the right
    Answer is the sum of all those products.
*/
class Solution2 {
    public int numTrees(int n) {
        int[] table = new int[n+1];
        table[0]=1;
        table[1]=1;
        for(int i=2;i<=n;i++){
            for(int k=0;k<i;k++){
                table[i] += table[k] * table[i-1-k];
            }
        }
        return table[n];
    }
}