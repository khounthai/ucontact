package com.ril.classes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.springframework.web.multipart.MultipartFile;

public class Traitement {

	public static void Photo(MultipartFile myFile, String idcontactEncrypt) throws URISyntaxException, IOException {
		URL url = GetClassLoader.class.getResource("/com/ril/imgs/avatar");		
		File furl = null;
		
		// crée le répertoire avatar s'il n'existe pas
		if (url==null)
		{
			System.out.println("url null");
			url = GetClassLoader.class.getResource("/com/ril/imgs");
			System.out.println(url.toURI().getPath());
			furl = new File(url.toURI().getPath()+"/avatar");			
			furl.mkdirs();			
		}
			
		url = GetClassLoader.class.getResource("/com/ril/imgs/avatar");
		
		furl = new File(url.toURI());		
		
		final String UPLOAD_PATH = furl.getAbsolutePath() + "/" + idcontactEncrypt + "/";

		System.out.println("traitement: "+myFile.getOriginalFilename());
		
		// Crée le répertoire de destintation
		File pathDes = new File(UPLOAD_PATH);
		if (!pathDes.exists())
			pathDes.mkdirs();
		
		//redimenssione l'image avec w=200 ou h=200
		BufferedImage bimg = ImageIO.read(myFile.getInputStream());
		
		float ratio ;
		int x,y,taille;
		taille=100;
		//Créé l'image redimensionnée
		Image image;
		if (bimg.getHeight()>bimg.getWidth())
		{
			ratio = (float)bimg.getHeight() / (float)bimg.getWidth();
			x=taille;	
			y=(int) (taille * ratio);
		}
		else
		{
			ratio = (float)bimg.getWidth()/(float) bimg.getHeight() ;
			x=(int) (taille * ratio);
			y=taille;
		
		}

		image = bimg.getScaledInstance(x,y, Image.SCALE_SMOOTH);
		
		System.out.println("r:="+ratio);
		System.out.println("x:="+x+"; y="+y);
		System.out.println("bx:="+bimg.getWidth()+"; by="+bimg.getHeight());
		
		//Enregistre l'image redimenssionnée dans un BufferedImage		
		BufferedImage buffered = new BufferedImage(x,y, Image.SCALE_SMOOTH);
		buffered.getGraphics().drawImage(image, 0, 0, null);
		//Enregistre l'image dans un byte[]
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(buffered, "jpg", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();

		System.out.println(UPLOAD_PATH + myFile.getOriginalFilename());

		Path destination = Paths.get(UPLOAD_PATH + myFile.getOriginalFilename());
		Files.write(destination, imageInByte);

		System.out.println("write file: " + destination.toFile().toPath());
		
	}

}
