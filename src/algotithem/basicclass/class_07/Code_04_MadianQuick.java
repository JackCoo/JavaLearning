package algotithem.basicclass.class_07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 获取数据流的中位数，时间复杂度O(1)
 * 
 * 实现1：利用堆
 * 	左侧大根堆：保存中位数左侧的数字
 * 	右侧小根堆：保存中位数右侧的数字
 * 	保证左侧所有数字均小于右侧，且两者数量差不大于1.
 * 	
 * @author Yanjie
 *
 */
public class Code_04_MadianQuick {
	
	
	public static class MedianHolder {
		private PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<Integer>(new MaxHeapComparator());
		
		private PriorityQueue<Integer> rightMinHeap = new PriorityQueue<Integer>(new MinHeapComparator());

		/**
		 * 调整两堆大小，使其数量差在1以内
		 * 
		 * @author Yanjie
		 *
		 */
		private void modifyTwoHeapsSize() {
			if (this.leftMaxHeap.size() == this.rightMinHeap.size() + 2) {
				this.rightMinHeap.add(this.leftMaxHeap.poll());
			}
			if (this.rightMinHeap.size() == this.leftMaxHeap.size() + 2) {
				this.leftMaxHeap.add(this.rightMinHeap.poll());
			}
		}

		/**
		 * 添加数字
		 * 
		 * @author Yanjie
		 *
		 * @param num
		 */
		public void addNumber(int num) {
			
			// 添加数字至堆，保证左堆所有数均小于右堆
			if (this.leftMaxHeap.isEmpty()) {
				this.leftMaxHeap.add(num);
				return;
			}
			if (this.leftMaxHeap.peek() >= num) {
				this.leftMaxHeap.add(num);
			} else { // 直接this.rightMinHeap.add(num)，但增加了调整堆的次数？
				
				if (this.rightMinHeap.isEmpty()) {
					this.rightMinHeap.add(num);
					return;
				}		
				
				// 位于两堆顶之间的数字放在左堆（也可放右堆）
				if (this.rightMinHeap.peek() > num) {
					this.leftMaxHeap.add(num);
				} else {
					this.rightMinHeap.add(num);
				}
			}
			
			// 调整堆大小
			modifyTwoHeapsSize();
		}

		/**
		 * 获取中位数
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public Integer getMedian() {
			int maxHeapSize = this.leftMaxHeap.size();
			int minHeapSize = this.rightMinHeap.size();
			if (maxHeapSize + minHeapSize == 0) {
				return null;
			}
			Integer maxHeapHead = this.leftMaxHeap.peek();
			Integer minHeapHead = this.rightMinHeap.peek();
			if (((maxHeapSize + minHeapSize) & 1) == 0) {// 偶数
				return (maxHeapHead + minHeapHead) / 2;
			}
			return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
		}

	}

	public static class MaxHeapComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			if (o2 > o1) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public static class MinHeapComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			if (o2 < o1) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	// for test
	public static int[] getRandomArray(int maxLen, int maxValue) {
		int[] res = new int[(int) (Math.random() * maxLen) + 1];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue);
		}
		return res;
	}

	// for test, this method is ineffective but absolutely right
	public static int getMedianOfArray(int[] arr) {
		int[] newArr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(newArr);
		int mid = (newArr.length - 1) / 2;
		if ((newArr.length & 1) == 0) {
			return (newArr[mid] + newArr[mid + 1]) / 2;
		} else {
			return newArr[mid];
		}
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		boolean err = false;
		int testTimes = 200000;
		for (int i = 0; i != testTimes; i++) {
			int len = 30;
			int maxValue = 1000;
			int[] arr = getRandomArray(len, maxValue);
			MedianHolder medianHold = new MedianHolder();
			for (int j = 0; j != arr.length; j++) {
				medianHold.addNumber(arr[j]);
			}
			if (medianHold.getMedian() != getMedianOfArray(arr)) {
				err = true;
				printArray(arr);
				break;
			}
		}
		System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

	}

}
