import com.sun.source.tree.SynchronizedTree;

public class LasyThreadSafeSingleObj {
    private LasyThreadSafeSingleObj(){
        System.out.println("enter LasyThreadSafeSingleObj()");
        try {
            Thread.sleep(100);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static volatile LasyThreadSafeSingleObj instance = null;
    public static LasyThreadSafeSingleObj getInstance(){
        if(null == instance)
        {
            synchronized(LasyThreadSafeSingleObj.class) {
                if (null == instance) {
                    instance = new LasyThreadSafeSingleObj();
                }
            }
        }
        return instance;
    }
}
