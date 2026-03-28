package ThreadLifeCycle;
class  ThreadEx1 extends Thread{
    public void run(){
        System.out.println("Thread run method called ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
public class ThreadLifeCycleEx {
    public static void  main(String []args) throws InterruptedException {
        ThreadEx1 threadEx1=new ThreadEx1();
        System.out.println(threadEx1.getState()); // NEW State

        threadEx1.start();
        System.out.println(threadEx1.getState()); // Runnable
        Thread.sleep(1000);
        System.out.println(threadEx1.getState()); // Waiting / Blocked
        threadEx1.join();
        System.out.println(threadEx1.getState()); // Terminated

    }
}
