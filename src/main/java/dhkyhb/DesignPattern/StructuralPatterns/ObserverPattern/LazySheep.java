package dhkyhb.DesignPattern.StructuralPatterns.ObserverPattern;

public class LazySheep extends Observer{

    public LazySheep(String name) {
        this.name = name;
    }

    @Override
    public void update(String params) {
        System.out.println(getName() + "收到了" + params);
        action();
    }

    public void action() {
        System.out.println(getName() + "逃跑了");
    }
}
