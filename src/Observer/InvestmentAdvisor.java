package Observer;

import Strategy.PredictionStrategy;

import java.util.ArrayList;

public abstract class InvestmentAdvisor {
    protected PredictionStrategy predictionStrategy;
    public int gavePrediction(ArrayList<Double> stockprices){
        return predictionStrategy.prediction(stockprices);
    }
}
