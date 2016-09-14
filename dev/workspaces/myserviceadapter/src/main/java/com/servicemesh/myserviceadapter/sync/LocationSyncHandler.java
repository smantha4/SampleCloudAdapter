package com.servicemesh.myserviceadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.LocationSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.LocationSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class LocationSyncHandler implements ISync<LocationSyncRequest, LocationSyncResponse> {

	public ICancellable sync(LocationSyncRequest arg0, ResponseHandler<LocationSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

};
