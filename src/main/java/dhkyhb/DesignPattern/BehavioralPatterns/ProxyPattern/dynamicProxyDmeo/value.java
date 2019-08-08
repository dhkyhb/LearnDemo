package dhkyhb.DesignPattern.BehavioralPatterns.ProxyPattern.dynamicProxyDmeo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface value {

    String value();

}
