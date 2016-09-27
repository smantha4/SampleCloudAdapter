package com.csc.agility.adapters.cloud.sampleadapter.operations;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.StorageAttachRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageCreateRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageDeleteRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageDetachRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.IStorage;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class StorageOperations implements IStorage {
	private static final Logger logger = Logger.getLogger(StorageOperations.class);

	public ICancellable attach(StorageAttachRequest arg0, ResponseHandler<StorageResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable create(StorageCreateRequest arg0, ResponseHandler<StorageResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable delete(StorageDeleteRequest arg0, ResponseHandler<StorageResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable detach(StorageDetachRequest arg0, ResponseHandler<StorageResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
