import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Documented

public @interface MyAutowire {
    String value() default "";
}
