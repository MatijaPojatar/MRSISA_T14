package com.backend.springboot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.backend.springboot.domain.LekERecepta;
import com.backend.springboot.domain.ListaLekovaHelper;
import com.backend.springboot.dto.LekEReceptaDTO;
import com.backend.springboot.util.QRCodeGenerator;
import com.backend.springboot.util.QRCodeReader;
import com.google.gson.Gson;
import com.google.zxing.WriterException;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class MrsisaBackendApplication{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MrsisaBackendApplication.class, args);
//		
//		gen();
//		read();
		
	}
//	public static void read() throws Exception {
//		QRCodeReader.readQRCode();
//	}
	
//	public static void gen() throws WriterException, IOException {   OVO JE STARA FUNKCIJA< NE STAVLJA ID
//		List<LekEReceptaDTO> dtos = new ArrayList<LekEReceptaDTO>();
//		
//		LekEReceptaDTO lekDTO = new LekEReceptaDTO();
//		lekDTO.setId(1);
//		lekDTO.setKolicina(2);
//		lekDTO.setNazivLeka("Bromazepam");
//		dtos.add(lekDTO);
//		
//		LekEReceptaDTO lekDTO2 = new LekEReceptaDTO();
//		lekDTO2.setId(2);
//		lekDTO2.setKolicina(3);
//		lekDTO2.setNazivLeka("Aspirin");
//		dtos.add(lekDTO2);
//		
//		ListaLekovaHelper lista = new ListaLekovaHelper(dtos);
//		
//		Gson gson = new Gson();
//		
//		String stringListe = gson.toJson(lista);
//		
//		//String text, int width, int height, String filePath
//		QRCodeGenerator.generateQRCodeImage(stringListe, 200, 200, "D:\\6semestar\\QR_test1.png");
//	}
}
