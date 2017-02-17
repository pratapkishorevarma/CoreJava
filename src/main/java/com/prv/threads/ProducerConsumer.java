package com.prv.threads;

public class ProducerConsumer {

	static class Producer implements Runnable{

		private SharedObject sharedObject;
		
		public Producer(SharedObject sharedObject) {
			super();
			this.sharedObject = sharedObject;
		}

		@Override
		public void run() {
			for(int i=0; i<10; i++){
				produce(i);
			}
		}

		private void produce(int i) {
			String item = "Item "+i;
			
			synchronized (sharedObject) {
				while(sharedObject.isAvailable()){
					try {
						System.out.println("Producer waiting");
						sharedObject.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
								
			synchronized (sharedObject) {
				sharedObject.setItem(item);				
				sharedObject.setAvailable(true);
				System.out.println("Produced "+ item);
				sharedObject.notify();
			}
		}
	}
	
	static class Consumer implements Runnable{

		private SharedObject sharedObject;
		
		public Consumer(SharedObject sharedObject) {
			super();
			this.sharedObject = sharedObject;
		}
		
		@Override
		public void run() {
			while(true){
				synchronized (sharedObject) {
					while(!sharedObject.isAvailable()){
						try {
							System.out.println("Consumer waiting");
							sharedObject.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				synchronized (sharedObject) {
					System.out.println("Consumed "+sharedObject.getItem());
					sharedObject.setAvailable(false);
					sharedObject.notify();
				}
			}
				
		}
		
	}
	
	static class SharedObject{
		private String item;
		private boolean available = false;
		
		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}

		public String getItem() {
			available = false;
			return item;
		}
		
		public void setItem(String item) {
			this.item = item;
			available = true;
		}
		
	}
	public static void main(String[] args) {
		SharedObject sharedObject = new SharedObject();
		Thread p = new Thread(new Producer(sharedObject));
		Thread c = new Thread(new Consumer(sharedObject));
		p.start();
		c.start();
	}

}
