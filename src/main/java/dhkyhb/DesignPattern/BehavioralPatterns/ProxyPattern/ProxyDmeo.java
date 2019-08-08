package dhkyhb.DesignPattern.BehavioralPatterns.ProxyPattern;

import java.lang.reflect.Proxy;

public class ProxyDmeo {

    public static void main(String[] args) {
        proxy();
    }

    public static void mySelfDemo() {
        Star star = new Star();
        Agent agent = new Agent(star);

        agent.movie();
    }

    public static void proxy() {
        Subject realSubject = new Star();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(realSubject);

        Subject ProxySubject = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, invocationHandler);

        ProxySubject.movie();
    }

}
