package com.boxinator.project.services;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.boxinator.models.Shipments;
import com.boxinator.repository.ShipmentRepository;

@Service
public class ShipmentService {

	
	@Autowired
	ShipmentRepository shipmentRepo;
	
	public ResponseEntity<List<Shipments>> getAllShipments(){
		List<Shipments> shipmentsList = null;
		HttpStatus httpStatus = null;
		
		try {
			shipmentsList = shipmentRepo.findAll();
			if (shipmentsList.isEmpty()) {
				httpStatus = HttpStatus.NOT_FOUND;
			}else {
				httpStatus = HttpStatus.OK;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(shipmentsList, httpStatus);
	}
	
	public ResponseEntity<Shipments> getShipmentById(Long id){
		HttpStatus httpStatus = null;
		Shipments returnShipment = null;

		try {
			if ((returnShipment = shipmentRepo.getReferenceById(id)) != null) {
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnShipment, httpStatus);
	}
	
	public ResponseEntity<Shipments> createShipment(Shipments newShipment) {
		HttpStatus httpStatus = null;
		Shipments returnShipment = null;
		Long id = newShipment.getBoxId();

		try {
			if (shipmentRepo.existsById(id) == false) {
				returnShipment = shipmentRepo.saveAndFlush(newShipment);
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnShipment, httpStatus);
	}
	
	public ResponseEntity<Shipments> updateShipment(Shipments newShipment, Long id) {
		Shipments returnShipment = null;
		HttpStatus httpStatus = null;
		try {
			if ((returnShipment = shipmentRepo.getReferenceById(id)) != null) {
				newShipment = (Shipments) HelperService.partialUpdate(returnShipment, newShipment);
				returnShipment = shipmentRepo.saveAndFlush(newShipment);
				httpStatus = HttpStatus.OK;
			}else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (BeansException e) {
			System.out.printf("Failed to copy values into user object...\nPrinting message...");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnShipment, httpStatus);
	}
	
	public ResponseEntity<Shipments> deleteShipment(Long id) {
		HttpStatus httpStatus = null;
		Shipments returnShipment = null;

		try {
			if((returnShipment = shipmentRepo.getReferenceById(id)) != null) {
				shipmentRepo.deleteById(id);
				httpStatus = HttpStatus.OK;
			}else {
				httpStatus = HttpStatus.BAD_GATEWAY;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnShipment, httpStatus);
	}
	
}
