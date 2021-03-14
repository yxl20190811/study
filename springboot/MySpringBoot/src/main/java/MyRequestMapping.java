import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented

public @interface MyRequestMapping {
    String value() default "";
}
