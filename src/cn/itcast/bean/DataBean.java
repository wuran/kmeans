package cn.itcast.bean;

public class DataBean {
	private String pointid=null;//×ø±êidºÅ
	private double xaxis;//x×ø±ê
	private double yaxis;//y×ø±ê
	public DataBean(double xaxis,double yaxis) {  
	        super();  
	        this.xaxis=xaxis;
	        this.yaxis=yaxis;
	        } 
	public DataBean(){}
	public String getPointid() {
		return pointid;
	}
	public void setPointid(String pointid) {
		this.pointid = pointid;
	}
	public double getXaxis() {
		return xaxis;
	}
	public void setXaxis(double xaxis) {
		this.xaxis = xaxis;
	}
	public double getYaxis() {
		return yaxis;
	}
	public void setYaxis(double yaxis) {
		this.yaxis = yaxis;
	}
	

}
