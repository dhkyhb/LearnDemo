package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class HPPcFactory extends PcFactory{


    @Override
    Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    Keybo createKeybo() {
        return new HpKeybo();
    }
}
