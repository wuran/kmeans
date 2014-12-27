package cn.itcast.kmeans;
import java.io.IOException;
import java.util.*;

import cn.itcast.bean.*;
import cn.itcast.dao.BuildPoints;
import cn.itcast.util.ToolDistance;
import cn.itcast.util.ToolFileWrite;
/*k-means算法基本步骤：
（1） 从 n个数据对象任意选择 k 个对象作为初始聚类中心；
（2） 根据每个聚类对象的均值（中心对象），计算每个对象与这些中心对象的距离；并根据最小距离重新对相应对象进行划分；
（3） 重新计算每个（有变化）聚类的均值（中心对象）；
（4） 计算标准测度函数，当满足一定条件，如函数收敛时，则算法终止；如果条件不满足则回到步骤（2），不断重复直到标准测度函数开始收敛为止。（一般都采用均方差作为标准测度函数。）*/
public class Kmeans {
	 public ArrayList<DataBean> datas = null;  //12个数据源
	    public int totalNumber = 0;// 得到所有的坐标点数目  (12)
	    public int K = 0;  
	 
	    public Kmeans(int k) {  
	    	datas = new BuildPoints().preparePoint();  
	        totalNumber = datas.size();  
	        K = k;  
	    }  
	  
	    // 第一次随机选取聚类中心  
	    public Set<Integer> firstRandom() {  
	        Set<Integer> center = new HashSet<Integer>();// 聚类中心的点的id,采用set保证不会有重复id  
	        Random ran = new Random();  
	        int roll = ran.nextInt(totalNumber);  
	        while (center.size() < K) {  
	            roll = ran.nextInt(totalNumber);  
	            center.add(roll);  
	        }  
	        return center;  
	    }  
	  
	    // 根据聚类中心初始化聚类信息  
	    public ArrayList<Cluster> init(Set<Integer> center) {  
	        ArrayList<Cluster> cluster = new ArrayList<Cluster>();// 聚类 的数组  
	        Iterator<Integer> it = center.iterator();  
	        
	        while (it.hasNext()) {  
	        	Cluster c = new Cluster();// 代表一个聚类  
	            c.setCenter(it.next());  
	            cluster.add(c);  
	        }  
	        return cluster;  
	    }  
	  
	    /** 
	     * 计算各个点到各个聚类中心的距离，重新聚类 
	     *  
	     * @param cluster 
	     *            聚类数组,用来聚类的，根据最近原则把点聚类 
	     * @param center 
	     *            中心点id,用于计算各个点到中心点的距离 return cluster 聚类后的所有聚类组成的数组 
	     */  
	    public ArrayList<Cluster> juLei(Set<Integer> center,  
	            ArrayList<Cluster> cluster) {  
	        ArrayList<Distance> distance = new ArrayList<Distance>();// 存放距离信息,表示每个点到各个中心点的距离组成的数组  
	        DataBean source = null;  
	        DataBean dest = null;  
	        int id = 0;// 目的节点id  
	        int id2 = 0;// 源节点id  
	        Object[] p = center.toArray();// p 为聚类中心点id数组  
	        boolean flag = false;  
	        // 分别计算各个点到各个中心点的距离，并将距离最小的加入到各个聚类中，进行聚类  
	        for (int i = 0; i < totalNumber; i++) {  
	            // 每个点计算完,并聚类到距离最小的聚类中就清空距离数组  
	            distance.clear();  
	            // 计算到j个类中心点的距离,便利各个中心点  
	            for (int j = 0; j < center.size(); j++) {  
	                // 如果该点不在中心点内 则计算距离  
	                if (!(center.contains(i))) {  
	                    flag = true;  
	                    // 计算距离  
	                    source = datas.get(i);// 某个点  
	                    dest = datas.get((Integer) p[j]);// 各个 中心点  
	                    // 计算距离并存入数组  
	                    distance.add(new Distance((Integer) p[j], i, ToolDistance.juli(  
	                            source, dest)));  
	                } else {  
	                    flag = false;  
	                }  
	            }  
	            // 说明计算完某个点到类中心的距离,开始比较  
	            if (flag == true) {  
	                // 排序比较一个点到各个中心的距离的大小,找到距离最小的点的 目的id,和源id,  
	                // 目的id即类中心点id，这个就归到这个中心点所在聚类中  
	                double min = distance.get(0).getDist();// 默认第一个distance距离是最小的  
	                // 从1开始遍历distance数组  
	                int minid = 0;  
	                for (int k = 1; k < distance.size(); k++) {  
	                    if (min > distance.get(k).getDist()) {  
	                        min = distance.get(k).getDist();  
	                        id = distance.get(k).getDest();// 目的，即类中心点  
	                        id2 = distance.get(k).getSource();// 某个点  
	                        minid = k;  
	                    } else {  
	                        id = distance.get(minid).getDest();  
	                        id2 = distance.get(minid).getSource();  
	                    }  
	                }  
	                // 遍历cluster聚类数组，找到类中心点id与最小距离目的点id相同的聚类  
	                for (int n = 0; n < cluster.size(); n++) {  
	                    // 如果和中心点的id相同 则setError  
	                    if (cluster.get(n).getCenter() == id) {  
	                        cluster.get(n).addDatabean(datas.get(id2));// 将与该聚类中心距离最小的点加入该聚类  
	                        break;  
	                    }  
	                }  
	            }  
	        }  
	        return cluster;  
	    }  

	    // 不断循环聚类直到各个聚类没有重新分配  
	    public ArrayList<DistanceBean> getResult() {  
	        ArrayList<Cluster> result = new ArrayList<Cluster>();  
	        ArrayList<Cluster> temp = new ArrayList<Cluster>();  
	        boolean flag = false;  
	        // 得到随机中心点然后进行聚类  
	        Set<Integer> center = firstRandom();  
	        result = juLei(center, init(center)); 
	        System.out.println("算法进行第1次随机打印第一次聚类:");
	        print(result);  //随机分配聚类（粗超聚类|随机聚类）
	        /*计算第一次随机聚类的距离*/
        	//1.取到各个随机中心点
	        
	        //center_point_result:存放3个分类中心的数组
	        ArrayList<DataBean> center_point_result = new ArrayList<DataBean>(); 
        	for(int i=0;i<result.size();i++){
        		DataBean pointofcenter=new DataBean();//pointofcenter
        		//pointofcenter.setPointid(result.get(i).getCenter()+"");//取到中心点id
        		for(int i1=0;i1<datas.size();i1++){
        			//用pointid去查询datas数据源，获取随机聚类中心点的信息
        			if(datas.get(i1).getPointid().equals(result.get(i).getCenter()+"")){
        				pointofcenter=(DataBean)datas.get(i1);//找到中心点的信息了
        			}
        		}
        		center_point_result.add(pointofcenter);//将找到的中心点加入中心点数组
        	}
        	
        	//3计算这些其他点到这三个点的距离(包含自己到自己)
        	double distance_to_center0=0.0,distance_to_center1=0.0,distance_to_center2=0.0;
        	double distanceoftemp=0.0;
        	//Double[] distancetoKcenter=new Double[K];//到K个中心点的距离
        	//distancebean封装了每一个点到随机聚类中心的距离信息，从它可以进一步达到分类的目的，相当重要
        	ArrayList<DistanceBean> distancebean=new ArrayList<DistanceBean>();
        	//Arrays.sort()
        	for(DataBean point:datas){
        		//求某一point到3个中心点的距离
        		
        		for(int i=0;i<center_point_result.size();i++){
        			//求point到centerpoint的距离
        			DistanceBean distemp=new DistanceBean();
        			distanceoftemp=ToolDistance.juli(point,center_point_result.get(i));
        			//distancetoKcenter[i]=distanceoftemp;
        			distemp.setDistance(distanceoftemp);//距离
        			distemp.setCenter(center_point_result.get(i));//聚类中心
        			distemp.setOtherpoint(point);//求距点
        			distancebean.add(distemp);//封装对象
        			
        		}
        		
        	}
        	
	        return distancebean;  
	    }  
	    //通过distancebean重新聚类
	    public ArrayList<Cluster> getlastCluster(ArrayList<DistanceBean> distancebean){
	    	ArrayList<Cluster> cluster=new ArrayList<Cluster>();//存放计算后的聚类
	    	//1.按某个点到所有中心点的距离求最小值，并且记住最小值对应的中心点，那么该点属于该中心点代表的类
	    	//a.通过distancebean获取中心对象数组
	    	int k=0;
	    	ArrayList<DistanceBean> mindis=new ArrayList<DistanceBean>();//12个最小的dis
	    	
	    	for(int j=0;j<datas.size();j++){//从12个点开始循环
	    	ArrayList<DistanceBean> tempdis=new ArrayList<DistanceBean>();//临时的，用来装确定点的所有距离	
	    	for(int i=0;i<distancebean.size();i++){
	    		//已经得到的所有36个距离
	    		DistanceBean temp=new DistanceBean();   //临时的，用来装确定点（otherpoint）的某一确定距离	
	    		
	    		
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
	    	
	    	//ArrayList<DataBean> ofcluster=new ArrayList<DataBean>();//集群里面其他的所有点
	    	for(k=0;k<tempdis.size();k++){
	    		if(min==tempdis.get(k).getDistance())
	    			{
	    			mindis.add(tempdis.get(k));
	    			break; 	    			
	    			}
	    	}	
	    	
	    		    	
	    }
	    	
	    //bug	
	   ArrayList<DataBean> allcenters=new ArrayList<DataBean>();//中心點
	   for(int i=0;i<K;i++){
		   allcenters.add(distancebean.get(i).getCenter());
	    }
	   
	    for(int i=0;i<allcenters.size();i++){	    	
	    	
	    	//从mindis中读到cluster
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
	      
	    //输出所有的聚类  
	    public void print(ArrayList<Cluster> cs) {  
	    	System.out.println("***************************************");  
	 	        for (int i = 0; i < cs.size(); i++) {  
	 	            Cluster c = cs.get(i);  
	 	            System.out.println("-----------------------------------------------------");  
	 	            System.out.println("center"+i+"\n"+"聚类中心ID：" +c.getCenter());  
	 	            ArrayList<DataBean> p = c.getOfCluster();  
	 	            for (int j = 0; j < p.size(); j++) {  
	 	                System.out.println("point"+j+"_of_center"+"\n"+"周围点ID："+p.get(j).getPointid()+"  x坐标："+p.get(j).getXaxis()+"  y坐标："+p.get(j).getYaxis()+"\n");  
	 	            }  
	 	        }
	    }
	    //输出所有的聚类 到文件 
	    public void printtoFile(ArrayList<Cluster> cs) throws IOException {  
	    	String _sDestFile="F:/workspace/KmeansDemo/clusterResult.txt";
	    	String _sContent0="K值为： "+K+""+"时*************************"+"\r\n";
	    	 ToolFileWrite.method1(_sDestFile, _sContent0);
	 	        for (int i = 0; i < cs.size(); i++) {  
	 	            Cluster c = cs.get(i);  
	 	           /*
	 	            * 聚类中心ID
	 	            * 周围点：ID   x-axis  y-axis(一行)
	 	            * */
	 	           
	 	           String  _sContent="聚类中心ID：  "+c.getCenter()+"\r\n";
	 	           ToolFileWrite.method1(_sDestFile, _sContent);
	 	            ArrayList<DataBean> p = c.getOfCluster();  
	 	            for (int j = 0; j < p.size(); j++) {  
	 	              //  System.out.println("point"+j+"_of_center"+"\n"+"周围点ID："+p.get(j).getPointid()+"  x坐标："+p.get(j).getXaxis()+"  y坐标："+p.get(j).getYaxis()+"\n");
	 	              // String _sDestFile1="F:/workspace/KmeansDemoclusterResult.txt";
		 	           String  _sContent1="point"+j+""+"_of_center"+"周围点ID："+p.get(j).getPointid()+"  x坐标："+p.get(j).getXaxis()+""+"  y坐标："+p.get(j).getYaxis()+""+"\r\n";
	 	               ToolFileWrite.method1(_sDestFile, _sContent1);
	 	            }  
	 	        }
	    }
	    	
	    

}
