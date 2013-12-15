package com.galaxyws.aircraftdemo.util;

import java.util.Collection;

public final class CollectionUtil {

	public static final boolean isEmpty(Collection<?> collection){
		return null == collection || 0 == collection.size();
	}
	
}
