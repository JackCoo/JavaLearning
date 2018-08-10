package nowcoder.offer;

import java.util.Scanner;

/**
 * 获取乱序数组的第k个小的数、中位数 ，时间复杂度o(N)
 * 关于中位数
 * 	奇数个：中位数
 * 	偶数个：中间两数平均值
 * 结果向下取整
 * 
 * 利用快排切分过程每次排定1个数的特性
 * @author Yanjie
 *
 */
public class Median {
	
	/**
	 * 获取乱序数组的第k个小的数、中位数 
	 *  奇数个：中位数
	 * 	偶数个：中间两数平均值
	 * 结果向下取整
	 * 
	 * @author Yanjie
	 *
	 * @param arr 
	 * @param k 从1索引，若为小数，则取其两侧均值
	 * @return
	 * 
	 */
	public static int getNumber(int[] arr, double k) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		
		/**
		 * k为整数
		 */
		if (k == (int)k) {
			return getNumber(arr, 0, arr.length - 1, (int)k -1 );
		
		/**
		 * k为小数，排定其两边的值。
		 */
		} else {
			int leftNumber = getNumber(arr, 0, arr.length - 1, (int)(k) - 1);
			
			/**
			 * 无需对整个数组切分，k左侧数已排定，只需对其右侧数组切分。
			 */
			int rightNumber = getNumber(arr, (int)(k), arr.length - 1, (int)(k));
			return (leftNumber + rightNumber) / 2;
		}

	}

	/**
	 * 排定并获取第k小数
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param l
	 * @param r
	 * @param k 正整数，从0索引。
	 */
	public static int getNumber(int[] arr, int l, int r, int k) {
		
		if (l <= r) {

			/**
			 * 产生随机切分元素
			 */
			swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
			int[] p = partition(arr, l, r);
			
			if (p[0] <= k && p[1] >= k) {
				return arr[p[0]];
			} else if (p[0] > k) {
				return getNumber(arr, l, p[0] - 1, k);
			} else {
				return getNumber(arr, p[1] + 1, r, k);
			}
		} else {
			return 0;
		}
		
	}

	/**
	 * 三向切分
	 * <p>以数组最后一个数为基准，将数组按照 小于该数、等于、大于该数的顺序原地排列
	 * 
	 * @author Yanjie
	 *
	 * @param arr 待切分的数组
	 * @param left 切分左边界（包含）边界值
	 * @param right 切分右边界（包含边界值）
	 * @return 大小为2的数组，{等于区域左边界(包含)，等于区域右边界(包含)}
	 */
	public static int[] partition(int[] arr, int left, int right) {
		
		/**
		 * 小于区域边界(包含)
		 */
		int smallAreaBoundary = left - 1;
		
		/**
		 * 大于区域边界(包含)
		 */
		int bigAreaBoundary = right;
		int currentIndex = left;
		
		/**
		 * 遍历数组，将小于数放置在小于区域，大于数放置到大于区域。当前指针与大于区域相遇时完成排列。
		 */
		while (currentIndex < bigAreaBoundary) {
			if (arr[currentIndex] < arr[right]) {
				
				/**
				 * 扩展小于区域，当前值与小于区域边界值交换，因为交换的值已判断过（在当前值左侧，等于或小于（即自身）比较数），无需再次判断，当前指针自增。
				 */
				swap(arr, ++smallAreaBoundary, currentIndex++);
			} else if (arr[currentIndex] > arr[right]) {
				
				/**
				 * 扩展大于区域，当前值与大于区域边界值交换，因为交换的值原位于待定区域，未判断过大小（在当前值右侧），所以需要判断以下，当前指针不自增。
				 */
				swap(arr, --bigAreaBoundary, currentIndex);
			} else {
				
				/**
				 * 相等，等于区域位于中间，无需交换。
				 */
				currentIndex++;
			}
		}
		
		/**
		 * 将数组末尾的切分元素放置在数组中间位置
		 * ps：boundary处的值为交换而来的大数
		 */
		swap(arr, bigAreaBoundary, right);
		return new int[] { smallAreaBoundary + 1, bigAreaBoundary };
	}

	/**
	 * 交换数组指定位置的两个数
	 * 
	 * @author Yanjie
	 *
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
        	int n = scanner.nextInt();
        	if (n == 0) return;
        	int a[] = new int[n];
            for (int i = 0; i < a.length; i++) {
                a[i] = scanner.nextInt();
            }
            System.out.println(getNumber(a, ((double)n + 1) / 2));
        }
        scanner.close();
	}
	
}

