package com.app.moviePilot.model.mediaPersonnel;

public class CrewMember extends MediaPersonnel{
	
	//attribute
	private String job;
	
	//builders
	public CrewMember() { super(); }
	
	public CrewMember(Long id, String name, String job) {
		super(id, name);
		setJob(job);
	}
	
	//getters y setters
	public final String getJob() {
		return job;
	}

	public final void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "CrewMember [id="+getId()+", name="+getName()+", job=" + job + "]";
	}
	
	
	
}//end class