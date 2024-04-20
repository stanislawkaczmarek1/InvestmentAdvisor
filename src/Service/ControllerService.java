package Service;


import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ControllerService {
    public static void generateStockPricesList(String filepath, ArrayList<Double> stockPrices){
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while ((line = reader.readLine()) != null){
                double value = Double.parseDouble(line);
                stockPrices.add(value);
            }
        }catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File not found");
        }
    }

    public static int generalizePrediction(HashMap<Integer, Integer> predictionsMap){
        ArrayList<Integer> predictionsList = new ArrayList<>(predictionsMap.values());
        System.out.println(predictionsList);
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        for (int i = 0; i < predictionsList.size(); i++) {
            if (predictionsList.get(i) == 1){
                sum1++;
            }
            if (predictionsList.get(i) == 2){
                sum2++;
            }
            if (predictionsList.get(i) == 3){
                sum3++;
            }
        }
        if ((sum1 > sum2) && (sum1 >= sum3)){
            return 1;
        } else if ((sum2 > sum1) && (sum2 >= sum3)){
            return 2;
        } else {
            return 3;
        }

    }
    public static String adviceMaker(int generalPrediction){

        if(generalPrediction == 1){
            return "Buy";
        }else if(generalPrediction == 2){
            return "Sell";
        }else {
            return "Conflicting signals";
        }
    }
    public static Color colorSwitcher(int generalPrediction){
        if(generalPrediction == 1){
            return Color.green;
        }else if(generalPrediction == 2){
            return Color.red;
        }else {
            return Color.DARK_GRAY;
        }
    }
    public static ArrayList<String> generateAdvisorsText(HashMap<Integer, Integer> predictionsMap, ArrayList<Integer> observersHashCodes){
        ArrayList<String> advisorsText = new ArrayList<>();
        ArrayList<Integer> predictionsValuesList = new ArrayList<>(predictionsMap.values());
        ArrayList<Integer> predictionsKeysList = new ArrayList<>(predictionsMap.keySet());
        for (int i = 0; i < observersHashCodes.size(); i++) {
            for (int j = 0; j < predictionsKeysList.size(); j++) {
                if ((predictionsKeysList.get(j)).equals(observersHashCodes.get(i))){
                    if (predictionsValuesList.get(j) == 1){
                        advisorsText.add("Buy");
                    }else if (predictionsValuesList.get(j) == 2){
                        advisorsText.add("Sell");
                    }else {
                        advisorsText.add("No signal");
                    }
                }
            }
        }
        return advisorsText;
    }

    public static String GetLastPriceFromStocksList(ArrayList<Double> stockPrice){
        String lastPrice = "";
        if(stockPrice != null){
            lastPrice = String.format("%.2f", stockPrice.get(stockPrice.size()-1)) + " USD";
        }
        return lastPrice;
    }
}
