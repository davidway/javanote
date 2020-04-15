

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止表单重复提交,
 * 在去表单的controller中加@FormToken(save=true),保存时使用@FormToken(remove=true)。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
public @interface FormToken {

    boolean save() default false;

    boolean remove() default false;
}