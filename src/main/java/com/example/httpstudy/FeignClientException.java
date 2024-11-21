package com.example.httpstudy;

public class FeignClientException extends RuntimeException {
	public FeignClientException(String message) {
		super(message);
	}
}
