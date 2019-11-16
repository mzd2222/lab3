package lab3.Observer;

import lab3.Observable.Observable;

/**
 * 观察者：：：抽象类
 */

public abstract class Observer {

    protected Observable observable;
    protected Boolean state = false;

    public void setObservable(Observable observable) {
        this.observable = observable;
    }

    public Boolean getState() {
        return state;
    }

    public void call() {
        updateState();
    }

    public abstract void updateState();

    public abstract String getType();

    public abstract String getName();

    public abstract String getStringPosition();

}
