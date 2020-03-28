import java.beans.ConstructorProperties;
import java.lang.reflect.Constructor;

public class TTestReflection {
    public static void test(){
        try {
            Class<?> classX = InnerPrivateClass_SingleObj.class;
            Constructor c = classX.getDeclaredConstructor();
            c.setAccessible(true);
            c.newInstance();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
