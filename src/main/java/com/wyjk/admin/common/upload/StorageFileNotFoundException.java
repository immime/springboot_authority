package com.wyjk.admin.common.upload;


public class StorageFileNotFoundException extends StorageException {
	private static final long serialVersionUID = 8726487869558282692L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}