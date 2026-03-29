package ThreadMethods;
class ThreadMethodEx extends Thread{
   public void run(){     // run method
       for (int i=0;i<10;i++){
           Thread.yield();   // yield method -> give chance to another thread
           System.out.println(Thread.currentThread().getName()+" Run method !!");
       }
   }
}
public class ThreadMethods {
    public static void main(String args[]) throws InterruptedException {
       ThreadMethodEx threadMethodEx=new ThreadMethodEx();
       ThreadMethodEx threadMethodEx2=new ThreadMethodEx();
        threadMethodEx.start();   // start method
        threadMethodEx2.start();

        Thread.sleep(3000);  // sleep method
        // threadMethodEx.interrupt();  // interrupt method -> request to stop or pause
        System.out.println("Thread after sleep ");
        threadMethodEx.join();    // join method -> once threadMethodEx works completed then main thread starts
    }
}
