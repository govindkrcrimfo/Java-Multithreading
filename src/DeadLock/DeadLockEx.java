package DeadLock;
/*
  Dead Lock -> Deadlock is a situation in multithreading where two or more threads
  are waiting for each other forever, and none of them can proceed.

** Conditions for Deadlock (Important for interview) **
    1.Mutual Exclusion → resource is locked
    2.Hold and Wait → thread holds one lock, waits for another
    3.No Preemption → lock can’t be forcefully taken
    4.Circular Wait → circular dependency
 */
public class DeadLockEx {
    static final  Object lock1=new Object();
    static final  Object lock2=new Object();
    public static  void main(String args[]) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 acquired lock 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println("Thread 1 acquired lock 2");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 acquired lock 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1) {
                    System.out.println("Thread 2 acquired lock 1");
                }
            }
        });
        t1.start();
        t2.start();
    }
}

