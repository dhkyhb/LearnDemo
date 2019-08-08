package dhkyhb.DesignPattern.StructuralPatterns.ObserverPattern;

import okhttp3.OkHttpClient;

public class ObserverPatternDemo {

    public static void main(String[] args) {
        Wolf wolf = new Wolf("灰太狼");
        PleasantSheep sheep = new PleasantSheep("喜羊羊");
        LazySheep lazySheep = new LazySheep("懒羊羊");
        wolf.attach(sheep);
        wolf.attach(lazySheep);
        wolf.invade();
    }

}
