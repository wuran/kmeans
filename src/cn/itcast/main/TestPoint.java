package cn.itcast.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cn.itcast.kmeans.Kmeans;

public class TestPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 读取xml数据:获取数据源
		// TODO 聚类K-means
	System.out.println("***************欢迎光临K-means聚类算法中心******************");
	while(true){
			System.out.print("请输入您想聚类的K值(注:输入'exit'可以退出系统)：");
			try{
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				String k_value=br.readLine();
				if("exit".equals(k_value)){
					System.out.println("退出系统成功，欢迎再次光临！");
					System.exit(0);//退出系统
				}
				Kmeans kmeans = new Kmeans(Integer.parseInt(k_value));  
				 kmeans.printtoFile(kmeans.getlastCluster(kmeans.getResult())); 
			}catch(Exception e){
				System.out.println("对不起，俺出错了！！！");
			}	
		}
		}
	}
