package Synchronisation;

import static Synchronisation.CounterEx2.count;

class CounterEx2 extends  Thread{
    static int count=0;
    public static synchronized void incrementCount(){  // Synchronized method
        count++;
    }

    /*
    public  void incrementCount(){
         synchronized (CounterEx2.class) {   // Synchronized block
        count++;
        }
    }
     */
    public void run(){
        for(int i=0;i<1000;i++){
            incrementCount();
        }
    }
}
public class SynchronizedEx {
    public static void main(String args[]) throws InterruptedException {
        CounterEx2 t1=new CounterEx2();
        CounterEx2 t2=new CounterEx2();
        // both thread call run method -> inside run method we are calling incrementCount simultaneously
         t1.start();
         t2.start();

         t1.join();
         t2.join();
        // after t1 and t2 finish then we are printing count , that why join is used

        System.out.println("Counter = "+ count);

    }
}
