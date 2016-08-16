package com.wangyitest.util;

import org.testng.annotations.Test;

public class test 
{
	public static void main(String[] args) {
		         int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
		         System.out.println("排序之前：");
		         for (int i = 0; i < a.length; i++) 
		         {
		              System.out.print(a[i]+" ");
		         }
		         //选择排序
//		         for(int i=0;i<a.length;i++)
//		         {
//		        	 int min =a[i];
//		        	 int n=i;
//		        	 for(int j=i+1;j<a.length;j++)
//		        	 {
//		        		 if(a[j]<min)
//		        		 {
//		        			 min=a[j];
//		        			 n=j;
//		        		 }
//		        	 }
//		        	 a[n]=a[i];
//		        	 a[i]=min;
//		         }
		         //冒泡排序
		         for(int i=0;i<a.length;i++)
		         {
		        	 for(int j=0;j<a.length-i-1;j++)
		        	 {
		        		 if(a[j]>a[j+1])
		        		 {
		        			 int temp=a[j];
		        			 a[j]=a[j+1];
		        			 a[j+1]=temp;
		        		 }
		        	 }
		         }
		         System.out.println();
		         for(int i=0;i<a.length;i++)
		         {
		        	 System.out.print(a[i]+" ");
		         }
		         
		     }


}
