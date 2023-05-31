package com.app.moviePilot.controller.register;
import java.util.Random;
/**
 * 
 * @author Marino Burillo
 *
 */
public abstract class AlphabeticStringGenerator {
	/**
	 * 
	 * @param textLength
	 * @return random String with only alphabetical values
	 */
	public static String create(final int textLength) {
		int leftLimit = 97; 
		int rightLimit = 122; 
		Random random = new Random();
		return random.ints(leftLimit, rightLimit + 1).limit(textLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

}
