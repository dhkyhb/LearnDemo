package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

import java.util.Optional;

import static dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern.ProduceEnum.PIZZA;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class ProduceFactory implements Factory {

    @Override
    public Produce factory(String produceType) {
        return null;
    }

    @Override
    public Produce factory(ProduceEnum produceEnum) {
        Produce produce = null;
        switch (produceEnum) {
            case PIZZA:
                produce = new Pizza();
                break;

            case ICECREAM:
                produce = new IceCream();
                break;

            default:

                break;
        }
        return Optional.ofNullable(produce).orElse(() -> System.out.println("没有默认实现"));
    }
}
