package com.backend.springboot;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.controller.PregledController;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.DermatologDTO;
import com.backend.springboot.service.DermatologService;
import com.backend.springboot.service.PregledService;
import com.backend.springboot.service.SavetovanjeService;

@SpringBootTest
class MrsisaBackendApplicationTests {
	
	@Autowired
	SavetovanjeService service;
	
	@Autowired
	DermatologService dermService;
	
	@Autowired
	SavetovanjeService savetovanjeService;
	
	@Autowired
	PregledService pregledService;

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
	
	@Test
	public void testGetOneDermatolog() {
		Dermatolog d=dermService.findOne(3);
		assertEquals("petar@gmail.com", d.getMail());
	}
	
	@Test
	public void testGetAllSavetovanjaForFarmaceut() {
		ArrayList<Savetovanje> savetovanja=(ArrayList<Savetovanje>) savetovanjeService.findAllByFarmaceutId(1);
		ArrayList<Integer> savetovanjaIds=new ArrayList<Integer>();
		for(Savetovanje s:savetovanja) {
			savetovanjaIds.add(s.getId());
		}
		ArrayList<Integer> savetovanjaIdsTest=new ArrayList<Integer>();
		savetovanjaIdsTest.add(1);
		savetovanjaIdsTest.add(5);
		savetovanjaIdsTest.add(6);
		savetovanjaIdsTest.add(7);
		savetovanjaIdsTest.add(11);
		savetovanjaIdsTest.add(16);
		
		assertArrayEquals(savetovanjaIdsTest.toArray(), savetovanjaIds.toArray());
		
	}
	
	@Test
	public void testGetAllInRangePreglediForDermatolog() {
		ArrayList<Pregled> pregledi=(ArrayList<Pregled>) pregledService.findAllInRangeForDermatolog(3, LocalDateTime.of(2021, 4, 26, 11, 0), LocalDateTime.of(2021, 4, 28, 11, 0));
		ArrayList<Integer> preglediIds=new ArrayList<Integer>();
		for(Pregled p:pregledi) {
			preglediIds.add(p.getId());
		}
		
		System.out.println(preglediIds.size());
		
		ArrayList<Integer> preglediIdsTest=new ArrayList<Integer>();
		preglediIdsTest.add(2);
		preglediIdsTest.add(10);
		
		assertArrayEquals(preglediIdsTest.toArray(), preglediIds.toArray());
		
	}


}
