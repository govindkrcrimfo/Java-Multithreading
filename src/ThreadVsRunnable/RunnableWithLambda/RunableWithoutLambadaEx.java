package ThreadVsRunnable.RunnableWithLambda;
/*
    Below one is normal run and getMsg call without lambada epression
 */

class RunableWithoutLambada implements Runnable{
    public void getMsg(){
        System.out.println("Greetings from getMsg() !!");
    }
    @Override
    public void run() {
        getMsg();
    }
}
public class RunableWithoutLambadaEx {
    public static void main(String args[]){
        RunableWithoutLambada runableWithoutLambada=new RunableWithoutLambada();
        Thread t=new Thread(runableWithoutLambada);
        t.start();
    }
}
