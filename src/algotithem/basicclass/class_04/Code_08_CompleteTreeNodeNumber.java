package algotithem.basicclass.class_04;

/**
 * 计算完全二叉树的节点数，时间复杂度小于O(N)，为O((logN)^2)
 * 
 * 利用先验知识：
 * 	1.高为h的满二叉树节点数为：2^n - 1
 * 	2.完全二叉树，只在最后一层缺少右边若干节点，意味着其
 * 
 * @author Yanjie
 *
 */
public class Code_08_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 统计完全二叉树的节点数
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		
		// mostLeftLevel(head, 1) = 二叉树的高度
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	/**
	 * 计算以node为根的完全二叉树节点数。
	 * 
	 * @author Yanjie
	 *
	 * @param node 当前节点
	 * @param level 当前节点的高度
	 * @param height 整棵树的高度
	 * @return
	 */
	public static int bs(Node node, int level, int height) {
		
		// 以叶节点为根的子树节点数为1
		if (level == height) {
			return 1;
		}
		
		// 当前节点右子树的最左节点为整棵树的叶节点=>左子树为满二叉树。利用公式计算左子树节点，递归计算右子树。
		if (mostLeftLevel(node.right, level + 1) == height) {
			return (1 << (height - level) - 1) + 1  + bs(node.right, level + 1, height);// 1 << n = 2^n
		} else {
			// 右子树的最左节点不是叶节点=>左子树情况未知，但右子树为高度差1的满二叉树。 
			return (1 << (height - level - 1)) + bs(node.left, level + 1, height);
		}
	}

	/**
	 * 统计当前节点最左节点的高度，从1编号。
	 * 
	 * @author Yanjie
	 *
	 * @param node 当前节点
	 * @param level 当前节点所在的高度（层）
	 * @return
	 */
	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		
		System.out.println(nodeNum(head));

	}

}
