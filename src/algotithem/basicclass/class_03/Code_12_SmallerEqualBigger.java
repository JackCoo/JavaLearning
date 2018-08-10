package algotithem.basicclass.class_03;

/**
 * 链表的荷兰国旗问题，切分链表。
 * 		给定一个数，将链表划分成 小于   等于   大于  三个区域
 * 
 * @author Yanjie
 *
 */
public class Code_12_SmallerEqualBigger {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 三切分链表：利用数组，空间复杂度O(N)，不稳定。
	 * @author Yanjie
	 *
	 * @param head 链表head节点
	 * @param pivot 切分元素
	 * @return
	 */
	public static Node listPartitionByArr(Node head, int pivot) {
		if (head == null) {
			return head;
		}
		
		// 遍历链表计算其长度
		Node cur = head;
		int nodeCount = 0;
		while (cur != null) {
			nodeCount++;
			cur = cur.next;
		}
		
		// 将该链表节点放入数组之中
		Node[] nodeArr = new Node[nodeCount];
		cur = head;
		for (int i = 0; i != nodeArr.length; i++) {
			nodeArr[i] = cur;
			cur = cur.next;
		}
		
		// 三向切分数组节点元素，将结果连接成链表。
		arrPartition(nodeArr, pivot);
		for (int i = 1; i != nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i];
		}
		nodeArr[nodeArr.length - 1].next = null;
		
		return nodeArr[0];
	}

	/**
	 * 数组三向切分，不稳定。
	 * 
	 * @author Yanjie
	 *
	 * @param nodeArr 带切分数组
	 * @param pivot 切分元素
	 */
	public static void arrPartition(Node[] nodeArr, int pivot) {
		
		/**
		 * 小于区域右边界：放置小于切分元素，包含边界元素
		 * 大于区域左边界：放置大于切分元素，包含边界元素
		 * 等于区域：(小于区域右边界，index) => (小于区域右边界, 大于区域左边界)
		 */
		int smallAreaBoundary = -1;
		int bigAreaBoundary = nodeArr.length;
		int index = 0;
		
		while (index != bigAreaBoundary) {
			if (nodeArr[index].value < pivot) {
				swap(nodeArr, ++smallAreaBoundary, index++);
			} else if (nodeArr[index].value == pivot) {
				index++;
			} else {
				swap(nodeArr, --bigAreaBoundary, index);
			}
		}
	}

	public static void swap(Node[] nodeArr, int a, int b) {
		Node tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}

	/**
	 * 三向切分链表：利用三链表（三区域），遍历原始链表依次发货至相应链表，最后重连三链表。
	 * 空间复杂度O(N)，稳定。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @param pivot
	 * @return
	 */
	public static Node listPartitionByThreeList(Node head, int pivot) {
		Node smallAreaHead = null; // small head
		Node smallAreaTail = null; // small tail
		Node equalAreaHead = null; // equal head
		Node equalAreaTail = null; // equal tail
		Node bigAreaHead = null; // big head
		Node bigAreaTail = null; // big tail
		
		Node next = null; // save next node
		
		// every node distributed to three lists
		Node cur = head;
		while (cur != null) {
			next = cur.next;
			cur.next = null;
			if (cur.value < pivot) {
				if (smallAreaHead == null) {
					smallAreaHead = cur;
					smallAreaTail = cur;
				} else {
					smallAreaTail.next = cur;
					smallAreaTail = cur;
				}
			} else if (cur.value == pivot) {
				if (equalAreaHead == null) {
					equalAreaHead = cur;
					equalAreaTail = cur;
				} else {
					equalAreaTail.next = cur;
					equalAreaTail = cur;
				}
			} else {
				if (bigAreaHead == null) {
					bigAreaHead = cur;
					bigAreaTail = cur;
				} else {
					bigAreaTail.next = cur;
					bigAreaTail = cur;
				}
			}
			cur = next;
		}
		
		//重连版本1-只考虑前两个区域null组合
		// small and equal reconnect
		if (smallAreaTail != null) {
			smallAreaTail.next = equalAreaHead;
			equalAreaTail = equalAreaTail == null ? smallAreaTail : equalAreaTail;
		}
		// all reconnect
		if (equalAreaTail != null) {
			equalAreaTail.next = bigAreaHead;
		}
		
//		// 重连版本2-只考虑前两个区域null组合
//		if (smallAreaHead != null) {
//			smallAreaTail.next = equalAreaHead;
//		}
//		if (equalAreaHead != null) {
//			equalAreaTail.next = bigAreaHead;
//		} else if (smallAreaHead != null) {
//			smallAreaTail.next = bigAreaHead;
//		}
		
		
		return smallAreaHead != null ? smallAreaHead : equalAreaHead != null ? equalAreaHead : bigAreaHead;
	}
	

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		// 构造链表：7 9 1 8 5 2 5
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		
		head1 = listPartitionByArr(head1, 4);
		printLinkedList(head1);
		
		head1 = listPartitionByThreeList(head1, 5);
		printLinkedList(head1);

	}

}
