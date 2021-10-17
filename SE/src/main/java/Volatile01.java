import java.util.concurrent.TimeUnit;

class MyData{
    volatile int number = 0;
    public void updateNum()
    {
        this.number = 60;
    }
}

/**
 * 原子性：不可分割，完整性，也指某个线程正在做具体业务时，中间不可被加塞或分割，需要整体完整，要么同时成功，要么同时失败
 */
public class Volatile01 {


    public static void main(String[] args) {


    }
    //volatile关键字可以保证可见性
    public void ensureVisible() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            myData.updateNum();
            System.out.println(myData.number+"number已经变化");
        },"AAA").start();

        while(myData.number==0){
            //主线程一直在此等待，直到Number不再等于0
        }
        System.out.println(Thread.currentThread().getName()+"mission is over");
    }


}
