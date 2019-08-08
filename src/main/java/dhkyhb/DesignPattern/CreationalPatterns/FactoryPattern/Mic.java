package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public abstract class Mic implements Produce{

    @Override
    public void produce() {
        System.out.println("生产了耳麦");
    }
}
