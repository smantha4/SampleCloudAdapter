package com.servicemesh.myserviceadapter;

import java.io.IOException;
import java.io.InputStream;

import org.apache.felix.framework.BundleWiringImpl.BundleClassLoader;
import org.osgi.framework.Bundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import com.servicemesh.myserviceadapter.operations.CredentialOperations;
import com.servicemesh.myserviceadapter.operations.ImageOperations;
import com.servicemesh.myserviceadapter.operations.InstanceOperations;
import com.servicemesh.myserviceadapter.operations.InstanceSnapshotOperations;
import com.servicemesh.myserviceadapter.operations.OperationsFactory;
import com.servicemesh.myserviceadapter.operations.StorageOperations;
import com.servicemesh.myserviceadapter.operations.StorageSnapshotOperations;
import com.servicemesh.myserviceadapter.sync.AddressRangeSyncHandler;
import com.servicemesh.myserviceadapter.sync.CloudSyncHandler;
import com.servicemesh.myserviceadapter.sync.CredentialSyncHandler;
import com.servicemesh.myserviceadapter.sync.ImageSyncHandler;
import com.servicemesh.myserviceadapter.sync.InstanceSyncHandler;
import com.servicemesh.myserviceadapter.sync.LocationSyncHandler;
import com.servicemesh.myserviceadapter.sync.ModelSyncHandler;
import com.servicemesh.myserviceadapter.sync.NetworkSyncHandler;
import com.servicemesh.myserviceadapter.sync.RepositorySyncHandler;
import com.servicemesh.myserviceadapter.sync.StorageSyncHandler;
import com.servicemesh.myserviceadapter.sync.SyncHandlerFactory;

/**
 * Spring config file which defines the spring definitions
 * 
 * @author srirammantha
 *
 * Note: Could not use component scan as spring and osgi do not go together for classpath scanning 
 */
@Configuration
public class AppConfig {
	
	
	@Bean
	public SyncHandlerFactory getSyncHandlerFactory(){
		return new SyncHandlerFactory();
	}
	
	@Bean
	public OperationsFactory getOperationsFactory(){
		return new OperationsFactory();
	}
	
	@Bean
	public MyAdapterConfiguration getMyAdapterConfiguration(){
		return new MyAdapterConfiguration();
	}
	
	/** Adapter operations **/
	
	@Bean
	public CredentialOperations getCredentialOperations(){
		return new CredentialOperations();
	}
	
	@Bean
	public ImageOperations getImageOperations(){
		return new ImageOperations();
	}
	
	@Bean
	public InstanceOperations getInstanceOperations(){
		return new InstanceOperations();
	}
	
	@Bean
	public InstanceSnapshotOperations getInstanceSnapshotOperations(){
		return new InstanceSnapshotOperations();
	}
	
	@Bean
	public StorageOperations getStorageOperations(){
		return new StorageOperations();
	}
	
	@Bean
	public StorageSnapshotOperations getStorageSnapshotOperations(){
		return new StorageSnapshotOperations();
	}
	
	/* sync handlers */
	
	@Bean
	public AddressRangeSyncHandler getAddressRangeSyncHandler(){
		return new AddressRangeSyncHandler();
	}
	
	@Bean
	public CloudSyncHandler getCloudSyncHandler(){
		return new CloudSyncHandler();
	}
	
	@Bean
	public CredentialSyncHandler getCredentialSyncHandler(){
		return new CredentialSyncHandler();
	}
	
	@Bean
	public ImageSyncHandler getImageSyncHandler(){
		return new ImageSyncHandler();
	}
	
	@Bean
	public InstanceSyncHandler getInstanceSyncHandler(){
		return new InstanceSyncHandler();
	}
	
	@Bean
	public LocationSyncHandler getLocationSyncHandler(){
		return new LocationSyncHandler();
	}
	
	@Bean
	public ModelSyncHandler getModelSyncHandler(){
		return new ModelSyncHandler();
	}
	
	@Bean
	public NetworkSyncHandler getNetworkSyncHandler(){
		return new NetworkSyncHandler();
	}
	
	@Bean
	public RepositorySyncHandler getRepositorySyncHandler(){
		return new RepositorySyncHandler();
	}
	
	@Bean
	public StorageSyncHandler getStorageSyncHandler(){
		return new StorageSyncHandler();
	}
	
	@Bean
	public MyAdapterRegistrationProvider getMyAdapterRegistrationProvider(){
		return new MyAdapterRegistrationProvider();
	}
	
	/**
	 * Read the properties into the classpath
	 * @return
	 * @throws IOException
	 */
	@Bean
	public PropertyPlaceholderConfigurer propertyConfigurer() throws IOException {
		
		InputStream ip = this.getClass().getClassLoader().getResourceAsStream("adapter.properties");
		
	    PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
	    props.setLocations(new Resource[] {
	    		new  InputStreamResource(ip)});
	    return props;
	}

}
