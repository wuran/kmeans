package cn.itcast.bean;

public class DistanceBean {
	private DataBean center=null;//中心点
	private DataBean otherpoint=null;//求距离点
	private double distance=0.0;//距离是？？？
	public DataBean getCenter() {
		return center;
	}
	public void setCenter(DataBean center) {
		this.center = center;
	}
	public DataBean getOtherpoint() {
		return otherpoint;
	}
	public void setOtherpoint(DataBean otherpoint) {
		this.otherpoint = otherpoint;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
