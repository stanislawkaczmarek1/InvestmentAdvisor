package Strategy;

import java.util.ArrayList;

public interface PredictionStrategy {
    int prediction(ArrayList<Double> stockPrices);
}
