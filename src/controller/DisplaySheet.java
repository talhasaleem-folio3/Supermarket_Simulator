package controller;

public class DisplaySheet implements Comparable<DisplaySheet> {

	private int customerTag;
	private double time;
	private String event;
	private String customerType;

	public DisplaySheet(int customerTag, double time, String event, String customerType) {
		this.customerTag = customerTag;
		this.time = time;
		this.event = event;
		this.customerType = customerType;
	}

	public int getCustomerTag() {
		return customerTag;
	}

	public void setCustomerTag(int customerTag) {
		this.customerTag = customerTag;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	@Override
	public int compareTo(DisplaySheet compare){
		
		double compareTime = ((DisplaySheet) compare).getTime();
		
		return (int) (this.time - compareTime);
	}

}
