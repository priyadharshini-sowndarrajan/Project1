package com.ofss;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtility {

	public static void main(String[] args) {
		BCryptPasswordEncoder bcrpt=new BCryptPasswordEncoder();
		String p1 = "john123";
		String p2 = "mary123";    
		String p3 = "michael123";   
		String p4 = "linda123"; 
		String p5 = "robert123";  
		String p6 = "nickk123";    
		String p7 = "alice123";  //admin 
		System.out.println(bcrpt.encode(p1));
		System.out.println(bcrpt.encode(p2)); 
		System.out.println(bcrpt.encode(p3)); 
		System.out.println(bcrpt.encode(p4)); 
		System.out.println(bcrpt.encode(p5)); 
		System.out.println(bcrpt.encode(p6)); 
		System.out.println(bcrpt.encode(p7)); 
		System.out.println("PasswordUtility is running");

	}

}