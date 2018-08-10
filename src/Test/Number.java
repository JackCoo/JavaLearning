package Test;
import org.junit.Test;

public class Number {

	/**
	 * 基本类型的初始化、类型转换
	 * https://www.nowcoder.com/questionTerminal/f838b38081b942fba7ab2869f71ad071
	 * 
	 * https://www.cnblogs.com/liujinhong/p/6005714.html
	 * 
	 * 知识点：
	 * 1.Java中数字直接量的默认类型为，整型（int），浮点型（double）。直接量赋值时，对于整型直接量，若其在目标类型的范围内，则可以发生自动转型。
	 * 2.从低位类型->高位类型，自动转换（隐式转换）；反之，需要手动强制转换（显式转换）。即存储范围小的类型可以自动转换到范围大的。
	 * 3.byte=1b, char=2b, short=2b, int=4b, long=8b, float=4b, double=8b
	 * 4.boolean不能与其他类型转化，包括强制转换。
	 * 5.char 为无符号整型，因为负数问题，byte不能自动转型到char，short和char之间也不能自动转换。直接量看范围。
	 * 6.在直接量超出int范围、需要装箱的情况下，需要指定直接量的类型，例如：加L。
	 * 
	 * 当进行数学运算时，数据类型会自动发生提升到运算符左右之较大者
	 */
	public void numberInit() {
		/**
		 * 原题
		 */
		long test = 012;//int->long
		float f = -412;//int->float
//		int other = (int) true;//编译错误，
		double d = 0x12345678;//十六进制表示的int类型赋值给double
//		byte b = 128;//编译错误，超出byte范围，cannot convert from int to byte
		
		/**
		 * 补充
		 */
//		Long test2 = 012;//编译错误，不能对int装箱为Long
//		float f2 = -412.3;//编译错误，double->float，需要强制转换。
//		long l2 = 11111111111;//编译错误，字面量超出int范围
//		char ca = (byte)-9;//编译错误，char无符号
		char cb = (byte)9;
		byte ba = 9;
//		cb = ba;//编译错误，因为char无符号，导致byte不能自动转型到char
//		int ia = test;//编译错误，非直接量，从大到小，不能自动转型。
		short sa = 12;//直接量，若在目标类型范围内，会自动转型。
	}
	
	/**
	 * 数值、对象比较
	 * https://www.nowcoder.com/questionTerminal/ccaf97ecf8f64e73a8982a6e8c792d4e
	 * 
	 * 知识点：
	 * 1.不同类型不能使用==比较，equal方法会先判断类型，然后拆箱比较数值。
	 * 2.Integer在初始化时会在内部类IntegerCache中缓存-128-127（max可通过配置自定义） 的Integer对象。
	 * 3.Integer直接赋值，自动装箱，调用valueOf方法从缓存中获取对象，若无则使用new 创建。ps，拆箱是调用xxxValue方法。
	 * 4.基本类型和包装类比较（equal、==），自动拆箱，比较值。
	 */
	@Test
	public void nubmerCompare() {
		Integer s = new Integer(9);
		Integer t = new Integer(9);
		Integer a = 9;
		Integer b = 9;
		Long u = new Long(9);
		
		System.out.println("s == t:" + (s == t));//new会重新创建对象，引用地址不同，==不相等。
		System.out.println("s == a:" + (s == a));//s是new出来的，a是从缓存中获取的，不==。
		System.out.println("a == b:" + (a == b));//a、b均从缓存中获取的。
		System.out.println("s == 9:" + (s == 9));//Integer拆箱，然后比较数值。
//		System.out.println(s == u);//编译错误，不同类型对象之间不能使用==比较。
		System.out.println("s.equals(u):" + s.equals(u));//false，equal方法会先使用instanceof判断类型，相同才会比较数值。
		System.out.println("s.equals(9):" + s.equals(9));
	
	}
}

class Hello{
	
}

class Hello3{
	
}
