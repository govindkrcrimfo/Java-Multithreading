package ThreadWithLambadaExpression;
/*
  Different way of creating the Runnable and passing in Thread
 */
public class LambdaExpressionEx {
    public static void main(String[] args) {
        // 1. By using anonymous function -> because Runnable is functional interface
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous function");
            }
        };
        Thread t1=new Thread(runnable);

        // 2.Using lambada Expression
        Runnable runnable1=(()->{
            System.out.println("Lambada Expression ");
        });
        Thread t2=new Thread(runnable1);

        // 3.Directly passing runnable object inside Thread
        Thread t3=new Thread(()->{
            System.out.println("Directly passing runnable object inside Thread");
        });

        /*
          Any of the above way we can we for Thread , all above three are similar
         */
        t1.start();
        t2.start();
        t3.start();
    }
}
