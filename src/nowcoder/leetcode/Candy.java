package nowcoder.leetcode;

/**
 * 分发糖果（局部最值）
 * 
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * 	Children with a higher rating get more candies than their neighbors.
 *	What is the minimum candies you must give?
 *
 * 贪心策略：
 * 	1.遍历一次，每次只处理当前数及其左右共三个数。---错误
 * 	2.每次处理当前及之前的数，类似插入排序。----时间复杂度O(N2)
 * 		ps:将sum并入主循环，耗时增加。
 * 	3.两次遍历，从左至右遍历保证右边高分孩子糖果多；从右至左遍历，保证左边高分孩子糖果多。每次只处理两个数。O(n)
 *
 * @author Yanjie
 *
 */
public class Candy {
	
	public int candy(int[] ratings) {
        
		int[] candy = new int[ratings.length];
		candy[0] = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) { // 后者比前者大
				candy[i] = candy[i - 1] + 1;
			} else if (candy[i - 1] > 1) { // 后者小于等于前者，若前者大于1，那么后者为1
				candy[i] = 1;
			} else { // 后者小于等于前者，若前者等于1，那么后者为1，然后持续向前处理。
				candy[i] = 1;
				for (int j = i - 1; j >= 0; j--) {
					if (ratings[j] > ratings[j + 1] && candy[j] <= candy[j + 1]) {
						candy[j]++;
					} else {
						break;
					}
				}
			}
		}
		int sum = 0;
		for (int i : candy) {
			sum += i;
		}
		return sum;
		
    }
	
	/**
	 * 解法2
	 * 
	 * @author Yanjie
	 *
	 * @param ratings
	 * @return
	 */
	public int candy2(int[] ratings) {
		
		
		int[] candy = new int[ratings.length];
		candy[0] = 1;
		
		// 从左至右遍历：保证右边高分孩子分得的糖果多
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i -1]) {
				candy[i] = candy[i -1] + 1;
			} else {
				candy[i] = 1;
			}
		}
		
		// 从右至左遍历：保证左边高分孩子分得的糖果多
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) {
				candy[i] = candy[i + 1] + 1;
			}
		}
		
		int sum = 0;
		for (int i : candy) {
			sum += i;
		}
		return sum;
		
		
	}
}
