package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public abstract class Keybo implements Produce{

    public Keybo() {
        produce();
    }

    @Override
    public abstract void produce();
}
