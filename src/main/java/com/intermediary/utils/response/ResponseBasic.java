package com.intermediary.utils.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(builderMethodName = "basicBuilder")
public class ResponseBasic<T> implements ContentAndBasicError<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5541901238414814823L;

	private boolean error;
	private String errorMsg;
	private T content;
	
}
