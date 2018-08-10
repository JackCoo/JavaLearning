package nowcoder.choice;
/** 在继承中代码的执行顺序为：
 * 	1.父类静态对象，父类静态代码块
 * 	2.子类静态对象，子类静态代码块
 * 	3.父类非静态对象，父类非静态代码块
 * 	4.父类构造函数
 * 	5.子类非静态对象，子类非静态代码块
 * 	6.子类构造函数
 * 	对于本题来说：在只想new Sub(5)的时候，父类先初始化了 int flag = 1，然后执行父类的构造函数Super（），
 * 	父类构造函数中执行的test（）方法，因子类是重写了test（）方法的，
 * 	因此父类构造函数中的test（）方法实际执行的是子类的test（）方法，所以输出为Sub.test() flag=1，
 * 	接着执行子类构造函数Sub(5) 将flag赋值为5，因此输出结果Sub.Sub() flag=5。最终选择了A。
*/
public class ObjectInit {
	class Super {
		int flag = 1;

		Super() {
			test();
		}

		void test() {
			System.out.println("Super.test() flag=" + flag);
		}
	}
	
	class Sub extends Super {
		Sub(int i) {
			flag = i;
			System.out.println("Sub.Sub()flag=" + flag);
		}

		void test() {
			System.out.println("Sub.test()flag=" + flag);
		}
	}

	public static void main(String[] args) {
		new ObjectInit().new Sub(5);
	}
}