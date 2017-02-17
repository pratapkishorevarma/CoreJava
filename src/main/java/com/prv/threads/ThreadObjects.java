package com.prv.threads;

public class ThreadObjects {

	static class ThreadA implements Runnable{

		@Override
		public void run() {
			Thread.currentThread().setName("Thread A");
			while(true){
				System.out.println(Thread.currentThread().getId() + ":" +
						Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " is interrupted");
					break;
				}
			}
			System.out.println(Thread.currentThread().getName() + ": Outside loop");	
		}
	}
	
	static class ThreadB extends Thread{

		@Override
		public void run() {
			Thread.currentThread().setName("Thread B");
			while(true){
				System.out.println(Thread.currentThread().getId() + ":" +
						Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority());
				if(Thread.interrupted()){
					break;
				}
			}
			System.out.println(Thread.currentThread().getName() + ": Outside loop");	
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new ThreadA());
		ThreadB t2 = new ThreadB();
		t.start();
		t2.start();
		t.join(6000);
		t.interrupt();
		t2.join(1);
		t2.interrupt();
		t.join();

	}

}
