package application;
/*
* Author : victor
* Date : Apr. 22, 2021
* Description :
*/
public class Calorie
	{
		
		// Tableau 1, Rapport du semain
		private String jour;
		private Double calorie;
		private Double exercise;
		private Double eau;
		private String legume;
		
	
		// Constructeur vide tableau 1
		
			public Calorie()
			{
					this(null,null);
			}
		
		
		
		public Calorie(String prenom, String nom) 
			{
				this.jour="";
				this.calorie=0.0;
				this.exercise=0.0;
				this.eau=0.0;
				this.legume="";
			}



		// Tableau 1, Getters et Setters
		public String getJour()
			{
				return jour;
			}
		public void setJour(String jour)
			{
				this.jour = jour;
			}
		public Double getCalorie()
			{
				return calorie;
			}
		public void setCalorie(Double calorie)
			{
				this.calorie = calorie;
			}
		public Double getExercise()
			{
				return exercise;
			}
		public void setExercise(Double exercise)
			{
				this.exercise = exercise;
			}
		public Double getEau()
			{
				return eau;
			}
		public void setEau(Double eau)
			{
				this.eau = eau;
			}
		public String getLegumes()
			{
				return legume;
			}
		public void setLegumes(String legume)
			{
				this.legume = legume;
			}
	
		
	}
