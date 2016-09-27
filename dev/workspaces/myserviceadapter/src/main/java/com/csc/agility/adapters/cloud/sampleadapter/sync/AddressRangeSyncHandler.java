package com.csc.agility.adapters.cloud.sampleadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.AddressRangeSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.AddressRangeSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class AddressRangeSyncHandler implements ISync<AddressRangeSyncRequest, AddressRangeSyncResponse> {

	public ICancellable sync(AddressRangeSyncRequest arg0, ResponseHandler<AddressRangeSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
