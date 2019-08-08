package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public interface Factory {

    // 传入参数的是产品类型，这里返回产品类的抽象工厂类Product
    Produce factory(String produceType);

    Produce factory(ProduceEnum produceEnum);


}
