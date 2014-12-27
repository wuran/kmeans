package cn.itcast.bean;

public class DistanceArrBean {
	private DataBean usualpoint=null;//具体某一个点
	private DataBean cterpoint=null;//聚类中心
	private Double[] distancetoKcenter;//到中心距离数组
	public DataBean getUsualpoint() {
		return usualpoint;
	}
	public void setUsualpoint(DataBean usualpoint) {
		this.usualpoint = usualpoint;
	}
	public DataBean getCterpoint() {
		return cterpoint;
	}
	public void setCterpoint(DataBean cterpoint) {
		this.cterpoint = cterpoint;
	}
	public Double[] getDistancetoKcenter() {
		return distancetoKcenter;
	}
	public void setDistancetoKcenter(Double[] distancetoKcenter) {
		this.distancetoKcenter = distancetoKcenter;
	}
}
