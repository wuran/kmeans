package cn.itcast.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cn.itcast.kmeans.Kmeans;

public class TestPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO ��ȡxml����:��ȡ����Դ
		// TODO ����K-means
	System.out.println("***************��ӭ����K-means�����㷨����******************");
	while(true){
			System.out.print("��������������Kֵ(ע:����'exit'�����˳�ϵͳ)��");
			try{
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				String k_value=br.readLine();
				if("exit".equals(k_value)){
					System.out.println("�˳�ϵͳ�ɹ�����ӭ�ٴι��٣�");
					System.exit(0);//�˳�ϵͳ
				}
				Kmeans kmeans = new Kmeans(Integer.parseInt(k_value));  
				 kmeans.printtoFile(kmeans.getlastCluster(kmeans.getResult())); 
			}catch(Exception e){
				System.out.println("�Բ��𣬰������ˣ�����");
			}	
		}
		}
	}
