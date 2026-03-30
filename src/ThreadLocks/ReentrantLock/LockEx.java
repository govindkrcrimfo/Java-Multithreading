package ThreadLocks.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class  LockEx1{
    int count=0;
    private Lock lock=new ReentrantLock();
    // or private ReentrantLock lock=new ReentrantLock();
    public void increment(){
        lock.lock(); // lock will done -> others thread will wait untill lock is unlock() in finally
        try {
            count++;
        }catch (Exception e){
            // caught any expection in critical operations
        }
        finally {
            lock.unlock();  // must unlock ,else deadlock condition arises
        }
    }
}
public class LockEx {
    public static void main(String agrs[]) throws InterruptedException {
        LockEx1 lockEx1 = new LockEx1();
        Runnable incrementTask1 = new Runnable() {  // anonymous call
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                    lockEx1.increment();
            }
        };
        Runnable incrementTask2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                    lockEx1.increment();
            }
        };
        Thread t1 = new Thread(incrementTask1); // takes runnable object
        Thread t2 = new Thread(incrementTask2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();  // t1 & t2 finish then print count
        System.out.println("Final counter value is "+lockEx1.count);
    }

}
