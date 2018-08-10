package algotithem.basicclass.class_03;

/**
 * 基于数组的 队列 栈
 * 
 * @author Yanjie
 *
 */
public class Code_01_Array_To_Stack_Queue {

	/**
	 * 使用数组实现定容栈
	 * 
	 * @author Yanjie
	 *
	 */
	public static class ArrayStack {
		private Integer[] arr;
		
		/**
		 * 栈元素个数
		 */
		private Integer size;

		public ArrayStack(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
		}

		/**
		 * 获取但不删除栈顶元素
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public Integer peek() {
			if (size == 0) {
				return null;
			}
			return arr[size - 1];
		}

		/**
		 * 添加元素
		 * 
		 * @author Yanjie
		 *
		 * @param obj
		 */
		public void push(int obj) {
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			arr[size++] = obj;
		}

		/**
		 * 获取并删除栈顶元素
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public Integer pop() {
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			return arr[--size];
		}
	}

	/**
	 * 基于数组的队列
	 * 
	 * @author Yanjie
	 *
	 */
	public static class ArrayQueue {
		private Integer[] arr;
		
		/**
		 * 队列大小，引入改变了用以简化队首和队尾的边界判断。
		 */
		private Integer size;
		private Integer first;
		private Integer last;

		public ArrayQueue(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
			
			/**
			 * 初始值，队首队尾同时指向0
			 */
			first = 0;
			last = 0;
		}

		/**
		 * 获取而不删除队首元素
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public Integer peek() {
			if (size == 0) {
				return null;
			}
			return arr[first];
		}

		/**
		 * 向队尾添加元素
		 * 
		 * @author Yanjie
		 *
		 * @param obj
		 */
		public void push(int obj) {
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			size++;
			arr[last] = obj;
			
			/**
			 * 更新队尾指针，自增或掉头开始
			 */
			last = last == arr.length - 1 ? 0 : last + 1;
		}

		/**
		 * 获取并删除队首元素
		 * 
		 * @author Yanjie
		 *
		 * @return
		 */
		public Integer poll() {
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			size--;
			int tmp = first;
			first = first == arr.length - 1 ? 0 : first + 1;
			return arr[tmp];
		}
	}

	public static void main(String[] args) {

	}

}
