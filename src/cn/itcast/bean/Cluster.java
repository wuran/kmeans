package cn.itcast.bean;

import java.util.ArrayList;
/*��bean����һ���������ĺͰ������е�*/
public class Cluster {
	private int center;// �������ĵ��id  
    private ArrayList<DataBean> ofCluster = new ArrayList<DataBean>();// �����������ĵ�ļ��� 
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
