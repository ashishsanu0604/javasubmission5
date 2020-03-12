package exer5;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
class Person{
	Integer id;
	String name;
	Integer salary;
	Person(Integer i,String nm,Integer s)
	{
		id=i;
		name=nm;
		salary=s;
	}
	Integer getId() {
		return id;
	}
	Integer getsal() {
		return salary;
	}
	String getName() {
		return name;
	}
	@Override
	public boolean equals(Object p)
	{
		if(this==p)
			return true;
		if(p==null|| getClass()!=p.getClass())
			return false;
		Person p2=(Person) p;
		return this.id==p2.getId() && this.name.equals(p2.getName()) && this.salary==p2.getsal();
	}
	@Override
	public String toString() {
		return "Person has id="+id+",with name="+name+"has salary="+salary;
	}
}
class Producer implements Runnable {
	   private final BlockingQueue<Person> queue;
	   private final List<Person> lp;
	   Producer(BlockingQueue<Person> q,List<Person> l) { queue = q;lp=l; }
	   public void run() {
	     try {
	       while(true) 
	       { 
	    	   Thread.sleep(2000);
	    	   queue.put(produce());
	    	   
	       }
	     } catch (InterruptedException ex) 
	     { 
			System.out.println("Producer interrupted..");
		}
	   }
	   Person produce() 
	   { 		   
		   //Person p1=null;
		   for(int i=0;i<lp.size();i++)
		   {
			   Person p1=lp.get(i);
			   for(int j=i;j<lp.size();j++)
			   {
				   if(lp.get(j).equals(p1)) {
					   System.out.println("Produced"+p1.toString());
					   return p1;
				   }
			   }
		   }
		   return null;
		}
}
class Consumer implements Runnable {
	private final BlockingQueue<Person> queue;
	private final List<Person> lp;
	//private HashMap<Person,Integer> hm;
	static Set<Person> stp=new HashSet<>();
	Consumer(BlockingQueue<Person> q,List<Person> l) { queue = q;lp=l; }

	public void run() {
		try {
			System.out.println("Consumer has started");
			while (true) {
				System.out.println("Consumer waiting to consume");
				Thread.sleep(2000);
				consume((Person) queue.take());
			}
		} catch (InterruptedException ex) {
			System.out.println("Consumer interrupted..");
		}
	}

	void consume(Person p) {
		if(lp.contains(p))
		{
			lp.remove(lp.indexOf(p));
			System.out.println("Consuming "+p.toString());
			stp.add(p);
		}
	}
	public Set<Person> getSet(){
		return stp;
	}
}


public class Testprodcons {

	public static void main(String[] args) {
		List<Person> lp=new ArrayList<>();
		lp.add(new Person(1,"abc",1000));
		lp.add(new Person(1,"abc",1000));
		lp.add(new Person(2,"csh",9800));
		lp.add(new Person(3,"cfg",9810));
		lp.add(new Person(3,"cfg",9810));
		
		BlockingQueue<Person> blq = new LinkedBlockingQueue<>();
		Producer p = new Producer(blq,lp);
	    Consumer c1 = new Consumer(blq,lp);
	    new Thread(p).start();
	    new Thread(c1).start();
	}

}
