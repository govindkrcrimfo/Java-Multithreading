package ThreadLocks.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class  ReadWriteLockEx1{
    public  int count=0;
    final ReadWriteLock lock=new ReentrantReadWriteLock();
    final Lock readLock=lock.readLock();
    final Lock writeLock=lock.writeLock();
    void increment(){
       writeLock.lock();  // writeLock ->  allow to write only one thread at a time
       try {
           count++;
       }
       finally {
           writeLock.unlock();
       }
    }
    int getCount(){
        readLock.lock(); // readLock -> allow to read multiple thread concurrently
        try {
            return count;
        }
        finally {
            readLock.unlock();
        }
    }

}
public class ReadWriteLockEx {
    public static void main(String args[]) throws InterruptedException {
        ReadWriteLockEx1 readWriteLockEx1=new ReadWriteLockEx1();
        Runnable writeTask=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    readWriteLockEx1.increment();
                }
            }
        };

        Runnable  readTask=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<200;i++){
                    System.out.println("count is "+readWriteLockEx1.getCount());
                }
            }
        };
        Thread writeThread=new Thread(writeTask);
        Thread readThread=new Thread(readTask);
        Thread writeThread1=new Thread(writeThread);
        writeThread.start();
        readThread.start();
        writeThread1.start();

        writeThread1.join();
        writeThread.join();
        readThread.join();

        System.out.println("final count "+readWriteLockEx1.getCount());

    }
}
