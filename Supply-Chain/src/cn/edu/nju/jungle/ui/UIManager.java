package cn.edu.nju.jungle.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import cn.edu.nju.jungle.data.PeriodData;
import cn.edu.nju.jungle.util.Constant;
import cn.edu.nju.jungle.util.LanguageManager;

/**
 * 
 * @author Jungle
 *
 */
public class UIManager {
    private static UIManager manager;
    private static JFrame mainFrame;
    private static ChartPanel chartPanel;
    private static JFreeChart chart;
    private static DefaultCategoryDataset dataset;
    static{
        mainFrame = new JFrame();
        mainFrame.setSize(200, 100);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle(LanguageManager.getInstance().getString(Constant.FRAME_TITLE));
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dataset = new DefaultCategoryDataset();
        assignData();
        chart = ChartFactory.createLineChart(LanguageManager.getInstance().getString(Constant.CHART_TITLE),
                    LanguageManager.getInstance().getString(Constant.PERIOD), LanguageManager.getInstance().getString(Constant.QUANTITY), 
                    dataset, PlotOrientation.VERTICAL, false , false, false);
        chartPanel = new ChartPanel(chart);
        mainFrame.add(chartPanel);
        mainFrame.pack();
        LanguageManager.releaseLanguageFile();
    };
    private UIManager(){
        
    }
    public static UIManager getInstance(){
        if(null==manager){
            manager = new UIManager();
        }
        return manager;
    }
    public static JFrame getMainFrame(){
        return mainFrame;
    }
    public static void updateTextLabel(int quantity){
        int last = PeriodData.period.size();
        PeriodData.period.add(last+"");
        PeriodData.customerRequire.add(quantity);
        dataset.clear();
        assignData();
    }
    public static void showFrame(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame.setVisible(true);
            }
        });
    }
    private static void assignData(){
        for(int i=0; i<PeriodData.period.size(); i++){
            dataset.addValue(PeriodData.customerRequire.get(i), Constant.AG_CUSTOMER, PeriodData.period.get(i));
        }
    }
}