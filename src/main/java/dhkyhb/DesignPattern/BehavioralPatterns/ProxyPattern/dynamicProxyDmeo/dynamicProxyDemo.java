package dhkyhb.DesignPattern.BehavioralPatterns.ProxyPattern.dynamicProxyDmeo;

import java.io.FileInputStream;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
public class dynamicProxyDemo {

    public static void main(String[] args) {
        try {
//            baseClass baseClass = valueFactory.create(new FileInputStream("E:\\IdeaProject\\LearnDemo\\config.properties"));
            baseClass baseClass = valueFactory.create(new FileInputStream("./config.properties"));
            System.out.println("name()" + baseClass.name());
            System.out.println("age()" + baseClass.age());
            System.out.println("martial()" + baseClass.martial());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
