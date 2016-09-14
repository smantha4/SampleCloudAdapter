package com.servicemesh.myserviceadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.CredentialSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.CredentialSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class CredentialSyncHandler implements ISync<CredentialSyncRequest, CredentialSyncResponse> {

	public ICancellable sync(CredentialSyncRequest arg0, ResponseHandler<CredentialSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
