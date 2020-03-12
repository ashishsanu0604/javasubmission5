package exer5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Test {
	
	private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
	private static AtomicInteger in;
	public static void main(String[] args) throws InterruptedException{
		 ExecutorService service = Executors.newFixedThreadPool(3);
		 in=new AtomicInteger(0);
		 for (int i = 0; i<atomicIntegerArray.length(); i++) {
	         atomicIntegerArray.set(i, i*2);
	      }
		 service.submit(new FutureTask<Integer>(new Callable<Integer>(){
			 public Integer call() throws Exception {
			            in.incrementAndGet();
				 		int add = atomicIntegerArray.get(in.get());
				 		System.out.print(add);
				 		return add;
				   }
		 }));
	}
	
}
