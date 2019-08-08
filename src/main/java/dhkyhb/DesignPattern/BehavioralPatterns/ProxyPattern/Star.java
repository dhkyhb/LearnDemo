package dhkyhb.DesignPattern.BehavioralPatterns.ProxyPattern;

public class Star implements Subject{
    @Override
    public void movie() {
        System.out.println(getClass().getSimpleName() + ":经纪人接下了一部电影，我负责拍就行了。");
    }

    @Override
    public Subject getAgent() {
        return new Agent(this);
    }
}
