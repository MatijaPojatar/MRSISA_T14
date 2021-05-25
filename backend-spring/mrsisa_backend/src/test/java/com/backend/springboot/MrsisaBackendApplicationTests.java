package com.backend.springboot;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.service.SavetovanjeService;

@SpringBootTest
class MrsisaBackendApplicationTests {
	
	@Autowired
	SavetovanjeService service;

	@Test
	public void testPessimisticLockingScenario() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Exception ex;
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
		        service.findAllInRangeForFarmaceut(1, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
				
			}
		});
		Future<?> future2 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
		        try { Thread.sleep(150); } catch (InterruptedException e) { }

		        service.findAllInRangeForFarmaceut(1, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
			}
		});
		Exception exception =assertThrows(PessimisticLockingFailureException.class,()->{future2.get();}); 

		executor.shutdown();
		
	}

}
