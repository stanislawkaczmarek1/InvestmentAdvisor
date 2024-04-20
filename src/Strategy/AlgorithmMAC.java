package Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmMAC implements PredictionStrategy{
    @Override
    public int prediction(ArrayList<Double> stockPrices) {
        System.out.println("Moving Average Crossover: ");

        List<Double> reversedList = new ArrayList<>(stockPrices);
        Collections.reverse(reversedList);
        ArrayList<Double> stockPricesReversed = new ArrayList<>(reversedList);

        int shortMA = 9;
        int longMA = 21;

        if (stockPricesReversed.size()<longMA){
            System.out.println("NO SIGNAL");
            return 3;
        }

        double shortAvg = calculateMovingAverage(stockPricesReversed, shortMA);
        double longAvg = calculateMovingAverage(stockPricesReversed, longMA);

        if (shortAvg > longAvg) {
            System.out.println("BUY");
            return 1;
        }
        else if (shortAvg < longAvg) {
            System.out.println("SELL");
            return 2;
        } else {
            System.out.println("NO SIGNAL");
            return 3;
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
