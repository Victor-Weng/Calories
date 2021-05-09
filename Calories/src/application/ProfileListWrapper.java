package application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
* Author : victor
* Date : May 5, 2021
* Description :
*/
@XmlRootElement(name="profiles")
public class ProfileListWrapper
	{
		
		private List<Profile> profiles;
		@XmlElement(name="profile")
		public List<Profile> getProfiles()
		{
				return profiles;
		}
		public void setProfiles(List<Profile> profiles)
		{
				this.profiles=profiles;
		}
		
	}
		
