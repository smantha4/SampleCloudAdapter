package com.servicemesh.myserviceadapter.operations;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.StorageCreateFromSnapshotRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageResponse;
import com.servicemesh.agility.sdk.cloud.msgs.StorageSnapshotCreateRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageSnapshotDeleteRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageSnapshotResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.IStorageSnapshot;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class StorageSnapshotOperations implements IStorageSnapshot {
	private static final Logger logger = Logger.getLogger(StorageSnapshotOperations.class);

	public ICancellable create(StorageSnapshotCreateRequest arg0, ResponseHandler<StorageSnapshotResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable createFromSnapshot(StorageCreateFromSnapshotRequest arg0,
			ResponseHandler<StorageResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable delete(StorageSnapshotDeleteRequest arg0, ResponseHandler<StorageSnapshotResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
