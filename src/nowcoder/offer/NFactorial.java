package nowcoder.offer;

/**
 * 计算N的阶乘，不使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
 * 
 * 思路：
 * 		递归 + 短路求值（逻辑与的短路特性实现递归终止）
 * 		if (n > 0) => (n > 0) &&
 * @author Yanjie
 *
 */
public class NFactorial {
    public int Sum_Solution(int n) {
        int sum = n;
        
        /**
         * temp变量可否去掉？sum > 0 恒成立
         */
        boolean temp = (n > 0) && (sum += Sum_Solution(n - 1)) > 0;
        return sum;
   }
    
}
