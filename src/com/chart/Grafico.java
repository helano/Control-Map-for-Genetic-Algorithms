package com.chart;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.DefaultMultiValueCategoryDataset;
import org.jfree.data.xy.XYDataset;

import com.orsoncharts.data.Dataset3DChangeListener;
import com.orsoncharts.data.xyz.XYZDataset;

import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class Grafico extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grafico frame = new Grafico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Grafico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 11, 691, 456);
		contentPane.add(panel);

		JButton btnGrafico = new JButton("grafico pizza");
		btnGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultPieDataset dpd = new DefaultPieDataset();

				dpd.setValue("populacao", 1);
				dpd.setValue("crossover", 2);
				dpd.setValue("torneio", 3);

				JFreeChart grafico = ChartFactory.createPieChart("pizza ", dpd, true, true, false);
				ChartPanel chartPanel = new  ChartPanel(grafico);


				panel.add(chartPanel);
				panel.validate();





			}
		});
		btnGrafico.setBounds(149, 493, 89, 23);
		contentPane.add(btnGrafico);

		JButton btnGraficoDipessao = new JButton("Grafico dipessao");
		btnGraficoDipessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultPieDataset dpd = new DefaultPieDataset();
				XYChart.Series series = new XYChart.Series();

				series.getData().add(new XYChart.Data("1", 23));

				XYZDataset ds = new XYZDataset() {

					@Override
					public void removeChangeListener(Dataset3DChangeListener arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public boolean hasListener(EventListener arg0) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void addChangeListener(Dataset3DChangeListener arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public double getZ(int arg0, int arg1) {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public double getY(int arg0, int arg1) {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public double getX(int arg0, int arg1) {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public List<Comparable<?>> getSeriesKeys() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Comparable<?> getSeriesKey(int arg0) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public int getSeriesIndex(Comparable<?> arg0) {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getSeriesCount() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getItemCount(int arg0) {
						// TODO Auto-generated method stub
						return 0;
					}
				};

				DefaultMultiValueCategoryDataset pdV = new DefaultMultiValueCategoryDataset ();


				dpd.setValue("teste", 1);
				dpd.setValue("teste", 2);
				dpd.setValue("teste", 3);
				dpd.setValue("teste", 3);

				JFreeChart grafico = ChartFactory.createPieChart("pizza ", dpd, true, true, false);

				//ScatterChart grafico2 = ChartFactory.createSc("grafico scatter","teste", "teste", ds);
				ChartPanel chartPanel = new  ChartPanel(grafico);


				panel.add(chartPanel);
				panel.validate();


			}
		});
		btnGraficoDipessao.setBounds(418, 493, 89, 23);
		contentPane.add(btnGraficoDipessao);
	}
}
