package com.backend.springboot.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.backend.springboot.domain.ListaLekovaHelper;
import com.google.gson.Gson;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRCodeReader {
	
	public static void readQRCode() throws Exception{
		try {
			String path = "D:\\6semestar\\Projekatt\\MRSISA_T14\\QR_kodovi\\QRcode_5.png";//ovde ubaci parametar funkcije
			
			BufferedImage bf = ImageIO.read(new FileInputStream(path)); //mozda ovde ubacim file koji sad dobavila preko uploada
			
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
					new BufferedImageLuminanceSource(bf)));
			Result result = new MultiFormatReader().decode(bitmap);
			
			System.out.println(result.getText());
		}catch(Exception e) {
			
		}
	}
	
	public static ListaLekovaHelper readQRCodeFromMultipartFile(MultipartFile file) throws Exception{
		try {
			InputStream inputStream =  new BufferedInputStream(file.getInputStream());
			
			BufferedImage bf = ImageIO.read(inputStream);
			
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
					new BufferedImageLuminanceSource(bf)));
			Result result = new MultiFormatReader().decode(bitmap);
			
			ListaLekovaHelper helper = new Gson().fromJson(result.getText(), ListaLekovaHelper.class);
			return helper;
			
		}catch(Exception e) {
			return null;
		}
		
	}
}
