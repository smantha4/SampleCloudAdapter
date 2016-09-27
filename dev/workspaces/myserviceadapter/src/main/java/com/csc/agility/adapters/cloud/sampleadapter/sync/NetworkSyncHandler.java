package com.csc.agility.adapters.cloud.sampleadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.NetworkSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.NetworkSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class NetworkSyncHandler implements ISync<NetworkSyncRequest, NetworkSyncResponse> {

	public ICancellable sync(NetworkSyncRequest arg0, ResponseHandler<NetworkSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

};
