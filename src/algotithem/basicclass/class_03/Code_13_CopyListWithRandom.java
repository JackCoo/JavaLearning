package algotithem.basicclass.class_03;

import java.util.HashMap;

/**
 * 链表拷贝
 * 深拷贝包含节点包含random指针的链表。
 * 难点：
 * 		节点的重复创建，拷贝random、next节点的定位。
 * 解决：分离元素拷贝与结构重连步骤。
 * @author Yanjie
 *
 */
public class Code_13_CopyListWithRandom {

	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 链表深拷贝，使用HashMap分离 元素拷贝 于 结构重连 两部分，同时利用hash定位拷贝元素。
	 * 两次遍历，空间复杂度O(N)
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static Node copyListWithRandByHashMap(Node head) {
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		
		// 元素拷贝，key-原节点，value-拷贝节点
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		
		// next、rand结构重连
		cur = head;
		while (cur != null) {
			Node curCopiedNode = map.get(cur);
			curCopiedNode.next = map.get(cur.next);
			curCopiedNode.rand = map.get(cur.rand);
			cur = cur.next;
		}
		return map.get(head);
	}

	/**
	 * 链表拷贝，无需额外空间。
	 * 分离 元素拷贝 与结构重连 ，先将拷贝元素至原始元素之后以便于定位，同时避免重复创建。
	 * 再分离重连。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static Node copyListWithRandWithoutSpace(Node head) {
		if (head == null) {
			return null;
		}
		
		// 拷贝节点，并将拷贝节点置于原节点之后。
		Node cur = head;
		Node next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		
		// 设置拷贝节点的random
		cur = head;
		Node curCopy = null;
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = next;
		}
		
		// 分离
		Node res = head.next; // 拷贝结果
		cur = head;
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next != null ? next.next : null;
			cur = next;
		}
		
		return res;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRandByHashMap(head);
		printRandLinkedList(res1);
		res2 = copyListWithRandWithoutSpace(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRandByHashMap(head);
		printRandLinkedList(res1);
		res2 = copyListWithRandWithoutSpace(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}

}
