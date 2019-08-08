package dhkyhb.rxjava;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.*;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.reactivestreams.Subscriber;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class RxjavaSet {

    private List<String> strList = Arrays.asList(new String[]{"黑夜到白昼", "十五楼", "你说没有没有", "拉塞尔40分13助攻", "4-2淘汰76人"});

    public static void main(String[] args) {
        RxjavaSet set = new RxjavaSet();
//        set.trampoline();
        set.testFunc(set);
    }


    public void observableCreate() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("黑夜到白昼");
                emitter.onNext("十五楼");
                emitter.onNext("你说没有没有");
                emitter.onNext("拉塞尔40分13助攻");
                emitter.onNext("4-2淘汰76人");
                emitter.onComplete();
            }
        });

        Observer<String> observer = new baseObserver<String>() {

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

        };
        Consumer<String> consumer = new Consumer<String>() {

            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        };

        Consumer<Throwable> consumerError = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        };

        Action complete = new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("onComplete");
            }
        };

//        observable.subscribe(consumer, consumerError, complete);
        observable.subscribe(observer);
    }

    public void groupBy() {
        Observable.range(0, 10)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer % 3;
                    }
                })
                .subscribe(new Consumer<GroupedObservable<Integer, Integer>>() {
                    @Override
                    public void accept(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) throws Exception {
                        Integer key = integerIntegerGroupedObservable.getKey();
                        System.out.println("key is " + key);
                    }
                });
    }

    public void observableFrom() {
        String[] strArray = {"黑夜到白昼", "十五楼", "你说没有没有", "拉塞尔40分13助攻", "4-2淘汰76人"};
        Observable.fromArray(strList)
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> s) throws Exception {
                        for (String str : s)
                            System.out.println(str);
                    }
                });
    }

    public void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        List<String> mLists = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            mLists.add("position " + integer + ": this value is " + i);
                        }
                        int delayTime = (int) (100 + Math.random() * 100);
                        return Observable.fromIterable(mLists).delay(delayTime, TimeUnit.MILLISECONDS);
//                        return Observable.fromIterable(mLists);
                    }
                }).subscribe(new baseObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("flatMap : accept : " + s);
            }
        });
    }

    public void concattMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        })
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        List<String> mLists = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            mLists.add("position " + integer + ": this value is " + i);
                        }
                        int delayTime = (int) (100 + Math.random() * 100);
                        return Observable.fromIterable(mLists).delay(delayTime, TimeUnit.MILLISECONDS);
//                        return Observable.fromIterable(mLists);
                    }
                }).subscribe(new baseObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("concatMap : accept : " + s);
            }
        });
    }

    public void merge() {
        Observable.merge(
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS)
        ).subscribe(new baseObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                printl("接收数据" + aLong);
            }
        });
    }

    public void concatArrayDelay() {
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new IllegalArgumentException("错误的属性"));
                        emitter.onComplete();
                    }
                }), Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(4);
                        emitter.onNext(5);
                        emitter.onNext(6);
                        emitter.onComplete();
                    }
                })
        ).subscribe(new baseObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                printl("接收到数据" + integer);
            }
        });
    }

    /**
     * 合并数据，最终发送事件以最小数量为准
     * 如果被观察者不发送onComplete，则多出来的事件仍会发送
     * 但如果发送onComplete，则不会发送
     */
    public void zip() {
        Observable<Integer> ObservableInt = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        Observable<String> observableStr = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("decide");
                emitter.onNext("decide");
            }
        });
        Observable.zip(ObservableInt, observableStr, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return s + integer;
            }
        }).subscribe(new baseObserver<String>() {
            @Override
            public void onNext(String s) {
                printl(s);
            }
        });
    }

    /**
     * 当两个Observables中的任何一个发送了数据后，将先发送了数据的Observables 的最新（最后）一个数据 与 另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据
     */
    public void combineLatest() {
        Observable.combineLatest(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                    }
                }),
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                new BiFunction<Integer, Long, Long>() {
                    @Override
                    public Long apply(Integer integer, Long aLong) throws Exception {
                        printl("获取数据" + integer + "&" + aLong);
                        return (Long) (aLong + integer);
                    }
                }
        ).subscribe(new baseObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                printl("接收数据" + aLong);
            }
        });
    }

    /**
     *
     */
    public void observableSubscrion() {
        String key = "4bccc3f1ee021fd12621dfffb8ddcfcf";
        String url = "http://v.juhe.cn/joke/randJoke.php";
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .get()
                .url(url + "?key=" + key)
                .build();
        final Call call = client.newCall(request);
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                System.out.println(Thread.currentThread());
                Response execute = call.execute();
                emitter.onNext(execute);
                emitter.onComplete();
            }
        });
        observable
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        String responseBody = response.body().string();
                        System.out.println(responseBody);
                        System.out.println(Thread.currentThread());
                    }
                });
    }

    public void trampoline() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 5; i++) {
                    System.out.println("发送线程-" + Thread.currentThread() + ",数据:" + i);
                    System.out.println(new Date(System.currentTimeMillis()).toString());
                    Thread.sleep(1000);
                    emitter.onNext(i);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("接受线程-" + Thread.currentThread() + ",数据:" + integer);
                        System.out.println(new Date(System.currentTimeMillis()).toString());
                    }
                });
    }

    public void single() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 3; i++) {
                    System.out.println("发射线程-" + Thread.currentThread() + ",数据:" + i);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.single())//设置   可观察对象在single线程中使用
                .observeOn(Schedulers.single())//设置map操作符在single线程中使用
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        System.out.println("处理线程-" + Thread.currentThread() + ",数据:" + integer);
                        return integer;
                    }
                })
                .observeOn(Schedulers.single())//设置观察者在single线程中接收数据
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("接受线程-" + Thread.currentThread() + ",数据:" + integer);
                    }
                });
    }

    /**
     * 去重
     */
    public void distinct() {
        Observable.just(1, 1, 2, 3, 4, 4, 6, 6)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        printl(integer);
                    }
                });
        Observable.just(1, 1, 2, 3, 4, 4, 6, 6, 4)
                .distinctUntilChanged()//去除数据中连续重复事件
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        printl(integer);
                    }
                });
    }

    /**
     * 它的作用是让订阅者在接收到数据之前干点有意思的事情
     */
    public void doOnNext() {
        Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("我在订阅之前就接收到了数据: " + integer);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("我接收到了数据:" + integer);
            }
        });
    }


    /**
     * 定期从 被观察者（Obervable）需要发送的事件中 获取一定数量的事件 & 放到缓存区中，最终发送
     */
    public void buffer() {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)// 设置缓存区大小 & 步长
                // 缓存区大小 = 每次从被观察者中获取的事件数量
                // 步长 = 每次获取新事件的数量
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        for (Integer i : integers) {
                            printl(i);
                        }
                        System.out.println("这行数据已经接收完毕!");
                    }
                });
    }

    public void timer() {
        System.out.println(new Date(System.currentTimeMillis()).toString());
        Observable.just(1, 2, 1)
                .timer(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
//                        printl(integer);
                        System.out.println(new Date(System.currentTimeMillis()).toString());
                    }
                });
    }


    public void interval() {
        Observable.just(1, 2)
                .interval(0, 2, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        System.out.println("在发送数据之前:");
                        System.out.println(new Date(System.currentTimeMillis()).toString());
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        printl(aLong);
                        System.out.println(new Date(System.currentTimeMillis()).toString());
                    }
                });
    }

    private void debounce() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(400);
                emitter.onNext(2);
                Thread.sleep(501);
                emitter.onNext(3);
                Thread.sleep(601);
                emitter.onNext(4);
                Thread.sleep(801);
                emitter.onNext(5);
                Thread.sleep(201);
                emitter.onNext(6);
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        printl(integer);
                    }
                });
    }

    /**
     * 按照实际划分窗口，将数据发送给不同的 Observable
     */
    public void window() {
        Observable.interval(1, 1, TimeUnit.SECONDS)
                .take(13)
                .window(3)
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        System.out.println("Sub Divide Begin....");
                        longObservable.subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                printl(aLong);
                            }
                        });
                    }
                });
    }

    public void defer() {
        Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(2, 4, 7);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                printl(integer);
            }
        });
    }

    /**
     * 需要发送的事件聚合成1个事件 & 发送
     */
    public void reduce() {
        Observable.just(1, 3, 7)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                printl(integer);
            }
        });
    }

    /**
     * 将被观察者Observable发送的数据事件收集到一个数据结构里
     */
    public void collect() {
        Observable.just(1, 2, 3, 4)
                .collect(
                        // 1. 创建数据结构（容器），用于收集被观察者发送的数据
                        new Callable<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> call() throws Exception {
                                return new ArrayList<>();
                            }
                            // 2. 对发送的数据进行收集
                        }, new BiConsumer<ArrayList<Integer>, Integer>() {
                            @Override
                            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                                integers.add(integer);
                            }
                        }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                System.out.println("本次数据" + integers);
            }
        });
    }

    /**
     * 在一个被观察者发送事件前，追加发送一些数据 / 一个新的被观察者
     */
    public void startWith() {
        Observable.just(4, 5, 6)
                .startWithArray(1, 2, 3)
                .startWith(0)
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        printl(integer);
                    }
                });
    }

    public void onErrorResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("new Exception"));
                emitter.onNext(3);
                emitter.onComplete();
            }
        })
//                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
////            @Override
////            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
////                return Observable.just(3, 4);
////            }
////        })
                .onExceptionResumeNext(new ObservableSource<Integer>() {
                    @Override
                    public void subscribe(Observer<? super Integer> observer) {
                        observer.onNext(3);
                        observer.onNext(4);
                        observer.onComplete();
                    }
                })
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        printl(integer);
                    }
                });
    }

    public void repeatWhen() {
        Observable.just(1, 2, 4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable
                // 此处有2种情况：
                // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                        // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                        return Observable.empty();
                        // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

//                         return Observable.error(new Throwable("不再重新订阅事件"));
                        // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
//                         return Observable.just(9);
                        // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                    }
                });

            }
        })
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer value) {
                        printl("接收到了事件" + value);
                    }

                });
    }

    public void doSet() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Exception("这里发送了Exception"));
            }
        }).doOnEach(new Consumer<Notification<Integer>>() {
            //每次发送的时候接收
            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {
                System.out.println("doOnEach接受到了数据" + integerNotification.getValue());
            }
        }).doOnNext(new Consumer<Integer>() {
            //在每次next执行前
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("doOnNext接受到了数据" + integer);
            }
        }).doAfterNext(new Consumer<Integer>() {
            //在每个next执行之后接收数据
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("doAfterNext接受到了数据" + integer);
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("onComplete执行了");
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("doOnError发生了" + throwable.getMessage());
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            //在订阅的时候执行
            @Override
            public void accept(Disposable disposable) throws Exception {
                System.out.println("subscribe开始订阅了");
            }
        }).doOnTerminate(new Action() {
            //Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
            @Override
            public void run() throws Exception {
                System.out.println("OnTerminate: Observable发送事件完毕了，");
            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("finally结束了");
            }
        }).subscribe(new baseObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println("接受到了数据" + integer);
            }
        });
    }

    public void onErrorReturn() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Throwable("抛出了个throwable"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                System.out.println(throwable.getMessage());
                return 4;
            }
        }).subscribe(new baseObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                printl(integer);
            }
        });
    }

    int errorTime = 0;

    public void retry() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

                if (errorTime == 1) {
                    emitter.onError(new Throwable("抛出了个Throwable"));
                } else {
                    emitter.onNext(4);
                }
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                errorTime++;
            }
        })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                })
                .retry().subscribe(new baseObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                printl(integer);
            }
        });
    }

    public void retryPredicate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(12);
                emitter.onError(new Throwable("抛出了个throwable"));
            }
        }).retry(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                return false;
            }
        }).subscribe(new baseObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                printl(integer);
            }
        });
    }

    public void repeat() {
        Observable.just(1)
                .repeat(3)
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        printl(integer);
                    }
                });
    }

    /**
     * scan 操作符作用和上面的 reduce 一致
     * 不过会把每个步骤都输出一遍
     */
    public void scan() {
        Observable.just(1, 3, 7)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                printl(integer);
            }
        });
    }

    /*-------------------------------------过滤操作符 -----------------------------------*/

    /**
     * 过滤
     */
    public void filter() {
        Observable.just(12, 34, 6, 24, 65, 90, 43, 99)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 3 != 0;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                printl(integer);
            }
        });
    }

    public void ofType() {
        Observable.just(1, 2, "dhkyhb", 'c', true)
                .ofType(Integer.class)
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        printl(integer);
                    }
                });
    }

    public void skip() {
        Observable.just(1, 2, 3, 4, 5)
                .skip(2)//正序 前几项
                .skipLast(2)//倒序 后几项
        .subscribe(new baseObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                printl(integer);
            }
        });
    }

    public void take() {
        Observable.interval(1,1, TimeUnit.SECONDS)
                .take(6)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        printl(aLong);
                    }
                });
        Observable.just(1, 2, 3, 4, 5, 6)
                .takeLast(2)
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        printl(integer);
                    }
                });
    }

    public void throttle() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(400);
                emitter.onNext(2);
                Thread.sleep(400);
                emitter.onNext(3);
                Thread.sleep(700);
                emitter.onNext(4);
                Thread.sleep(400);
                emitter.onNext(5);
                Thread.sleep(600);
                emitter.onNext(6);
                Thread.sleep(900);
                emitter.onNext(7);
            }
        }).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        printl(integer);
                    }
                });
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(400);
                emitter.onNext(2);
                Thread.sleep(400);
                emitter.onNext(3);
                Thread.sleep(700);
                emitter.onNext(4);
                Thread.sleep(400);
                emitter.onNext(5);
                Thread.sleep(600);
                emitter.onNext(6);
                Thread.sleep(900);
                emitter.onNext(7);
            }
        }).throttleLast(1, TimeUnit.SECONDS)
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        printl(integer);
                    }
                });
    }

    public void throttleWithoutTime() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(400);
                emitter.onNext(2);
                Thread.sleep(400);
                emitter.onNext(3);
                Thread.sleep(700);
                emitter.onNext(4);
                Thread.sleep(400);
                emitter.onNext(5);
                Thread.sleep(600);
                emitter.onNext(6);
                Thread.sleep(900);
                emitter.onNext(7);
            }
        }).throttleWithTimeout(600, TimeUnit.MILLISECONDS)
                .subscribe(new baseObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        printl(integer);
                    }
                });
    }

    public void elementAt() {
        Observable.just(1, 2, 3, 4 ,5)
                .elementAt(2, 4)//接收某个索引上的值，并设置默认值
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        printl(integer);
                    }
                });
    }

    /*---------------------------------------背压----------------------------------------*/
    public void backPressure() {

    }


    private void testFunc(RxjavaSet set) {
        set.elementAt();


        try {
            new Thread().sleep(14000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printl(Object o) {
        System.out.println("我已经接收到数据:" + o);
    }

    public void print(Object o) {
        System.out.print("我已经接收到数据:" + o);
    }

}
