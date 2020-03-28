public class InnerPrivateClass_SingleObj {

    public InnerPrivateClass_SingleObj(){
        System.out.println("enter InnerPrivateClass_SingleObj()");

        if(InnerPrivateClass.instance != null){
            throw new RuntimeException("非法创建");
        }
    }
    public static InnerPrivateClass_SingleObj getInstance(){
        return InnerPrivateClass.instance;
    }
    private final static class InnerPrivateClass{
        public static final InnerPrivateClass_SingleObj instance = new InnerPrivateClass_SingleObj();
    }
}
