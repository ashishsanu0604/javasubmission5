package exer5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

class User{
	Integer id;
	String name;
	Integer age;
	User(Integer id1,String nm,Integer a){
		id=id1;
		name=nm;
		age=a;
	}
	Integer getAge()
	{
		return age;
	}
	public void setAge(Integer a1) {
		age=a1;
	}
	@Override
	public String toString() {
		return "User has id="+id+" name="+name+" and of age="+age;
	}
}
public class Test9 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(1000);
		List<User> lu=Collections.synchronizedList(new ArrayList<>());
		ReentrantLock lock=new ReentrantLock();
		lu.add(new User(1,"abc",12));
		lu.add(new User(2,"kcdn",31));
		lu.add(new User(3,"kcfnd",27));
		lu.add(new User(4,"lkcmrkjf",42));
		service.submit(new FutureTask<String>(new Callable<String>() {
			public synchronized String call() throws Exception,InterruptedException {
		       try {
		    	   if(!lock.isLocked())
		    	   {
		    		   lock.lock();
		    		   Thread.sleep(2000);
		    		   for(User u:lu) {
		    		   System.out.println(u.toString());}
		    	   }
		       }
		       finally {
		    	   lock.unlock();
		       }
		       return "";
			   }
		}));
		service.submit(new FutureTask<String>(new Callable<String>() {
			public synchronized String call() throws Exception,InterruptedException {
		       try {
		    	   if(!lock.isLocked())
		    	   {
		    		   lock.lock();
		    		   User u=lu.get(0);
		    		   u.setAge(u.getAge()+1);
		    	   }
		       }
		       finally {
		    	   lock.unlock();
		       }
		       return "";
			   }
		}));
		service.submit(new FutureTask<String>(new Callable<String>() {
			public synchronized String call() throws Exception,InterruptedException {
		       try {
		    	   if(!lock.isLocked())
		    	   {
		    		   lock.lock();
		    		   Thread.sleep(2000);
		    		   for(User u:lu) {
		    		   System.out.println(u.toString());}
		    	   }
		       }
		       finally {
		    	   lock.unlock();
		       }
		       return "";
			   }
		}));

	}

}
