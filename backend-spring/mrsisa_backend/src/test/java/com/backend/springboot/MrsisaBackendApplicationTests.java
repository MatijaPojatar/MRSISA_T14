package com.backend.springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.SavetovanjeRepository;
import com.backend.springboot.service.DermatologService;
import com.backend.springboot.service.PregledService;
import com.backend.springboot.service.SavetovanjeService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MrsisaBackendApplicationTests {
	
	@Autowired
	SavetovanjeService service;
	
	@Autowired
	DermatologService dermService;
	
	@Autowired
	SavetovanjeService savetovanjeService;
	
	@Autowired
	PregledService pregledService;
	
	@Mock
	private SavetovanjeRepository savetovanjeRep;
	
	@InjectMocks
	private SavetovanjeService savetovanjeServiceUnit;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

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
	
	//unit testovi
	
	@Test
	public void testFindAllByPacijentId() {
		
		when(savetovanjeRep.findAllByPacijentId(anyInt())).thenReturn(Arrays.asList(new Savetovanje(),new Savetovanje(),new Savetovanje()));
		
		java.util.List<Savetovanje> savetovanja=savetovanjeServiceUnit.findAllByPacijentId(1);
		
		assertThat(savetovanja).hasSize(3);
		
		verify(savetovanjeRep).findAllByPacijentId(1);
	}
	
	@Test
	public void testFindAllByFarmaceutId() {
		
		when(savetovanjeRep.findAllByFarmaceutId(anyInt())).thenReturn(Arrays.asList(new Savetovanje(),new Savetovanje()));
		
		java.util.List<Savetovanje> savetovanja=savetovanjeServiceUnit.findAllByFarmaceutId(1);
		
		assertThat(savetovanja).hasSize(2);
		
		verify(savetovanjeRep).findAllByFarmaceutId(1);
	}
	
	@Test
	public void testFindOne() {
		
		Savetovanje sav=new Savetovanje();
		
		when(savetovanjeRep.findOneById(anyInt())).thenReturn(sav);
		
		Savetovanje savetovanje=savetovanjeServiceUnit.findOne(1);
		
		assertEquals(sav, savetovanje);
		
		verify(savetovanjeRep).findOneById(1);
	}
	
	//integracioni testovi
	
	
	@Test
	public void testGetAllSavetovanja() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/savetovanje/all")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(6)));
	}
	
	@Test
	public void testGetAllSavetovanjaForFarmaceut() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/savetovanje/all/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(6)));
	}
	
	@Test
	public void testGetAllSavetovanjaForPacijent() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/savetovanje/zauzmi/1/2")).andExpect(status().is(405));
	}


}
