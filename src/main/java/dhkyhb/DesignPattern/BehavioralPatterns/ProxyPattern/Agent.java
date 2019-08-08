package dhkyhb.DesignPattern.BehavioralPatterns.ProxyPattern;

public class Agent implements Subject{
    private Subject star;

    public Agent(Subject star) {
        this.star = star;
    }

    @Override
    public void movie() {
        System.out.println(getClass().getSimpleName() + ":剧本题材好，这部电影我接下来");
        star.movie();
    }

    @Override
    public Subject getAgent() {
        return this;
    }
}
