package com.campusx.srv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.campusx.dao.VendorDAO;
import com.campusx.mdl.Address;
import com.campusx.mdl.Item;
import com.campusx.mdl.Kyc;
import com.campusx.mdl.Shop;
import com.campusx.mdl.Vendor;
import com.campusx.vdr.Validator;

@Service(value="vendorService")
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorDAO vendorDAO;
	
	@Override
	public Vendor registerVendor(Vendor vendor) throws Exception {
		if(Validator.validatePassword(vendor.getPassword())) {
			if(Validator.validateNumber(vendor.getPhoneNumber())) {
				return vendorDAO.registerVendor(vendor);
			}
			else {
				throw new Exception("Service.INVALID_PHONE_NUMBER_FORMAT");
			}
		}
		else {
			throw new Exception("Service.INVALID_PASSWORD_FORMAT");
		}
	}
	
	@Override
	public Vendor loginVendor(Long phoneNumber, String password) throws Exception {
		Vendor v = vendorDAO.loginVendor(phoneNumber, password);
		if(v == null) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			return v;
		}
	}

	@Override
	public Integer addShop(Integer vendorId, Shop shop) throws Exception {
		Integer _statusCode = vendorDAO.addShop(vendorId, shop);
		if(_statusCode == -1) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			String emailId = shop.getOfficialEmailId();
			if(emailId == null) {
				if(Validator.validateNumber(shop.getOfficialPhoneNumber())) {
					return _statusCode;
				}
				else {
					throw new Exception("Service.INVALID_PHONE_NUMBER_FORMAT");
				}
			}
			else {
				if(Validator.validateEmail(shop.getOfficialEmailId())) {
					if(Validator.validateNumber(shop.getOfficialPhoneNumber())) {
						return _statusCode;
					}
					else {
						throw new Exception("Service.INVALID_PHONE_NUMBER_FORMAT");
					}
				}
				else {
					throw new Exception("Service.INVALID_EMAIL_FORMAT");
				}
			}
		}
	}

	@Override
	public Integer completeKYC(Integer shopId, Kyc kyc) throws Exception {
		Integer _statusCode = vendorDAO.completeKYC(shopId, kyc);
		if(_statusCode == -1) {
			throw new Exception("Service.SHOP_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}
	
	@Override
	public Integer addNewItem(Integer vendorId, Item item) throws Exception {
		Integer _statusCode = vendorDAO.addNewItem(vendorId, item);
		if(_statusCode == -1) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Vendor getProfile(Integer vendorId) throws Exception {
		Vendor vendor = vendorDAO.getProfile(vendorId);
		if(vendor == null) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			return vendor;
		}
	}

	@Override
	public Shop getShop(Integer vendorId) throws Exception {
		Shop shop = vendorDAO.getShop(vendorId);
		if(shop == null) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			return shop;
		}
	}

	@Override
	public List<Item> getItems(Integer vendorId) throws Exception {
		List<Item> itemList = vendorDAO.getItems(vendorId);
		if(itemList.isEmpty()) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			return itemList;
		}
	}

	@Override
	public Integer updateVendorPhoneNumber(Integer vendorId, Long phoneNumber) throws Exception {
		Integer _statusCode = vendorDAO.updateVendorPhoneNumber(vendorId, phoneNumber);
		if(_statusCode == -1) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else if(_statusCode == -2) {
			throw new Exception("Service.VENDOR_PHONE_ALREADY_EXISTS");
		}
		else {
			if(Validator.validateNumber(phoneNumber)) {
				return _statusCode;
			}
			else {
				throw new Exception("Service.INVALID_PHONE_NUMBER_FORMAT");
			}
		}
	}

	@Override
	public Integer updateShopContactPhoneNumber(Integer vendorId, Long phoneNumber) throws Exception {
		if(Validator.validateNumber(phoneNumber)) {
			Integer _statusCode = vendorDAO.updateShopContactPhoneNumber(vendorId, phoneNumber);
			if(_statusCode == -1) {
				throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
			}
			else if(_statusCode == -2) {
				throw new Exception("Service.SHOP_PHONE_ALREADY_EXISTS");
			}
			else {
				return _statusCode;
			}
		}
		else {
			throw new Exception("Service.INVALID_PHONE_NUMBER_FORMAT");
		}
	}

	@Override
	public Integer updateShopContactEmail(Integer vendorId, String emailId) throws Exception {
		if(Validator.validateEmail(emailId)) {
			Integer _statusCode = vendorDAO.updateShopContactEmail(vendorId, emailId);
			if(_statusCode == -1) {
				throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
			}
			else if(_statusCode == -2) {
				throw new Exception("Service.SHOP_EMAIL_ALREADY_EXISTS");
			}
			else {
				return _statusCode;
			}
		}
		else {
			throw new Exception("Service.INVALID_EMAIL_FORMAT");
		}
	}

	@Override
	public Integer updateShopAddress(Integer vendorId, Address address) throws Exception {
		Integer _statusCode = vendorDAO.updateShopAddress(vendorId, address);
		if(_statusCode == -1) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Integer updateShopPicture(Integer vendorId, MultipartFile pic) throws Exception {
		Integer _statusCode = vendorDAO.updateShopPicture(vendorId, pic);
		if(_statusCode == -1) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else if(_statusCode == -2) {
			throw new Exception("Service.INVALID_FILE_SIZE");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Integer closeAccount(Integer vendorId) throws Exception {
		Integer _statusCode = vendorDAO.closeAccount(vendorId);
		if(_statusCode == -1) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Integer deleteItem(Integer itemId) throws Exception {
		Integer _statusCode = vendorDAO.deleteItem(itemId);
		if(_statusCode == -1) {
			throw new Exception("Service.ITEM_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Integer verifyPhoneNumberAndSendOTP(Long phoneNumber) throws Exception {
		Integer _statusCode = vendorDAO.verifyPhoneNumberAndSendOTP(phoneNumber);
		if(_statusCode == -1) {
			throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Integer verifyOTP(Integer otp) throws Exception {
		Integer _statusCode = vendorDAO.verifyOTP(otp);
		if(_statusCode == -1) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else if(_statusCode == -2) {
			throw new Exception("Service.OTP_EXPIRED");
		}
		else if(_statusCode == -3) {
			throw new Exception("Service.INVALID_OTP");
		}
		else {
			return _statusCode;
		}
	}
	
	@Override
	public Integer updatePassword(Integer vendorId, String password) throws Exception {
		Integer _statusCode = vendorDAO.updatePassword(vendorId, password);
		if(Validator.validatePassword(password)) {
			if(_statusCode == -1) {
				throw new Exception("Service.VENDOR_DOES_NOT_EXISTS");
			}
			else {
				return _statusCode;
			}
		}
		else {
			throw new Exception("Service.INVALID_PASSWORD_FORMAT");
		}
	}

}
