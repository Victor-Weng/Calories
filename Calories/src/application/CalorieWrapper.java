package application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
* Author : victor
* Date : May 5, 2021
* Description :
*/
@XmlRootElement(name="calories")
public class CalorieListWrapper
	{
		private List<Calorie> calorie;
		@XmlElement(name="etudiant")
		public List<Calorie> getCalorie()
		{
				return etudiants;
		}
		public void setEtudiants(List<Etudiant> etudiants)
		{
				this.etudiants=etudiants;
		}
		
