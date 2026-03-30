package ThreadLocks.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TryLockEx1{
    int count=0;
    int skipCount=0; // no of times other threads skip ( move to else block due to tryLock() )
    private Lock lock=new ReentrantLock();
    public void increment(){
        if(lock.tryLock()){  // lock will done , othere thread will not wait they will move to else block
        try{
            count++;
        }catch (Exception e){
            // caught any expection in critical operations
        }finally {
            lock.unlock();
        }
        }else{
            skipCount++;
            System.out.println("Could not get lock ");
        }
    }
}
public class TryLockEx {
    public static void main(String args[]) throws InterruptedException {
        TryLockEx1 tryLockEx1=new TryLockEx1();
        Runnable incrementTask1 = new Runnable() {  // anonymous call
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                    tryLockEx1.increment();
            }
        };
        Runnable incrementTask2 = new Runnable() {  // anonymous call
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                    tryLockEx1.increment();
            }
        };

        Thread t1 = new Thread(incrementTask1); // takes runnable object
        Thread t2 = new Thread(incrementTask2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();  // t1 & t2 finish then print count
        System.out.println("Final counter value is "+tryLockEx1.count);
        System.out.println("Number of times others threads skip is "+tryLockEx1.skipCount);
        /*
          output will not exactly 2000 it will be less than that (1775,1896 etc )
          because trylock() is lock then  others thread will skip ( move to else block() )
         */
    }
}
