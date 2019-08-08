package dhkyhb.DesignPattern.StructuralPatterns.ObserverPattern;

public class PleasantSheep extends Observer{

    public PleasantSheep(String name) {
        this.name = name;
    }

    /**
     * 具体业务逻辑
     */
    @Override
    public void update(String params) {
        System.out.println(getName() + "收到了" + params);
        action();
    }

    public void action() {
        System.out.println(getName() + "逃跑了");
    }

}
