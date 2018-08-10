package algotithem.basicclass.class_04;

/**
 * 判断二叉树是否平衡
 * 	平衡：任意节点的左子树与右子树高度差不大于1.
 * 
 * @author Yanjie
 *
 */
public class Code_06_IsBalancedTree {
	
	/**
	 * 判断二叉树是否平衡
	 * @author Yanjie
	 *
	 * @param head
	 * @return -1-不平衡，否则，返回二叉树高度
	 */
	public static int isBalanceVer2(Node head) {
		if (head == null) {
			return 0;
		}
		int leftTreeHeight = isBalanceVer2(head.left);
		if (leftTreeHeight < 0) {
			return -1;
		}
		int rightTreeHeight = isBalanceVer2(head.right);
		if (rightTreeHeight < 0) {
			return -1;
		}
		if (Math.abs(leftTreeHeight - rightTreeHeight) > 1) {
			return -1;
		} else {
			return Math.max(leftTreeHeight, rightTreeHeight) + 1;
		}
	}

	/**
	 * 另外版本，二叉树是否平衡
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	@Deprecated
	public static boolean isBalance(Node head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}

	public static int getHeight(Node head, int level, boolean[] res) {
		if (head == null) {
			return level;
		}
		int leftTreeHeight = getHeight(head.left, level + 1, res);
		if (!res[0]) {
			return level;
		}
		int rightHeight = getHeight(head.right, level + 1, res);
		if (!res[0]) {
			return level;
		}
		if (Math.abs(leftTreeHeight - rightHeight) > 1) {
			res[0] = false;
		}
		return Math.max(leftTreeHeight, rightHeight);
	}

	public static void main(String[] args) {
		
		// 平衡
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		
		// 添加不平衡分支
		head.right.right.left = new Node(7);
		head.right.right.left.left = new Node(7);
		
		Node.printTree(head);
		System.out.println("是否平衡：" + isBalanceVer2(head));

	}

}



