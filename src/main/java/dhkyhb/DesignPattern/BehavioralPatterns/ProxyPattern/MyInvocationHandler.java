package dhkyhb.DesignPattern.BehavioralPatterns.ProxyPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 定义一个处理器
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 因为需要处理真实角色，所以要把真实角色传进来
     */
    Subject RealSubject;

    public MyInvocationHandler(Subject realSubject) {
        RealSubject = realSubject;
    }

    /**
     * @param proxy 代理类
     * @param method    正在调用的方法
     * @param args  方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理类");
        if (method.getName().equals("movie")) {
            method.invoke(RealSubject, args);
        }
        return null;
    }
}
