package algotithem.basicclass.class_03;

import java.util.HashSet;
import java.util.Set;

/**
 * 两链表相交问题，返回第一个相交节点。
 * 
 * 难点：
 * 	1.链表是否有环——链表最多存在一个环
 * 		- 判断链表遍历时是否有重复节点，利用HashSet、HashMap等。
 * 		- 快慢指针，有环 <=> 两指针在环上相遇，快指针降速回归起点，两指针会在第一个相交点再次相遇。
 * 
 * 	2.相交情况
 * 		- 无环 + 无环 : 
 * 			* 是否有公共节点：使用HashSet，一链表放入Set，遍历另一链表判断isContain。
 * 			* 依据length、endNode。仅有一种相交情况，两者止于同一end节点。两者长度差位于差异分支。
 * 		- 有环 + 无环 : 不存在相交
 * 		- 有环 + 有环：三种情况，相交情况只有一个环
 * 			* 6-6型：不相交，loop1 != loop2 && loop1、loop2不在同一个环上
 * 			* Y-O型：相交，loop1 = loop2
 *			* 机器人型：loop1 != loop2 && 在同一个环上
 *
 * @author Yanjie
 *
 */
public class Code_14_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		return null;
	}

	/**
	 * 判断链表是否有环，若有返回入环节点，否则返回null。
	 * 空间复杂度O(1)
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		
		// 速度，快=2，慢=1，相遇 <=> 有环，且在环上相遇
		Node n1 = head.next; // n1 -> slow
		Node n2 = head.next.next; // n2 -> fast
		while (n1 != n2) {
			if (n2.next == null || n2.next.next == null) {
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		
		// 寻找入环点，快指针降速，回归起点，再次在入环点相遇。
		n2 = head; // n2 -> walk again from head
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}
	
	/**
	 * 判断链表是否有环，若有返回入环节点，否则返回null。
	 * 空间复杂度O(N)
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static Node getLoopNodeByHashSet(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Set<Node> nodeSet = new HashSet<>();
		while (head != null) {
			boolean isContain = nodeSet.add(head);
			if (isContain) {
				head = head.next;
			} else {
				return head;
			}
		}
		return null;
	}

	/**
	 * 判断两个无环链表是否相交，若相交返回相交节点，否则返回null。
	 * 
	 * @author Yanjie
	 *
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		
		Node cur1 = head1;
		Node cur2 = head2;
		
		// 链表head1与head2长度之差
		int lengthDiff = 0;
		
		// 计算链表长度差
		while (cur1.next != null) {
			lengthDiff++;
			cur1 = cur1.next;
		}
		while (cur2.next != null) {
			lengthDiff--;
			cur2 = cur2.next;
		}
		
		// 相交的无环链表必定止于同一节点
		if (cur1 != cur2) {
			return null;
		}
		
		// 较长链表先走差值步，然后同时走，相遇于第一个相交节点。cur1-较长链表，cur2-较短链表。
		cur1 = lengthDiff > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;
		lengthDiff = Math.abs(lengthDiff);
		while (lengthDiff != 0) {
			lengthDiff--;
			cur1 = cur1.next;
		}
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	/**
	 * 判断两个无环链表是否相交，若相交返回相交节点，否则返回null。
	 * 
	 * @author Yanjie
	 *
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static Node noLoopByHashSet(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Set<Node> nodeSet = new HashSet<>();
		while (head1 != null) {
			nodeSet.add(head1);
			head1 = head1.next;
		}
		while (head2 != null) {
			boolean isContain = nodeSet.contains(head2);
			if (isContain) {
				return head2;
			} 
			head2 = head2.next;
		}
		return null;
	}
	
	/**
	 * 两个有环链表是否相交，若相交返回相交节点，否则返回null。
	 * 
	 * @author Yanjie
	 *
	 * @param head1
	 * @param loop1 链表1第一个入环节点
	 * @param head2
	 * @param loop2
	 * @return
	 */
	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		
		// 第一种情况loop1 = loop2，Y-O型，等同于两无环链表相交节点求解。
		if (loop1 == loop2) {
			cur1 = head1;
			cur2 = head2;
			int lengthDiff = 0;
			
			// 计算链表长度差，去掉环，只统计入环前的长度。
			while (cur1 != loop1) {
				lengthDiff++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) {
				lengthDiff--;
				cur2 = cur2.next;
			}
			
			cur1 = lengthDiff > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			lengthDiff = Math.abs(lengthDiff);
			while (lengthDiff != 0) {
				lengthDiff--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			
			return cur1;
			
		// 入环节点不相等：1.不相交，2.相交，则两入环节点均在环上（只有一个环）。由loop1遍历一次环，判断是否由loop2。
		} else {
			cur1 = loop1.next;
			while (cur1 != loop1) {
				if (cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}

}
