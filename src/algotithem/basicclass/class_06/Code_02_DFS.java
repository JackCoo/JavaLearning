package algotithem.basicclass.class_06;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先搜索
 * 
 * 栈 + set：源节点入栈，每弹出一个节点，若其邻居节点有未入栈节点，将当前节点和邻居节点依次入栈，直至栈空。
 * 	遍历需要完成的额外操作在入set时执行，以唯一。
 *  只需要Node类型即可，不需要Graph类。
 * @author Yanjie
 *
 */
public class Code_02_DFS {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		stack.add(node);
		set.add(node);
		
		// do something
		System.out.println(node.value);
		
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					stack.push(cur);
					stack.push(next);
					set.add(next);
					
					// do something
					System.out.println(next.value);
					
					break;
				}
			}
		}
	}

}
