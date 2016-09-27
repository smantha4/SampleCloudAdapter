package com.csc.agility.adapters.cloud.sampleadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.InstanceSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class InstanceSyncHandler implements ISync<InstanceSyncRequest, InstanceSyncResponse> {

	public ICancellable sync(InstanceSyncRequest arg0, ResponseHandler<InstanceSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
