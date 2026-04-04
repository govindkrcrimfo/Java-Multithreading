package ThreadLocks.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
   Reentrancy
   Same thread can enter the lock multiple times
   It will NOT get stuck (no deadlock)
   But it must unlock the same number of times
 */
class LockEx3{
    final Lock lock=new ReentrantLock();
    public  void outerMethod(){
        lock.lock();
        try {
            System.out.println("Inside the Outer method "+Thread.currentThread().getName());
            innerMethod();
        }finally {
            lock.unlock();
        }
    }
    public  void innerMethod(){
        lock.lock();
        try {
            System.out.println("Inside the Inner method "+Thread.currentThread().getName());
        }finally {
            lock.unlock();
        }
    }
}
public class LockEx2 {
    public static void  main(String args[]) throws InterruptedException {
      LockEx3 lockEx3 =new LockEx3();
      Runnable r=new Runnable() {
          @Override
          public void run() {
                  lockEx3.outerMethod();
          }
      };
      Thread t1=new Thread(r);
      Thread t2=new Thread(r);

      t1.start();
      t2.start();

      t1.join();
      t2.join();
        System.out.println("t1 and t2 Thread finished  !!");
    }
}
