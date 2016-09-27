package com.csc.agility.adapters.cloud.sampleadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.StorageSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class StorageSyncHandler implements ISync<StorageSyncRequest, StorageSyncResponse> {

	public ICancellable sync(StorageSyncRequest arg0, ResponseHandler<StorageSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
