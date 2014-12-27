package cn.itcast.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.itcast.bean.DataBean;
import cn.itcast.util.XmlParse;;

public class XmlUtil {
	public static DataBean find(String pointid){
		try {
			String xaxis=null;
			String yaxis=null;
			
			/*首先还是要获取文档对象树*/
			Document document=XmlParse.getDocument();
			/*拿pointid与xml里面的节点point的属性比对*/
			NodeList list=document.getElementsByTagName("point");
			for(int i=0;i<list.getLength();i++){
				Element elist=(Element)list.item(i);//获得每一个point
				if(elist.getAttribute("pointid").equals(pointid)){
					String idcard=elist.getAttribute("X-axis");
					String examid0=elist.getAttribute("Y-axis");
					NodeList children=elist.getChildNodes();
					for(int j=0;j<children.getLength();j++){
						/*这是关键点：刨去children中的空白节点*/
						if((children.item(j).getTextContent().getClass().equals(String.class))&&((children.item(j).getTextContent())!=null)){
							Node e=children.item(j);
							if(e.getNodeName().equals("X-axis")){
								xaxis=e.getTextContent();
							}else if(e.getNodeName().equals("Y-axis")){
								yaxis=e.getTextContent();
							}
						}
					}
					double xaxis_double=Double.parseDouble(xaxis);
					double yaxis_double=Double.parseDouble(yaxis);
					DataBean point=new DataBean();
					point.setPointid(pointid);
					point.setXaxis(xaxis_double);
					point.setYaxis(yaxis_double);
					return point;
					}
			}
			return null;
		} catch (Exception e) {
			 throw new RuntimeException(e);
			}
		
	}
}
