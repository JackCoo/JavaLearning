package nowcoder.leetcode;

/**
 * 跳跃游戏 - 最少跳跃次数
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps
 * For example:
 * 	Given array A =[2,3,1,1,4]
 * 	The minimum number of jumps to reach the last index is2. (Jump1step from index 0 to 1, then3steps to the last index.)
 * 
 * 贪心策略：
 * 	1.每次跳跃最大步数。----错误
 * 	2.遍历当前最大步数范围内所有位置可到达的下一个位置，选择最远的。
 * 	
 * @author Yanjie
 *
 */
public class JumpGameTwo {

	public int jump(int[] A) {
		int endIndex = A.length - 1;
		int curIndex = 0;
		int step = 0;
		while (curIndex < endIndex) {
			step++;
			if (curIndex + A[curIndex] >= endIndex) {
				return step;
			}
			
			// 遍历当前位置可达的下一个位置，选择下一个位置中可达位置最远的。
			int bestNextPos = 0;
			for (int nextPos = curIndex + 1; nextPos <= curIndex + A[curIndex]; nextPos++) {
				int nextNextPos = nextPos + A[nextPos];
				bestNextPos = nextNextPos >= bestNextPos + A[bestNextPos] ? nextPos : bestNextPos;
			}
			curIndex = bestNextPos;
		}
		return step;
	}
}
