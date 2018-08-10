package algotithem.basicclass.class_01;

import java.util.Arrays;

/**
 * 给定数组，求该数组排序后，相邻两数最大差值，线性时间复杂度。
 * 
 * 使用桶结构实现：给定N个数，准备N+1个桶，min-max等分成N+1份，相应的数对应N+1个桶。
 * 由于桶数大于N，必有空桶。空桶的存在，使得最大差值必定不存在与同一个桶内，而是位于不同桶之间 ，但不一定是
 * 位于空桶两侧。排定后，只需比较相邻两个非空桶之间的差值即可。
 * 
 * @author Yanjie
 *
 */
public class Code_11_MaxGap {

	public static int maxGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int len = nums.length;
		
		/**
		 * 求得数组最值
		 */
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		
		if (min == max) {
			return 0;
		}
		
		/**
		 * 桶结构：桶内不保存具体的入桶数字，每个桶含有三个变量，是否有数字进入，最大值，最小值。使用三个数组表示三个桶。——ps，可以创建桶类。
		 */
		boolean[] hasNum = new boolean[len + 1];
		int[] maxs = new int[len + 1];
		int[] mins = new int[len + 1];
		
		/**
		 * 遍历数组，将数放入相应的桶内，根据入桶数字更新桶变量，并不在桶中保留该数。
		 */
		int bid = 0;
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		
		/**
		 * 计算相邻两个非空桶之间的差值，求的他们的最大值。
		 */
		int result = 0;
		int lastBuckerMax = maxs[0];
		for (int i = 1; i <= len; i++) {
			if (hasNum[i]) {
				
				/**
				 * 两桶差值：当前桶min-上一桶max
				 */
				result = Math.max(result, mins[i] - lastBuckerMax);
				lastBuckerMax = maxs[i];
			}
		}
		return result;
	}

	/**
	 * 计算某一数所属的桶编号，从0开始编号
	 * 
	 * @author Yanjie
	 *
	 * @param num 待入桶的数字
	 * @param len 数组长度
	 * @param min 数组最大值
	 * @param max 数组最小值
	 * @return 桶编号
	 */
	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}

	// for test
	public static int comparator(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int gap = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++) {
			gap = Math.max(nums[i] - nums[i - 1], gap);
		}
		return gap;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (maxGap(arr1) != comparator(arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
