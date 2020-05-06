package com.campusx.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.campusx.amg.AuthenticationManager;
import com.campusx.ety.AddressEntity;
import com.campusx.ety.ItemEntity;
import com.campusx.ety.KycEntity;
import com.campusx.ety.OtpEntity;
import com.campusx.ety.ShopEntity;
import com.campusx.ety.VendorEntity;
import com.campusx.mdl.Address;
import com.campusx.mdl.Item;
import com.campusx.mdl.Kyc;
import com.campusx.mdl.Otp;
import com.campusx.mdl.Shop;
import com.campusx.mdl.Vendor;

/**
 * 
 * @author campusx
 * @author https://campusx.com
 * @version 1.0
 * @since 2020-05-03
 *
 */
@Repository(value="vendorDAO")
public class VendorDAOImpl implements VendorDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Vendor registerVendor(Vendor vendor) throws Exception {
		
		// create vendor entity object
		VendorEntity vendorEn = new VendorEntity();
		
		// populating vendor entity with data
		vendorEn.setFirstName(vendor.getFirstName());
		vendorEn.setLastName(vendor.getLastName());
		vendorEn.setPhoneNumber(vendor.getPhoneNumber());
		vendorEn.setSecretPin(vendor.getSecretPin());
		vendorEn.setPassword(AuthenticationManager.encryptPassword(vendor.getPassword()));
		vendorEn.setAddTimestamp(LocalDateTime.now());
		vendorEn.setSecretPinLastUpdateTimestamp(null);
		vendorEn.setPasswordLastUpdateTimestamp(null);
		vendorEn.setAccountStatus(true);
		vendorEn.setIsAccountClosed(false);
		
		// persisting vendor entity to the database
		entityManager.persist(vendorEn);
		
		// creating object of vendor.
		Vendor v = new Vendor();
		
		// populating vendor object with data from vendor entity.
		v.setVendorId(vendorEn.getVendorId());
		v.setFirstName(vendorEn.getFirstName());
		v.setLastName(vendorEn.getLastName());
		v.setPhoneNumber(vendorEn.getPhoneNumber());
		v.setSecretPin(vendorEn.getSecretPin());
		v.setPassword(vendorEn.getPassword());
		v.setAddTimestamp(vendorEn.getAddTimestamp());
		v.setSecretPinLastUpdateTimestamp(vendorEn.getSecretPinLastUpdateTimestamp());
		v.setPasswordLastUpdateTimestamp(vendorEn.getPasswordLastUpdateTimestamp());
		v.setAccountStatus(vendorEn.getAccountStatus());
		v.setIsAccountClosed(vendorEn.getIsAccountClosed());
		
		// returning the vendor information.
		return v;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Vendor loginVendor(Long phoneNumber, String password) throws Exception {
		
		// checking whether the entered phone number exists or not and its password matches or not
		String phoneNumberCheckQuery = "SELECT ve FROM VendorEntity ve WHERE ve.phoneNumber=?1";
		Query query = entityManager.createQuery(phoneNumberCheckQuery);
		query.setParameter(1, phoneNumber);
		List<VendorEntity> veList = query.getResultList();
		
		// if list is empty, return -1;
		if(veList.isEmpty()) {
			return null;
		}
		
		// else, decrypt password and check account status
		else {
			
			// creating object of vendor class.
			Vendor v = new Vendor();
			for(VendorEntity ve : veList) {
				v.setVendorId(ve.getVendorId());
				v.setFirstName(ve.getFirstName());
				v.setLastName(ve.getLastName());
				v.setPhoneNumber(ve.getPhoneNumber());
				v.setSecretPin(ve.getSecretPin());
				v.setPassword(ve.getPassword());
				v.setAddTimestamp(ve.getAddTimestamp());
				v.setSecretPinLastUpdateTimestamp(ve.getSecretPinLastUpdateTimestamp());
				v.setPasswordLastUpdateTimestamp(ve.getPasswordLastUpdateTimestamp());
				v.setAccountStatus(ve.getAccountStatus());
				v.setIsAccountClosed(ve.getIsAccountClosed());
			}
			
			// checking whether the entered password matches with encrypted password or not
			Boolean passwordMatching = AuthenticationManager.decryptPassword(password, v.getPassword());
			
			// if matches, check for account status
			if(passwordMatching) {
				
				// checking account status
				// if all good, return user object.
				if(v.getAccountStatus()==true && v.getIsAccountClosed()==false) {
					return v;
				}
				
				// else, throw new exception.
				else {
					throw new Exception("DAO.ACCOUNT_CLOSED");
				}
			}
			
			// else throw an exception.
			else {
				throw new Exception("DAO.PASSWORD_NOT_MATCH");
			}
		}
	}

	@Override
	public Integer addShop(Integer vendorId, Shop shop) throws Exception {
		// checking whether the required parameter vendor id exists or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if not exists, return -1
		if(ve == null) {
			return -1;
		}
		
		// else, return 1
		else {
			
			// creating object of shop entity.
			ShopEntity shopEn = new ShopEntity();
			
			// populating shop entity object with data coming from request body.
			shopEn.setName(shop.getName());
			shopEn.setOfficialPhoneNumber(shop.getOfficialPhoneNumber());
			
			// perform email id check because it can be null
			if(shop.getOfficialEmailId() != null) {
				shopEn.setOfficialEmailId(shop.getOfficialEmailId());
			}
			else {
				shopEn.setOfficialEmailId(null);
			}
			shopEn.setShopPicture(null);
			shopEn.setNumberOfItems(0);
			shopEn.setAveragePrice(0.0);
			shopEn.setShopRating(0f);
			shopEn.setAddTimestamp(LocalDateTime.now());
			shopEn.setLastUpdateTimestamp(null);
			shopEn.setStatus(false);
			
			// creating object of address entity integrated with shop.
			AddressEntity addrEn = new AddressEntity();
			
			// populating information into the object
			addrEn.setCity(shop.getAddress().getCity());
			addrEn.setState(shop.getAddress().getState());
			addrEn.setStreet(shop.getAddress().getStreet());
			addrEn.setCountry(shop.getAddress().getCountry());
			addrEn.setZipCode(shop.getAddress().getZipCode());
			addrEn.setGeoLat(shop.getAddress().getGeoLat());
			addrEn.setGeoLang(shop.getAddress().getGeoLang());
			shopEn.setAddress(addrEn);
			
			// getting the list of items which vendor wants to add to his/her catalog. for now, we have just limited it to the one.
			List<Item> items = shop.getItems();
			
			// creating a new list of type items entity which will contain the list of all the items added by vendor.
			List<ItemEntity> itemEn = new ArrayList<ItemEntity>();
			
			// traverse through the items list and populate data to database.
			for(Item i : items) {
				
				// creating object of item entity which will represents the information related to items added by vendor.
				ItemEntity ie = new ItemEntity();
				ie.setName(i.getName());
				ie.setDescription(i.getDescription());
				ie.setPrice(i.getPrice());
				ie.setFoodType(i.getFoodType());
				ie.setPicture(i.getPicture());
				ie.setItemRating(0f);
				ie.setAddTimestamp(LocalDateTime.now());
				ie.setLastUpdateTimestamp(null);
				
				// adding the newly added item to the items list.
				itemEn.add(ie);
			}
			shopEn.setItems(itemEn);
			
			// setting the vendor entity to the shop which creates a one to one relationship between vendor and shop.
			shopEn.setVendor(ve);
			
			// persisting the data into the database;
			entityManager.persist(shopEn);
			
			return 1;
		}
	}

	@Override
	public Integer completeKYC(Integer vendorId, Kyc kyc) throws Exception {
		
		// checking whether the request parameter shopId exists or not
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if does not, it will return a status code of -1.
		if(ve == null) {
			return -1;
		}
		
		// else, it will return a status code 1.
		else {
			
			// extracting shop from vendor id
			ShopEntity se = ve.getShop();
			
			// creating an object of Kyc entity class.
			KycEntity kycEn = new KycEntity();
			
			// populate the object with information coming from request body.
			kycEn.setGstNumber(kyc.getGstNumber());
			kycEn.setKycIssueTimestamp(LocalDateTime.now());
			
			// update shop status
			se.setStatus(true);
			
			// mapping the one to one relationship between kyc and shop.
			kycEn.setShop(se);
			
			// persisting the information into the database.
			entityManager.persist(kycEn);
			
			return 1;
		}
		
	}
	
	@Override
	public Integer addNewItem(Integer vendorId, Item item) throws Exception {
		
		// checking whether the vendor id exists or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if not exists, return -1
		if(ve == null) {
			return -1;
		}
		
		// else, return 1
		else {
			
			// extracting shop entity from vendor id
			ShopEntity se = ve.getShop();
		
			// getting list of items from shop entity
			List<ItemEntity> ieList = se.getItems();
			ItemEntity ie = new ItemEntity();
			ie.setName(item.getName());
			ie.setDescription(item.getDescription());
			ie.setPrice(item.getPrice());
			ie.setFoodType(item.getFoodType());
			ie.setPicture(item.getPicture());
			ie.setItemRating(0f);
			ie.setAddTimestamp(LocalDateTime.now());
			ie.setLastUpdateTimestamp(null);
			ieList.add(ie);
			
			// update vendor number of items
			Integer oldNOI = se.getNumberOfItems();
			Integer newNOI = oldNOI+1;
			se.setNumberOfItems(newNOI);
			
			return 1;
		}
	}

	@Override
	public Vendor getProfile(Integer vendorId) throws Exception {
		
		// checking whether required vendor id is exist or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if not, return null
		if(ve == null) {
			return null;
		}
		
		// else return an object of vendor populated with required information.
		else {
			Vendor v = new Vendor();
			v.setVendorId(ve.getVendorId());
			v.setFirstName(ve.getFirstName());
			v.setLastName(ve.getLastName());
			v.setPhoneNumber(ve.getPhoneNumber());
			v.setSecretPin(ve.getSecretPin());
			v.setPassword(ve.getPassword());
			v.setAddTimestamp(ve.getAddTimestamp());
			v.setSecretPinLastUpdateTimestamp(ve.getSecretPinLastUpdateTimestamp());
			v.setPasswordLastUpdateTimestamp(ve.getPasswordLastUpdateTimestamp());
			v.setAccountStatus(ve.getAccountStatus());
			v.setIsAccountClosed(ve.getIsAccountClosed());
				
			return v;
		}
	}
	
	@Override
	public Shop getShop(Integer vendorId) throws Exception {
		
		// checking whether the required vendor id is valid or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if not valid, return null
		if(ve == null) {
			return null;
		}
		
		// else, return object of shop
		else {
			
			// getting the values of shop entity from vendor entity
			ShopEntity se = ve.getShop();
			
			// creating object of shop.
			Shop s = new Shop();
			
			// populating the shop object with data from shop entity.
			s.setShopId(se.getShopId());
			s.setName(se.getName());
			s.setOfficialPhoneNumber(se.getOfficialPhoneNumber());
			s.setOfficialEmailId(se.getOfficialEmailId());
			s.setShopPicture(se.getShopPicture());
			s.setNumberOfItems(se.getNumberOfItems());
			s.setAveragePrice(se.getAveragePrice());
			s.setShopRating(se.getShopRating());
			s.setAddTimestamp(se.getAddTimestamp());
			s.setLastUpdateTimestamp(se.getLastUpdateTimestamp());
			s.setStatus(se.getStatus());
			
			// address values from shop entity having one to one mapping with address entity.
			AddressEntity ae = se.getAddress();
			Address a = new Address();
			a.setAddressId(ae.getAddressId());
			a.setStreet(ae.getStreet());
			a.setCity(ae.getCity());
			a.setState(ae.getState());
			a.setCountry(ae.getCountry());
			a.setZipCode(ae.getZipCode());
			a.setGeoLat(ae.getGeoLat());
			a.setGeoLang(ae.getGeoLang());
			s.setAddress(a);
			
			// kyc values from shop entity having one to one mapping with kyc entity
			KycEntity ke = se.getKyc();
			if(ke == null) {
				s.setKyc(null);
			}
			else {
				Kyc k = new Kyc();
				k.setKycId(ke.getKycId());
				k.setGstNumber(ke.getGstNumber());
				k.setKycIssueTimestamp(ke.getKycIssueTimestamp());
				s.setKyc(k);
			}
			
			// items list from shop entity having one to many relationship with item entity.
			List<ItemEntity> ieList = se.getItems();
			List<Item> iList = new ArrayList<Item>();
			for(ItemEntity ie : ieList) {
				Item i = new Item();
				i.setItemId(ie.getItemId());
				i.setName(ie.getName());
				i.setDescription(ie.getDescription());
				i.setPrice(ie.getPrice());
				i.setFoodType(ie.getFoodType());
				i.setPicture(ie.getPicture());
				i.setItemRating(ie.getItemRating());
				i.setAddTimestamp(ie.getAddTimestamp());
				i.setLastUpdateTimestamp(ie.getLastUpdateTimestamp());
				iList.add(i);
			}
			s.setItems(iList);
			
			return s;
		}
	}

	@Override
	public List<Item> getItems(Integer vendorId) throws Exception {
		
		// checking whether the required vendor id exists or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// creating object of new arraylist of type item.
		List<Item> iList = new ArrayList<Item>();
		
		// if vendor id does not exists, return empty list.
		if(ve == null) {
			return iList;
		}
		
		// else, return a list populated with item data.
		else {
			ShopEntity se = ve.getShop();
			List<ItemEntity> ieList = se.getItems();
			for(ItemEntity ie : ieList) {
				Item i = new Item();
				i.setItemId(ie.getItemId());
				i.setName(ie.getName());
				i.setName(ie.getName());
				i.setDescription(ie.getDescription());
				i.setPrice(ie.getPrice());
				i.setFoodType(ie.getFoodType());
				i.setPicture(ie.getPicture());
				i.setItemRating(ie.getItemRating());
				i.setAddTimestamp(ie.getAddTimestamp());
				i.setLastUpdateTimestamp(ie.getLastUpdateTimestamp());
				
				iList.add(i);
			}
			return iList;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer updateVendorPhoneNumber(Integer vendorId, Long phoneNumber) throws Exception {
		
		// checking whether the request parameter value vendor id is valid or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if vendor id does not exists, it will return -1
		if(ve == null) {
			return -1;
		}
		
		// else, it will check whether the passed phoneNumber already exists or not because it is a unique attribute.
		else {
			
			// Query for checking whether exists or not.
			Query query = entityManager.createQuery("SELECT ve FROM VendorEntity ve WHERE ve.phoneNumber=?1");
			query.setParameter(1, phoneNumber);
			List<VendorEntity> veList = query.getResultList();
			
			// if does not exists, it will update the existing phone number to the new number
			if(veList.isEmpty()) {
				ve.setPhoneNumber(phoneNumber);
				return 1;
			}
			
			// else return -2 which says, duplicate key error from database
			else {
				return -2;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer updateShopContactInfo(Integer vendorId, String emailId, Long phoneNumber) throws Exception {
		
		// checking whether the request parameter value vendor id is valid or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if vendor id does not exists, it will return -1
		if(ve == null) {
			return -1;
		}
		
		// else, it will check whether the entered email id or phone number already exists or not.
		else {
			
			// Query for checking the information
			Query query = entityManager.createQuery("SELECT se FROM ShopEntity se WHERE se.officialEmailId=?1 OR se.officialPhoneNumber=?2");
			query.setParameter(1, emailId);
			query.setParameter(2, phoneNumber);
			List<ShopEntity> seList = query.getResultList();
			
			// if does not exists, it will update the existing phone number and email to the new ones.
			if(seList.isEmpty()) {
				ShopEntity se = ve.getShop();
				se.setOfficialEmailId(emailId);
				se.setOfficialPhoneNumber(phoneNumber);
				return 1;
			}
			
			// else, it will return -2 error which symbolizes the sql duplicate error.
			else {
				return -2;
			}
		}
	}

	@Override
	public Integer updateShopAddress(Integer vendorId, Address address) throws Exception {
		
		// checking whether the request parameter value vendor id is valid or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if vendor id does not exists, it will return -1
		if(ve == null) {
			return -1;
		}
		
		// else, it will update the existing address to the new address
		else {
			ShopEntity se = ve.getShop();
			AddressEntity ae = se.getAddress();
			ae.setStreet(address.getStreet());
			ae.setCity(address.getCity());
			ae.setState(address.getState());
			ae.setCountry(address.getCountry());
			ae.setZipCode(address.getZipCode());
			ae.setGeoLat(address.getGeoLat());
			ae.setGeoLang(address.getGeoLang());
			
			return 1;
		}
		
	}

	@Override
	public Integer updateShopPicture(Integer vendorId, MultipartFile pic) throws Exception {
		
		// check whether the vendor id is valid or not
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if vendor id is invalid, return -1
		if(ve == null) {
			return -1;
		}
		
		// else, update shop picture
		else {
			
			// path of the picture stored in FS.
			String folder = "photos/shops/";
			
			// getting bytes of passed image
			byte[] bytes = pic.getBytes();
			
			// performing check on picture bytes.
			// if bytes length is equal to or less than zero, throw an exception of invalid size
			if(bytes.length <= 0) {
				return -2;
			}
			
			// else, update the shop picture path into database.
			else {
				Path path = Paths.get(folder, LocalDateTime.now()+pic.getOriginalFilename());
				Files.write(path, bytes);
				
				ShopEntity se = ve.getShop();
				se.setShopPicture(path.toString());
				return 1;
			}
			
		}
		
	}
	
	@Override
	public Integer closeAccount(Integer vendorId) throws Exception {
		
		// checking whether the request parameter value vendor id is valid or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if vendor id does not exists, it will return -1
		if(ve == null) {
			return -1;
		}
		
		// else, it will update the vendor info and set the suitable attributes.
		else {
			ve.setIsAccountClosed(true);
			ve.setAccountStatus(false);
			return 1;
		}
	}

	@Override
	public Integer deleteItem(Integer itemId) throws Exception {
		
		// checking whether the request parameter value vendor id is valid or not.
		ItemEntity ie = entityManager.find(ItemEntity.class, itemId);
		
		// if vendor id does not exists, it will return -1
		if(ie == null) {
			return -1;
		}
		
		// else, it will delete the passed item.
		else {
			entityManager.remove(ie);
			return 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer verifyPhoneNumberAndSendOTP(Long phoneNumber) throws Exception {
		
		// checking whether the phone number exists or not
		String strQuery = "SELECT ve FROM VendorEntity ve WHERE ve.phoneNumber=?1";
		Query query = entityManager.createQuery(strQuery);
		query.setParameter(1, phoneNumber);
		List<VendorEntity> veList = query.getResultList();
		
		// if not exists, return -1
		if(veList.isEmpty()) {
			return -1;
		}
		
		// else, send an otp
		else {
			Integer otp = AuthenticationManager.generateOTP(8);
			
			// store otp into database
			OtpEntity oe = new OtpEntity();
			oe.setOtp(otp);
			oe.setAddTimestamp(LocalDateTime.now());
			oe.setExpiryTimestamp(LocalDateTime.now().plusMinutes(15));
			oe.setUseTimestamp(null);
			oe.setGeneratedFor(phoneNumber);
			oe.setStatus(false);
			entityManager.persist(oe);
			
			return otp;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer verifyOTP(Integer otp) throws Exception {
		
		// check whether the otp is valid or not
		String strQuery = "SELECT oe FROM OtpEntity oe WHERE oe.otp=?1";
		Query query = entityManager.createQuery(strQuery);
		query.setParameter(1, otp);
		List<OtpEntity> oeList = query.getResultList();
		
		// if not found, return -1
		if(oeList.isEmpty()) {
			return -1;
		}
		
		// else, perform other checks
		else {
			
			// create otp object and populate it with required information
			Otp o = new Otp();
			for(OtpEntity oe : oeList) {
				o.setOtpId(oe.getOtp());
				o.setOtp(oe.getOtp());
				o.setAddTimestamp(oe.getAddTimestamp());
				o.setExpiryTimestamp(oe.getExpiryTimestamp());
				o.setUseTimestamp(oe.getUseTimestamp());
				o.setGeneratedFor(oe.getGeneratedFor());
				o.setStatus(oe.getStatus());
			}
			
			// if otp is expiry time is already passed, return -2
			if(o.getExpiryTimestamp().isBefore(LocalDateTime.now())) {
				return -2;
			}
			
			// else, if otp is already used, return -3
			else if(o.getStatus() == true) {
				return -3;
			}
			
			// else, verify otp and update changes
			else {
				
				String strQuery1 = "UPDATE OtpEntity oe SET oe.status=true, oe.useTimestamp=?1";
				Query query1 = entityManager.createQuery(strQuery1);
				query1.setParameter(1, LocalDateTime.now());
				query1.executeUpdate();
				
				return 1;
			}
		}
	}
	
	@Override
	public Integer updatePassword(Integer vendorId, String password) throws Exception {

		// checking whether the requested user id exist or not.
		VendorEntity ve = entityManager.find(VendorEntity.class, vendorId);
		
		// if not exist, return -1
		if(ve == null) {
			return -1;
		}
		
		// else, update old password with new.
		else {
			ve.setPassword(AuthenticationManager.encryptPassword(password));
			return 1;
		}
		
	}
	
}
