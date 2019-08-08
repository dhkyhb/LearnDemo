package dhkyhb.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Create by hl
 * Data by 2019-05-20
 * Description:
 */
public class DemoMain {

    public static void main(String[] args) {
        combinelatest();
    }

    public static void combinelatest() {
        Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4);
        String[] Stirngs = new String[]{"1", "2", "3", "4"};
        Observable<String> stringObservable = Observable.fromArray(Stirngs);

        Observable.combineLatest(integerObservable, stringObservable, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    public static void funcCompose() {
        Observable.just(1, 2, 3, 4)
                .compose(upstream -> upstream.map(integer -> String.class.getTypeName() + integer + ""))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s.getClass().getTypeName() + s);
                    }
                });
    }

}
