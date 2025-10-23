
public class StackDriver<E> implements StackDriverInterface {

	@Override
	public StackInterface<String> createStack(StackDriverInterface.StackType stackType,
			StackDriverInterface.TestType stackTestType) {
		
		StackInterface<String> stack;
		
		if (stackType == StackDriverInterface.StackType.ArrayBasedStack) {
			stack = new ArrayBasedStack<String>();
		}
		else {
			stack = new LinkedStack<String>();
		}
		
		switch (stackTestType) {
		case Push:
			return stack;
		case Pop:
			for (int i = 10000; i > 0; i --) {
				stack.push("String " + i);
			}
			return stack;
		default:
			return stack;
		}
		
	}

	@Override
	public Object[] runTestCase(StackDriverInterface.StackType stackType, StackDriverInterface.TestType testType,
			int numberOfTimes) {
		Object[] main = new Object[(numberOfTimes*2)+1];
		TestTimes time = new TestTimes();
		
		long startTime;
		long endTime;
		long finalTime;
		
		
		for (int i = 0; i < numberOfTimes; i++) {
			StackInterface<String> stack = createStack(stackType, testType);
			StackInterface<String> copyBeforeTest;
			StackInterface<String> copyAfterTest;
			
			
			if (stack instanceof ArrayBasedStack) {
				copyBeforeTest = new ArrayBasedStack<String>();
			}
			else {
				copyBeforeTest = new LinkedStack<String>();
			}
			
			
			copyBeforeTest = stack.copy();
			
			main[i*2] = copyBeforeTest;
			
			
			
			startTime = System.nanoTime();
			
			switch(testType) {
			case Push:
				for (int k = 10000; k > 0; k --) {
					stack.push("String " + k);
				}
				break;
			case Pop:
				for (int k = 0; k < 10000; k++) {
					stack.pop();
				}
				break;
			}
			
			endTime = System.nanoTime();
			finalTime = endTime - startTime;
			
			double amount;
			Runtime run = Runtime.getRuntime();
//			run.gc();
			
			long totalMemory = run.totalMemory();
			long remainingMemory = run.freeMemory();
			long usedMemory = totalMemory - remainingMemory;
			
			
			amount = (double) usedMemory;
			
			time.addTestTime(finalTime);
			time.addMemoryUsage(amount);
			
			
			
			if (stack instanceof ArrayBasedStack) {
				copyAfterTest = new ArrayBasedStack<String>();
			}
			else {
				copyAfterTest = new LinkedStack<String>();
			}
			
			copyAfterTest = stack.copy();
			
			main[(i*2)+1] = copyAfterTest;
			
		}
		
		main[numberOfTimes*2] = time;
		
		return main;
		
	}

}
