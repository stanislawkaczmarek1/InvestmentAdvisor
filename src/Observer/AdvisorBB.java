package Observer;

import Controller.Controller;
import Observable.Subject;
import Strategy.AlgorithmBB;

import java.util.ArrayList;

public class AdvisorBB extends InvestmentAdvisor implements Observer{
    private Subject subject;
    private ArrayList<Double> stockPrices = new ArrayList<>();
    private int prediction;
    public AdvisorBB(Subject observableSubject){
        this.predictionStrategy = new AlgorithmBB();
        this.subject =observableSubject;
        subject.registerObserver(this);
        Controller.AddToObserversHashCodes(this.hashCode());
    }
    @Override
    public void update(ArrayList<Double> stockprices) {
        this.stockPrices = stockprices;
        doAndSetPrediction();
        Controller.setDataFromModel(this.hashCode(), prediction);
    }
    public void doAndSetPrediction(){
        this.prediction = this.gavePrediction(stockPrices);
    }
}
