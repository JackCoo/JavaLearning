package Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Bag {
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\Test\\Bag"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int testNum = in.nextInt();
		
		for (int test = 0; test < testNum; test++) {
			int capacity = in.nextInt();
			int goodsKind = in.nextInt();
			int[] volume = new int[goodsKind];
			for (int i = 0; i < goodsKind; i++) {
				volume[i] = in.nextInt();
			}
			int m = in.nextInt();
			int[] likeOrder = new int[m];
			for (int i = 0; i < m; i++) {
				likeOrder[i] = in.nextInt() - 1;
			}
			
			
			System.out.println(process(volume, likeOrder, capacity, 0, new int[goodsKind], 0));
		}
		
	}
	
	public static int process(int[] volume, int[] likeOrder, int capacity, int curIndex, int[] curState, int curVolume) {
		
		// base case
		if (curIndex == volume.length) {
			return curVolume == capacity ? 1 : 0;
		}
		
		int curGoodsLoverRank = -1;
		for (int x = 0; x < likeOrder.length; x++) {
			if (curIndex == likeOrder[x]) {
				curGoodsLoverRank = x;
				break;
			}
		}
		
		int kMin = 0;
		int kMax = (capacity - curVolume) / volume[curIndex];
		if (curGoodsLoverRank == -1 || curGoodsLoverRank == 0) {
			
		} else {
			for (int y = 1; y <= curGoodsLoverRank; y++) {
				int preferGoodsIndex = likeOrder[curGoodsLoverRank - y];
				if (preferGoodsIndex < curIndex) {
					kMax = Math.min(curState[preferGoodsIndex] - 1, kMax);
					break;
				}
			}
		}
		
		int caseCount = 0;
		for (int k = kMin; k <= kMax; k++) {
			curState[curIndex] = k;
			curVolume += k * volume[curIndex];
			caseCount += process(volume, likeOrder, capacity, curIndex + 1, curState, curVolume);
		}
		
		return caseCount % 10000007;
	}
}
