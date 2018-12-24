package com.fh;

public class test1 {

	public static void main(String[] args) {
		String str1 = " 1023 #  this is hello ";
		String[] s1 = str1.split("#");
		for(String s2:s1){
			System.out.println(s2.trim());
		}
	}

}
