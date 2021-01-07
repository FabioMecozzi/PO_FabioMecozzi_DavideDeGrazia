package com.ddgfm.ProgettoOOP.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IdsNotFoundException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public List<Long> collectionOfNotFound = new ArrayList<Long>();

	public IdsNotFoundException(Long idNotFound) {
		super();
		collectionOfNotFound.add(idNotFound);
	}

	public IdsNotFoundException(Collection<Long> idsNotFound) {
		super();
		collectionOfNotFound.addAll(idsNotFound);

	}
}
