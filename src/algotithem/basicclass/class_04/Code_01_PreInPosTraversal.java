package algotithem.basicclass.class_04;

import java.util.Stack;


/**
 * 二叉树遍历
 * 	先序：中 - 左 - 右
 * 	中序：左 - 中 - 右，搜索二叉树的顺序打印
 * 	后序：左 - 右 - 中
 * 
 * @author Yanjie
 *
 */
public class Code_01_PreInPosTraversal {

	/**
	 * 先序打印，递归版本。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static void preOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " "); // 当前节点
		preOrderRecur(head.left); // 左子树
		preOrderRecur(head.right); // 右子树
	}

	/**
	 * 中序打印，递归版本
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static void inOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	/**
	 * 后续打印，递归版本。
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static void posOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");
	}

	/**
	 * 先序打印，非递归。
	 * 利用栈，弹出打印当前节点时依次将其右、左节点入栈。
	 * 栈提供了由下向上的树访问路径。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static void preOrderUnRecur(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.add(head);
			
			while (!stack.isEmpty()) {
				head = stack.pop();
				System.out.print(head.value + " ");
				if (head.right != null) {
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}

	/**
	 * 中序遍历，非递归。
	 * 栈：循环压入左节点，null时栈弹出打印，切换到右节点。
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static void inOrderUnRecur(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			
			/**
			 *  cur != null，入栈，cur = left  ===> 所有左节点入栈
			 *  cur == null，出栈打印，cur = right  ===> 打印当前节点，切换到右子树
			 */
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					System.out.print(head.value + " ");
					head = head.right;
				}
			}
		}
		System.out.println();
	}

	/**
	 * 后序遍历，非递归版本。
	 * 栈：
	 * 先序（中 左 右） -> 调整为 中 右 左 -> 利用辅助栈倒序 -> 左 中 右
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static void posOrderUnRecur1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<Node>();
			Stack<Node> printStack = new Stack<Node>();
			
			s1.push(head);
			while (!s1.isEmpty()) {
				head = s1.pop();
				printStack.push(head);
				if (head.left != null) {
					s1.push(head.left);
				}
				if (head.right != null) {
					s1.push(head.right);
				}
			}
			
			while (!printStack.isEmpty()) {
				System.out.print(printStack.pop().value + " ");
			}
		}
		System.out.println();
	}

	// TODO 待学
	public static void posOrderUnRecur2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);
		
		// print binary tree struct
		Node.printTree(head);

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderRecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println();
		System.out.print("pos-order: ");
		posOrderRecur(head);
		System.out.println();

		// unrecursive
		System.out.println("============unrecursive=============");
		preOrderUnRecur(head);
		inOrderUnRecur(head);
		posOrderUnRecur1(head);
		posOrderUnRecur2(head);

	}

}


