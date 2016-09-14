package com.servicemesh.myserviceadapter.operations;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.servicemesh.agility.sdk.cloud.msgs.InstanceCreateSnapshotRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceRemoveAllSnapshotRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceRemoveSnapshotRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceRevertSnapshotRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceSnapshotResponse;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceUpdateSnapshotRequest;
import com.servicemesh.agility.sdk.cloud.spi.ICancellable;
import com.servicemesh.agility.sdk.cloud.spi.ISnapshot;
import com.servicemesh.core.async.ResponseHandler;

@Component
public class InstanceSnapshotOperations implements ISnapshot {
	private static final Logger logger = Logger.getLogger(InstanceSnapshotOperations.class);

	public ICancellable createSnapshot(InstanceCreateSnapshotRequest arg0,
			ResponseHandler<InstanceSnapshotResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable removeAllSnapshots(InstanceRemoveAllSnapshotRequest arg0,
			ResponseHandler<InstanceSnapshotResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable removeSnapshot(InstanceRemoveSnapshotRequest arg0,
			ResponseHandler<InstanceSnapshotResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable revertSnapshot(InstanceRevertSnapshotRequest arg0,
			ResponseHandler<InstanceSnapshotResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICancellable updateSnapshot(InstanceUpdateSnapshotRequest arg0,
			ResponseHandler<InstanceSnapshotResponse> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
