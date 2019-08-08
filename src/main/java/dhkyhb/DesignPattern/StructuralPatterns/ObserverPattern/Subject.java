package dhkyhb.DesignPattern.StructuralPatterns.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    protected List<Observer> observerVector = new ArrayList<>();

    public void attach(Observer observer){
        observerVector.add(observer);
        System.out.println("添加了一个观察者" + observer.getName());
    }
    public void deleteObserver(Observer observer){
        observerVector.remove(observer);
        System.out.println("删除了一个观察者");
    }
    public void notifyAllObservers(String info){
        System.out.println(info);
        for (Observer observer: observerVector){
            observer.update(info);
        }
    }

}
