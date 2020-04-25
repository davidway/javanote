package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//定义你的注解将用在什么地方？，这里定义是方法
@Retention(RetentionPolicy.RUNTIME) //有三个级别，源码级别，类文件，运行时，SOURCE,CLASS,RUNTIME
public @interface MyTest { //没有元素的注解称为标记注解
}
