package nowcoder.offer;

import java.util.Arrays;
import java.util.PriorityQueue;

import algotithem.basicclass.class_07.Code_04_MadianQuick.MedianHolder;

/**
 * 数据流中的中位数
 * 题目：
 * 	如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 	如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 
 * @see MedianHolder
 * 
 * @author Yanjie
 *
 */
public class StreamMedian {
	
	private PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>((Integer i1, Integer i2) ->  {return i2 - i1;});
	
	private PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>((Integer i1, Integer i2) -> {return i1 - i2;});
	
	/**
	 * 添加数字
	 * 
	 * @author Yanjie
	 *
	 * @param num
	 */
    public void Insert(Integer num) {
    	
    	// 左侧堆所有数字小于右侧堆
        if (leftMaxHeap.isEmpty()) {
        	leftMaxHeap.add(num);
        } else if (leftMaxHeap.peek() >= num) {
        	leftMaxHeap.add(num);
        } else {
        	rightMinHeap.add(num);
        }
        
        // 调整堆大小
        modifyHeapSize();
    }

    /**
     * 调整堆大小，使其数量差在1以内
     * 
     * @author Yanjie
     *
     */
    public void modifyHeapSize() {
    	if (leftMaxHeap.size() >= rightMinHeap.size() + 2) {
    		rightMinHeap.add(leftMaxHeap.poll());
    	} else if (rightMinHeap.size() >= leftMaxHeap.size() + 2) {
    		leftMaxHeap.add(rightMinHeap.poll());
    	}
    }
    
    /**
     * 获取中位数
     * 
     * @author Yanjie
     *
     * @return
     */
    public Double GetMedian() {
        int leftHeapSize = leftMaxHeap.size();
        int rightHeapSize = rightMinHeap.size();
        Integer leftHeapTop = leftMaxHeap.peek();
        Integer rightHeapTop = rightMinHeap.peek();
		if (leftHeapSize + rightHeapSize == 0) {
			return null;
		} else if ( ((leftHeapSize + rightHeapSize) & 1 ) == 0 ) {
        	return (leftHeapTop + rightHeapTop) / 2.0;
        } else {
        	return (leftHeapSize > rightHeapSize ? leftHeapTop : rightHeapTop) * 1.0;
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
 	public static Double getMedianOfArray(int[] arr) {
 		int[] newArr = Arrays.copyOf(arr, arr.length);
 		Arrays.sort(newArr);
 		int mid = (newArr.length - 1) / 2;
 		if ((newArr.length & 1) == 0) {
 			return (newArr[mid] + newArr[mid + 1]) / 2.0;
 		} else {
 			return newArr[mid] * 1.0;
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
 			int maxValue = 10;
 			int[] arr = getRandomArray(len, maxValue);
 			
 			StreamMedian streamMedian = new StreamMedian();
 			for (int j = 0; j != arr.length; j++) {
 				streamMedian.Insert(arr[j]);
 			}
 			if (!streamMedian.GetMedian().equals(getMedianOfArray(arr)) ) {
 				err = true;
 				printArray(arr);
 				System.out.println(streamMedian.GetMedian());
 				System.out.println(getMedianOfArray(arr));
 				break;
 			}
 		}
 		System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

 	}
    
    
}
