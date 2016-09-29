package com.csc.agility.adapters.cloud.sampleadapter;

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

import com.csc.agility.adapters.cloud.sampleadapter.operations.CredentialOperations;
import com.csc.agility.adapters.cloud.sampleadapter.operations.ImageOperations;
import com.csc.agility.adapters.cloud.sampleadapter.operations.InstanceOperations;
import com.csc.agility.adapters.cloud.sampleadapter.operations.InstanceSnapshotOperations;
import com.csc.agility.adapters.cloud.sampleadapter.operations.OperationsFactory;
import com.csc.agility.adapters.cloud.sampleadapter.operations.StorageOperations;
import com.csc.agility.adapters.cloud.sampleadapter.operations.StorageSnapshotOperations;
import com.csc.agility.adapters.cloud.sampleadapter.sync.AddressRangeSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.CloudSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.CredentialSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.ImageSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.InstanceSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.LocationSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.ModelSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.NetworkSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.RepositorySyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.StorageSyncHandler;
import com.csc.agility.adapters.cloud.sampleadapter.sync.SyncHandlerFactory;

/**
 * Spring config file which defines the spring definitions
 * 
 * @author srirammantha
 *
 * Note: Could not use component scan as spring and osgi do not go together for classpath scanning 
 */
@Configuration
public class AppConfig {
	
	
	//Define all the beans that can be auto wired as dependencies
	
	@Bean
	public SyncHandlerFactory getSyncHandlerFactory(){
		return new SyncHandlerFactory();
	}
	
	@Bean
	public OperationsFactory getOperationsFactory(){
		return new OperationsFactory();
	}
	
	@Bean
	public AdapterConfiguration getMyAdapterConfiguration(){
		return new AdapterConfiguration();
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
	public AdapterRegistrationProvider getMyAdapterRegistrationProvider(){
		return new AdapterRegistrationProvider();
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
