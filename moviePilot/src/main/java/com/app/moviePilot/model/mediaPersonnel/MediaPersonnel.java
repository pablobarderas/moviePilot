package com.app.moviePilot.model.mediaPersonnel;


public abstract class MediaPersonnel {
	
	private Long id;
	private String name;
	
	public MediaPersonnel() {}
	
	public MediaPersonnel(Long id, String name) {
		setId(id);
		setName(name);
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}	

}//end class.