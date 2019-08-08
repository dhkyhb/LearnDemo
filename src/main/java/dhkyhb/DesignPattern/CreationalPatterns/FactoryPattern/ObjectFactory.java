package dhkyhb.DesignPattern.CreationalPatterns.FactoryPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class ObjectFactory {

    public <T> T getObject(Class<T> tClass) {
        if (tClass == null) {
            return null;
        }

        T obj = null;
        try {
            obj = (T) Class.forName(tClass.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
