package exer5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

class Emp{
	String name;
	Integer age;
	Integer sal;
	String address;
	Emp(String nm,Integer a,Integer s,String ad)
	{
		name=nm;
		age=a;
		sal=s;
		address=ad;
	}
	@Override
	public String toString() {
		return "Employee has name="+name+",with age="+age+"has salary="+sal;
	}
}
public class Test8 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(1000);
		List<Emp> listBooks = Collections.synchronizedList(new ArrayList<>());
		listBooks.add(new Emp("c",42,3214,"cdsd,m s"));
		for(int i=0;i<2;i++) {
		service.submit(new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
		       listBooks.add(new Emp("abc",12,12324,"kjdcds"));
		       Thread.sleep(1000);
		       return "";
			   }
		}));}
		service.submit(new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
		       Iterator<Emp> it=listBooks.iterator();  
		       while(it.hasNext())
		       {
		    	   System.out.println(((Emp)it.next()));
		       }
		       return "";
			   }
		}));
		

	}

}
