package Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmSSAR implements PredictionStrategy{
    @Override
    public int prediction(ArrayList<Double> stockPrices) {
        System.out.println("Simple Support and Resistance");

        List<Double> reversedList = new ArrayList<>(stockPrices);
        Collections.reverse(reversedList);
        ArrayList<Double> stockPricesReversed = new ArrayList<>(reversedList);

        double recentPrice = stockPricesReversed.get(0);
        double support = calculateSupport(stockPricesReversed);
        double resistance = calculateResistance(stockPricesReversed);

        if (recentPrice > resistance) {
            System.out.println("SELL");
            return 2;
        } else if (recentPrice < support) {
            System.out.println("BUY");
            return 1;
        } else {
            System.out.println("NO SIGNAL");
            return 3;
        }
    }

    private static double calculateSupport(ArrayList<Double> prices) {
        return calculateAverage(prices);
    }


    private static double calculateResistance(ArrayList<Double> prices) {
        return calculateMax(prices);
    }

    private static double calculateAverage(ArrayList<Double> prices) {
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.size();
    }

    private static double calculateMax(ArrayList<Double> prices) {
        double max = Double.MIN_VALUE;
        for (double price : prices) {
            if (price > max) {
                max = price;
            }
        }
        return max;
    }


}
