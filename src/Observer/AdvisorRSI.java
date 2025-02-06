package Observer;

import Controller.Controller;
import Enums.TradeSignal;
import Observable.Subject;
import Strategy.AlgorithmRSI;

import java.util.ArrayList;

public class AdvisorRSI extends InvestmentAdvisor implements Observer{
    private Subject subject;
    private ArrayList<Double> stockPrices = new ArrayList<>();
    private TradeSignal prediction;
    public AdvisorRSI(Subject observableSubject){
        this.predictionStrategy = new AlgorithmRSI();
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
