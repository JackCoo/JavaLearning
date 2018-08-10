package nowcoder.offer;

import java.util.ArrayList;




/**
 * 输入一个链表，从尾到头打印链表每个节点的值。
 * 
 * 思考：
 * 		1.先反转链表，然后遍历打印。时间复杂度O(N2)
 * 		2.利用栈的LIFO特性，时间复杂度O(N2)，空间复杂度O(N)
 * 		3.递归
 * @author Yanjie
 *
 */
public class ReversePrintLinkedList {
	
	/**
	 * 反转单向链表
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static ListNode reverseLinkedList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode cur = head;
		ListNode cachedPreNode = null;
		while (cur != null) {
			ListNode cachedNextNode = cur.next;
			cur.next = cachedPreNode;
			cachedPreNode = cur;
			cur = cachedNextNode;
		}
		return cachedPreNode;
	}
	
	/**
	 * 输出反向链表
	 * 
	 * @author Yanjie
	 *
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode reversedLinkedListCurNode = reverseLinkedList(listNode);
        ArrayList<Integer> result = new ArrayList<>();
        while (reversedLinkedListCurNode != null) {
        	result.add(reversedLinkedListCurNode.val);
        	reversedLinkedListCurNode = reversedLinkedListCurNode.next;
        }
        return result;
    }
	

	/**
	 * 递归版本
	 * 
	 * @author Yanjie
	 *
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
		ArrayList<Integer> arrayList=new ArrayList<Integer>();
		if(listNode!=null){
			this.printListFromTailToHead(listNode.next);
			arrayList.add(listNode.val);
		}
		return arrayList;
	}
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		System.out.println(new ReversePrintLinkedList().printListFromTailToHead(node1));
		
	}
}

class ListNode {
	int val;
	ListNode next = null;
	
	public ListNode(int val) {
		this.val = val;
	}
}


