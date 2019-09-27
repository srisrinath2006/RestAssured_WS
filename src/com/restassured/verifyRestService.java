package com.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class verifyRestService {

public static void main(String[] args) {
		
		String result;
		String strBaseURI = "https://jsonplaceholder.typicode.com/posts/1";
		
		 result = verifyGetReq(strBaseURI);
		 
		 if (result.equals("PASS")) {
			 System.out.println("Rest Assured Get Call is Successful.");
		 }else {
			 System.out.println("Rest Assured Get Call Failed");
		 }
	}
	
	public static String verifyGetReq(String strBaseURI) {
		
		try {			
			RestAssured.baseURI =  strBaseURI;
			
			Response response = null;
			try{
				response = RestAssured.given()
							.when()
							.get(strBaseURI);
			}catch(Exception e){
				e.printStackTrace();
			}

			int statusCode = response.getStatusCode();

			if (Integer.valueOf(0).equals(statusCode)) {
				System.out.println("Status Code validation failed");
				return "FAIL";
			}
			
			if (statusCode == 200) {
				System.out.println("Status Code is - "+statusCode);
				System.out.println("Response - "+response.asString());
				return "PASS";
			}else {
				System.out.println("Status Code is Incorrect. Expected Value is 200 and Actual Value is -"+statusCode);
				return "FAIL";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}

}