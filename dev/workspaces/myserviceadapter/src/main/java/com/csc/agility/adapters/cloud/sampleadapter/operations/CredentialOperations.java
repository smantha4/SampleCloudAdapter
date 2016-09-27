package com.csc.agility.adapters.cloud.sampleadapter.operations;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.CredentialCreateRequest;
import com.servicemesh.agility.sdk.cloud.msgs.CredentialDeleteRequest;
import com.servicemesh.agility.sdk.cloud.msgs.CredentialResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ICredential;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class CredentialOperations implements ICredential {
	private static final Logger logger = Logger.getLogger(CredentialOperations.class);

	public CredentialOperations() {
	}

	public ICancellable create(CredentialCreateRequest arg0, ResponseHandler<CredentialResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable delete(CredentialDeleteRequest arg0, ResponseHandler<CredentialResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
