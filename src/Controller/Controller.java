package Controller;

import Enums.TradeSignal;
import Observable.Observable;
import Service.ControllerService;
import View.Gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private static ArrayList<Double> stockPrices = new ArrayList<>();
    private static HashMap<Integer, TradeSignal> predictions = new HashMap<>(); //<advisor hashcode, advisor prediction>
    private static ArrayList<String> advisorsText = new ArrayList<>();
    private static final ArrayList<Integer> observersHashCodes = new ArrayList<>();
    private static Observable observable;
    private static Gui gui;

    public static void setObservable(Observable observable1){
        observable = observable1;
    }
    public static void setGui(Gui gui1){
        gui = gui1;
    }

    public static void setStockPricesFromView(String filepath){
        if(filepath != null){
            ControllerService.generateStockPricesList(filepath, stockPrices);
            if (!stockPrices.isEmpty()){
                putStockPricesListToModel();
            }
        }
    }
    public static void putStockPricesListToModel(){
        observable.setObservableData(stockPrices);
    }
    public static void setDataFromModel(int hashCode, TradeSignal prediction){
        predictions.put(hashCode, prediction);
        if (predictions.size() == observersHashCodes.size()){
            int generalPrediction = ControllerService.generalizePrediction(predictions);
            putDataToView(generalPrediction);
        }
    }
    public static void putDataToView(int generalPrediction){

        //result label
        String advice = ControllerService.adviceMaker(generalPrediction);
        Color color = ControllerService.colorSwitcher(generalPrediction);
        gui.setTextOnResultLabel(advice, color);

        //Last price label
        String price = ControllerService.GetLastPriceFromStocksList(stockPrices);
        gui.setTextOnLastPriceLabel(price);

        //chart
        gui.clearChart();
        gui.createChart(stockPrices);

        //advisors labels
        gui.clearAdvisorsLabels();
        advisorsText = ControllerService.generateAdvisorsText(predictions, observersHashCodes);
        gui.createAdvisorsLabel(advisorsText);

        //clear all data at the end
        clearAllData();
    }

    public static void clearAllData(){
        stockPrices.clear();
        predictions.clear();
        advisorsText.clear();
    }


    public static void AddToObserversHashCodes(int observerHashCode){
        observersHashCodes.add(observerHashCode);
    }

    public static ArrayList<Double> getStockPrices() {
        return stockPrices;
    }

    public static void setStockPrices(ArrayList<Double> stockPrices) {
        Controller.stockPrices = stockPrices;
    }

    public static HashMap<Integer, TradeSignal> getPredictions() {
        return predictions;
    }

    public static void setPredictions(HashMap<Integer, TradeSignal> predictions) {
        Controller.predictions = predictions;
    }

}
