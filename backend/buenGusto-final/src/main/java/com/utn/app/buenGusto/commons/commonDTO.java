package com.utn.app.buenGusto.commons;

import java.io.Serializable;

public class commonDTO implements Serializable{
	
	private static final long serialVersionUID = 7118915634025124619L;
	
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
