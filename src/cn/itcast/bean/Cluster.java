package cn.itcast.bean;

import java.util.ArrayList;
/*该bean包含一个聚类中心和包含所有点*/
public class Cluster {
	private int center;// 聚类中心点的id  
    private ArrayList<DataBean> ofCluster = new ArrayList<DataBean>();// 属于这个聚类的点的集合 
	public int getCenter() {
		return center;
	}
	public void setCenter(int center) {
		this.center = center;
	}
	public ArrayList<DataBean> getOfCluster() {
		return ofCluster;
	}
	public void setOfCluster(ArrayList<DataBean> ofCluster) {
		this.ofCluster = ofCluster;
	}
	public void addDatabean(DataBean databean) {  
        if (!(this.ofCluster.contains(databean)))  
            this.ofCluster.add(databean);  
    }  

}
