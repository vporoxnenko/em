package com.nsisc.emconnector.core.web.gwt.server;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.nsisc.emconnector.core.exceptions.ApplicationException;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.model.AbstractPersistentObject;

public class ModelConverterUtil {

	public static <P extends AbstractPersistentObject<? extends Serializable>, D extends DefaultClientDataModel<? extends Serializable>> D convertToClientDataModel(
			Class<D> dstClass, P object, boolean ignoreMissedMethods) {
		if (object != null) {
			try {
				D dataModel = dstClass.newInstance();

				for (Method m : object.getClass().getMethods()) {
					String methodName = m.getName();
					if (methodName.startsWith("get") && !methodName.equals("getClass")) {
						StringBuilder property = new StringBuilder(methodName.substring(3, 4).toLowerCase())
						.append(methodName.substring(4, methodName.length()));
						try {
							//check that same method name exists for Client Data Model
							dataModel.getClass().getMethod(methodName);
							
							dataModel.set(property.toString(), m.invoke(object));
						} catch (Exception e) {
							if (!ignoreMissedMethods)
								throw new ApplicationException(e);
						}
					}
				}
				return dataModel;
			} catch (Exception e) {
				throw new ApplicationException(e);
			}
		}
		return null;
	}

	public static <P extends AbstractPersistentObject<? extends Serializable>, D extends DefaultClientDataModel<? extends Serializable>> P convertToPersistenceObject(
			Class<P> dstClass, P base, D dataModel, boolean ignoreMissedMethods) {
		if (dataModel != null) {
			try {
				P object = base == null ? dstClass.newInstance() : base;
				for (String property : dataModel.getPropertyNames()) {
					StringBuilder methodName = new StringBuilder("set").append(
							property.substring(0, 1).toUpperCase()).append(
							property.substring(1, property.length()));
					Method m;
					try {
						m = object.getClass().getMethod(methodName.toString(),
								dataModel.get(property).getClass());
						m.invoke(object,
								new Object[] { dataModel.get(property) });
					} catch (Exception e) {
						if (!ignoreMissedMethods)
							throw new ApplicationException(e);
					}
				}
				return object;
			} catch (Exception e) {
				throw new ApplicationException(e);
			}
		}
		return null;
	}
}
