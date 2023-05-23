package com.app.moviePilot.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

/**
 * 
 * @author Marino Burillo
 *
 */
public class KeyStorer {
	private static final String FILE_NAME = "project.properties";
	private static final String FILE_PATH = "src/main/resources";
	 public static boolean saveEncryptedSecretToProperties(final String encryptedSecret, final String index) {
		 	if(encryptedSecret==null || encryptedSecret.trim().length()==0 || index==null || index.trim().length()==0) return false;
	        Properties properties = new Properties();
	        Path filePath = Path.of(FILE_PATH, FILE_NAME);
	        try {	
	        	InputStream inputStream = Files.newInputStream(filePath, StandardOpenOption.READ);
	            properties.load(inputStream);
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	        if(properties.get(index)==null) properties.setProperty(index, encryptedSecret);	       
	        try {
	            OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
	            properties.store(outputStream, "Encrypted Secrets");
	            return true;	             
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 /**
	  * This functions should be added to the code when the product gets into a commercial state
	  */
	 /*
	 private static void changeFileOwnership(final Path filePath){
	        FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(filePath, FileOwnerAttributeView.class);
	        UserPrincipalLookupService lookupService = filePath.getFileSystem().getUserPrincipalLookupService();
	        UserPrincipal userPrincipal;
			try {
				userPrincipal = lookupService.lookupPrincipalByName("Marino Burillo");
		        ownerAttributeView.setOwner(userPrincipal);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	 private static boolean changeFileState(final Path filePath) {
		  UserPrincipalLookupService lookupService = filePath.getFileSystem().getUserPrincipalLookupService();
			try {
				 UserPrincipal authenticatedUsers = lookupService.lookupPrincipalByName("Administradores");
				  AclFileAttributeView aclView = Files.getFileAttributeView(filePath, AclFileAttributeView.class);
		            List<AclEntry> aclEntries = aclView.getAcl();
		            AclEntry.Builder entryBuilder = AclEntry.newBuilder();
		            entryBuilder.setType(AclEntryType.ALLOW);
		            entryBuilder.setPrincipal(authenticatedUsers);
		            entryBuilder.setPermissions(AclEntryPermission.READ_DATA, AclEntryPermission.WRITE_DATA, AclEntryPermission.READ_ATTRIBUTES, AclEntryPermission.READ_ACL);
		            
		            AclEntry aclEntry = entryBuilder.build();
		            aclEntries.add(aclEntry);
		            
		            aclView.setAcl(aclEntries);
		            return true;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
	 }*/

}
