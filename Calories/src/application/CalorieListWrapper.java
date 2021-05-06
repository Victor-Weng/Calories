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
		@XmlElement(name="calorie")
		public List<Calorie> getCalorie()
		{
				return calorie;
		}
		public void setCalories(List<Calorie> calorie)
		{
				this.calorie=calorie;
		}
		
	}
		
