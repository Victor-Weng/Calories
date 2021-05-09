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
		private List<Calorie> calories;
		@XmlElement(name="calorie")
		public List<Calorie> getCalories()
		{
				return calories;
		}
		public void setCalories(List<Calorie> calories)
		{
				this.calories=calories;
		}
		
	}
		
