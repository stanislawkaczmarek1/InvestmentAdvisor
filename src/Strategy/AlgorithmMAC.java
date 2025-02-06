package Strategy;

import Enums.TradeSignal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmMAC implements PredictionStrategy{
    @Override
    public TradeSignal prediction(ArrayList<Double> stockPrices) {
        System.out.println("Moving Average Crossover: ");

        List<Double> reversedList = new ArrayList<>(stockPrices);
        Collections.reverse(reversedList);
        ArrayList<Double> stockPricesReversed = new ArrayList<>(reversedList);

        int shortMA = 9;
        int longMA = 21;

        if (stockPricesReversed.size()<longMA){
            System.out.println("NO SIGNAL");
            return TradeSignal.NO_SIGNAL;
        }

        double shortAvg = calculateMovingAverage(stockPricesReversed, shortMA);
        double longAvg = calculateMovingAverage(stockPricesReversed, longMA);

        if (shortAvg > longAvg) {
            System.out.println("BUY");
            return TradeSignal.BUY;
        }
        else if (shortAvg < longAvg) {
            System.out.println("SELL");
            return TradeSignal.SELL;
        } else {
            System.out.println("NO SIGNAL");
            return TradeSignal.NO_SIGNAL;
        }
    }

    public static double calculateMovingAverage(ArrayList<Double> prices, int period) {
        double sum = 0;
        for (int i = 0; i < period; i++) {
            sum += prices.get(i);
        }
        return sum / period;
    }


}
