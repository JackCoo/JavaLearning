package algotithem.basicclass.class_03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 队列 栈 的互相实现.
 * 
 * @author Yanjie
 *
 */
public class Code_03_StackAndQueueConvert {

	/**
	 * 双栈实现队列
	 * 
	 * 倒序弹出,push pop双栈
	 * 
	 * @author Yanjie
	 *
	 */
	public static class TwoStacksQueue {
		private Stack<Integer> stackPush;
		private Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		/**
		 * 添加
		 * 
		 * @author Yanjie
		 *
		 * @param pushInt
		 */
		public void push(int pushInt) {
			stackPush.push(pushInt);
		}

		/**
		 * 弹出
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public int poll() {
			
			/**
			 * push栈为倒序结构,将其弹出至pop栈可由倒序转顺序.然后弹出pop栈即可,当pop栈空时,再次将push栈全部弹出至pop栈.
			 */
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.pop();
		}

		public int peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.peek();
		}
	}

	/**
	 * 双队列 实现 栈
	 * 
	 * 复制见底弹出
	 * 
	 * @author Yanjie
	 *
	 */
	public static class TwoQueuesStack {
		private Queue<Integer> data;
		private Queue<Integer> help;

		public TwoQueuesStack() {
			data = new LinkedList<Integer>();
			help = new LinkedList<Integer>();
		}

		/**
		 * 压栈
		 * 
		 * @author Yanjie
		 *
		 * @param pushInt
		 */
		public void push(int pushInt) {
			data.add(pushInt);
		}

		/**
		 * 弹出
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public int pop() {
			if (data.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			
			/**
			 * 复制弹出:由于队列只能弹出队首元素,故将当前队列复制至辅助队列.只剩下一个元素,然后弹出即可.
			 * 最后交换data和help的身份(引用).
			 */
			while (data.size() > 1) {
				help.add(data.poll());
			}
			int res = data.poll();
			swap();
			return res;
		}

		/**
		 * 获取栈顶元素
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public int peek() {
			if (data.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			while (data.size() != 1) {
				help.add(data.poll());
			}
			int res = data.poll();
			
			/**
			 * 复制弹出后,再加入.
			 */
			help.add(res);
			swap();
			return res;
		}
		
		private void swap() {
			Queue<Integer> tmp = help;
			help = data;
			data = tmp;
		}
	}   

}
