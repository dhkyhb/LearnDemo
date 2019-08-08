package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class facroteyDemo {

    public static void main(String[] args) {
        func3();
    }

    public static void func1() {
        Factory iceCreamFactory = new IceCreamFactory();
        Factory PizzaFactory = new PizzaFactory();
        Produce iceCream = iceCreamFactory.factory("iceCream");
        Produce pizza = PizzaFactory.factory("pizza");
        iceCream.produce();
        pizza.produce();
    }

    public static void func2() {
        Factory produceFactory = new ProduceFactory();
        Produce iceCream = produceFactory.factory(ProduceEnum.PIZZA);
        Produce pizza = produceFactory.factory(ProduceEnum.ICECREAM);
        iceCream.produce();
        pizza.produce();
    }

    public static void func3() {
        PcFactory hpPcFactory = new HPPcFactory();
        PcFactory deilPcFactory = new DeilPcFactory();
        hpPcFactory.createKeybo();
        hpPcFactory.createMouse();
        deilPcFactory.createKeybo();
        deilPcFactory.createMouse();
    }
}
