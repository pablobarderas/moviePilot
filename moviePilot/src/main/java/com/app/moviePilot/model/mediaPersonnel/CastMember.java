package com.app.moviePilot.model.mediaPersonnel;

import java.util.Objects;


public class CastMember extends MediaPersonnel{
	
	//attributes
	private String profilePath;
	private String characterName;
	
	//builders
	public CastMember() {
		super();
	}
	
	public CastMember(Long id, String name, String profilePath, String characterName) {
		super(id, name);
		setProfilePath(profilePath);
		setCharacterName(characterName);
	}
	
	//getters y setters
	public final String getProfilePath() {
		return profilePath;
	}

	public final void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public final String getCharacterName() {
		return characterName;
	}

	public final void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	@Override
	public String toString() {
		return "CastMember [id="+this.getId()+", name="+this.getCharacterName()+", profilePath=" + profilePath + ", characterName=" + characterName + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CastMember other = (CastMember) obj;
		return Objects.equals(characterName, other.characterName) && Objects.equals(profilePath, other.profilePath);
	}
	
	

}//end class