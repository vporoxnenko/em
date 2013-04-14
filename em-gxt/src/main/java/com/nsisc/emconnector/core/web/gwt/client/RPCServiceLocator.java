package com.nsisc.emconnector.core.web.gwt.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;



/**
 * 
 * @author Valentin A. Poroxnenko
 */
public class RPCServiceLocator {

	public static <K extends Serializable,D extends DefaultClientDataModel<K>,F extends FilterData, A extends CoreUIServiceAsync<K, D, F>> A locate(A asyncService, String serviceName) {
		ServiceDefTarget target = (ServiceDefTarget) asyncService;
		target.setServiceEntryPoint(serviceName);
		return asyncService;
	}
}
