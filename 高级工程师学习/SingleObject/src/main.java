
public class main {
    public static void main(String argv[]){
        System.out.println("enter main()");


        /*
        for(int i = 0; i < 3; ++i){
            new TTestThread().start();
        }



        TTestReflection.test();
        TTestThread.test();
        */
        InnerPrivateClass_SingleObj  obj = InnerPrivateClass_SingleObj.getInstance();
        TTestThread.test();
        TTestSeriable.test();

        System.out.println("exit main()");
        return;
    }
}
