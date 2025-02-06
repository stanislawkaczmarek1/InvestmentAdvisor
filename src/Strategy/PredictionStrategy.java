package Strategy;

import Enums.TradeSignal;

import java.util.ArrayList;

public interface PredictionStrategy {
    TradeSignal prediction(ArrayList<Double> stockPrices);
}
