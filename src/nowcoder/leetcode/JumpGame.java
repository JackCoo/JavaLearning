package nowcoder.leetcode;

/**
 * 跳跃游戏 - 是否可以达到终点
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * 
 * 贪心策略：
 * 	1.若当前最大步数无法达到终点，那么跳最少步数。----错误
 * 	2.若当前最大步数无法到达终点，那么跳最大步数。----正确
 * 	
 * @author Yanjie
 *
 */
public class JumpGame {
	
    public boolean canJump(int[] A) {
        int endIndex = A.length - 1;
        int curIndex = 0;
        while (curIndex <= endIndex) {
        	if (A[curIndex] + curIndex >= endIndex) {
        		return true;
        	} else if (A[curIndex] != 0){
        		curIndex += A[curIndex];
        	} else {
        		break;
        	}
        }
    	return false;
    }
}
