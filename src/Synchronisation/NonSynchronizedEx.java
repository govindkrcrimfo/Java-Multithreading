package Synchronisation;
class CounterEx1 extends Thread{
    static int count=0;
    public  void incrementCount(){  // non synchronnised method , multiple thread can access at same time
        count++;
    }
    public void run(){
        for(int i=0;i<1000;i++){
          incrementCount();
        }
    }
}
public class NonSynchronizedEx {
    public static void main(String args[]) throws InterruptedException {
        CounterEx1 t1=new CounterEx1();
        CounterEx1 t2=new CounterEx1();
        // both thread call run method -> inside run method we are calling incrementCount simultaneously
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        // after t1 and t2 finish then we are printing count , that why join is used

        System.out.println("Counter = "+ CounterEx1.count);
        /*
        Output should 2000 , but we will get 1756 , 1850 , anything because increment
        counter method is not Synchronised
         */

    }
}
