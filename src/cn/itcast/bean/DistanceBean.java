package cn.itcast.bean;

public class DistanceBean {
	private DataBean center=null;//���ĵ�
	private DataBean otherpoint=null;//������
	private double distance=0.0;//�����ǣ�����
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
