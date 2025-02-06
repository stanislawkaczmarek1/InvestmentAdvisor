package Observer;

import Controller.Controller;
import Enums.TradeSignal;
import Observable.Subject;
import Strategy.AlgorithmMACD;

import java.util.ArrayList;

public class AdvisorMACD extends InvestmentAdvisor implements Observer{
    private Subject subject;
    private ArrayList<Double> stockPrices = new ArrayList<>();
    private TradeSignal prediction;
    public AdvisorMACD(Subject observableSubject){
        this.predictionStrategy = new AlgorithmMACD();
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