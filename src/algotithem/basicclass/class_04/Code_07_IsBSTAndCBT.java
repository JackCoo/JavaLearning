package algotithem.basicclass.class_04;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 判断二叉树类型：
 * 		搜索二叉树：中序遍历 = 顺序遍历，任意节点的值大于左子树所有节点而小于右子树。
 * 		完全二叉树：最后一层只缺少若干右边节点，其他层节点数为最大值。满二叉树是完全二叉树更严格定义，要求最后一层也达到最大值。
 * @author Yanjie
 *
 */
public class Code_07_IsBSTAndCBT {


	/**
	 * 判断是否为搜索二叉树
	 * 
	 * Morris 遍历
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			if (pre != null && pre.value > cur1.value) {
				res = false;
			}
			pre = cur1;
			cur1 = cur1.right;
		}
		return res;
	}

	/**
	 * 判断二叉树是否为搜索二叉树
	 * 
	 * 利用中序遍历，非递归。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 */
	public static boolean isBSTVer2(Node head) {
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			
			/**
			 *  cur != null，入栈，cur = left  ===> 所有左节点入栈
			 *  cur == null，出栈打印，cur = right  ===> 打印当前节点，切换到右子树
			 */
			int preValue = Integer.MIN_VALUE;
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					if (head.value > preValue) {
						preValue = head.value;
						head = head.right;
					} else {
						return false;
					}
					
				}
			}
		}
		return true;
	}

	/**
	 * 判断二叉树是否为完全二叉树。
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static boolean isCBT(Node head) {
		if (head == null) {
			return true;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		
		// 标记是否进入全叶节点检测
		boolean leaf = false;
		
		// 避免在while里重复创建对象
		Node left = null;
		Node right = null;
		
		queue.offer(head);
		
		// 最后一层节点4种情况
		while (!queue.isEmpty()) {
			head = queue.poll();
			left = head.left;
			right = head.right;
			if ((leaf && (left != null || right != null)) // 仅缺少右边若干节点 
					|| (left == null && right != null)) {
				return false;
			}
			if (left != null) {
				queue.offer(left);
			}
			if (right != null) { // left != null && right != null
				queue.offer(right);
			} else { // right == null, left任意
				leaf = true;
			}
		}
		return true;
	}

	
	/**
	 * 判断二叉树是否为完全二叉树。
	 * 
	 * 未经过严格测试
	 * 
	 * @author Yanjie
	 *
	 * @param head
	 * @return
	 */
	public static boolean isCBTVer2(Node head) {
		if (head == null) {
			return true;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(head);
		
		// 最后一层节点4种情况
		while (!queue.isEmpty()) {
			Node parentNode = queue.poll();
			if (parentNode.left == null && parentNode.right != null) {
				return false;
			} else if (parentNode.left != null && parentNode.right != null) {
				queue.offer(parentNode.left);
				queue.offer(parentNode.right);
			} else if (parentNode.left != null && parentNode.right == null) {
				queue.offer(parentNode.left);
				while (!queue.isEmpty()) {
					parentNode = queue.poll();
					if (!(parentNode.left == null && parentNode.right == null)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);

		// 添加分支为非完全二叉树
		head.right.left.left = new Node(0);
		
		Node.printTree(head);
		System.out.println("是否为搜索二叉树：" + isBST(head));
		System.out.println("是否为完全二叉树：" + isCBTVer2(head));

	}
}