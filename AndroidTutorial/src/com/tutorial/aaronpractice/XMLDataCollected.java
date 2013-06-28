package com.tutorial.aaronpractice;

public class XMLDataCollected {

	private int temp = 0;
	private String city = null;
	private String data;

	public void setCity(String c) {
		city = c;
	}

	public void setTemp(int t) {
		temp = t;
	}

	public String dataToString() {
		data = new StringBuilder("in ").append(city)
				.append(" the current temp is ").append(temp)
				.append(" degrees F").toString();
		return data;
	}

}
