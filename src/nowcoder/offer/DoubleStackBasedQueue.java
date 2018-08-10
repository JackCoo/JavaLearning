package nowcoder.offer;
import java.util.EmptyStackException;
import java.util.Stack;
/**
 * 双栈实现队列
 * 	  （全部）倒出弹出（全部）
 * @author Yanjie
 *
 */
public class DoubleStackBasedQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if (stack2.size() < 1) {
        	while (!stack1.isEmpty()) {
        		stack2.push(stack1.pop());
        	}
        }
        if (!stack2.isEmpty()) {
        	return stack2.pop();
        } else {
        	throw new EmptyStackException();
        }
    }
}
