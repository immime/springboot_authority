package com.wyjk.admin.common.upload;


public class StorageException extends RuntimeException {
	private static final long serialVersionUID = 609385002386348062L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
