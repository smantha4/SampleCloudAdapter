package com.servicemesh.myserviceadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.ModelSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.ModelSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class ModelSyncHandler implements ISync<ModelSyncRequest, ModelSyncResponse> {

	public ICancellable sync(ModelSyncRequest arg0, ResponseHandler<ModelSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

};
