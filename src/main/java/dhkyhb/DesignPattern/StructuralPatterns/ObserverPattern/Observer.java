package dhkyhb.DesignPattern.StructuralPatterns.ObserverPattern;

public abstract class Observer {

    protected String name;

    public String getName() {
        return name;
    }

    public abstract void update(String params);

}
