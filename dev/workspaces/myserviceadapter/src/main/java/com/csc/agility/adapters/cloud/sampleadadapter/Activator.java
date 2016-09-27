package com.csc.agility.adapters.cloud.sampleadadapter;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.csc.agility.adapters.cloud.sampleadapter.operations.OperationsFactory;
import com.csc.agility.adapters.cloud.sampleadapter.sync.SyncHandlerFactory;
import com.servicemesh.agility.sdk.cloud.msgs.AddressRangeSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.AddressRangeSyncResponse;
import com.servicemesh.agility.sdk.cloud.msgs.CloudSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.CloudSyncResponse;
import com.servicemesh.agility.sdk.cloud.msgs.CredentialSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.CredentialSyncResponse;
import com.servicemesh.agility.sdk.cloud.msgs.ImageSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.ImageSyncResponse;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.InstanceSyncResponse;
import com.servicemesh.agility.sdk.cloud.msgs.LocationSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.LocationSyncResponse;
import com.servicemesh.agility.sdk.cloud.msgs.ModelSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.ModelSyncResponse;
import com.servicemesh.agility.sdk.cloud.msgs.NetworkSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.NetworkSyncResponse;
import com.servicemesh.agility.sdk.cloud.msgs.RegistrationRequest;
import com.servicemesh.agility.sdk.cloud.msgs.RegistrationResponse;
import com.servicemesh.agility.sdk.cloud.msgs.StorageSyncRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageSyncResponse;
import com.servicemesh.agility.sdk.cloud.spi.CloudAdapter;
import com.servicemesh.agility.sdk.cloud.spi.ICredential;
import com.servicemesh.agility.sdk.cloud.spi.IImage;
import com.servicemesh.agility.sdk.cloud.spi.IInstance;
import com.servicemesh.agility.sdk.cloud.spi.IStorage;
import com.servicemesh.agility.sdk.cloud.spi.ISync;
import com.servicemesh.core.async.AsyncService;
import com.servicemesh.core.reactor.TimerReactor;

/**
 * 
 * Adapter definition class
 */
public class Activator extends CloudAdapter {

	private OperationsFactory operationsFactory;

	private SyncHandlerFactory syncHandlerFactory;

	private AnnotationConfigApplicationContext ctx;

	private AdapterConfiguration myAdapterConfiguration;

	private static final Logger logger = Logger.getLogger(Activator.class);

	public Activator() {
		super(TimerReactor.getTimerReactor("MyAdapter"));
		/* Init the spring beans */
		initSpringBeans();
		logger.info("<Template> adapter started");
	}

	public Activator(AsyncService service) {
		super(service);

		logger.info("Initializing spring beans");
		initSpringBeans();
		logger.info("Initialized MyAdapter");

	}

	/*
	 * Init the spring context
	 */
	private void initSpringBeans() {
		ctx = new AnnotationConfigApplicationContext();
		ctx.setClassLoader(this.getClass().getClassLoader());
		ctx.register(AppConfig.class);
		ctx.refresh();

		syncHandlerFactory = ctx.getBean(SyncHandlerFactory.class);
		operationsFactory = ctx.getBean(OperationsFactory.class);
		myAdapterConfiguration = ctx.getBean(AdapterConfiguration.class);
	}

	@Override
	public String getAdapterName() {
		return myAdapterConfiguration.getAdapterName();
	}

	@Override
	public String getAdapterVersion() {
		return myAdapterConfiguration.getAdapterVersion();
	}

	@Override
	public ISync<AddressRangeSyncRequest, AddressRangeSyncResponse> getAddressRangeSync() {
		return syncHandlerFactory.getAddressRangeSyncHandler();
	}

	@Override
	public ISync<CloudSyncRequest, CloudSyncResponse> getCloudSync() {
		return syncHandlerFactory.getCloudSyncHandler();
	}

	@Override
	public String getCloudType() {
		return myAdapterConfiguration.getCloudType();
	}

	@Override
	public ICredential getCredentialOperations() {
		return operationsFactory.getCredentialOperations();
	}

	@Override
	public ISync<CredentialSyncRequest, CredentialSyncResponse> getCredentialSync() {
		return syncHandlerFactory.getCredentialSyncHandler();
	}

	@Override
	public IImage getImageOperations() {
		return operationsFactory.getImageOperations();
	}

	@Override
	public ISync<ImageSyncRequest, ImageSyncResponse> getImageSync() {
		return syncHandlerFactory.getImageSyncHandler();
	}

	@Override
	public IInstance getInstanceOperations() {
		return operationsFactory.getInstanceOperations();
	}

	@Override
	public ISync<InstanceSyncRequest, InstanceSyncResponse> getInstanceSync() {
		return syncHandlerFactory.getInstanceSyncHandler();
	}

	@Override
	public ISync<LocationSyncRequest, LocationSyncResponse> getLocationSync() {
		return syncHandlerFactory.getLocationSyncHandler();
	}

	@Override
	public ISync<ModelSyncRequest, ModelSyncResponse> getModelSync() {
		return syncHandlerFactory.getModelSyncHandler();
	}

	@Override
	public ISync<NetworkSyncRequest, NetworkSyncResponse> getNetworkSync() {
		return syncHandlerFactory.getNetworkSyncHandler();
	}

	@Override
	public RegistrationRequest getRegistrationRequest() {
		AdapterRegistrationProvider myAdapterRegistrationProvider = ctx.getBean(AdapterRegistrationProvider.class);
		return myAdapterRegistrationProvider.getRegistrationRequest();
	}

	@Override
	public IStorage getStorageOperations() {
		return operationsFactory.getStorageOperations();
	}

	@Override
	public ISync<StorageSyncRequest, StorageSyncResponse> getStorageSync() {
		return syncHandlerFactory.getStorageSyncHandler();
	}

	@Override
	public void onRegistration(RegistrationResponse registrationResponse) {

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		ctx.destroy();
		super.stop(context);
	}
}
