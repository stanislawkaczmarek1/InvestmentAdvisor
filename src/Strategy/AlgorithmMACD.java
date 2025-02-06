package Strategy;

import Enums.TradeSignal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmMACD implements PredictionStrategy {
    @Override
    public TradeSignal prediction(ArrayList<Double> stockPrices) {
        System.out.println("Moving Average Convergence Divergence: ");

        List<Double> reversedList = new ArrayList<>(stockPrices);
        Collections.reverse(reversedList);
        ArrayList<Double> stockPricesReversed = new ArrayList<>(reversedList);

        if (stockPricesReversed.size() < 26) {
            return TradeSignal.NO_SIGNAL;
        }

        ArrayList<Double> ema12 = calculateEMA(stockPricesReversed, 12);
        ArrayList<Double> ema26 = calculateEMA(stockPricesReversed, 26);

        ArrayList<Double> macdLine = calculateMACDLine(ema12, ema26);

        int lastIndex = macdLine.size() - 1;

        if (macdLine.get(lastIndex) > 0 && macdLine.get(lastIndex - 1) <= 0) {
            System.out.println("BUY");
            return TradeSignal.BUY;
        } else if (macdLine.get(lastIndex) < 0 && macdLine.get(lastIndex - 1) >= 0) {
            System.out.println("SELL");
            return TradeSignal.SELL;
        } else {
            System.out.println("NO SIGNAL");
            return TradeSignal.NO_SIGNAL;
        }
    }

    private static ArrayList<Double> calculateEMA(ArrayList<Double> prices, int period) {
        ArrayList<Double> ema = new ArrayList<>();
        double multiplier = 2.0 / (period + 1);

        double sum = 0;
        for (int i = 0; i < period; i++) {
            sum += prices.get(i);
        }
        ema.add(sum / period);

        for (int i = period; i < prices.size(); i++) {
            double currentPrice = prices.get(i);
            double previousEMA = ema.get(i - period);
            double currentEMA = (currentPrice - previousEMA) * multiplier + previousEMA;
            ema.add(currentEMA);
        }

        return ema;
    }

    private static ArrayList<Double> calculateMACDLine(ArrayList<Double> ema12, ArrayList<Double> ema26) {
        ArrayList<Double> macdLine = new ArrayList<>();

        int minLength = Math.min(ema12.size(), ema26.size());

        for (int i = 0; i < minLength; i++) {
            double macdValue = ema12.get(i) - ema26.get(i);
            macdLine.add(macdValue);
        }

        return macdLine;
    }
}
