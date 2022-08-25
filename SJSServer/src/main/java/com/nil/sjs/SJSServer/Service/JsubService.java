package com.nil.sjs.SJSServer.Service;

public class JsubService {
	
	public String JobSubmitService(String jsubArguments) {
		String[] argumentParts = jsubArguments.split(",");
		System.out.println("Queue_name: "+argumentParts[1].split(":")[1]);
//		System.out.println("resource: "+argumentParts[2].split(":")[1]);
		String[] parts = argumentParts[2].split(":");
//		System.out.println(parts.length);
 		for (int i =1; i > parts.length; i++) {
 			System.out.println("HIT");
			System.out.println(parts[i]);
		}
		System.out.println("job_name: "+argumentParts[3].split(":")[1].replace("--", ""));
		return "Job Submited";
	}

}
