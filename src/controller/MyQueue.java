package controller;

import java.util.ArrayList;

public class MyQueue {
	private int rear;
	private int front;

	private ArrayList<Integer> queue;

	public MyQueue() {

		this.queue = new ArrayList<>();
		this.rear = -1;
		this.front = -1;

	}

	public boolean isEmpty() {
		return (rear == -1 && front == -1);
	}

	public boolean isFull() {
		return (rear + 1) % queue.size() == front;
	}

	public void enqueue(int val) {
		this.queue.add(0);
		if (isFull()) {
			System.out.println("OverFlow Error!");
			return;
		} else if (isEmpty()) {
			rear = 0;
			front = 0;
		}

		else {
			rear = (rear + 1) % queue.size();
		}
		this.queue.set(rear, val);
	}

	public void dequeue() {
		if (isEmpty()) {
			System.out.println("UnderFlow Error!");
			return;
		} else if (rear == front) {
			this.queue.remove(front + 1);
			
			rear = -1;
			front = -1;
		} else {
			this.queue.remove(front + 1);
			front = (front + 1) % queue.size();
		}
		
	}

	public int getFront() {
		return queue.get(front);
	}

	public int getSize() {
		return queue.size();
	}
}
