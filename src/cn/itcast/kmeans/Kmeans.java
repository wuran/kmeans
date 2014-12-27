package cn.itcast.kmeans;
import java.io.IOException;
import java.util.*;

import cn.itcast.bean.*;
import cn.itcast.dao.BuildPoints;
import cn.itcast.util.ToolDistance;
import cn.itcast.util.ToolFileWrite;
/*k-means�㷨�������裺
��1�� �� n�����ݶ�������ѡ�� k ��������Ϊ��ʼ�������ģ�
��2�� ����ÿ���������ľ�ֵ�����Ķ��󣩣�����ÿ����������Щ���Ķ���ľ��룻��������С�������¶���Ӧ������л��֣�
��3�� ���¼���ÿ�����б仯������ľ�ֵ�����Ķ��󣩣�
��4�� �����׼��Ⱥ�����������һ���������纯������ʱ�����㷨��ֹ�����������������ص����裨2���������ظ�ֱ����׼��Ⱥ�����ʼ����Ϊֹ����һ�㶼���þ�������Ϊ��׼��Ⱥ�������*/
public class Kmeans {
	 public ArrayList<DataBean> datas = null;  //12������Դ
	    public int totalNumber = 0;// �õ����е��������Ŀ  (12)
	    public int K = 0;  
	 
	    public Kmeans(int k) {  
	    	datas = new BuildPoints().preparePoint();  
	        totalNumber = datas.size();  
	        K = k;  
	    }  
	  
	    // ��һ�����ѡȡ��������  
	    public Set<Integer> firstRandom() {  
	        Set<Integer> center = new HashSet<Integer>();// �������ĵĵ��id,����set��֤�������ظ�id  
	        Random ran = new Random();  
	        int roll = ran.nextInt(totalNumber);  
	        while (center.size() < K) {  
	            roll = ran.nextInt(totalNumber);  
	            center.add(roll);  
	        }  
	        return center;  
	    }  
	  
	    // ���ݾ������ĳ�ʼ��������Ϣ  
	    public ArrayList<Cluster> init(Set<Integer> center) {  
	        ArrayList<Cluster> cluster = new ArrayList<Cluster>();// ���� ������  
	        Iterator<Integer> it = center.iterator();  
	        
	        while (it.hasNext()) {  
	        	Cluster c = new Cluster();// ����һ������  
	            c.setCenter(it.next());  
	            cluster.add(c);  
	        }  
	        return cluster;  
	    }  
	  
	    /** 
	     * ��������㵽�����������ĵľ��룬���¾��� 
	     *  
	     * @param cluster 
	     *            ��������,��������ģ��������ԭ��ѵ���� 
	     * @param center 
	     *            ���ĵ�id,���ڼ�������㵽���ĵ�ľ��� return cluster ���������о�����ɵ����� 
	     */  
	    public ArrayList<Cluster> juLei(Set<Integer> center,  
	            ArrayList<Cluster> cluster) {  
	        ArrayList<Distance> distance = new ArrayList<Distance>();// ��ž�����Ϣ,��ʾÿ���㵽�������ĵ�ľ�����ɵ�����  
	        DataBean source = null;  
	        DataBean dest = null;  
	        int id = 0;// Ŀ�Ľڵ�id  
	        int id2 = 0;// Դ�ڵ�id  
	        Object[] p = center.toArray();// p Ϊ�������ĵ�id����  
	        boolean flag = false;  
	        // �ֱ��������㵽�������ĵ�ľ��룬����������С�ļ��뵽���������У����о���  
	        for (int i = 0; i < totalNumber; i++) {  
	            // ÿ���������,�����ൽ������С�ľ����о���վ�������  
	            distance.clear();  
	            // ���㵽j�������ĵ�ľ���,�����������ĵ�  
	            for (int j = 0; j < center.size(); j++) {  
	                // ����õ㲻�����ĵ��� ��������  
	                if (!(center.contains(i))) {  
	                    flag = true;  
	                    // �������  
	                    source = datas.get(i);// ĳ����  
	                    dest = datas.get((Integer) p[j]);// ���� ���ĵ�  
	                    // ������벢��������  
	                    distance.add(new Distance((Integer) p[j], i, ToolDistance.juli(  
	                            source, dest)));  
	                } else {  
	                    flag = false;  
	                }  
	            }  
	            // ˵��������ĳ���㵽�����ĵľ���,��ʼ�Ƚ�  
	            if (flag == true) {  
	                // ����Ƚ�һ���㵽�������ĵľ���Ĵ�С,�ҵ�������С�ĵ�� Ŀ��id,��Դid,  
	                // Ŀ��id�������ĵ�id������͹鵽������ĵ����ھ�����  
	                double min = distance.get(0).getDist();// Ĭ�ϵ�һ��distance��������С��  
	                // ��1��ʼ����distance����  
	                int minid = 0;  
	                for (int k = 1; k < distance.size(); k++) {  
	                    if (min > distance.get(k).getDist()) {  
	                        min = distance.get(k).getDist();  
	                        id = distance.get(k).getDest();// Ŀ�ģ��������ĵ�  
	                        id2 = distance.get(k).getSource();// ĳ����  
	                        minid = k;  
	                    } else {  
	                        id = distance.get(minid).getDest();  
	                        id2 = distance.get(minid).getSource();  
	                    }  
	                }  
	                // ����cluster�������飬�ҵ������ĵ�id����С����Ŀ�ĵ�id��ͬ�ľ���  
	                for (int n = 0; n < cluster.size(); n++) {  
	                    // ��������ĵ��id��ͬ ��setError  
	                    if (cluster.get(n).getCenter() == id) {  
	                        cluster.get(n).addDatabean(datas.get(id2));// ����þ������ľ�����С�ĵ����þ���  
	                        break;  
	                    }  
	                }  
	            }  
	        }  
	        return cluster;  
	    }  

	    // ����ѭ������ֱ����������û�����·���  
	    public ArrayList<DistanceBean> getResult() {  
	        ArrayList<Cluster> result = new ArrayList<Cluster>();  
	        ArrayList<Cluster> temp = new ArrayList<Cluster>();  
	        boolean flag = false;  
	        // �õ�������ĵ�Ȼ����о���  
	        Set<Integer> center = firstRandom();  
	        result = juLei(center, init(center)); 
	        System.out.println("�㷨���е�1�������ӡ��һ�ξ���:");
	        print(result);  //���������ࣨ�ֳ�����|������ࣩ
	        /*�����һ���������ľ���*/
        	//1.ȡ������������ĵ�
	        
	        //center_point_result:���3���������ĵ�����
	        ArrayList<DataBean> center_point_result = new ArrayList<DataBean>(); 
        	for(int i=0;i<result.size();i++){
        		DataBean pointofcenter=new DataBean();//pointofcenter
        		//pointofcenter.setPointid(result.get(i).getCenter()+"");//ȡ�����ĵ�id
        		for(int i1=0;i1<datas.size();i1++){
        			//��pointidȥ��ѯdatas����Դ����ȡ����������ĵ����Ϣ
        			if(datas.get(i1).getPointid().equals(result.get(i).getCenter()+"")){
        				pointofcenter=(DataBean)datas.get(i1);//�ҵ����ĵ����Ϣ��
        			}
        		}
        		center_point_result.add(pointofcenter);//���ҵ������ĵ�������ĵ�����
        	}
        	
        	//3������Щ�����㵽��������ľ���(�����Լ����Լ�)
        	double distance_to_center0=0.0,distance_to_center1=0.0,distance_to_center2=0.0;
        	double distanceoftemp=0.0;
        	//Double[] distancetoKcenter=new Double[K];//��K�����ĵ�ľ���
        	//distancebean��װ��ÿһ���㵽����������ĵľ�����Ϣ���������Խ�һ���ﵽ�����Ŀ�ģ��൱��Ҫ
        	ArrayList<DistanceBean> distancebean=new ArrayList<DistanceBean>();
        	//Arrays.sort()
        	for(DataBean point:datas){
        		//��ĳһpoint��3�����ĵ�ľ���
        		
        		for(int i=0;i<center_point_result.size();i++){
        			//��point��centerpoint�ľ���
        			DistanceBean distemp=new DistanceBean();
        			distanceoftemp=ToolDistance.juli(point,center_point_result.get(i));
        			//distancetoKcenter[i]=distanceoftemp;
        			distemp.setDistance(distanceoftemp);//����
        			distemp.setCenter(center_point_result.get(i));//��������
        			distemp.setOtherpoint(point);//����
        			distancebean.add(distemp);//��װ����
        			
        		}
        		
        	}
        	
	        return distancebean;  
	    }  
	    //ͨ��distancebean���¾���
	    public ArrayList<Cluster> getlastCluster(ArrayList<DistanceBean> distancebean){
	    	ArrayList<Cluster> cluster=new ArrayList<Cluster>();//��ż����ľ���
	    	//1.��ĳ���㵽�������ĵ�ľ�������Сֵ�����Ҽ�ס��Сֵ��Ӧ�����ĵ㣬��ô�õ����ڸ����ĵ�������
	    	//a.ͨ��distancebean��ȡ���Ķ�������
	    	int k=0;
	    	ArrayList<DistanceBean> mindis=new ArrayList<DistanceBean>();//12����С��dis
	    	
	    	for(int j=0;j<datas.size();j++){//��12���㿪ʼѭ��
	    	ArrayList<DistanceBean> tempdis=new ArrayList<DistanceBean>();//��ʱ�ģ�����װȷ��������о���	
	    	for(int i=0;i<distancebean.size();i++){
	    		//�Ѿ��õ�������36������
	    		DistanceBean temp=new DistanceBean();   //��ʱ�ģ�����װȷ���㣨otherpoint����ĳһȷ������	
	    		
	    		
	    			if(datas.get(j).getPointid().equals(distancebean.get(i).getOtherpoint().getPointid()))
	    				{
	    					temp.setCenter(distancebean.get(i).getCenter());
	    					temp.setOtherpoint(distancebean.get(i).getOtherpoint());
	    					temp.setDistance(distancebean.get(i).getDistance());
	    					tempdis.add(temp);
	    					if(tempdis.size()>=4)
	    						break;	    				
	    				}
	    		
	    		
	    		}
	    	double min=Double.MAX_VALUE;
	    	for(int i=0;i<tempdis.size();i++){
	    		if(tempdis.get(i).getDistance()<=min){
	    			min=tempdis.get(i).getDistance();
	    		}
	    		//zui xiao de 
	    	}
	    	
	    	//ArrayList<DataBean> ofcluster=new ArrayList<DataBean>();//��Ⱥ�������������е�
	    	for(k=0;k<tempdis.size();k++){
	    		if(min==tempdis.get(k).getDistance())
	    			{
	    			mindis.add(tempdis.get(k));
	    			break; 	    			
	    			}
	    	}	
	    	
	    		    	
	    }
	    	
	    //bug	
	   ArrayList<DataBean> allcenters=new ArrayList<DataBean>();//�����c
	   for(int i=0;i<K;i++){
		   allcenters.add(distancebean.get(i).getCenter());
	    }
	   
	    for(int i=0;i<allcenters.size();i++){	    	
	    	
	    	//��mindis�ж���cluster
	    	int j=0;
	    	Cluster tempclu=new Cluster();
	    	while(j<mindis.size()){
	    		if(mindis.get(j).getCenter().getPointid().equals(allcenters.get(i).getPointid())){
	    			tempclu.setCenter(Integer.parseInt(allcenters.get(i).getPointid()));
	    			tempclu.addDatabean(mindis.get(j).getOtherpoint());
	    			
	    		}
	    		j++;
	    	}
	    	
	    	cluster.add(tempclu);  
	    
	    }
	    return cluster;
}
	      
	    //������еľ���  
	    public void print(ArrayList<Cluster> cs) {  
	    	System.out.println("***************************************");  
	 	        for (int i = 0; i < cs.size(); i++) {  
	 	            Cluster c = cs.get(i);  
	 	            System.out.println("-----------------------------------------------------");  
	 	            System.out.println("center"+i+"\n"+"��������ID��" +c.getCenter());  
	 	            ArrayList<DataBean> p = c.getOfCluster();  
	 	            for (int j = 0; j < p.size(); j++) {  
	 	                System.out.println("point"+j+"_of_center"+"\n"+"��Χ��ID��"+p.get(j).getPointid()+"  x���꣺"+p.get(j).getXaxis()+"  y���꣺"+p.get(j).getYaxis()+"\n");  
	 	            }  
	 	        }
	    }
	    //������еľ��� ���ļ� 
	    public void printtoFile(ArrayList<Cluster> cs) throws IOException {  
	    	String _sDestFile="F:/workspace/KmeansDemo/clusterResult.txt";
	    	String _sContent0="KֵΪ�� "+K+""+"ʱ*************************"+"\r\n";
	    	 ToolFileWrite.method1(_sDestFile, _sContent0);
	 	        for (int i = 0; i < cs.size(); i++) {  
	 	            Cluster c = cs.get(i);  
	 	           /*
	 	            * ��������ID
	 	            * ��Χ�㣺ID   x-axis  y-axis(һ��)
	 	            * */
	 	           
	 	           String  _sContent="��������ID��  "+c.getCenter()+"\r\n";
	 	           ToolFileWrite.method1(_sDestFile, _sContent);
	 	            ArrayList<DataBean> p = c.getOfCluster();  
	 	            for (int j = 0; j < p.size(); j++) {  
	 	              //  System.out.println("point"+j+"_of_center"+"\n"+"��Χ��ID��"+p.get(j).getPointid()+"  x���꣺"+p.get(j).getXaxis()+"  y���꣺"+p.get(j).getYaxis()+"\n");
	 	              // String _sDestFile1="F:/workspace/KmeansDemoclusterResult.txt";
		 	           String  _sContent1="point"+j+""+"_of_center"+"��Χ��ID��"+p.get(j).getPointid()+"  x���꣺"+p.get(j).getXaxis()+""+"  y���꣺"+p.get(j).getYaxis()+""+"\r\n";
	 	               ToolFileWrite.method1(_sDestFile, _sContent1);
	 	            }  
	 	        }
	    }
	    	
	    

}
