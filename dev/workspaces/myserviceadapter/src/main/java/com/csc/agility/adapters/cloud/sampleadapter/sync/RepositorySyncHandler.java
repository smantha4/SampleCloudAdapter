package com.csc.agility.adapters.cloud.sampleadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.RepositorySyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.RepositorySyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class RepositorySyncHandler implements ISync<RepositorySyncRequest, RepositorySyncResponse> {

	public ICancellable sync(RepositorySyncRequest arg0, ResponseHandler<RepositorySyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
