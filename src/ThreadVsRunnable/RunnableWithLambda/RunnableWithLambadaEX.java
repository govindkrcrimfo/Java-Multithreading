package ThreadVsRunnable.RunnableWithLambda;

class RunnableWithLambada {
    public  void getMsg(){
        System.out.println("Greetings from getMsg() !!");
    }
}
public class RunnableWithLambadaEX {
    public static void  main(String []args) {
        RunnableWithLambada runnableWithLambada = new RunnableWithLambada();
        // lambada call using Runnable interface and call the getMsg()
        Thread t1 = new Thread(() -> {
            runnableWithLambada.getMsg();
        });
        t1.start();
    }
}
