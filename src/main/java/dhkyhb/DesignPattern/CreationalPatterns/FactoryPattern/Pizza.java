package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class Pizza implements Produce {
    @Override
    public void produce() {
        System.out.println("披萨做好了!");
    }
}
