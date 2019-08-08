package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class DeilPcFactory extends PcFactory {
    @Override
    Mouse createMouse() {
        return new DeilMouse();
    }

    @Override
    Keybo createKeybo() {
        return new DeilKeybo();
    }
}
