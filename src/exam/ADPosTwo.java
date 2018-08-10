package exam;

import java.util.Scanner;

public class ADPosTwo {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String[] inputArray = input.split(" ");
		int N = Integer.parseInt(inputArray[inputArray.length - 1]);
		int[] housePosArray = new int[inputArray.length - 1];
		for (int i = 0; i < housePosArray.length; i++) {
			housePosArray[i] = Integer.parseInt(inputArray[i]);
		}
		in.close();
		process(housePosArray, N);
	}
	
	private static void process(int[] housePosArray, int N) {
		int[][] dp = new int[housePosArray.length + 1][housePosArray.length + 1];
		int[][] distance = new int[housePosArray.length + 1][housePosArray.length + 1];
		int[] a = new int[housePosArray.length + 1];

		for (int i = 1; i <= housePosArray.length; i++)
			a[i] = housePosArray[i - 1];
		for (int i = 1; i <= housePosArray.length; i++)
			for (int j = i + 1; j <= housePosArray.length; j++) {
				distance[i][j] = distance[i][j - 1] + a[j] - a[(i + j) / 2];
			}
		for (int i = 1; i <= housePosArray.length; i++) {
			dp[i][i] = 0;
			dp[i][1] = distance[1][i];
		}
		for (int j = 2; j <= N; j++) {
			for (int i = j + 1; i <= housePosArray.length; i++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = j - 1; k < i; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + distance[k + 1][i]);
				}
			}
		}
		System.out.println(dp[housePosArray.length][N]);
	}
	
	
	
}
