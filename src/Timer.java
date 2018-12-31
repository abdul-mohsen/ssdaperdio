

class Timer extends Thread {
	int time;
	boolean run;
	public Timer(int time,boolean run){
		this.time=time;
		this.run=run;
	}
	public boolean isRun() {
		return run;
	}
	public void setRun(boolean run) {
		this.run = run;
	}
	public void run() {
		super.run();
		do {
			
			if(run)time++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}



}

