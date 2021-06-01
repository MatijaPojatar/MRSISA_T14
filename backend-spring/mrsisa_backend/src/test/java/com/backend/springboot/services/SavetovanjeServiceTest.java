package com.backend.springboot.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.SavetovanjeRepository;
import com.backend.springboot.service.SavetovanjeService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SavetovanjeServiceTest {
	
	@Mock
	private SavetovanjeRepository savetovanjeRep;
	
	@InjectMocks
	private SavetovanjeService service;
	
	@Test
	public void testFindAllByPacijentId() {
		
		when(savetovanjeRep.findAllByPacijentId(anyInt())).thenReturn(Arrays.asList(new Savetovanje(),new Savetovanje(),new Savetovanje()));
		
		java.util.List<Savetovanje> savetovanja=service.findAllByPacijentId(1);
		
		assertThat(savetovanja).hasSize(3);
		
		verify(savetovanjeRep).findAllByPacijentId(1);
	}

}
