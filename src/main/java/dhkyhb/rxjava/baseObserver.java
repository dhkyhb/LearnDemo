package dhkyhb.rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class baseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe");
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("onError:");
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
