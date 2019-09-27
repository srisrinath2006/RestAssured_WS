package com.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class verifyRestPostReq {

	public static void main(String[] args) {
		
		String result;
		String strBaseURI = "https://jsonplaceholder.typicode.com/posts";
		
		 result = verifyPost(strBaseURI);
		 
		 if (result.equals("PASS")) {
			 System.out.println("Rest Assured Post Call is Successful.");
		 }else {
			 System.out.println("Rest Assured Post Call Failed");
		 }
	}
	
	@SuppressWarnings("unchecked")
	public static String verifyPost(String strBaseURI) {
		
		try {
			RestAssured.baseURI =  strBaseURI;

			JSONObject Params = new JSONObject();
			Params.put("title", "foo");
			Params.put("body", "bar");
			Params.put("userId", "1");
			Response response = null;
			try{
				response = RestAssured.given()
							.contentType("application/json; charset=UTF-8")
							.body(Params)
							.post();
			}catch(Exception e){
				e.printStackTrace();
			}

			int statusCode = response.getStatusCode();

			if (Integer.valueOf(0).equals(statusCode)) {
				System.out.println("Status Code validation failed");
				return "FAIL";
			}
			
			if (statusCode == 201) {
				System.out.println("Status Code is - "+statusCode);
				System.out.println("Response - "+response.asString());
				return "PASS";
			}else {
				System.out.println("Status Code is Incorrect. Expected Value is 201 and Actual Value is -"+statusCode);
				return "FAIL";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	}

