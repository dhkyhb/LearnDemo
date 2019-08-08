package dhkyhb.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
public class Retrofit {

    private OkHttpClient okClient;

    public Retrofit(OkHttpClient okClient) {
        this.okClient = okClient;
    }

    public <T> T createService(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //获取方法所有的注解
                final Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof GET) {
                        final GET getAnnotation = (GET) annotation;
                        final String url = getAnnotation.value();
                        final Request request = new Request.Builder().url(url).get().build();
                        return okClient.newCall(request);
                    }
                }
                return null;
            }
        });
    }

}
