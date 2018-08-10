package algotithem.basicclass.class_03;

import java.util.Stack;

/**
 * 实现一个拥有pop、push、getMin操作的栈，各方法时间复杂度为o(1)。
 * 使用了Stack类
 * 
 * @author Yanjie
 *
 */
public class Code_02_GetMinStack {
	
	/**
	 * 双栈实现1：较2节约了最小值栈空间。特点，比较弹出。
	 * 
	 * @author Yanjie
	 *
	 */
	public static class MyStack1 {
		
		/**
		 * 普通数据栈
		 */
		private Stack<Integer> stackData;
		
		/**
		 * 最小值栈，保存了当前最小值信息。
		 */
		private Stack<Integer> stackMin;

		public MyStack1() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		/**
		 * 压栈
		 * 
		 * @author Yanjie
		 *
		 * @param newNum
		 */
		public void push(int newNum) {
			
			/**
			 * 数据栈：正常压入
			 * 最小值栈：若当前入栈数 小于等于 最小值栈顶，则压入当前数，否则不更新最小值栈。
			 */
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum <= this.getmin()) {
				this.stackMin.push(newNum);
			}
			this.stackData.push(newNum);
		}

		/**
		 * 弹出栈顶
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			
			/**
			 * 数据栈：正常弹出
			 * 最小值栈：等于弹出，数据栈弹出值=最小值栈顶值 时弹出，否则不操作。
			 */
			int value = this.stackData.pop();
			if (value == this.getmin()) {
				this.stackMin.pop();
			}
			return value;
		}

		/**
		 * 获取当前栈的最小值，但不删除。
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	/**
	 * 双栈实现2，特点，同步弹出。
	 * 
	 * @author Yanjie
	 *
	 */
	public static class MyStack2 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack2() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		/**
		 * 压栈
		 * 
		 * @author Yanjie
		 *
		 * @param newNum
		 */
		public void push(int newNum) {
			
			/**
			 * 最小值栈：入栈数小于最小值栈顶，压入当前入栈数；否则，重复压入最小值栈顶。保存了最小值及其出栈距离信息。
			 */
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum < this.getmin()) {
				this.stackMin.push(newNum);
			} else {
				int newMin = this.stackMin.peek();
				this.stackMin.push(newMin);
			}
			this.stackData.push(newNum);
		}

		/**
		 * 出栈
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			
			/**
			 * 同时弹出，返回数据栈值。
			 */
			this.stackMin.pop();
			return this.stackData.pop();
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	public static void main(String[] args) {
		MyStack1 stack1 = new MyStack1();
		stack1.push(3);
		System.out.println(stack1.getmin());
		stack1.push(4);
		System.out.println(stack1.getmin());
		stack1.push(1);
		System.out.println(stack1.getmin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getmin());

		System.out.println("=============");

		MyStack1 stack2 = new MyStack1();
		stack2.push(3);
		System.out.println(stack2.getmin());
		stack2.push(4);
		System.out.println(stack2.getmin());
		stack2.push(1);
		System.out.println(stack2.getmin());
		System.out.println(stack2.pop());
		System.out.println(stack2.getmin());
	}

}
