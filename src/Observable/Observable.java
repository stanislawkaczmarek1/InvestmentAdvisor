package Observable;

import java.util.ArrayList;
import java.util.List;
import Observer.Observer;

public class Observable implements Subject{
    private ArrayList<Observer> observers = new ArrayList<>();
    private ArrayList<Double> stockPrices = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(stockPrices);
        }
    }
    public void setObservableData(ArrayList<Double> stockprices){
        this.stockPrices = stockprices;
        observableDataChanged();

    }
    public void observableDataChanged(){
        notifyObservers();
    }

}
