package nowcoder.offer;

import Test.Test;

/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * @author Yanjie
 *
 */
public class ReplaceBlank {
	
	public String replaceSpace(StringBuffer str) {
        for (int index = 0; index < str.length(); index++) {
        	if (str.charAt(index) == ' ') {
        		str.deleteCharAt(index);
        		str.insert(index, "%20");
        	}
        }
		return str.toString();
    }
	
	public static void main(String[] args) {
		System.out.println(new ReplaceBlank().replaceSpace(new StringBuffer("123 456")));
	}
	
}
