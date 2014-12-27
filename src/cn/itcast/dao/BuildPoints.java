package cn.itcast.dao;

import java.util.ArrayList;

import cn.itcast.bean.DataBean;
import cn.itcast.util.XmlUtil;

/*从xml获得所有结点信息，封装一个ArrayList对象*/
public class BuildPoints {
	private ArrayList<DataBean> points=new ArrayList<DataBean>();
	public ArrayList<DataBean> preparePoint(){
		for(int i=0;i<12;i++){
			DataBean datatemp=new DataBean();
			datatemp=XmlUtil.find(i+"");
			points.add(datatemp);
		}
		System.out.println("从xml文档读进来待聚类的源数据是：");
		for(DataBean temp:points){
			System.out.println(" "+temp.getPointid()+" "+temp.getXaxis()+" "+temp.getYaxis());
		}
		System.out.println("\n");
		return points;
	}

}
