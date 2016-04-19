package com.test.project;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class UserService extends CustomService{

	public boolean insertUser(User user){

		EntityManager mgr=null;
		mgr=getEntityManager();
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		boolean userInserted=false;
		try{
			
			mgr.persist(user);
			txn.commit();
			userInserted=true;
		}finally{
			if(txn.isActive())
			{
				txn.rollback();
			}
			mgr.close();
		}
		return userInserted;

	}
	
	public List<String> getHashed_Password(String password)  {
		String salt ="";
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		List<String> hashed_Password = new ArrayList<String>();
		hashed_Password.add(get_SHA_512_SecurePassword(password, salt));
		hashed_Password.add(salt);

		return hashed_Password;
	}
	
	public boolean checkPassword(String password, List<String>hashedPassword){
		boolean value = true;
		String hashed = get_SHA_512_SecurePassword(password,hashedPassword.get(1));
		String test = hashedPassword.get(0);
		System.out.println(hashed);
		System.out.println(test);
		int i =0;
		if(test.length() != hashed.length()){
			value = false;
		}
		else{
			while(i<test.length()){
				if (test.charAt(i) != hashed.charAt(i)){
					value =false;
				}
				i = i+1;
			}
		}
			System.out.print(value);
		
		
		return value;
	}
	public String get_Hash()
	{
		String value =null;
		try {
			value = get_SHA_512_SecurePassword(getSalt(),getSalt());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	private static String get_SHA_512_SecurePassword(String passwordToHash, String salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return generatedPassword;
    }
	
	private static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
	
	@SuppressWarnings("unchecked")
	public UserExist Check_email(String email){
		EntityManager mgr=null;
		List<User> users =null;
		List<String> emails = new ArrayList<String>();
		UserExist value = new UserExist();
		try{
			mgr=getEntityManager();
			
			Query query=mgr.createQuery("Select From User as User");
			users = (List<User>) query.getResultList();
			
			
		}finally{
			mgr.close();
		}
		boolean temp = false;
		for (User user: users){
			emails.add(user.getEmail());
		}
		int i=0;
		
		if (emails.contains(email)){
			temp = true;
			i=emails.indexOf(email);
			
		}
		value.setCheck_email(temp);
		if (temp){
		value.setUser(users.get(i));
		}
		else{
			value.setUser(null);
		}
		return value;
	}
	
	public void sendEmail(User user){
		String name = user.getFirstName()+" "+user.getLastName();
		String description = "Click on this link to activate your email";
		String userEmail = user.getEmail();
		String emailHash = user.getEmailhash();
		Properties prop = new Properties();
		Session session = Session.getDefaultInstance(prop, null);
		String message = "Hi "+ name + "\n" + description + "\n" +"http://1-dot-astral-depth-124605.appspot.com/verifyEmail"+"?email="+userEmail + "?emailHash="+emailHash;
		try{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("astral-depth-124605@appspot.gserviceaccount.com","Neeserg"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail,name));
			msg.setSubject("Activate your acount with Guestbook");
			msg.setText(message);
			Transport.send(msg);
		}catch(Exception e){
            throw new RuntimeException(e);

		}
		
	}
	
	
	
	public boolean updateUser(User user, Long hotelId, int numStars){
		EntityManager mgr=null;
		Long UserId = user.getId();
		HotelService hotelService = new HotelService();
		mgr=getEntityManager();
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		boolean userUpdated=false;
		try{
			
			
			user = mgr.find(User.class,UserId);
			user.addHotelId(hotelId);
			user.addStarRatings(numStars);
			hotelService.updateHotel(hotelId, numStars);
			txn.commit();
			userUpdated=true;
		}finally{
			if(txn.isActive())
			{
				txn.rollback();
			}
			mgr.close();
		}
		return userUpdated;
		
	}
	public boolean updateUserStatus(User user){
		EntityManager mgr=null;
		Long UserId = user.getId();
		mgr=getEntityManager();
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		boolean userUpdated=false;
		try{
			user = mgr.find(User.class,UserId);
			user.setActivated(true);
			txn.commit();
			userUpdated=true;
		}finally{
			if(txn.isActive())
			{
				txn.rollback();
			}
			mgr.close();
		}
		return userUpdated;
		
	}
	
}
