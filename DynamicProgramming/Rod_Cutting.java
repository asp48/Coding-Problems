public class Rod_Cutting {

	public static int bottomUpDp(int[] val, int N, int i, Integer[][] dp){
		if(i==-1){
			return Integer.MIN_VALUE;
		}
		if(N==0){
			return 0;
		}
		if(dp[i][N]!=null){
			return dp[i][N];
		}
		int include = Integer.MIN_VALUE;
		if(i+1 <= N){
			include = val[i] + bottomUpDp(val, N-i-1, i, dp);
		}
		int exclude = bottomUpDp(val, N, i-1, dp);
		dp[i][N] = Math.max(include, exclude);
		return dp[i][N];
	}
	
	public static int singleArrDp(int[] val, int N){
		int[] dp = new int[N+1];
		for(int i=1;i<=N;i++){
			dp[i] = Integer.MIN_VALUE;
		}
		for(int i=0;i<val.length;i++){
			for(int j=1;j<=W;j++){
				if(i+1<=N && dp[j-i-1]!=Integer.MIN_VALUE){
					dp[j] = Math.max(dp[j], val[i] + dp[j-i-1]);
				}
			}
		}
		return dp[N];
	}
}
