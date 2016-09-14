package com.servicemesh.myserviceadapter.operations;

import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.InstanceBootRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceCreateRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceRebootRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceReleaseRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceResponse;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceStartRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceStopRequest;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.IInstance;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class InstanceOperations implements IInstance {

	public ICancellable boot(InstanceBootRequest arg0, ResponseHandler<InstanceResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable create(InstanceCreateRequest arg0, ResponseHandler<InstanceResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable reboot(InstanceRebootRequest arg0, ResponseHandler<InstanceResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable release(InstanceReleaseRequest arg0, ResponseHandler<InstanceResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable start(InstanceStartRequest arg0, ResponseHandler<InstanceResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable stop(InstanceStopRequest arg0, ResponseHandler<InstanceResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

};
