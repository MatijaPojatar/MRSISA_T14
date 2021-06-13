package com.backend.springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.MagacinRepository;
import com.backend.springboot.repository.NarudzbenicaRepository;
import com.backend.springboot.repository.SavetovanjeRepository;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.DermatologService;
import com.backend.springboot.service.MagacinService;
import com.backend.springboot.service.NarudzbenicaService;
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
	
	@Autowired
	ApotekaService apotekaService;
	
	@Mock
	private SavetovanjeRepository savetovanjeRep;
	
	@InjectMocks
	private SavetovanjeService savetovanjeServiceUnit;
	
	
	@InjectMocks
	private NarudzbenicaService narudzbenicaServiceUnit;
	
	@Mock
	private NarudzbenicaRepository narudzbenicaRep;
	
	@InjectMocks
	private MagacinService magacinServiceUnit;
	
	@Mock
	private MagacinRepository magacinRep;
	
	@Mock
	private ApotekaRepository apotekaRep;
	
	@InjectMocks
	private ApotekaService apotekaServiceUnit;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	/*@Test
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
		
	}*/
	
	//unit testovi
	
	//S3
	
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
	
	
	
	//S2
	
	@Test
	public void testFindOneNarudzbenica() {
		
		Narudzbenica n=new Narudzbenica();
		
		when(narudzbenicaRep.findOneById(anyInt())).thenReturn(n);
		
		Narudzbenica nar=narudzbenicaServiceUnit.findOne(1);
		
		assertEquals(n, nar);
		
		verify(narudzbenicaRep).findOneById(1);
	}
	
	@Test
	public void testFindAllMagacin() {
		
		when(magacinRep.findAll()).thenReturn(Arrays.asList(new Magacin(),new Magacin(),new Magacin()));
		
		java.util.List<Magacin> magacini=magacinServiceUnit.findAll();
		
		assertThat(magacini).hasSize(3);
		
		verify(magacinRep).findAll();
	}
	
	@Test
	public void testFindOneByApotekaIdMagacin() {
		
		Magacin m=new Magacin();
		
		when(magacinRep.findOneByApotekaId(anyInt())).thenReturn(m);
		
		Magacin mag=magacinServiceUnit.findOneByApotekaId(1);
		
		assertEquals(m, mag);
		
		verify(magacinRep).findOneByApotekaId(1);
	}
	
	//S1
	
	@Test
	public void testFindAllApoteka() {
		when(apotekaRep.findAll()).thenReturn(Arrays.asList(new Apoteka(), new Apoteka(), new Apoteka()));
	
		java.util.List<Apoteka> apoteke = apotekaServiceUnit.findAll();
		
		assertThat(apoteke).hasSize(3);
		
		verify(apotekaRep).findAll();
	}
	
	@Test
	public void testFindByGradApoteka() {
		when(apotekaRep.findByGradIgnoringCase(anyString())).thenReturn(Arrays.asList(new Apoteka(), new Apoteka()));
		
		java.util.List<Apoteka> apoteke = apotekaServiceUnit.pretraga("", "Novi Sad");
		
		assertThat(apoteke).hasSize(2);
		
		verify(apotekaRep).findByGradIgnoringCase("Novi Sad");
	}
	
	@Test
	public void testFindByNazivAndGradApoteka() {
		when(apotekaRep.findByNazivIgnoringCaseAndGradIgnoringCase(anyString(), anyString())).thenReturn(Arrays.asList(new Apoteka()));
		
		java.util.List<Apoteka> apoteke = apotekaServiceUnit.pretraga("Galen", "Novi Sad");
		
		assertThat(apoteke).hasSize(1);
		
		verify(apotekaRep).findByNazivIgnoringCaseAndGradIgnoringCase("Galen", "Novi Sad");
	}
	
	@Test
	public void testFindByPacijentIdRezervacija() {
		when(apotekaRep.findByNazivIgnoringCaseAndGradIgnoringCase(anyString(), anyString())).thenReturn(Arrays.asList(new Apoteka()));
		
		java.util.List<Apoteka> apoteke = apotekaServiceUnit.pretraga("Galen", "Novi Sad");
		
		assertThat(apoteke).hasSize(1);
		
		verify(apotekaRep).findByNazivIgnoringCaseAndGradIgnoringCase("Galen", "Novi Sad");
	}
	
	//integracioni testovi
	
	//S3
	
	@Test
	public void testFindAllFarmaceutByApotekaId() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/farmaceut/apoteka/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)));
	}
	
	@WithMockUser(roles="FARMACEUT")
	@Test
	public void testGetAllSavetovanjaForFarmaceut() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/savetovanje/all/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)));
	}
	
	@Test
	public void testZauzmiSavetovanje() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/savetovanje/zauzmi/1/2")).andExpect(status().is(405));
	}
	
	//S2
	
	@Test
	public void testPreuzmiUpite() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/apoteke/upiti/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void testGetAllLekoviVanApoteke() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/lekovi/vanApoteke/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(7)));
	}
	
	@Test
	public void testfindAllLekoviByApotekaId() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/lekovi/apoteka/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(0)));
	}

	//S1
	
	@Test
	public void testRezervacijePacijenta() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/rezervacija/pacijent/2")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)));
	}
	
	@Test
	public void testAlergijePacijenta() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/pacijent/alergije/2")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void testAkcijePromocijePacijenta() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.mockMvc.perform(get( "/akcijaPromocija/pacijent/2")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)));
	}
}
