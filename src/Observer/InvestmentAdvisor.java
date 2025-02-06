package Observer;

import Enums.TradeSignal;
import Strategy.PredictionStrategy;

import java.util.ArrayList;

public abstract class InvestmentAdvisor {
    protected PredictionStrategy predictionStrategy;
    public TradeSignal gavePrediction(ArrayList<Double> stockprices){
        return predictionStrategy.prediction(stockprices);
    }
}
