package dhkyhb.DesignPattern.StructuralPatterns.ObserverPattern;


public class Wolf extends Subject {

    private String name;

    public Wolf(String name) {
        this.name = name;
    }

    public void invade() {
        notifyAllObservers(name + "开始狩猎了！");
    }
}
