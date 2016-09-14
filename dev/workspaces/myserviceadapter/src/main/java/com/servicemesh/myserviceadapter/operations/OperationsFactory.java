package com.servicemesh.myserviceadapter.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationsFactory {

	@Autowired
	private CredentialOperations credentialOperations;

	@Autowired
	private ImageOperations imageOperations;

	@Autowired
	private InstanceOperations instanceOperations;

	@Autowired
	private StorageOperations storageOperations;

	@Autowired
	private StorageSnapshotOperations storageSnapshotOperations;

	public CredentialOperations getCredentialOperations() {
		return credentialOperations;
	}

	public InstanceOperations getInstanceOperations() {
		return instanceOperations;
	}

	public void setInstanceOperations(InstanceOperations instanceOperations) {
		this.instanceOperations = instanceOperations;
	}

	public StorageOperations getStorageOperations() {
		return storageOperations;
	}

	public void setStorageOperations(StorageOperations storageOperations) {
		this.storageOperations = storageOperations;
	}

	public StorageSnapshotOperations getStorageSnapshotOperations() {
		return storageSnapshotOperations;
	}

	public void setStorageSnapshotOperations(StorageSnapshotOperations storageSnapshotOperations) {
		this.storageSnapshotOperations = storageSnapshotOperations;
	}

	public void setCredentialOperations(CredentialOperations credentialOperations) {
		this.credentialOperations = credentialOperations;
	}

	public void setImageOperations(ImageOperations imageOperations) {
		this.imageOperations = imageOperations;
	}

	public ImageOperations getImageOperations() {
		return imageOperations;
	}

}
