package com.nsisc.emconnector.core.services.support;


import javax.naming.InitialContext;

import com.nsisc.emconnector.core.exceptions.ServiceLocateException;
import com.nsisc.emconnector.core.services.ActionService;



/**
 * Service locator
 * 
 * @author Valentin A. Poroxnenko
 */
public final class ServiceLocator<T extends ActionService> {

	@SuppressWarnings("unchecked")
	public static <T extends ActionService> T locate(String serviceName) {
		try {
			return (T) new InitialContext().lookup(serviceName );
		} catch (Exception e) {
			throw new ServiceLocateException(
					"Couldn't locate service " + serviceName); //$NON-NLS-1$
		}
	}
}
