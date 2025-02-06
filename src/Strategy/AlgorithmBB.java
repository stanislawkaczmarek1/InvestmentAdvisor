package Strategy;

import Enums.TradeSignal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmBB implements PredictionStrategy{
    @Override
    public TradeSignal prediction(ArrayList<Double> stockPrices) {
        System.out.println("Bollinger Bands: ");

        List<Double> reversedList = new ArrayList<>(stockPrices);
        Collections.reverse(reversedList);
        ArrayList<Double> stockPricesReversed = new ArrayList<>(reversedList);

        int period = 20;

        if (stockPricesReversed.size()<period){
            return TradeSignal.NO_SIGNAL;
        }

        double stdDevMultiplier = 2.0;

        double movingAverage = calculateMovingAverage(stockPricesReversed, period);

        double stdDev = calculateStandardDeviation(stockPricesReversed, period, movingAverage);

        double upperBand = movingAverage + stdDevMultiplier * stdDev;
        double lowerBand = movingAverage - stdDevMultiplier * stdDev;

        double lastPrice = stockPricesReversed.get(0);

        if (lastPrice > upperBand) {
            System.out.println("SELL");
            return TradeSignal.SELL;
        } else if (lastPrice < lowerBand) {
            System.out.println("BUY");
            return TradeSignal.BUY;
        } else {
            System.out.println("NO SIGNAL");
            return TradeSignal.NO_SIGNAL;
        }
    }

    private static double calculateMovingAverage(ArrayList<Double> prices, int period) {
        double sum = 0.0;
        for (int i = 0; i < period; i++) {
            sum += prices.get(i);
        }
        return sum / period;
    }

    private static double calculateStandardDeviation(ArrayList<Double> prices, int period, double movingAverage) {
        double sum = 0.0;
        for (int i = 0; i < period; i++) {
            sum += Math.pow(prices.get(i) - movingAverage, 2);
        }
        return Math.sqrt(sum / period);
    }
}
