import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented

public @interface MyGetMapping {
    public String value() default "\\";
}
