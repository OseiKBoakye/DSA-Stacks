import java.util.Arrays;

public class TestTimes implements TestTimesInterface {
	private final int size = 10;
	private long [] testTimes;
	private double[] memoryUsage;
	private int countTime;
	private TimeUnits timeUnits;
	private MemoryUnits memoryUnits;
	private int countMemory;
	
	public TestTimes() {
		this.testTimes = new long[size];
		this.memoryUsage = new double[size];
		this.countTime = 0;
		this.countMemory=0;
		this.timeUnits = TimeUnits.NanoSeconds;
		this.memoryUnits = MemoryUnits.Bytes;
	}

	@Override
	public TestTimesInterface.TimeUnits getTimeUnits() {
		return this.timeUnits;
	}

	@Override
	public void setTimeUnits(TestTimesInterface.TimeUnits timeUnits) {
		this.timeUnits = timeUnits;
		
	}

	@Override
	public TestTimesInterface.MemoryUnits getMemoryUnits() {
		
		return this.memoryUnits;
	}

	@Override
	public void setMemoryUnits(TestTimesInterface.MemoryUnits memoryUnits) {
		this.memoryUnits = memoryUnits;
		
	}

	@Override
	public double getLastTestTime() {
		if(this.countTime == 0) {
			return 0;
		}
		
		double lastTime = 0.0;
		
		switch (this.timeUnits) {
		case NanoSeconds:
			lastTime = this.testTimes[Math.min(this.countTime-1, this.testTimes.length-1)];
			break;
		case MicroSeconds:
			lastTime = this.testTimes[Math.min(this.countTime-1, this.testTimes.length-1)] / 1000.0;
			break;
		case MilliSeconds:
			lastTime = this.testTimes[Math.min(this.countTime-1, this.testTimes.length-1)] / 1000000.0;
			break;
		case Seconds:
			lastTime = this.testTimes[Math.min(this.countTime-1, this.testTimes.length-1)] / 1000000000.0;
			break;
		}
		return lastTime;
		
	}

	@Override
	public double getLastMemoryUsage() {
		if (this.countMemory ==0) {
			return 0;
		}
		
		double unit = this.memoryUsage[Math.min(this.countMemory-1, this.memoryUsage.length-1)];
		
		switch (this.memoryUnits) {
		case Bytes:
			return unit;
		case KiloBytes:
			return unit / 1024.0;
		case MegaBytes:
			return unit / (1024.0 * 1024.0);
		default:
			return unit;
		}
		
		
	}

	@Override
	public double[] getTestTimes() {
		double[] times = new double[testTimes.length];
		
		if (this.countTime < this.testTimes.length) {
			for (int i=0; i < countTime; i++) {
				double convert = this.testTimes[i];
				
				switch (this.timeUnits) {
				case NanoSeconds:
					times[i] = convert;
					break;
				case MicroSeconds:
					times[i] = convert / 1000.0;
					break;
				case MilliSeconds:
					times[i] = convert / 1000000.0;
					break;
				case Seconds:
					times[i] = convert / 1000000000.0;
				}
			}
			for (int j = this.countTime; j < this.testTimes.length; j++) {
				times[j] = 0.0;
			}	
			
		}
		else {
			for (int i=0; i < testTimes.length;i++) {
				double convert = this.testTimes[i];
				
				switch (this.timeUnits) {
				case NanoSeconds:
					times[i] = convert;
					break;
				case MicroSeconds:
					times[i] = convert / 1000.0;
					break;
				case MilliSeconds:
					times[i] = convert / 1000000.0;
					break;
				case Seconds:
					times[i] = convert / 1000000000.0;
				}
			}
		}
		return times;
		
	}

	@Override
	public double[] getMemoryUsages() {
		double[] memory = new double[this.memoryUsage.length];
		
		for (int i = 0; i < memory.length; i++) {
			double convertUnits = (i < this.countMemory) ? memoryUsage[i] : 0.0;
			
			switch (this.getMemoryUnits()) {
			case Bytes:
				memory[i] = convertUnits;
				break;
			case KiloBytes:
				memory[i] = convertUnits / 1024.0;
				break;
			case MegaBytes:
				memory[i] = convertUnits / (1024.0 * 1024.0);
				break;
			}
			
		}
		
		return memory;
		
	}

	@Override
	public void resetTestTimes() {
		this.testTimes = new long[size];
		countTime = 0;
		
	}

	@Override
	public void addTestTime(long runTime) {
		if(this.countTime < this.testTimes.length){
			this.testTimes[this.countTime] = runTime;
			this.countTime++;
		}
		
		else {
			for(int i = 0; i < testTimes.length-1; i++) {
				this.testTimes[i] = this.testTimes[i+1];
			}
			
			this.testTimes[testTimes.length-1] = runTime;
			
		}
		
	}
	
	public void addMemoryUsage(double unit) {
		if (this.countMemory < this.memoryUsage.length) {
			this.memoryUsage[this.countMemory] = unit;
			this.countMemory++;
		}
		else {
			for (int i = 0; i < this.memoryUsage.length-1; i++) {
				this.memoryUsage[i] = this.memoryUsage[i+1];
			}
			this.memoryUsage[this.memoryUsage.length-1] = unit;
			
		}
	}
	

	@Override
	public double getAverageTestTime() {
		double average;
		double sum=0;
		double size=0;
		
		if (this.countTime != 0) {
			for(int i = 0; i < testTimes.length; i++) {
				if(testTimes[i] != 0) {
					sum += (double)testTimes[i];
					size++;
				}
			}
			
			average =  sum / size;
			
			switch (this.timeUnits) {
			case NanoSeconds:
				return average;
			case MicroSeconds:
				return average / 1000.0;
			case MilliSeconds:
				return average / 1000000.0;
				
			case Seconds:
				return average / 1000000000.0;
			}
			
			
			
		}
		
		return 0;
	}

	@Override
	public double getAverageMemoryUsage() {
		double sum = 0;
		double number =0;
		
		if (this.countTime != 0) {
			for(int i = 0; i < this.memoryUsage.length; i++) {
				if (this.memoryUsage[i] != 0) {
					sum += this.memoryUsage[i];
					number++;
				}
			}
			
			return sum / number;
		}
		return 0.0;
	}
	
	
	
	public static void main(String[] args) {
		TestTimes t = new TestTimes();
		t.addMemoryUsage(1000);
		
		System.out.println(t.countMemory);
		System.out.println(t.getLastMemoryUsage());
		
		for (int i = 0; i < 12; i ++) {
			t.addMemoryUsage(1000 + i);
		}
		
		for (int i = 0; i < 10; i ++) {
			System.out.println(Arrays.toString(t.memoryUsage));
		}
		
		System.out.println(t.getMemoryUsages()[0]);
	}

}
