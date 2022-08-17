package mk.ukim.finki.web.financeproject.service;

import mk.ukim.finki.web.financeproject.model.dto.PieChartDataDto;
import mk.ukim.finki.web.financeproject.model.piechart.Dataset;
import mk.ukim.finki.web.financeproject.model.piechart.Options;
import mk.ukim.finki.web.financeproject.model.piechart.PieChart;
import mk.ukim.finki.web.financeproject.model.piechart.PieChartData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieChartFactory {
    public static PieChart createPieChartData(List<PieChartDataDto> dataList) {
        PieChart pieChart = new PieChart();

        PieChartData pieChartData = new PieChartData();

        List<String> labels = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        List<String> backgroundColors = new ArrayList<>();
        List<String> hoverBackgroundColor = new ArrayList<>();

        List<Dataset> datasets = new ArrayList<>();
        Dataset dataset = new Dataset();

        for (PieChartDataDto dataDto : dataList) {
            labels.add(dataDto.getLabel());
            data.add(dataDto.getValue());
            backgroundColors.add(dataDto.getBackgroundColor());
            hoverBackgroundColor.add(dataDto.getHoverBackgroundColor());
        }

        dataset.setData(data);
        dataset.setBackgroundColor(backgroundColors);
        dataset.setHoverBackgroundColor(hoverBackgroundColor);

        datasets.add(dataset);

        Options options = new Options();
        options.setResponsive(true);

        pieChartData.setLabels(labels);
        pieChartData.setDatasets(datasets);

        pieChart.setData(pieChartData);
        pieChart.setType("pie");
        pieChart.setOptions(options);

        return pieChart;
    }

    public static PieChart createPieChartSentiment(Integer positiveCount, Integer negativeCount, Integer neutralCount) {
        List<PieChartDataDto> dataDtos;

        PieChartDataDto redData = new PieChartDataDto();
        redData.setLabel("Negative");
        redData.setBackgroundColor("#F7464A");
        redData.setHoverBackgroundColor("#FF5A5E");
        redData.setValue(negativeCount);

        PieChartDataDto greenData = new PieChartDataDto();
        greenData.setLabel("Positive");
        greenData.setBackgroundColor("#46BFBD");
        greenData.setHoverBackgroundColor("#5AD3D1");
        greenData.setValue(positiveCount);

        PieChartDataDto greyData = new PieChartDataDto();
        greyData.setLabel("Grey");
        greyData.setBackgroundColor("#949FB1");
        greyData.setHoverBackgroundColor("#949FB1");
        greyData.setValue(neutralCount);

        dataDtos = Arrays.asList(redData, greenData, greyData);

        return PieChartFactory.createPieChartData(dataDtos);
    }
}
