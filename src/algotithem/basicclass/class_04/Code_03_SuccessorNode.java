package algotithem.basicclass.class_04;

/**
 * 二叉树的后继节点
 * 	后继：中序遍历时的下一个节点
 * 	前驱：中序遍历时的前一个节点
 * 
 * 中序遍历顺序： 左子树  中  右子树，考虑
 * 	1.当前节点位于  中  这个位置：则后继节点为其右子树的最左节点
 * 	2.当前节点位于  左  这个位置：顺序向上，当当前节点是其父的左节点（以此构成一棵左子树）时，其父为  中  位置，即为后继节点。
 * 
 * 前驱节点：
 * 	与后继节点的解法相反。
 * 	1.有左子树？左子树最右节点
 * 	2.没有左子树？顺序向上，当前节点是其父的右子节点时，其父为前驱。
 * 
 * PS：解题先由整体出发，勿陷于细节。有大局观。
 * 
 * @author Yanjie
 *
 */
public class Code_03_SuccessorNode {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 获取指定节点的后继节点
	 * 
	 * @author Yanjie
	 *
	 * @param node
	 * @return
	 */
	public static Node getSuccessorNode(Node node) {
		if (node == null) {
			return node;
		}
		
		if (node.right != null) {
			return getLeftMost(node.right);
		} else {
			Node parent = node.parent;
			while (parent != null && parent.left != node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

	/**
	 * 获取当前子树的最左节点
	 * 
	 * @author Yanjie
	 *
	 * @param node
	 * @return
	 */
	public static Node getLeftMost(Node node) {
		if (node == null) {
			return node;
		}
		
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getSuccessorNode(test));
	}

}