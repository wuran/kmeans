package cn.itcast.util;

import java.util.ArrayList;

import cn.itcast.bean.DataBean;
import cn.itcast.bean.DistanceBean;

public class ToolDistance {
	  //����ŷʽ���� ������������󡣡�  
    public static double juli(DataBean g1, DataBean g2) {  
    	//��άŷ�Ͼ��룺d = sqrt((x1-x2)^2+(y1-y2)^2)
        double result = (Double) Math.sqrt(StrictMath.pow(g1.getXaxis() - g2.getXaxis(), 2)  
                + StrictMath.pow(g1.getYaxis() - g2.getYaxis(), 2));  
               return result;  
    }  
    public static double getDistanceByptop(DataBean usualpoint,DataBean center,ArrayList<DistanceBean> disBean){
    	//DistanceBean bean=new DistanceBean();
    	ArrayList<DistanceBean> disBean1=new ArrayList<DistanceBean>();
    	//disBean1=disbean;
    	//for(DistanceBean bean:disBean)
    	return 0;
    }
}
