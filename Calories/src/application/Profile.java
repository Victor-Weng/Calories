package application;
/*
* Author : victor
* Date : Apr. 22, 2021
* Description :
*/
public class Profile
	{
		
		// Tableau 2, Profiles
		private String prenom;
		private String nom;
		private String sexe;
		private Double age;
		private Double poid;
		private Double taille;

		// Constructeur vide tableau 2
		
			public Profile()
			{
					this(null,null);
			}
			
			public Profile(String prenom, String nom) 
				{
					this.prenom=prenom;
					this.nom=nom;
					this.sexe="";
					this.age=0.0;
					this.poid=0.0;
					this.taille=0.0;
				}
		
		// Tableau 2, Getters et Setters
		public String getPrenom()
			{
				return prenom;
			}
		public void setPrenom(String prenom)
			{
				this.prenom = prenom;
			}
		public String getNom()
			{
				return nom;
			}
		public void setNom(String nom)
			{
				this.nom = nom;
			}
		public String getSexe()
			{
				return sexe;
			}
		public void setSexe(String sexe)
			{
				this.sexe = sexe;
			}
		public Double getAge()
			{
				return age;
			}
		public void setAge(Double age)
			{
				this.age = age;
			}
		public Double getPoid()
			{
				return poid;
			}
		public void setPoid(Double poid)
			{
				this.poid = poid;
			}
		public Double getTaille()
			{
				return taille;
			}
		public void setTaille(Double taille)
			{
				this.taille = taille;
			}
		
		
	}
