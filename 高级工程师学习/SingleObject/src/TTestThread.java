
public class TTestThread extends Thread {
    private static Object CreateObj() {
        return InnerPrivateClass_SingleObj.getInstance();
    }

    public static void test() {
        for(int i = 0; i < 2; ++i){
            CreateObj();
            try {
                Thread.sleep(1);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void run(){
        test();
    }
}
