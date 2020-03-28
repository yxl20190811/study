public class LasySingleObj {
    private LasySingleObj(){
        System.out.println("enter LasySingleObj()");
        try {
            Thread.sleep(100);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static LasySingleObj  instance = null;
    public static LasySingleObj getInstance(){
        if(null == instance)
        {
            {
                instance = new LasySingleObj();
            }
        }
        return instance;
    }
}
