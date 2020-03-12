package exer5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

public class Testsem {

	private static Semaphore sem=new Semaphore(1);
	private static List<Integer> l=new ArrayList<>();
	private static Integer in=-1;
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(3);
		l.add(1);l.add(2);l.add(3);
		service.submit(new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
		        try {
		        	sem.tryAcquire();
		        	in++;
		        	if(in<l.size()){
		        		l.get(in);
		        		System.out.println(l.get(in));
		        	}
		        }
		        finally {
		        	sem.release();
		        }return "read";
			}
		}));
		service.submit(new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
		        try {
		        	sem.tryAcquire();
		        	in++;
		        	if(in<l.size()){
		        		l.get(in);
		        		System.out.println(l.get(in));
		        	}
		        }
		        finally {
		        	sem.release();
		        }return "read";
			}
		}));
		service.submit(new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
		        try {
		        	sem.tryAcquire();
		        	l.add(10);
		        	
		        }
		        finally {
		        	sem.release();
		        }return "written";
			}
		}));
		service.submit(new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
		        try {
		        	sem.tryAcquire();
		        	in++;
		        	if(in<l.size()){
		        		l.get(in);
		        		System.out.println(l.get(in));
		        	}
		        }
		        finally {
		        	sem.release();
		        }return "read";
			}
		}));
		service.submit(new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
		        try {
		        	sem.tryAcquire();
		        	in++;
		        	if(in<l.size()){
		        		l.get(in);
		        		System.out.println(l.get(in));
		        	}
		        }
		        finally {
		        	sem.release();
		        }return "read";
			}
		}));
	}

}
