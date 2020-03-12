public class Util{
    public void print2DArray(int[][] matrix, int m, int n){
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void print1DArray(int[] arr){
        for(int num: arr) {
            System.out.print(num + " ");
        }
    }
}