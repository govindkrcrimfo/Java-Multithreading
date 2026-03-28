package ThreadVsRunnable;
//Thread example
class ThreadEx extends  Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
        System.out.println("Thread Ex is Running...");
    }
}

// Runnable example
class RunnableEx implements  Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Runnable Ex is Running...");
    }
}
public class ThreadVsRunnable {
    public static void main(String args[]){

        // print main Thread -> main thread is responsible for executing all the code inside the main() method.
        System.out.println(Thread.currentThread().getName()+" thread");

        // Thread call
        ThreadEx t=new ThreadEx();
        t.start();

        // Runnable call
        RunnableEx runnableEx=new RunnableEx();
        Thread t2=new Thread(runnableEx);
        t2.start();
    }
}

