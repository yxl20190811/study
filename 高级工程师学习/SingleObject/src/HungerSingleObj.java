import java.io.Serializable;

public class HungerSingleObj implements Serializable {
    private HungerSingleObj(){
        System.out.println("enter HungerSingleObj()");

        //休眠100毫秒，尽量引发多线程并发导致的构造过个对象
        try {
            Thread.sleep(100);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static HungerSingleObj instance = new HungerSingleObj();
    public static HungerSingleObj getInstance(){
        return instance;
    }
}
