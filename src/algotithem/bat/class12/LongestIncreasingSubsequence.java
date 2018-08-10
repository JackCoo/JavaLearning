package algotithem.bat.class12;

import java.util.Arrays;

/**
 * 最长递增子序列 的长度
 * 例如：
 *  2 1 5 3 6 4 8 9 7，最长子序列 1 3 4 8 9，长度为5.
 * 
 * 1.暴力递归 -> 动态规划：
 * 		暴力递归1、2难以改写为动态规划的共同原因：可变参数取值范围过大，无法申请到足够的数组内存！解决办法：尝试替换可变参数。
 * 		暴力递归3改进了可变参数，使得其取值范围有界。由其修改得到的动态规划3通过测试。
 * 		动态规划3展示了可变参数有负数时的改写规则：改表不改逻辑。
 *	ps:
 * 		暴力递归1、动态规划1分别因 递归太深、数组过大 导致栈溢出，无法验证，且未考虑负数数组。
 * 		暴力递归1存在冗余逻辑，多出的while循环使得动态规划难以改写。暴力2改进了这点。
 *  注意点：
 *     	可变参数的取值范围，可变参数的替换。
 *     	取值范围 至 dp表 的映射，无需改变逻辑，只是改变数据的存储位置。
 * 		
 * 2.LeetCode
 * 		暴力递归2 -> 记忆化递归 -> 动态规划2 -> 动态规划+二分查找
 *  
 *  参考：
 *  	https://leetcode.com/problems/longest-increasing-subsequence/solution/
 * @author Yanjie
 *
 */
public class LongestIncreasingSubsequence {
	
	
	/**
	 * 暴力递归2
	 * 
	 * 思路：遍历数组，当前元素比LIS中元素大的有 放入 和 不放入，否则，只有不放入选项。选择两种情况最大的。
	 * 
	 * 相较于暴力递归1，改进了冗余的跳过小于元素逻辑。
	 * 
	 * 时间复杂度：O(2^n)
	 * 
	 * LeetCode方法1
	 * 
	 * @author Yanjie
	 *
	 * @param A
	 * @param n
	 * @return
	 */
	public static int getLIS4(int[] A, int n) {
		 return process2(A, n, 0, Integer.MIN_VALUE);
	}
	public static int process2(int[] A, int n, int index, int lastNum) {
		
		// base case
		if (index == n) {
			return 0;
		}
		
		int including = 0;
		if (A[index] > lastNum) {
			including = 1 + process2(A, n, index + 1, A[index]);
		}
		int exclusive = process2(A, n, index + 1, lastNum);
		
		return Math.max(including, exclusive);
	}
	
	
	/**
	 * 记忆化递归
	 * 
	 * 通过将递归调用结果保存在一个二维记忆数组memo中，可以消除冗余的调用。
	 * 
	 * 由暴力递归2改写。递归函数可变参数 preIndex、curpos，无后效性，故memo[i][j]中缓存由特定递归过程(preIndex, curpos)计算出的值。
	 * 考虑到数组元素的取值范围无界，在上一个方法中使用的pre参数（表示LIS中的上一个元素的值）需要替换，这里使用preIndex表示该元素在nums数组中的位置。
	 * 另外，由于preIndex取值-1 ~ n -1，所以递归(i, j)的值保存在memo[i+1, j]中，其余逻辑照搬递归。
	 * 
	 * LeetCode 方法2
	 * 
	 * @author Yanjie
	 *
	 * @param A
	 * @param n
	 * @return
	 */
	public static int getLIS5(int[] A, int n) {
		int[][] memo = new int[n + 1][n];
		for (int[] is : memo) {
			Arrays.fill(is, -1);
		}
		return process3(A, n, -1, 0, memo);
	}
	public static int process3(int[] A, int n, int preIndex, int curIndex, int[][] memo) {
		
		// base case
		if (curIndex == n) {
			return 0;
		}
		
		// search in memo firstly
		if (memo[preIndex + 1][curIndex] > -1) {
			return memo[preIndex + 1][curIndex];
		}
		
		// compute and cache
		int including = 0;
		if (preIndex == -1 || A[curIndex] > A[preIndex]) {
			including = process3(A, n, curIndex, curIndex + 1, memo) + 1;
		}
		int executive = process3(A, n, preIndex, curIndex + 1, memo);
		memo[preIndex + 1][curIndex] =  Math.max(including, executive);
		
		return memo[preIndex + 1][curIndex];
	}
	
	/**
	 * 动态规划2
	 * 	
	 * 状态转移方程：
	 * 		dp[i] = max(dp[j]) + 1, 0 <= j < i & arr[i] > arr[j]
	 * 	dp[i]表示以i元素作为结尾的子序列的最大长度。
	 * 
	 * 时间复杂度：O(n2)
	 * 
	 * leetcode 方法 3
	 * 
	 * @author Yanjie
	 *
	 * @param A
	 * @param n
	 * @return
	 */
	public static int getLIS3(int[] A, int n) {
		
		int[] dp = new int[n];
		
		dp[0] = 1;
		
		for (int i = 1; i < n; i++) {
			int maxVal = 0;
			for(int j = i - 1; j >= 0; j--) {
				if (A[j] < A[i]) { // 当前元素较大，可以追加至既有的子序列。
					maxVal = Math.max(maxVal, dp[j]);
				}
			}
			dp[i] = maxVal + 1;
		}
		
		return Arrays.stream(dp).max().getAsInt();
		
	}
	
	
	/**
	 * 动态规划+二分查找
	 * 
	 * O(nlogn)
	 * 
	 * LeetCode 方法4
	 * 
	 * 方法3花费了大量时间寻找最大dp[j]上（第二个for循环），如果dp[]是一个递增数列，那么我们可以使用二分查找进行优化，使得整个算法的复杂度下降到O(nlogn)。
	 * 方法3中dp[i]保存了以nums[i]元素结尾的LIS长度，这里我们使用dp[i]保存所有长度为i+1的递增子序列中末尾元素的最小值。
	 * 根据这个最小值，可以判断num数组中的后续元素是否可以追加到既有IS中以形成更长的IS，或者更新既有IS的末尾元素使其更小。由于dp数组是递增的，所以可以使用二分查找。 
	 * 
	 * @author Yanjie
	 *
	 * @return
	 */
	public static int getLIS6(int[] A, int n) {
        int[] dp = new int[n];
        int len = 0;
        for (int num : A) {
        	/**
        	 * 在有序数组中二分查找
        	 * binarySearch():在指定范围内[form, to)查找并返回key的index，
        	 * 		若不存在返回 -insert pos（该key应该被插入的位置，即数组中第一个大于key元素的索引，若key大于所有元素，那么插入位置为to，返回-to）
        	 */
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            
            // len = i + 1
            if (i == len) {
                len++;
            }
        }
        return len;
	}
	
	
    /**
     * 暴力递归3
     * 
     * 修改了暴力递归1和2中取值范围过大的lastNum可变参数，改为preIndex表示其下标。
     * 
     * @author Yanjie
     *
     * @param A
     * @param n
     * @return
     */
    public static int getLIS7(int[] A, int n) {
    	return process4(A, n, -1, 0);
    }
    public static int process4(int[] A, int n, int preIndex, int curIndex) {
    	
    	// base case
    	if (curIndex == n) {
    		return 0;
    	}
    	
    	int included = 0;
    	if (preIndex == -1 || A[curIndex] > A[preIndex]) {
    		included = process4(A, n, curIndex, curIndex + 1) + 1;
    	}
    	int executive = process4(A, n, preIndex, curIndex + 1);
    	
    	return Math.max(included, executive);
    }
    
    /**
     * 动态规划 3
     * 
     * 由 暴力递归3 改写
     * 
     * @author Yanjie
     *
     * @param A
     * @param n
     * @return
     */
    public static int getLIS8(int[] A, int n) {
    	
    	/** dp
    	 *  preIndex:-1 ~ n - 1, -1超出数组的索引，此处不改变变量涉及的逻辑判断，而修改dp表的存储逻辑，(i, j)在dp中存储在[i + 1, j]处，简化修改逻辑。
    	 */
    	int[][] dp = new int[n + 1][n + 1];
    	
    	// base case 解
    	for (int i = 0; i <= n; i++) {
    		dp[i][n] = 0;
    	}
    	
    	/**
    	 * 1.确定计算顺序，两个for循环，此处参数取值源于递归函数（preIndex: -1 ~ n - 1）。
    	 * 2.照搬递归函数中的计算逻辑，存储或者获取dp表时，dp[x + 1][y]。
    	 */
    	for(int j = n - 1; j >= 0; j--) {
    		for(int i = n - 1; i >= -1; i--) {
    	    	int included = 0;
    	    	if (i == -1 || A[j] > A[i]) {
    	    		included = dp[j + 1][j + 1] + 1; // 所有dp表x索引+1
    	    	}
    	    	int executive = dp[i + 1][j + 1];
    	    	dp[i + 1][j] = Math.max(included, executive);
    		}
    	}
    	
    	return dp[0][0];
    }
    
    
	/**
	 * 暴力递归1
	 * 思路：
	 * 		遍历数组，在比前一数字大的位置 有 选择 和 忽略 两种情况，选择最长的。
	 * 		另外，没有在形参中加入变量表示当前子序列长度，而是在返回值中体现，即将形参变量移至外部，通过由后往前的累加获得。
	 * 		// TODO 修改背包问题的三维参数。
	 * @author Yanjie
	 *
	 * @param A
	 * @param n
	 * @return
	 */
	@Deprecated
    public static int getLIS1(int[] A, int n) {

    	return process1(A, n, 0, Integer.MIN_VALUE);
    
    }
    public static int process1(int[] A, int n, int index, int lastNum) {
    	
    	// 跳过比当前数小的数字，但至多跳至倒数第二个元素
    	while (index < n - 1) {
    		if (A[index] <= lastNum) {
    			index++;
    		} else {
    			break;
    		}
    	} 
    	
    	// base case
    	if (index == n - 1) {
    		return lastNum < A[index] ? 1 : 0;
    	}
    	
    	/**
    	 * withoutCur:子序列 不包含 当前值的后续长度
    	 * withCur：子序列 包含 当前值的后续长度，+ 1 当前数入列所增加的长度。
    	 */
    	int withoutCur = process1(A, n, index + 1, lastNum); // (i + 1, lastNum)
    	int withCur = process1(A, n, index + 1, A[index]) + 1; // (i + 1, a[index])
    	
    	return Math.max(withoutCur, withCur);
    }
    
    /**
     * 动态规划1
     * 
     * 由暴力递归1改写，数组内存过大，无法执行。
     * 
     * @author Yanjie
     *
     * @param A
     * @param n
     * @return
     */
    @Deprecated
    public static int getLIS2(int[] A, int n) {
    	
    	// dp
    	int yLength = Arrays.stream(A).max().getAsInt() + 1; // 获取数组最大值 + 1
    	int[][] dp = new int[n][yLength];
    	
    	// base case 解
    	for (int j = 0; j < yLength; j++) {
    		dp[n - 1][j] = j < A[n - 1] ? 1 : 0;
    	}
    	
    	// 求解普通位置
    	for (int i = n - 2; i >= 0; i--) {
    		for (int j = 0; j < yLength; j++) {
    	    	if (A[i] <= j) {
    	    		dp[i][j] = 0;
    	    		break;
//    	    		i++;
    	    	} else {
    	    		dp[i][j] = Math.max(dp[i + 1][j], (dp[i + 1][A[i]] + 1) );
    	    	}
    	    	
    		}
    		
    	}
    	
    	
    	return dp[0][0];
    	
    }
    
    public static void main(String[] args) {
		int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};// 5
		int[] arr2 = {2,1,4,3,1,5,6};//4
		
		//31
		int[] arr3 = {395,132,276,31,612,103,209,105,214,541,454,87,600,385,345,393,45,154,70,101,468,496,253,181,162,605,425,588,74,261,155,58,549,378,535,217,213,35,564,204,193,301,78,470,140,566,315,162,471,80,451,208,402,80,224,375,279,567,272,39,495,622,256,396,452,141,344,586,310,506,348,481,388,599,412,105,75,338,71,149,19,317,23,8,592,452,624,395,412,12,303,207,491,466,238,94,538,478,163,624,308,271,18,417,209,83,18,113,169,521,539,242,36,180,429,360,203,164,580,198,98,119,157,249,609,93,323,592,105,573,243,132,25,208,505,141,454,83,199,279,464,96,285,239,24,299,484,562,410,285,421,280,63,288,502,503,55,615,395,115,560,218,165,224,536,556,201,573,167,248,541,539,35,112,56,326,138,362,91,14,531,539,291,497,570,171,615,318,586,354,462,31,199,297,589,86,257,618,591,59,532,199,302,195,587,51,87,504,62,403,513,33,86,166,576,51,201,254,343,422,388,604,305,511,388,403,564,534,466,423,42,92,146,435,613,92,239,455,614,332,176,218,60,432,584,205,323,170,320};
		int[] arr4 = {10,9,2,5,3,7,101,18};
		int[] arr5 = {157,232,6};
//		System.out.println(getLIS1(arr3, arr3.length));
//		System.out.println(getLIS2(arr3, arr3.length));
		
		System.out.println(getLIS3(arr3, arr3.length));
		System.out.println(getLIS4(arr5, arr5.length));
		System.out.println(getLIS5(arr5, arr5.length));
		System.out.println(getLIS6(arr5, arr5.length));
		System.out.println(getLIS7(arr5, arr5.length));
		System.out.println(getLIS8(arr, arr.length));
		
//		System.out.println(Arrays.binarySearch(new int[] {1, 2, 5}, 0, 2, 3));
	}
}
