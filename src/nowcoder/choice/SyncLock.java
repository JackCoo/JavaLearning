package nowcoder.choice;
//我觉得误区有两个：一个是run和start区别，Thread.run()是调用方法，Thread. start()是启动线程；另一个是锁持有问题。这个题是调用方法，和多线程就无关。本题只有一个线程，持有HelloSogou.class锁。那么，就是另一个问题：同步方法调用另一个同步方法的锁问题？
//
//public synchronized void methodA(int a, int b){}
//public synchronized void methodB(int a）{
//     methodA(a, 0);
//}
//首先要明白两个问题，1.锁的对象是谁？2.谁持有了锁？
//假设方法A和B是在同一个类Test中的两个方法。
//Test t=new Test(); 
//t.methodB();
//调用methodB()方法，获得锁，锁是对象t；锁谁持有？当前线程（不可以说是methodB持有该锁），methodB又调用methodA，也需要锁t，该线程已持有t，当然可以直接调用methodA。
//
//类比到此题，只有一个主线程，调用main，持有HelloSogou.class锁，那当然可以直接调用Sogou方法。
//
//第二，如果是t.statrt()，那么这个题，静态同步函数的锁是该类的字节码文件.class。此题中，main函数和Sogou方法都是static的，所以持有相同锁 HelloSogou.class ，那么，在main线程（main 是一个线程也是一个进程 ）中又开了一个线程，调用Sogou方法，锁会冲突。
//
//我的分析是：调用main函数（一个线程），main函数开启另一个线程，并启动，但是main函数和Sogou方法是同一个锁，所以main函数执行完毕后才会释放锁，Sogou方法才会执行，这就是为什么，换成start，是HelloSogou。
//第三，将Sogou方法的锁改为其他.class锁，那么，HelloSogou和SogouHello都可能出现。因为没有互斥现象了，变为抢占式的了。
public class SyncLock {
	public static synchronized void main(String[] a) {
		Thread t = new Thread() {
			public void run() {
				Sogou();
			}
		};
		t.start();
		System.out.print("Hello");
	}

	static synchronized void Sogou() {
		System.out.print("Sogou");
	}
}
