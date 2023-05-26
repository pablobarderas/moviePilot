package com.app.moviePilot.registerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.moviePilot.controller.register.UserImageManager;
import org.springframework.mock.web.MockMultipartFile;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author Marino Burillo
 *
 */
@SpringBootTest
class UserImageManagerTest {
	/**
	 * This test requires an image located in src/main/resources/testImages in order to 
	 * know wether the function saves images received by client calls
	 */
	@Test
	void saveImageTest() {
		Path resourceDirectory = Paths.get("src","main","resources", "testImages");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		MultipartFile multipartFile = null;
		try {
			multipartFile = new MockMultipartFile(System.currentTimeMillis()+".jpg", new FileInputStream(new File(absolutePath+"/testImage.jpg")));	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(UserImageManager.saveImage(multipartFile));
	}
	@Test
	void failedSavingImageTest() {
		MultipartFile multipartFile = null;
		assertNull(UserImageManager.saveImage(multipartFile));
	}
	@Test
	void deleteImageTest() {
		Path resourceDirectory = Paths.get("src","main","resources", "testImages");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		MultipartFile multipartFile = null;
		try {
			multipartFile = new MockMultipartFile(System.currentTimeMillis()+".jpg", new FileInputStream(new File(absolutePath+"/testImage.jpg")));	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imageAbsolutePath=UserImageManager.saveImage(multipartFile);
		Path pathImageToDelete = Path.of(imageAbsolutePath);
		assertTrue(UserImageManager.deleteImage(pathImageToDelete));
	}
	@Test
	void deleteNotExistingImageTest() {
		Path pathImageToDelete = Path.of("InvalidPath, Image does not exist");
		assertFalse(UserImageManager.deleteImage(pathImageToDelete));
	}
}
