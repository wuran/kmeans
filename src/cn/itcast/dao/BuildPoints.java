package cn.itcast.dao;

import java.util.ArrayList;

import cn.itcast.bean.DataBean;
import cn.itcast.util.XmlUtil;

/*��xml������н����Ϣ����װһ��ArrayList����*/
public class BuildPoints {
	private ArrayList<DataBean> points=new ArrayList<DataBean>();
	public ArrayList<DataBean> preparePoint(){
		for(int i=0;i<12;i++){
			DataBean datatemp=new DataBean();
			datatemp=XmlUtil.find(i+"");
			points.add(datatemp);
		}
		System.out.println("��xml�ĵ��������������Դ�����ǣ�");
		for(DataBean temp:points){
			System.out.println(" "+temp.getPointid()+" "+temp.getXaxis()+" "+temp.getYaxis());
		}
		System.out.println("\n");
		return points;
	}

}
