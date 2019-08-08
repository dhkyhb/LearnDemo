package dhkyhb.DesignPattern.CreationalPatterns.SingletonPattern;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public enum  Singleton {

    INSTACE;

    public void getCurrentHashCode() {
        System.out.println(this.hashCode());
    }

}
