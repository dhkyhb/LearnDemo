package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class IceCreamFactory implements Factory{
    @Override
    public Produce factory(String produceType) {
        return new IceCream();
    }

    @Override
    public Produce factory(ProduceEnum produceEnum) {
        return null;
    }
}
