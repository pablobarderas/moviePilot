package com.app.moviePilot.controller.register;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.BooleanSupplier;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Marino Burillo
 *
 */
public abstract class UserImageManager {
	private static final String FILE_PATH = "src/main/resources/images/";

	public static String saveImage(MultipartFile file) {
		if(file == null) return null;
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Path.of(FILE_PATH, System.currentTimeMillis() + ".jpg");
		fileNames.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			return null;
		}
		return fileNameAndPath.toAbsolutePath().toString();
	}

	public static boolean deleteImage(Path pathImageToDelete) {
		try {
			Files.delete(pathImageToDelete);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
