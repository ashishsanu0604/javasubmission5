package exer5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Test6 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(1000);
		AtomicInteger in=new AtomicInteger(-1);
		while(true) {
		service.submit(new FutureTask<String>(new Callable<String>(){
			 public String call() throws Exception {
		            
		            in.set(in.get()+2);
		            
		            System.out.println(in.get());
		            Thread.sleep(100);
		            return "";
			   }
	 }));
		service.submit(new FutureTask<String>(new Callable<String>(){
			 public String call() throws Exception {
				 	Integer a=in.get()+1;
		            System.out.println(a);
		            
		            return "";
			   }
	 }));
		}
	}

}
