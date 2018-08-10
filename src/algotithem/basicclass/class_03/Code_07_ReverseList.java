package algotithem.basicclass.class_03;

/**
 * 反转单向和双向链表
 *   单向链表无法获取前一节点值，缓存之。
 * 	 双向链表，直接交换。
 * @author Yanjie
 *
 */
public class Code_07_ReverseList {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 反转单向链表
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static Node reverseList(Node head) {
		Node cur = head;
		
		// 缓存当前节点的前一节点，单向链表无法获取前一节点值。
		Node pre = null;
		
		while (cur != null) {
			Node next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	public static class DoubleNode {
		public int value;
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int data) {
			this.value = data;
		}
	}

	/**
	 * 反转双向链表
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static DoubleNode reverseList(DoubleNode head) {
		DoubleNode cur = head;
		while (true) {
			DoubleNode next = cur.next;
			cur.next = cur.last;
			cur.last = next;
			
			// 处理下一节点cur.last
			if (cur.last != null) {
				cur = cur.last;
			} else {
				return cur;
			}
		}
	}

	/**
	 * 顺序打印链表
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void printDoubleLinkedList(DoubleNode head) {
		System.out.print("Double Linked List: ");
		DoubleNode end = null;
		while (head != null) {
			System.out.print(head.value + " ");
			end = head;
			head = head.next;
		}
		System.out.print("| ");
		while (end != null) {
			System.out.print(end.value + " ");
			end = end.last;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		printLinkedList(head1);
		head1 = reverseList(head1);
		printLinkedList(head1);

		DoubleNode head2 = new DoubleNode(1);
		head2.next = new DoubleNode(2);
		head2.next.last = head2;
		head2.next.next = new DoubleNode(3);
		head2.next.next.last = head2.next;
		head2.next.next.next = new DoubleNode(4);
		head2.next.next.next.last = head2.next.next;
		printDoubleLinkedList(head2);
		printDoubleLinkedList(reverseList(head2));

	}

}
