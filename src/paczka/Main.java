package paczka;

import java.util.Arrays;
import java.util.Random;

public class Main {
	private static int cost = 1;
	public static void main(String[] args) {
		int k = 2;
		int n = 2;
		//int[][] matrix = generateMatrix(k, n, 10);
		int[][] matrix = new int[][]{{1,1},{1,6}};
		topDown(k, n, matrix);
		System.out.println("BottomUp");
		System.out.println(bottomUp(k, n, matrix));
	}
	private static void topDown(int k, int n, int[][] matrix){
		int[][] memo = new int[k][n];
		String[][] seq = new String[k][n];
		for (int[] row: memo){
		    Arrays.fill(row, -1);
		}
		System.out.println("TopDown");
		int topdown = calcTopDown(k, n, matrix, memo,seq);
		for(int i = 0; i < k; i++){
			for(int j = 0; j < n; j++){
				if(seq[i][j] != null){
					System.out.println(seq[i][j]);
				}
			}
		}
		System.out.println(topdown);
	}
	private static int calcTopDown(int k, int n, int[][] input, int[][] memo,String[][] seq) {
		if(memo[k-1][n-1] >= 0) {
			return memo[k-1][n-1];
		}
		int res = input[k -1][n-1];
		String singleCut = null;
		for(int i = 1; i<k;i++) {
			int pom = calcTopDown(i,n,input,memo,seq)+calcTopDown(k-i, n,input,memo,seq)-cost;
			if (pom>res) {
				res = pom;
				singleCut = "|" + i;
			}
		}
		for(int i = 1;i < n; i++){
			int pom = calcTopDown(k, i, input, memo,seq) + calcTopDown(k, n-i, input, memo,seq) - cost;
			if (pom>res) {
				res = pom;
				singleCut = "-" + i;
			}
		}
		seq[k-1][n-1] = singleCut;
		return memo[k-1][n-1] = res;
	}
	private static int[][] generateMatrix(int k, int n, int maxVal){
		int[][] matrix = new int[k][n];
		for(int i = 0; i<k;i++){
			for(int j = 0; j<n;j++){
				Random generator = new Random(); 
				matrix[i][j] = generator.nextInt(maxVal) + 1;
			}
		}
		matrix[0][0] = 1;
		return matrix;
	}
	private static int  bottomUp(int k, int n, int[][] input) {
		String[][] seq = new String[k][n];
		int[][] res = new int[k+1][n+1];
		for(int i = 1; i <= k; i++){
			for(int j = 1; j <= n; j++){
				String singleCut = null;
				int max = input[i-1][j-1];
				for(int l = 0; l < i; l++){
					int pomDebug = input[l][j-1] + res[i-l-1][j] - cost;
					if (pomDebug > max) {
						max = pomDebug;
						singleCut = "|"+(l+1);
					}
					
				}
				for(int m = 0; m < j; m++){
					int pomDebug = input[i-1][m] + res[i][j-m-1] - cost;
					if (pomDebug > max) {
						max = pomDebug;
						singleCut = "-"+(m+1);
					}
				}
				res[i][j] = max;
				input[i-1][j-1] = max;
				seq[i-1][j-1] = singleCut;
			}
		}
		for(int i = 0; i < k; i++){
			for(int j = 0; j < n; j++){
				if(seq[i][j] != null){
					System.out.println(seq[i][j]);
				}
			}
		}
		return res[k][n];
	}
//	private static int  bottomUp(int k, int n, int[][] input) {
//		int[][] res = new int[k+1][n+1];
//		for(int i = 1; i <= k; i++){
//			for(int j = 1; j <= n; j++){
//				int max = input[i-1][j-1];
//				for(int l = 0; l < i; l++){
//					int pomDebug = input[l][j-1] + res[i-l-1][j] - cost;
//					max = Math.max(max,pomDebug);
//				}
//				for(int m = 0; m < j; m++){
//					int pomDebug = input[i-1][m] + res[i][j-m-1] - cost;
//					max = Math.max(max,pomDebug);
//				}
//				res[i][j] = max;
//				input[i-1][j-1] = max;
//			}
//		}
//		return res[k][n];
//	}
//	private static int topDown(int k, int n, int[][] input, int[][] memo) {
//		if(memo[k-1][n-1] >= 0) {
//			return memo[k-1][n-1];
//		}
//		int res = input[k -1][n-1];
//		for(int i = 1; i<k;i++) {
//			res = Math.max(res,topDown(i,n,input,memo)+topDown(k-i, n,input,memo)-cost);
//		}
//		for(int i = 1;i < n; i++){
//			res = Math.max(res, topDown(k, i, input, memo) + topDown(k, n-i, input, memo) - cost);
//		}
//		return memo[k-1][n-1] = res;
//	}
}
