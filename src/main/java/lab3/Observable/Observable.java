package lab3.Observable;

/**
 * 被观察者：：：抽象类
 */

import lab3.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    protected List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public abstract void UpdateState();

    public abstract Object getState();

}
