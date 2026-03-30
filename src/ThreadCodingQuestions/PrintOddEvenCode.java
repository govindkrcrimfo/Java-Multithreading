package ThreadCodingQuestions;
// print odd and even using two threads
class PrintOddEven{
    public static int Range=15;
    public static int st=0;
    public synchronized void printEven() throws InterruptedException {
       while (st<=Range){
                if(st%2!=0){
                    wait(); // st is odd
                }else{
                    System.out.println("Even number "+st);
                    st++;
                    notify();
                }
        }
    }
    public synchronized void printOdd() throws InterruptedException {
        while (st<=Range){
            if(st%2==0){
                wait(); // st is even
            }else{
                System.out.println("Odd number "+st);
                notify();
                st++;
            }
        }
    }
}
public class PrintOddEvenCode {
    public static void main(String args[]){
    PrintOddEven printOddEven=new PrintOddEven();
    Thread t=new Thread(()->{
        try {
            printOddEven.printEven();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
    Thread t1=new Thread(()->{
        try {
            printOddEven.printOdd();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
    t.start();
    t1.start();
    }
}
