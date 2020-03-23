/*
https://leetcode.com/problems/count-primes/

Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
*/

/*
Brute Force (Time limit Exceeded)
*/
class Solution {
    public int countPrimes(int n) {
        if(n<=2){
            return 0;
        }
        int count = 1;
        for(int i=3;i<n;i++){
            boolean isPrime = true;
            for(int j=2;j<=Math.sqrt(i);j++){
                if(i%j == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                count++;
            }
        }
        return count;
    }
}
/*
Sieve of Eratosthenes
43 ms, faster than 27.00%

Time complexity : O(n*log(log(n))) (How?)
Space Complexity: O(n)
*/
class Solution {
    public int countPrimes(int n) {
        Boolean[] prime = new Boolean[n];
        Arrays.fill(prime, true);
        for(int p=2; p*p<n; p++){
            if(prime[p]){
                for(int i=p*p;i<n;i+=p){
                    prime[i] = false;
                }
            }
        }
        int count = 0;
        for(int i=2;i<n;i++){
            if(prime[i]){
                count++;
            }
        }
        return count;
    }
}
/*
Using primitive boolean array
11 ms, faster than 94.42%
*/
class Solution {
    public int countPrimes(int n) {
        boolean[] composite = new boolean[n];
        // Count stores the number of non-primes less than n. Since 0 and 1 are non-prime, initialize the count to 2.
        int count = 2;
        for(int p=2; p*p<n; p++){
            if(!composite[p]){
                for(int i=p*p;i<n;i+=p){
                    // if already marked as composite, do not increment the count
                    count = composite[i]? count: count+1;
                    composite[i] = true;
                }
            }
        }
        return n>count? n-count: 0;
    }
}