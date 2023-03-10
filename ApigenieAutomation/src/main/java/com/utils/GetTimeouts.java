package com.utils;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import com.connectors.AddingConnector;

public class GetTimeouts {
	Long max_time,min_time,avg_time;
	public GetTimeouts() {
		super();
		
		FileInputStream ip;
		Properties prop=new Properties();;
		try {
			 final URL resource = GetTimeouts.class.getResource("/TimeConfig.properties");
		       System.out.println(resource);
			ip = new FileInputStream(resource.getFile());
//			ip = new FileInputStream("C:\\Users\\Vinayak Hene\\git\\UbuntuSmokeAutomation\\src\\main\\resources\\TimeConfig.properties");
			prop.load(ip);
			max_time =Long.parseLong(prop.getProperty("max_time"));
			min_time =Long.parseLong(prop.getProperty("min_time"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Long getMax_time() {
		return max_time;
	}
	public void setMax_time(Long max_time) {
		this.max_time = max_time;
	}
	public Long getMin_time() {
		return min_time;
	}
	public void setMin_time(Long min_time) {
		this.min_time = min_time;
	}
	public Long getAvg_time() {
		return avg_time;
	}
	public void setAvg_time(Long avg_time) {
		this.avg_time = avg_time;
	}
	
	
	
}
