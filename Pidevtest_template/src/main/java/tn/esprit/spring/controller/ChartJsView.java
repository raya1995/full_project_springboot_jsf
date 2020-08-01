package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bubble.BubbleChartModel;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.scatter.ScatterChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.services.ConnectedUserService;


@Named
@RequestScoped
public class ChartJsView  {
	@Autowired
	ConnectedUserService connectedUserService;
    
	
	 static int  yearselected  ;
    private PieChartModel pieModel;
    
    private PolarAreaChartModel polarAreaModel;
    
    private LineChartModel lineModel;
    
    private LineChartModel cartesianLinerModel;
    
    private BarChartModel barModel;
    
    private BarChartModel barModel2;
    
    private HorizontalBarChartModel hbarModel;
    
    private BarChartModel stackedBarModel;
    
    private BarChartModel stackedGroupBarModel;
    
    private RadarChartModel radarModel;
    
    private RadarChartModel radarModel2;
    
    private BubbleChartModel bubbleModel;
    
    private BarChartModel mixedModel;
    
    private DonutChartModel donutModel;
    
    private ScatterChartModel scatterModel;

    @PostConstruct
    public void init() {
        createPieModel();
        
        createLineModel();
       
    }
    
    public int getYearselected() {
		return yearselected;
	}

	public void setYearselected(int yearselected) {
		this.yearselected = yearselected;
	}

	private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values=connectedUserService.getconnectedmonthofyearnumber();
        dataSet.setData(values);
        
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(242, 98, 121)");
        bgColors.add("rgb(75, 37, 109)");
        bgColors.add("rgb(160, 158, 214)");
        bgColors.add("rgb(63, 100, 128)");
        bgColors.add("rgb(104, 143, 173)");
        bgColors.add("rgb(0, 176, 178)");
        bgColors.add("rgb(215, 51, 255)");
        bgColors.add("rgb(69, 65, 70)");
        bgColors.add("rgb(255, 228, 2)");
        dataSet.setBackgroundColor(bgColors);
        
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");
        data.setLabels(labels);
        
        pieModel.setData(data);
    }
    
    
    
    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();
        
        
        LineChartDataSet dataSet = new LineChartDataSet();
        LineChartDataSet dataSet1 = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        List<Object> values1 = new ArrayList<>();
        values=connectedUserService.getconnectedmonthofyear();
       
        dataSet.setData(values);


       
        dataSet.setFill(false);
        dataSet.setLabel("My First Dataset");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");
        data.setLabels(labels);
        
        //Options
        LineChartOptions options = new LineChartOptions();        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line Chart");
        options.setTitle(title);
        
        lineModel.setOptions(options);
        lineModel.setData(data);
    }
    
 
    
    
    
   
    
  
    
    

  

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PolarAreaChartModel getPolarAreaModel() {
        return polarAreaModel;
    }

    public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
        this.polarAreaModel = polarAreaModel;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public LineChartModel getCartesianLinerModel() {
        return cartesianLinerModel;
    }

    public void setCartesianLinerModel(LineChartModel cartesianLinerModel) {
        this.cartesianLinerModel = cartesianLinerModel;
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public HorizontalBarChartModel getHbarModel() {
        return hbarModel;
    }

    public void setHbarModel(HorizontalBarChartModel hbarModel) {
        this.hbarModel = hbarModel;
    }

    public BarChartModel getStackedBarModel() {
        return stackedBarModel;
    }

    public void setStackedBarModel(BarChartModel stackedBarModel) {
        this.stackedBarModel = stackedBarModel;
    }

    public BarChartModel getStackedGroupBarModel() {
        return stackedGroupBarModel;
    }

    public void setStackedGroupBarModel(BarChartModel stackedGroupBarModel) {
        this.stackedGroupBarModel = stackedGroupBarModel;
    }

    public RadarChartModel getRadarModel() {
        return radarModel;
    }

    public void setRadarModel(RadarChartModel radarModel) {
        this.radarModel = radarModel;
    }

    public RadarChartModel getRadarModel2() {
        return radarModel2;
    }

    public void setRadarModel2(RadarChartModel radarModel2) {
        this.radarModel2 = radarModel2;
    }

    public BubbleChartModel getBubbleModel() {
        return bubbleModel;
    }

    public void setBubbleModel(BubbleChartModel bubbleModel) {
        this.bubbleModel = bubbleModel;
    }

    public BarChartModel getMixedModel() {
        return mixedModel;
    }

    public void setMixedModel(BarChartModel mixedModel) {
        this.mixedModel = mixedModel;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }
    
    public ScatterChartModel getScatterModel() {
        return scatterModel;
    }
    
    public void setScatterModel(ScatterChartModel scatterModel) {
        this.scatterModel = scatterModel;
    }
}