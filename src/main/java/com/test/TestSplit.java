package com.test;

public class TestSplit {
	/*
	 * 实验：测试String.split()的用法
	 */
	public static void main(String[] args) {
		String str1 = " MSG#1101$ f# hello world ";
		String[] str=str1.split("\\$");
		for(String s1:str){
			System.out.println(s1);
		}
		//注意：分隔符如果是使用$，则使用\\$进行分裂
	}

}
