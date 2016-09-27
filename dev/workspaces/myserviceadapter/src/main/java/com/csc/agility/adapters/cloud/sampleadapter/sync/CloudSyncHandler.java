package com.csc.agility.adapters.cloud.sampleadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.CloudSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.CloudSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class CloudSyncHandler implements ISync<CloudSyncRequest, CloudSyncResponse> {

	public ICancellable sync(CloudSyncRequest arg0, ResponseHandler<CloudSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
