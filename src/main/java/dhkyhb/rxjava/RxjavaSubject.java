package dhkyhb.rxjava;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * Create by hl
 * Data by 2019-05-17
 * Description:
 */
public class RxjavaSubject {

    public static void main(String[] args) {
        BehaviorSubject();
    }

    /**
     * 所有订阅者 ，无论何时都能收到发送信息
     */
    public static void RepalaySubject() {
        ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);

        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("subscribe1:" + integer);
            }
        });
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("subscribe2:" + integer);
            }
        });
    }

    /**
     * 接收最近发送的数据对象
     * 后续释放所有对象
     * 如果遇到error，不会再给后续observer发送数据对象，而是一个Error
     */
    public static void BehaviorSubject() {
        BehaviorSubject<Integer> subjectDefault = BehaviorSubject.createDefault(-1);
        subjectDefault.subscribe(consumer("subjectDefault"));
        subjectDefault.onNext(1);
        subjectDefault.onNext(2);
        subjectDefault.onNext(3);

        BehaviorSubject<Integer> subjectCreate = BehaviorSubject.create();
        subjectCreate.onNext(4);
        subjectCreate.onNext(5);
        subjectCreate.onNext(6);
        subjectCreate.subscribe(consumer("subjectCreate"));
        subjectCreate.onNext(7);
        subjectCreate.onNext(8);

        // observer will receive only onComplete
        BehaviorSubject<Integer> subjectComplete = BehaviorSubject.create();
        subjectComplete.onNext(9);
        subjectComplete.onNext(10);
        subjectComplete.onComplete();
        subjectComplete.subscribe(consumer("subjectComplete"), new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("onComplete");
            }
        });

        // observer will receive only onError
        BehaviorSubject<Integer> subjectError = BehaviorSubject.create();
        subjectError.onNext(11);
        subjectError.onNext(12);
        subjectError.onError(new IllegalArgumentException("数据错误"));
        try {
//            subjectError.subscribe(consumer("subjectError"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    public static <T> Consumer<T> consumer(String name) {
        Consumer<T> consumer = new Consumer<T>() {
            @Override
            public void accept(T integer) throws Exception {
                System.out.println(name + integer);
            }
        };
        return consumer;
    }

}
