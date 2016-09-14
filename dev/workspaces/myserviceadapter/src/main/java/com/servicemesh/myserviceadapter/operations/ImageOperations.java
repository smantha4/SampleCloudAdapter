package com.servicemesh.myserviceadapter.operations;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.ImageCreateRequest;
import com.servicemesh.agility.sdk.cloud.msgs.ImageDeleteRequest;
import com.servicemesh.agility.sdk.cloud.msgs.ImageResponse;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.IImage;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class ImageOperations implements IImage {
	private static final Logger logger = Logger.getLogger(ImageOperations.class);

	public ICancellable create(ImageCreateRequest arg0, ResponseHandler<ImageResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable delete(ImageDeleteRequest arg0, ResponseHandler<ImageResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
