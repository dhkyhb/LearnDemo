package dhkyhb.DesignPattern.BehavioralPatterns.ProxyPattern.dynamicProxyDmeo;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
public class valueFactory {

    private valueFactory() {

    }

    public static baseClass create(final InputStream is) throws Exception{
        final Properties properties = new Properties();
        properties.load(is);

        return (baseClass) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{baseClass.class}, new wrapperProperties(properties));
    }

    static class wrapperProperties implements InvocationHandler{

        private Properties properties;

        public wrapperProperties(Properties properties) {
            this.properties = properties;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                value annotation = method.getAnnotation(value.class);
                if (annotation == null) return null;
                String property = properties.getProperty(annotation.value());
                if (property == null) return null;
                final Class<?> type = method.getReturnType();
                if (type.isPrimitive()) {//是不是八大基本类型
                    if (type.equals(int.class)) return (Integer.valueOf(property));
                    else if (type.equals(long.class)) return (Long.valueOf(property));
                    else if (type.equals(double.class)) return (Double.valueOf(property));
                    else if (type.equals(float.class)) return (Float.valueOf(property));
                    else if (type.equals(boolean.class)) return (Boolean.valueOf(property));
                }
                return property;
        }
    }

}
