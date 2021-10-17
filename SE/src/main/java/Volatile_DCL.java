public class Volatile_DCL {
    //DCL单例
    private Volatile_DCL(){
        System.out.println("1");
    }
    private static Volatile_DCL instance;
    private static Volatile_DCL getInstance(){
        if(instance == null){
            synchronized (Volatile_DCL.class){
                if(instance == null){
                    instance = new Volatile_DCL();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for(int i=0;i<5;i++){
            threads[i] = new Thread(()->{
                Volatile_DCL.getInstance();
            });
            threads[i].start();
        }
    }
}
