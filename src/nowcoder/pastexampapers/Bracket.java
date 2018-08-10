package nowcoder.pastexampapers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * 合法的括号匹配序列被定义为:
 * 1. 空串""是合法的括号序列
 * 2. 如果"X"和"Y"是合法的序列,那么"XY"也是一个合法的括号序列
 * 3. 如果"X"是一个合法的序列,那么"(X)"也是一个合法的括号序列
 * 4. 每个合法的括号序列都可以由上面的规则生成
 * 例如"", "()", "()()()", "(()())", "(((())))"都是合法的。 东东现在有一个合法的括号序列s,一次移除操作分为两步:
 * 1. 移除序列s中第一个左括号
 * 2. 移除序列s中任意一个右括号.保证操作之后s还是一个合法的括号序列
 * 东东现在想知道使用上述的移除操作有多少种方案可以把序列s变为空
 * 如果两个方案中有一次移除操作移除的是不同的右括号就认为是不同的方案。
 * 例如: s = "()()()()()",输出1, 因为每次都只能选择被移除的左括号所相邻的右括号.
 * s = "(((())))",输出24, 第一次有4种情况, 第二次有3种情况, ... ,依次类推, 4 * 3 * 2 * 1 = 24 
 * 
 * 输入包括一行,一个合法的括号序列s,序列长度length(2 ≤ length ≤ 20).
 * 输出一个整数,表示方案数
 * 
 * 思路：
 * 	由于n较小，可使用复杂度较高的算法。
 * 	递归思路：每次删除当前(并尝试删除存在的)，若删除后合法，继续递归下去，最终形成一条完成的删除链，为一个有效方案。
 * 
 * 注意点：
 * 	递归函数不能传递数组，后续的递归会改变其值。
 * 
 * 	递归形成类似于树结构，父结点汇集了其结果和。不要被题目中相乘迷惑。
 * 
 * 
 * @author Yanjie
 *
 */
public class Bracket{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
         
        try {
			in = new Scanner(new FileInputStream("F:\\java\\JavaLearning\\src\\nowcoder\\pastexampapers\\Bracket"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        String s = in.nextLine();  
        System.out.println(process(s));
        
        in.close();
    }
 

    
    private static int process(String s){
    	
    	// base case
        if (s.length() == 0){
            return 1;
        } else if (s.startsWith(")")) {
            return 0;
        } 

        int midResult = 0;
        s = s.substring(1);
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ')'){
            	String tempS = s.substring(0, i) + s.substring(i + 1); 
                boolean isLegal = isLegal(tempS);
                if (isLegal){
                    midResult += process(tempS);
                }
            }
        }
        return midResult;
    }
     
    private static boolean isLegal(String s){

        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')' && !stack.isEmpty()){
                stack.pop();
            } else {
                return false;
            }
        }
        return true;
        
        
    }
}