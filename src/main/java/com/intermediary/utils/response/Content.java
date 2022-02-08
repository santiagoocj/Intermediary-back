package com.intermediary.utils.response;

import java.io.Serializable;

public interface Content<T> extends Serializable {
	
	T getContent();
}
