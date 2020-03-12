package exer5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Test7 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(1000);
		SynchronousQueue<Integer> blq = new SynchronousQueue<>(); 
		AtomicInteger in=new AtomicInteger(1);
		while(true) {
		service.submit(new FutureTask<String>(new Callable<String>(){
			 public String call() throws Exception {
		            blq.put(in.get());
		            System.out.println(in.get());
		            in.set(in.get()+2);
		            Thread.sleep(1000);
		            return "";
			   }
	 }));
		service.submit(new FutureTask<String>(new Callable<String>(){
			 public String call() throws Exception {
		            Integer a=blq.take()+1;		        
		            System.out.println(a);
		            //Thread.sleep(100);
		            return "";
			   }
	 }));
		}
	}

}
