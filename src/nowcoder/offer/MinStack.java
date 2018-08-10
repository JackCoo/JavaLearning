package nowcoder.offer;

import java.util.LinkedList;

/**
 * 双栈实现最小值栈，可以在o(1)内获取到当前栈的最小值
 * 
 *     使用LinkedList实现栈，Stack类不推荐使用。
 * 
 * @author Yanjie
 *
 */
public class MinStack {

	private LinkedList<Integer> dataStack = new LinkedList<>();
	private LinkedList<Integer> minStack = new LinkedList<>();
	
	public void push(int node) {
	     dataStack.addFirst(node); 
	     if (minStack.size() == 0) {
	    	 minStack.addFirst(node);
	     } else if (node <= minStack.getFirst()) {
	    	 minStack.addFirst(node);
	     } else {
	    	 minStack.addFirst(minStack.getFirst());
	     }
    }
    
    public void pop() {
        dataStack.removeFirst();
        minStack.removeFirst();
    }
    
    public int top() {
        return dataStack.peekFirst();
    }
    
    public int min() {
        return minStack.getFirst();
    }
}
