package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * Description : A program that calculates your calorie consumption over a week and compares it to what should be 
 * attained on average based on your gender, age, weight.
 * TODO LIST:
 * - Make it so the programs works and tracks data for different people
 * - Starting page that lets you choose your profile, if not, 
 * you make a new one and asks for gender, age, weight and a name that creates a separate profile
 * - A table with each day of the week, asking for how many calories consumed per meal or in total that day and 
 * the type of food ate.
 * - Graph 1 : Calories consumed per day, compared against the average that should be met
 * - Graph 2 : Distribution of calories per type of food (Meat, Produce, Dairy..etc) in a pie chart
 */

public class CalorieController implements Initializable{

    @FXML
    private TextField txtEau;
    
    @FXML
    private ComboBox<String> cboSexe;

    @FXML
    private TextField txtPoids;

    @FXML
    private Button btnClear;

    @FXML
    private ComboBox<String> cboExercise;

    @FXML
    private TableColumn<Calorie, String> jourColumn;

    @FXML
    private TableColumn<Calorie, Double> calorieColumn;
    
    @FXML
    private TableColumn<Calorie, Double> eauColumn;

    @FXML
    private TableColumn<Calorie, String> legumeColumn;

    @FXML
    private ComboBox<String> cboLegumes;

    @FXML
    private Label lblUser;

    @FXML
    private Label lblDay;

    @FXML
    private TableView<Calorie> calorieTable;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtTaille;
    
    @FXML
    private Button btnEffacer;

    @FXML
    private ComboBox<String> cboJour;

    @FXML
    private Button btnModifier;

    @FXML
    private ComboBox<String> cboProfil;

    @FXML
    private Button btnAjouter;

    @FXML
    private Label lblWeek;
    
    @FXML
    private TextField txtCalorie;
    
    @FXML
    private TextField txtPrenom;
    
    @FXML
    private TextField txtNom;
    
    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> intervalJour=FXCollections.observableArrayList();

    
    
    // TABLEAU 1
    // liste pour "jour"
    private ObservableList<String> jourlist=(ObservableList<String>) FXCollections.observableArrayList("Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche");
    
    // liste pour "legumes"
    private ObservableList<String> legumeslist=(ObservableList<String>) FXCollections.observableArrayList("Aucun","1-2 repas","tous les repas");
    
    // liste pour 'exercise'
    private ObservableList<String> exerciselist=(ObservableList<String>) FXCollections.observableArrayList("Sedentaire", "Peu actif", "Actif", "Tres actif");
    
    // TABLEAU 2
    // liste pour "sexe"
    
    
    // PROFILE MANAGEMENT
    // liste pour "profile"
    // TODO : FIGURE OUT HOW TO SAVE PROFILES
    
    private ObservableList<String> sexelist=(ObservableList<String>) FXCollections.observableArrayList("Homme","Femme","Autre");
  
    
    public ObservableList<Calorie> calorieData=FXCollections.observableArrayList();

    
    
    // Tableau 1 return combo
    
    public ObservableList<Calorie> getcalorieData()
		{
			return calorieData;
		}
    
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources)
		{
			cboSexe.setItems(sexelist);
			cboJour.setItems(jourlist);
			cboLegumes.setItems(legumeslist);
			cboExercise.setItems(exerciselist);

			//attribuer les valeurs aux colonnes du tableView
			jourColumn.setCellValueFactory(new PropertyValueFactory<>("jour"));
			calorieColumn.setCellValueFactory(new PropertyValueFactory<>("calorie"));
			eauColumn.setCellValueFactory(new PropertyValueFactory<>("eau"));
			legumeColumn.setCellValueFactory(new PropertyValueFactory<>("legume"));
			calorieTable.setItems(calorieData);

			btnModifier.setDisable(true);
			btnEffacer.setDisable(true);
			btnClear.setDisable(true);

			showCalorie(null);
			// Metre a jour l'affichage d'un Calorie selectionne
			calorieTable.getSelectionModel().selectedItemProperty().addListener((
					observable, oldValue, newValue)-> showCalorie(newValue));
			
			// Afficher le Bar Chart
			   intervalJour.add("Lundi");
	    	   intervalJour.add("Mardi");
	    	   intervalJour.add("Mercredi");
	    	   intervalJour.add("Jeudi");
	    	   intervalJour.add("Vendredi");
	    	   intervalJour.add("Samedi");
	    	   intervalJour.add("Dimanche");
	    		
	           xAxis.setCategories(intervalJour);
	          
			 

		}
    

    public void SetStats(List<Calorie> calories)
        {
            //Compter le calorie par jour
	    	
    		double[] jourCounter= new double[7]; 
    		   
    		Objects.requireNonNull(intervalJour, "intervalJour must not be null");
    		Objects.requireNonNull(barChart, "barChart must not be null");
    		Objects.requireNonNull(jourCounter, "jourCounter must not be null");
            
            for(Calorie e:calories)
            {
            	double jourCount = 0.0;
            	String aJour = "";
            	aJour= e.getJour();
                
            	if(aJour == "Lundi")
                {
             	   jourCount = 1;
                }
            		else if(aJour == "Mardi")
                     {
                  	   jourCount = 2;
                     }
            			else if(aJour == "Mercredi")
		                {
		             	   jourCount = 3;
		                }
		            		else if(aJour == "Jeudi")
		                     {
		                  	   jourCount = 4;
		                     }
		            			else if(aJour == "Vendredi")
				                {
				             	   jourCount = 5;
				                }
				            		else if(aJour == "Samedi")
				                     {
				                  	   jourCount = 6;
				                     }
				            			else if(aJour == "Dimanche")
						                {
						             	   jourCount = 7;
						                }
						            		
            	
            	
            	double tmpcal = 0.0;
            	tmpcal = e.getCalorie();
            	//tmpcal = Double.parseDouble(txtCalorie.getText());
            	
                if(jourCount == 1)
             	   jourCounter[0] = tmpcal;
                else
                    if(jourCount == 2)
                    	jourCounter[1] = tmpcal;
                    else
                        if(jourCount == 3)
                        	jourCounter[2] = tmpcal;
                        else
                            if(jourCount == 4)
                            	jourCounter[3] = tmpcal;
                            else
                                if(jourCount == 5)
                                	jourCounter[4] = tmpcal;
                                else
                                    if(jourCount == 6)
                                    	jourCounter[5] = tmpcal;
                                    else
                                        if(jourCount == 7)
                                        	jourCounter[6] = tmpcal;
                                        else
                                            if(jourCount == 8)
                                            	jourCounter[7] = tmpcal; 
            }
            
            XYChart.Series<String, Double> series=new XYChart.Series<>();
            series.setName("Jour"); // legende pour le graphique

            //creation du graphique
            for(int i=0 ; i<jourCounter.length;i++)
            {
                series.getData().add(new XYChart.Data<>(intervalJour.get(i), jourCounter[i]));

            }
            barChart.getData().add(series);
        }
    
    
    	@FXML
    	void handleStats()
    	{
    			try {
					SetStats(calorieData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
 // Ajouter un Calorie
 		@FXML
 		void ajouter()
 			{
 				// Verifier si un champ n'est pas vide
 				if(noEmptyInput())
 				{
 					Calorie tmp=new Calorie();
 					tmp=new Calorie();
 					tmp.setJour(cboJour.getValue());
 					tmp.setCalorie(Double.parseDouble(txtCalorie.getText()));
 					tmp.setEau(Double.parseDouble(txtEau.getText()));
					tmp.setLegumes(cboLegumes.getValue());
 					calorieData.add(tmp);
 					//SetStats(calorieData);
 					clearFields();
 					
 				}
 				
 			}
 	
 		
 		
 		@FXML
 		public void verifNum() // methode pour trouer des input non numeriques
 		{
 				txtCalorie.textProperty().addListener((observable,oldValue,newValue)->
 					{
 						if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
 						{
 							txtCalorie.setText(newValue.replaceAll("[^\\d*\\.]","")); 
 						}
 					});
 				
 				txtEau.textProperty().addListener((observable,oldValue,newValue)->
 					{
 						if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
 						{
 							txtEau.setText(newValue.replaceAll("[^\\d*\\.]","")); 
 						}
 					});
 				
 				txtAge.textProperty().addListener((observable,oldValue,newValue)->
 					{
 						if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
 						{
 							txtAge.setText(newValue.replaceAll("[^\\d*\\.]","")); 
 						}
 					});
 				
 				txtPoids.textProperty().addListener((observable,oldValue,newValue)->
 					{
 						if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
 						{
 							txtPoids.setText(newValue.replaceAll("[^\\d*\\.]","")); 
 						}
 					});
 				
 				txtTaille.textProperty().addListener((observable,oldValue,newValue)->
 					{
 						if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
 						{
 							txtTaille.setText(newValue.replaceAll("[^\\d*\\.]","")); 
 						}
 					});
 		}

 		// Effacer le contenu des champs
 		@FXML
 		void clearFields()
 			{
 				txtEau.setText("");
 				cboJour.setValue(null);
 				txtCalorie.setText("");
 				cboLegumes.setValue(null);

 				/*txtNom.setText("");
 				cboSexe.setPromptText("");
 				txtAge.setText("");
 				txtPoids.setText("");
 				txtTaille.setText("");*/
 			}

 		// Afficher les calorie
 		
 		
 		public void showCalorie(Calorie calorie)
 			{
 				if(calorie !=null)
 				{

 					cboJour.setValue(calorie.getJour());
 					txtEau.setText(Double.toString(calorie.getEau()));
 					txtCalorie.setText(Double.toString(calorie.getCalorie()));
 					cboLegumes.setValue(calorie.getLegumes());
 					btnModifier.setDisable(false);
 					btnEffacer.setDisable(false);
 					btnClear.setDisable(false);

 				}
 				else
 				{
 					clearFields();

 				}
 			}

 		// Mise a jour d'un Calorie
 		@FXML
 		public void updateCalorie()
 			{
 				// Verifier si un champ n'est pas vide
 				if(noEmptyInput())
 				{
 					Calorie calorie=calorieTable.getSelectionModel().getSelectedItem();

 					calorie.setJour(cboJour.getValue());
 					calorie.setCalorie(Double.parseDouble(txtCalorie.getText()));
 					calorie.setLegumes(cboLegumes.getValue());
 					calorie.setEau(Double.parseDouble(txtEau.getText()));
 					calorieTable.refresh();
 				}
 			}
 		
 		// Effacer un Calorie
 		@FXML
 		public void deleteCalorie()
 		{
 				int selectedIndex = calorieTable.getSelectionModel().getSelectedIndex();
 				if (selectedIndex >= 0)
 				{
 					Alert alert=new Alert(AlertType.CONFIRMATION);
 					alert.setTitle("Effacer");
 					alert.setContentText("Confirmer la suppression!");
 					Optional<ButtonType> result=alert.showAndWait();
 					if(result.get()==ButtonType.OK)	
 						calorieTable.getItems().remove(selectedIndex);
 				}
 		}
 		
 		// Verifier les champs vides
 		private boolean noEmptyInput()
 		{
 				String errorMessage="";
 				if(cboJour.getValue()==null)
 				{
 					errorMessage+="Le champ jour ne doit pas etre vide! \n";
 				}
 				if(txtCalorie.getText().trim().equals(""))
 				{
 					errorMessage+="Le champ Calorie ne doit pas etre vide! \n";
 				}
 				if(txtEau.getText().trim().equals(""))
 				{
 					errorMessage+="Le champ eau ne doit pas etre vide! \n";
 				}
 				if(cboLegumes.getValue()==null)
 				{
 					errorMessage+="Le champ legumes ne doit pas etre vide! \n";
 				}
 				
 				if(errorMessage.length()==0)
 				{
 					return true;
 				}
 				else
 				{
 					Alert alert=new Alert(AlertType.ERROR);
 					alert.setTitle("Champs manquants");
 					alert.setHeaderText("Completer les champs manquants");
 					alert.setContentText(errorMessage);
 					alert.showAndWait();
 					return false;
 				}
 					
 		}
 		
 		
 	    
 	// Methode pour calculer le "Consommation calorique suggérée"
 		@FXML
 	 	private void calculateCalorie() 
 				{
 				String sexe = (cboSexe.getValue());
 				double age = Double.parseDouble(txtAge.getText());
 				double poids = Double.parseDouble(txtPoids.getText());
 				double taille = Double.parseDouble(txtTaille.getText());
 				String exercise = (cboExercise.getValue());
 				double PAI = 0.0;
 				double caloriesuggest = 0.0;
 				double caloriesuggestsemaine = 0.0;
 				
 				
 	 			// HOMME	
 				if(sexe == "Homme")
 				{
 					if(exercise == "Sedentaire")
 	 				{
 	 					PAI = 1;
 	 				}
 	 					else if (exercise == "Peu actif")
 	 					{
 	 						PAI = 1.12;
 	 					}
 			 				else if (exercise == "Actif")
 			 				{
 			 					PAI = 1.27;
 			 				}
 				 				else if (exercise == "Tres actif")
 				 				{
 				 					PAI = 1.54;
 				 				}

 					caloriesuggest = (864 - 9.72*age + PAI*(14.2*poids + 503*taille/100));
 				}
 			
 				// FEMME
 				else if(sexe == "Femme")
 				{
 					if(exercise == "Sedentaire")
 	 				{
 	 					PAI = 1;
 	 				}
 	 					else if (exercise == "Peu actif")
 	 					{
 	 						PAI = 1.14;
 	 					}
 			 				else if (exercise == "Actif")
 			 				{
 			 					PAI = 1.27;
 			 				}
 				 				else if (exercise == "Tres actif")
 				 				{
 				 					PAI = 1.45;
 				 				}

 					caloriesuggest = (387 - 7.31*age + PAI*(10.9*poids + 660.7*taille/100));
 				}
 				
 			// AUTRE	
 				else if(sexe == "Autre")
 				{
 					if(exercise == "Sedentaire")
 	 				{
 	 					PAI = 1;
 	 				}
 	 					else if (exercise == "Peu actif")
 	 					{
 	 						PAI = 1.12;
 	 					}
 			 				else if (exercise == "Actif")
 			 				{
 			 					PAI = 1.27;
 			 				}
 				 				else if (exercise == "Tres actif")
 				 				{
 				 					PAI = 1.54;
 				 				}

 					caloriesuggest = (625.5 - 8.52*age + PAI*(12.55*poids + 581.85*taille/100));
 				}
 				caloriesuggestsemaine = 7*caloriesuggest;
 				
 				System.out.println("poids = " + poids);
 				System.out.println("exercise = " + exercise);
 				System.out.println("taille = " + taille);
 				System.out.println("sexe = " + sexe);
 				
 				lblDay.setText(Double.toString(caloriesuggest));
 				lblWeek.setText(Double.toString(caloriesuggestsemaine));
 				
 	 		}
 		
 		// SAUVEGARDE DE DONNEES
 		
 		//Recuperer le chemin (path) des fichiers si ca existe
 		public File getCalorieFilePath()
 		{
 				Preferences prefs = Preferences.userNodeForPackage(Main.class);
 				String filePath = prefs.get("filePath", null);
 				
 				if (filePath != null)
 				{
 					return new File(filePath);
 				}
 				else
 				{
 					return null;
 				}
 		}
 		
 		//Attribuer un chemin des fichiers
 		
 		public void setCalorieFilePath(File file)
 		{
 				Preferences prefs = Preferences.userNodeForPackage(Main.class);
 				if (file != null)
 				{
 					prefs.put("filePath", file.getPath());
 				}
 				else
 				{
 					prefs.remove("filePath");
 				}
 		}
 		
 // Prendre les donnees de type XML et les convertir en donnees de type javaFx (processus de Unmarshall)
 	public void loadCalorieDataFromFile(File file)
 	{
 			try {
 				JAXBContext context = JAXBContext.newInstance(CalorieListWrapper.class);
 				Unmarshaller um = context.createUnmarshaller();
 				
 				CalorieListWrapper wrapper = (CalorieListWrapper) um.unmarshal(file);
 				calorieData.clear();
 				calorieData.addAll(wrapper.getCalories());
 				setCalorieFilePath(file);
 				// Donner le titre du fichier chargee
 				Stage pStage=(Stage) calorieTable.getScene().getWindow();
 				pStage.setTitle(file.getName());
 				
 			} catch (Exception e) {
 				Alert alert = new Alert(AlertType.ERROR);
 				alert.setTitle("Erreur");
 				alert.setHeaderText("Les donnees n'ont pas ete trouvees");
 				alert.setContentText("Les donnees ne pouvaient pas etre trouvees dans le fichier : \n" +file.getPath());
 				alert.showAndWait();
 			}
 	}
 	
 	// Prendre les donnees de type JavaFx et les convertir en type XML
 	public void saveCalorieDataToFile(File file) {
 			try {
 				JAXBContext context = JAXBContext.newInstance(CalorieListWrapper.class);
 				Marshaller m = context.createMarshaller();
 				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 				CalorieListWrapper wrapper = new CalorieListWrapper();
 				wrapper.setCalories(calorieData);
 				
 				m.marshal(wrapper,  file);
 				
 				// Sauvegarde dans le registre
 				setCalorieFilePath(file);
 				// Donner le titre du fichier sauvegarde
 				Stage pStage=(Stage) calorieTable.getScene().getWindow();
 				pStage.setTitle(file.getName());
 				
 			} catch (Exception e) {
 				
 				Alert alert = new Alert(AlertType.ERROR);
 				alert.setTitle("Erreur");
 				alert.setHeaderText("Donnees non sauvegardees");
 				alert.setContentText("Les donnees ne pouvaient pas etre sauvegardees dans le fichier : \n" +file.getPath());
 				alert.showAndWait();
 			}
 	}
 	
 	// Commencer un nouveau
 	@FXML
 	private void handleNew()
 	{
 			getcalorieData().clear();
 			setCalorieFilePath(null);
 	}
 		
 	// Le FileChooser permet a l'usager de choisir le fichier a ouvrir.
 	@FXML
 	private void handleOpen() {
 			FileChooser fileChooser = new FileChooser();
 			
 			// Permetrtre un filtre sur l'extension du fichier a chercher
 			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
 					"XML files (*.xml)","*xml");
 			fileChooser.getExtensionFilters().add(extFilter);
 			
 			// Montrer le dialogue
 			File file = fileChooser.showOpenDialog(null);
 			
 			if (file != null) {
 				loadCalorieDataFromFile(file);
 			}
 	}
 	
 	
 		
 	// Sauvegarde le fichier correspondant a l'Calorie actif
 	// S'il n y a pas de fichier, le menu sauvegarder sous va s'afficher
 	
 	
 	@FXML
 	private void handleSave() {
 			
 			File CalorieFile = getCalorieFilePath();
 			if (CalorieFile != null) {
 				saveCalorieDataToFile(CalorieFile);
 				
 			} else {
 				handleSaveAs();
 			}
 	}
 	
 	
 	// Ouvrir le FileChooser pour trouver le chemin.
 	
 	
 	@FXML
 	private void handleSaveAs() {
 			FileChooser fileChooser = new FileChooser();
 			
 			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
 					"XML files (*.xml)","*xml");
 			fileChooser.getExtensionFilters().add(extFilter);
 			
 			// Sauvegarde
 			File file = fileChooser.showSaveDialog(null);
 			
 			if (file != null) {
 				// Verification de l'extension
 				if (!file.getPath().endsWith(".xml")) {
 					file = new File(file.getPath() + ".xml");
 				}
 				saveCalorieDataToFile(file);
 			}
 	}
 	
 	
 	
 	}
    
    

