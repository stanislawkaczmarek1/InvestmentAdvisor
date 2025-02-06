package Strategy;

import Enums.TradeSignal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmRSI implements PredictionStrategy{
    @Override
    public TradeSignal prediction(ArrayList<Double> stockPrices) {

        List<Double> reversedList = new ArrayList<>(stockPrices);
        Collections.reverse(reversedList);
        ArrayList<Double> stockPricesReversed = new ArrayList<>(reversedList);

        int period = 14;

        if (stockPricesReversed.size()<period){
            System.out.println("Relative Strength Index");
            return TradeSignal.NO_SIGNAL;
        }

        double rsi = calculateRSI(stockPricesReversed, period);

        System.out.println("Relative Strength Index ("+ rsi +"): ");
        if (rsi >= 70) {
            System.out.println("SELL");
            return TradeSignal.SELL;
        } else if (rsi <= 30) {
            System.out.println("BUY");
            return TradeSignal.BUY;
        } else {
            System.out.println("NO SIGNAL");
            return TradeSignal.NO_SIGNAL;
        }
    }

    private static double calculateRSI(List<Double> prices, int period) {
        double avgGain = 0;
        double avgLoss = 0;

        for (int i = 1; i < period; i++) {
            double priceDiff = prices.get(i) - prices.get(i - 1);
            if (priceDiff >= 0) {
                avgGain += priceDiff;
            } else {
                avgLoss -= priceDiff;
            }
        }

        avgGain /= period;
        avgLoss /= period;

        double RS = avgGain / avgLoss;
        double RSI = 100 - (100 / (1 + RS));

        return RSI;

    }
}
