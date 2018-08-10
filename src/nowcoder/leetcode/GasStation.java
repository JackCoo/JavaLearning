package nowcoder.leetcode;

/**
 * 加油站 - 是否可以绕环路一周
 * 
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note: 
 * 	The solution is guaranteed to be unique.
 * 
 * 思路：（解法3）
 * 	1.遍历枚举，当前油量不足以支撑到达油站时，continue。
 * 	2.油站总油量与总耗油量之差决定是否能走完全程；假设现在我们到达了第i个油站， 这时候还剩余的油量为sum， 如果 sum + gas[i] - cost[i]小于0， 
 *  我们无法走到下一个油站， 所以起点一定不在第i个以及之前的油站里面（都铁定走不到第i + 1号油站） ，
 *  起点只能在i + 1后者后面。
 *  3.从start出发， 如果油量足够， 可以一直向后走 end++；  油量不够的时候，start向后退 （向前加油）
 *  最终 start == end的时候，如果有解一定是当前 start所在位置。
 * @author Yanjie
 *
 */
public class GasStation {
	
	/**
	 * 解法1-枚举
	 * 
	 * @author Yanjie
	 *
	 * @param gas
	 * @param cost
	 * @return
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
		
//		if (gas.length == 1) {
//			return gas[0] - cost[0] >= 0 ? 0 : -1;
//		}
		
        for (int i = 0; i < gas.length; i++) {
        	int curStation = i;
        	
        	// 预计到达下一站点所剩油量
        	int nextGas = gas[i] - cost[i];
        	
        	while (nextGas >= 0) {
        		curStation = curStation == gas.length - 1 ? 0 : curStation + 1;
        		nextGas += gas[curStation] - cost[curStation];
        		if (curStation == i ) {
        			return nextGas >= 0 ? i : -1;
        		}
        	}
        }
		return -1;
    }
	
	/**
	 * 解法2
	 * 
	 * @author Yanjie
	 *
	 * @param gas
	 * @param cost
	 * @return
	 */
	public int canCompleteCircuit2(int[] gas, int[] cost) {

		int sum = 0;
		
		// 所有油站油量与总耗油量之差
		int totalDiff = 0;
		
		int k = 0;
		for (int i = 0; i < gas.length; i++) {
			sum += gas[i] - cost[i];
			
			// 阶段性油量为负，小于0就只可能在i + 1或者之后了
			if (sum < 0) {
				k = i + 1;
				sum = 0;
			}
			totalDiff += gas[i] - cost[i];
		}
		 
		if (totalDiff < 0) {
			
			// 油站油量不足以支撑总的耗油量
			return -1;
		} else {
			return k;
		}
	}
	
	/**
	 * 解法3
	 * 
	 * @author Yanjie
	 *
	 * @param gas
	 * @param cost
	 * @return
	 */
	public int canCompleteCircuit3(int[] gas, int[] cost) {

		// 将start位置定于末尾，end位置定于开始，巧妙地使用数组实现了环结构。
		int start = gas.length - 1;
		int end = 0;
		
		// end从start位置已尝试走了一步
		int totalDiff = gas[start] - cost[start];
		
		while (start != end) {
			if (totalDiff < 0) {
				start--;
				totalDiff += gas[start] - cost[start];
			} else {
				totalDiff += gas[end] - cost[end];
				end++;
			}
		}
		return totalDiff >= 0 ? end : -1;
	}
}
