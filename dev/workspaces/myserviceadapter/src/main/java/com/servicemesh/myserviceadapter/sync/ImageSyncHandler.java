package com.servicemesh.myserviceadapter.sync;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.ImageSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.ImageSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class ImageSyncHandler implements ISync<ImageSyncRequest, ImageSyncResponse> {

	public ICancellable sync(ImageSyncRequest arg0, ResponseHandler<ImageSyncResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

};
