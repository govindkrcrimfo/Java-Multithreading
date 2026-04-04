package ThreadCommunication;
class SharedResource{
    private int data;
    private boolean hasData=false;

    public synchronized void produceData(int i) throws InterruptedException {
        while (hasData) {
            wait();
        }
        data=i;
        hasData=true;
        System.out.println("Produced : "+data);
        notify();
    }
    public synchronized  void consume() throws InterruptedException {
        while (!hasData){
            wait();
        }
        System.out.println("Consumed : "+data);
        hasData=false;
        notify();

    }
}
class Prouduce implements  Runnable{
    private SharedResource sharedResource;
    Prouduce(SharedResource sharedResource){
        this.sharedResource=sharedResource;
    }
    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            try {
                sharedResource.produceData(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class Consume implements Runnable{
    private  SharedResource sharedResource;
    Consume(SharedResource sharedResource){
        this.sharedResource=sharedResource;
    }
    @Override
    public void run() {
        for (int i=1;i<=10;i++){
            try {
                sharedResource.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ThreadCommunicationEx {
public static void main(String args[]){
    SharedResource sh=new SharedResource();
    Prouduce prouduce=new Prouduce(sh);
    Consume consume=new Consume(sh);
    Thread t1=new Thread(prouduce);
    Thread t2=new Thread(consume);
    t1.start();
    t2.start();
}
}
