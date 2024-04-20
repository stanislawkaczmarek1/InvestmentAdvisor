package View;

import Controller.Controller;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gui extends JFrame {
    private JPanel panel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel infoLeftLabel;
    private JTextField inputTextField;
    private JButton inputButton;
    private JLabel infoRightLabel;
    private JLabel resultLabel;
    private ChartPanel chartPanel;
    private JLabel advisorsLabelTitle;
    private ArrayList<JLabel> advisorsTextList = new ArrayList<>();
    private JLabel lastPriceLabel;
    public Gui(){
        crateFrame();
        createGui();
    }
    public void crateFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Investment Advisor");
        setSize(700,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public void createGui(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //left panel
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(220,0));
        panel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

        infoLeftLabel = new JLabel("Entry stock data");
        leftPanel.add(infoLeftLabel);
        leftPanel.add(Box.createVerticalStrut(10));

        inputTextField = new JTextField();
        inputTextField.setMaximumSize(new Dimension(350,25));
        leftPanel.add(inputTextField);
        leftPanel.add(Box.createVerticalStrut(10));

        inputButton = new JButton("Run");
        inputButton.addActionListener(new RunButtonReaction());
        leftPanel.add(inputButton);
        leftPanel.add(Box.createVerticalStrut(25));

        leftPanel.add(new JLabel("Last price: "));
        leftPanel.add(Box.createVerticalStrut(15));

        lastPriceLabel = new JLabel();
        leftPanel.add(lastPriceLabel);
        leftPanel.add(Box.createVerticalStrut(15));

        advisorsLabelTitle = new JLabel("Predictions of individual algorithms: ");
        leftPanel.add(advisorsLabelTitle);
        leftPanel.add(Box.createVerticalStrut(15));



        //right panel
        rightPanel = new JPanel();
        panel.add(rightPanel, BorderLayout.CENTER);

        infoRightLabel = new JLabel("Algorithms general prediction:");
        infoRightLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        rightPanel.add(infoRightLabel);

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        rightPanel.add(resultLabel);


        add(panel);
        revalidate();
        repaint();
    }
    public void createAdvisorsLabel(ArrayList<String> advisorsText){
        ArrayList<String> advisorsNames = new ArrayList<>(List.of("Moving Average Crossover", "Relative Strength Index", "MACD", "Bollinger Bands", "Simple Support and Resistance"));
        for (int i = 0; i < advisorsText.size(); i++) {
            if(advisorsText.size() == advisorsNames.size()) {
                String text = advisorsNames.get(i) +  " : " + advisorsText.get(i);
                advisorsTextList.add(new JLabel(text));
                leftPanel.add(advisorsTextList.get(i));
                advisorsTextList.get(i).setVisible(true);
            }
        }
    }
    public void createChart(ArrayList<Double> stockPrices){

        XYSeries series = new XYSeries("Stock Prices");
        for (int i = 0; i < stockPrices.size(); i++) {
            series.add(i + 1, stockPrices.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);


        JFreeChart chart = ChartFactory.createXYLineChart(
                "Stock chart based on the entered data",
                "Time",
                "Price",
                dataset
        );

        XYPlot plot = (XYPlot) chart.getPlot();

        plot.getDomainAxis().setLabelFont(new Font("Arial", Font.PLAIN, 12));

        plot.getRangeAxis().setLabelFont(new Font("Arial", Font.PLAIN, 12));

        chart.getTitle().setFont(new Font("Arial", Font.PLAIN, 10));

        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(450,280));
        chartPanel.setVisible(true);
        rightPanel.add(chartPanel);
    }
    class RunButtonReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputTextField.getText();
            Controller.setStockPricesFromView(input);
        }
    }

    public void setTextOnResultLabel(String advice, Color color) {
        resultLabel.setText(advice);
        resultLabel.setForeground(color);
    }
    public void setTextOnLastPriceLabel(String price){
        lastPriceLabel.setText(price);
    }

    public void clearChart(){
        if(chartPanel != null) {
            chartPanel.setVisible(false);
            rightPanel.remove(chartPanel);
        }
    }
    public void clearAdvisorsLabels(){
        if (!advisorsTextList.isEmpty()){
            for (int i = 0; i < advisorsTextList.size(); i++) {
                advisorsTextList.get(i).setVisible(false);
                leftPanel.remove(advisorsTextList.get(i));
            }
            advisorsTextList.clear();
        }
    }

}
