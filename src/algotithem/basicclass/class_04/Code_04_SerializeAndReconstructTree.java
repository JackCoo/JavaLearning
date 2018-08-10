package algotithem.basicclass.class_04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 序列化、反序列化 二叉树。
 * 递归方式实现，先序遍历。
 * 
 * @author Yanjie
 *
 */
public class Code_04_SerializeAndReconstructTree {


	/**
	 * 先序递归序列化
	 * 分隔符:!
	 * null:#
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static String serialByPre(Node head) {
		if (head == null) {
			return "#!";
		}
		String res = head.value + "!";
		res += serialByPre(head.left);
		res += serialByPre(head.right);
		return res;
	}

	/**
	 * 先序非递归序列化
	 * 分隔符:!
	 * null:# 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static String serialByPreUnRecur(Node head) {
		if (head == null) {
			return "#!";
		}
		Stack<Node> nodeStack = new Stack<>();
		StringBuilder serialSb = new StringBuilder();
		nodeStack.push(head);
		while (!nodeStack.isEmpty()) {
			Node curNode = nodeStack.pop();
			serialSb.append(curNode.value).append("!");
			if (curNode.right != null) {
				nodeStack.push(curNode.right);
			} else {
				serialSb.append("#!");
			}
			if (curNode.left != null) {
				nodeStack.push(curNode.left);
			} else {
				serialSb.append("#!");
			}
		}
		return serialSb.toString();		
	}
	
	/**
	 * 先序反序列化
	 * 递归实现，非递归较难。
	 * 
	 * @author Yanjie
	 *
	 * @param preStr
	 * @return
	 */
	public static Node reconPreOrder(String preStr) {
		// 预处理，分割字符串并将元素存入队列。
		String[] values = preStr.split("!");
		Queue<String> queue = new LinkedList<String>();
		for (int i = 0; i != values.length; i++) {
			queue.offer(values[i]);
		}
		
		return reconPreOrderFromQueue(queue);
	}

	/**
	 * 先序反序列化，从元素队列恢复二叉树结构。
	 * 递归实现。
	 * 
	 * @author Yanjie
	 *
	 * @param queue
	 * @return
	 */
	public static Node reconPreOrderFromQueue(Queue<String> queue) {
		String value = queue.poll();
		if ("#".equals(value)) {
			return null;
		}
		Node curNode = new Node(Integer.valueOf(value));
		curNode.left = reconPreOrderFromQueue(queue);
		curNode.right = reconPreOrderFromQueue(queue);
		return curNode;
	}
	
	
	/**
	 * 按层序列化
	 * 
	 * 水平按层序列化所需信息：上一层父结点。故处理完当前节点后，需要将其缓存至队列中。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static String serialByLevel(Node head) {
		if (head == null) {
			return "#!";
		}
		String res = head.value + "!";
		Queue<Node> cachedParentqueue = new LinkedList<Node>();
		cachedParentqueue.offer(head);
		
		while (!cachedParentqueue.isEmpty()) {
			
			// 由上层缓存的父结点处理当前层。
			head = cachedParentqueue.poll();
			if (head.left != null) {
				res += head.left.value + "!";
				cachedParentqueue.offer(head.left);
			} else {
				res += "#!";
			}
			if (head.right != null) {
				res += head.right.value + "!";
				cachedParentqueue.offer(head.right);
			} else {
				res += "#!";
			}
		}
		return res;
	}

	/***
	 * 按层反序列化
	 * 
	 * 与序列化流程一致。
	 * @author Yanjie
	 *
	 * @param levelStr
	 * @return
	 */
	public static Node reconByLevelString(String levelStr) {
		String[] values = levelStr.split("!");
		int index = 0;
		Node head = generateNodeByString(values[index++]);
		Queue<Node> queue = new LinkedList<Node>();
		if (head != null) {
			queue.offer(head);
		}
		
		Node node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.left = generateNodeByString(values[index++]);
			node.right = generateNodeByString(values[index++]);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return head;
	}

	/**
	 * 节点生成
	 * 
	 * @author Yanjie
	 *
	 * @param val
	 * @return
	 */
	public static Node generateNodeByString(String val) {
		if (val.equals("#")) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}


	public static void main(String[] args) {
		Node head = null;
		Node.printTree(head);

		// 空二叉树-先序
		String pre = serialByPreUnRecur(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconPreOrder(pre);
		System.out.print("reconstruct tree by pre-order, ");
		Node.printTree(head);

		// 空二叉树-按层
		String level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		Node.printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		Node.printTree(head);

		// 单节点-先序
		pre = serialByPreUnRecur(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconPreOrder(pre);
		System.out.print("reconstruct tree by pre-order, ");
		Node.printTree(head);

		// 单节点-按层
		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		Node.printTree(head);

		System.out.println("====================================");

		
		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.right = new Node(5);
		Node.printTree(head);
		
		// 
		pre = serialByPreUnRecur(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconPreOrder(pre);
		System.out.print("reconstruct tree by pre-order, ");
		Node.printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		Node.printTree(head);

		System.out.println("====================================");

		head = new Node(100);
		head.left = new Node(21);
		head.left.left = new Node(37);
		head.right = new Node(-42);
		head.right.left = new Node(0);
		head.right.right = new Node(666);
		Node.printTree(head);

		// 
		pre = serialByPreUnRecur(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconPreOrder(pre);
		System.out.print("reconstruct tree by pre-order, ");
		Node.printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		Node.printTree(head);

		System.out.println("====================================");

	}
}
