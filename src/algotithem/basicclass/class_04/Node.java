package algotithem.basicclass.class_04;

/**
 * Node类，包含了打印方法。
 * 
 * @author Yanjie
 *
 */
public class Node {
	
	public int value;
	public Node left;
	public Node right;

	public Node(int data) {
		this.value = data;
	}
	
	
	/**
	 * 打印二叉树
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}
}
