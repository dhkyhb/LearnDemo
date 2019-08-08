package dhkyhb.retrofit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:mini Retrofit GET
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GET {
    //注解中 方法名写成value 这样的话，在使用注解传入参数时就不用带key了，它会作为一个默认的调用
    String value();
}
