
public class main {
    public static void main(String argv[]){
        System.out.println("enter main()");


        
        for(int i = 0; i < 3; ++i){
            new TTestThread().start();
        }



        TTestReflection.test();
        TTestThread.MyRun();

        System.out.println("exit main()");
        return;
    }
}
