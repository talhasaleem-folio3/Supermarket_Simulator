package controller;

public class ExpressSystem {

	private int customerTag;

	private double interarrivalTime;
	private double serviceTime;
	private double absoluteArrival;
	private double departure;
	private double delay;
	private double waitingTime;

	public ExpressSystem(int customerTag, double interarrivalTime, double serviceTime, double absoluteArrival,
			double departure, double delay, double waitingTime) {
		super();
		this.customerTag = customerTag;
		this.interarrivalTime = interarrivalTime;
		this.serviceTime = serviceTime;
		this.absoluteArrival = absoluteArrival;
		this.departure = departure;
		this.delay = delay;
		this.waitingTime = waitingTime;
	}

	public int getCustomerTag() {
		return customerTag;
	}

	public void setCustomerTag(int customerTag) {
		this.customerTag = customerTag;
	}

	public double getInterarrivalTime() {
		return interarrivalTime;
	}

	public void setInterarrivalTime(double interarrivalTime) {
		this.interarrivalTime = interarrivalTime;
	}

	public double getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}

	public double getAbsoluteArrival() {
		return absoluteArrival;
	}

	public void setAbsoluteArrival(double absoluteArrival) {
		this.absoluteArrival = absoluteArrival;
	}

	public double getDeparture() {
		return departure;
	}

	public void setDeparture(double departure) {
		this.departure = departure;
	}

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}

	public double getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}

}
