package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class IceCream implements Produce {
    @Override
    public void produce() {
        System.out.println("雪糕制作好了");
    }
}
