import java.util.concurrent.atomic.AtomicInteger;

public class Volatile02 {
    public static volatile int race = 0;
    public static void increase(){
        race++;
    }
    private static final int THREADS_COUNT = 20;

    //volatile并不能保证原子性

    public static void main(String[] args) {
        //使用Atomic来保证
        AtomicInteger atomicInteger = new AtomicInteger(0);

        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i=0;i<THREADS_COUNT;i++){
            threads[i] = new Thread(()->{
                for (int j=0;j<1000;j++){
                    increase();
                    atomicInteger.getAndIncrement();
                }
            });
            threads[i].start();
        }
        while(Thread.activeCount()>2){
            //线程让步
            Thread.yield();
        }
        System.out.println(race);
        System.out.println(atomicInteger);
    }
}
