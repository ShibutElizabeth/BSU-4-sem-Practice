import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

public class MainWindow extends JFrame {
    private JFreeChart chart;

    private MainWindow() {
        setVisible(true);
        setPreferredSize(new Dimension(800, 600));

        try {
            JsonReader reader = new JsonReader(new FileReader(
                    "C:\\Users\\Лиза\\IdeaProjects\\Practice4.3\\src\\statisticData"));
            Gson g = new Gson();
            Cosmetics[] cosmetics = g.fromJson(reader, Cosmetics[].class);
            for (var elem : cosmetics) {
                if (elem.getCount() <= 0)
                    throw new NumberFormatException("Number of women cannot be less than 1");
            }
            PieDataset pieDataset = createDataSet(cosmetics);
            chart = createChart(pieDataset);
            PiePlot plot = (PiePlot) chart.getPlot();
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                    "{0}: {1} women, {2} ");
            plot.setLabelGenerator(gen);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new ChartPanel(chart), BorderLayout.CENTER);
            add(panel);
            panel.validate();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        } catch (IOException | NumberFormatException | JsonSyntaxException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
        pack();
    }

    private JFreeChart createChart(final PieDataset dataSet) {
        chart = ChartFactory.createPieChart("The best cosmetics",
                dataSet, true, true, false);
        return chart;
    }

    private PieDataset createDataSet(Cosmetics[] container) {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (var elem : container) {
            dataSet.setValue(elem.getBrand(), elem.getCount());
        }
        return dataSet;
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}
class Cosmetics {
    private int count;
    private String brand;

    int getCount() {
        return count;
    }

    String getBrand() {
        return brand;
    }
}
