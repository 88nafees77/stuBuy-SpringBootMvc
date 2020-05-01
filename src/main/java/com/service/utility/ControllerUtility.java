package com.service.utility;

import org.springframework.stereotype.Service;

import com.service.accountdetails.AddItems;
import com.service.products.Electronics;
import com.service.products.Shirts;
import com.service.products.Sports;
import com.service.products.jewellery;

@Service
public class ControllerUtility {
	int i = 10;

	public void serialized(AddItems addItems, Electronics object) {

		object.setId(i++);
		object.setDiscount(5);
		object.setDiscription(addItems.getDiscription());
		object.setPrice(addItems.getPrice());
		object.setProductName(addItems.getProductName());
		object.setQuantity(10);

	}

	public void serialized(AddItems addItems, Sports object) {

		object.setId(i++);
		object.setDiscount(5);
		object.setDiscription(addItems.getDiscription());
		object.setPrice(addItems.getPrice());
		object.setProductName(addItems.getProductName());
		object.setQuantity(10);

	}

	public void serialized(AddItems addItems, Shirts object) {

		object.setId(i++);
		object.setDiscount(5);
		object.setDiscription(addItems.getDiscription());
		object.setPrice(addItems.getPrice());
		object.setProductName(addItems.getProductName());
		object.setQuantity(10);

	}

	public void serialized(AddItems addItems, jewellery object) {

		object.setId(i++);
		object.setDiscount(5);
		object.setDiscription(addItems.getDiscription());
		object.setPrice(addItems.getPrice());
		object.setProductName(addItems.getProductName());
		object.setQuantity(10);

	}

}
