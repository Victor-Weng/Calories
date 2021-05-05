package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
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
    private BarChart<Calorie, Integer> barChart;
    
    @FXML
    private ComboBox<String> cboSexe;

    @FXML
    private TextField txtPoids;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtExercise;

    @FXML
    private TableColumn<Calorie, String> jourColumn;

    @FXML
    private TableColumn<Calorie, Double> calorieColumn;

    @FXML
    private TableColumn<Calorie, Double> exerciseColumn;
    
    @FXML
    private TableColumn<Calorie, Double> eauColumn;

    @FXML
    private TableColumn<Calorie, String> legumeColumn;

    @FXML
    private TextField txtCalories;

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
    private TextField txtjour;
    
    @FXML
    private TextField txtCalorie;

    @FXML
    void ajouter() {

    }

    @FXML
    void updateCalorie() {

    }

    @FXML
    void deleteCalorie() {

    }

    @FXML
    void clearFields(ActionEvent event) {

    }
    
    
    // TABLEAU 1
    // liste pour "jour"
    private ObservableList<String> jourlist=(ObservableList<String>) FXCollections.observableArrayList("Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche");
    
    // liste pour "legumes"
    private ObservableList<String> legumeslist=(ObservableList<String>) FXCollections.observableArrayList("Aucun","1-2 repas","tous les repas");
    
    
    // TABLEAU 2
    // liste pour "sexe"
    
    
    // PROFILE MANAGEMENT
    // liste pour "profile"
    // TODO : FIGURE OUT HOW TO SAVE PROFILES
    
    private ObservableList<String> sexelist=(ObservableList<String>) FXCollections.observableArrayList("Homme","Femme","Autre");
  
    
    public ObservableList<Calorie> caloriejourData=FXCollections.observableArrayList();
    public ObservableList<Calorie> calorielegumesData=FXCollections.observableArrayList();
    public ObservableList<Profile> profilesexeData=FXCollections.observableArrayList();
    
    // Tableau 1 return combo
    
    public ObservableList<Calorie> getcaloriejourData()
    {
    		return caloriejourData;
    }
    
    public ObservableList<Calorie> getcalorielegumesData()
    {
    		return calorielegumesData;
    }
    
    // Tableau 2 return combo
    
    public ObservableList<Profile> getprofilesexeData()
    {
    		return profilesexeData;
    }
    
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

			//attribuer les valeurs aux colonnes du tableView
			jourColumn.setCellValueFactory(new PropertyValueFactory<>("jour"));
			calorieColumn.setCellValueFactory(new PropertyValueFactory<>("calorie"));
			exerciseColumn.setCellValueFactory(new PropertyValueFactory<>("exercise"));
			eauColumn.setCellValueFactory(new PropertyValueFactory<>("eau"));
			legumeColumn.setCellValueFactory(new PropertyValueFactory<>("legume"));
			calorieTable.setItems(calorieData);

			btnModifier.setDisable(true);
			btnEffacer.setDisable(true);
			btnClear.setDisable(true);

			showCalorie(null);
			// Metre a jour l'affichage d'un Calorie selectionne
			calorieTable.getSelectionModel().selectedItemProperty().addListener((
					observable, oldValue, newValue)-> showCalories(newValue));



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
 					tmp.setJour(txtjour.getText());
 					tmp.setCalorie((Double.parseDouble(txtCalorie.getText())));
 					tmp.setExercise(Double.parseDouble(txtExercise.getText()));
 					tmp.setEau((Double.parseDouble(txtEau.getText())));
 					CalorieData.add(tmp);
 					clearFields();
 				}
 				
 			}
 		
 		
 		
 		@FXML
 		public void verifNum() // methode pour trouer des input non numeriques
 		{
 				txtExercise.textProperty().addListener((observable,oldValue,newValue)->
 					{
 						if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
 						{
 							txtExercise.setText(newValue.replaceAll("[^\\d*\\.]","")); // si le input est non numerique, ca le remplace
 						}
 					});
 		}

 		// Effacer le contenu des champs
 		@FXML
 		void clearFields()
 			{
 				cboeau.setValue(null);
 				txtjour.setText("");
 				txtCalorie.setText("");
 				txtExercise.setText("");
 			}

 		// Afficher les calorie
 		
 		public void showcalorie(Calorie Calorie)
 			{
 				if(Calorie !=null)
 				{

 					txtEau.setText(Double.toString(Calorie.getEau()));
 					txtjour.setText(Calorie.getJour());
 					txtCalorie.setText(Double.toString(Calorie.getCalorie()));
 					txtExercise.setText(Double.toString(Calorie.getExercise()));
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
 					Calorie Calorie=calorieTable.getSelectionModel().getSelectedItem();

 					Calorie.setjour(txtjour.getText());
 					Calorie.setCalorie(Double.parseDouble(txtCalorie.getText()));
 					Calorie.setExercise(Double.parseDouble(txtExercise.getText()));
 					Calorie.setEau(Double.parseDouble(txtEau.getText()));
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
 				String errorMessexercise="";
 				if(txtjour.getText().trim().equals(""))
 				{
 					errorMessexercise+="Le champ jour ne doit pas etre vide! \n";
 				}
 				if(txtCalorie.getText().trim().equals(""))
 				{
 					errorMessexercise+="Le champ Calorie ne doit pas etre vide! \n";
 				}
 				if(txtExercise.getText().trim().equals(""))
 				{
 					errorMessexercise+="Le champ exercise ne doit pas etre vide! \n";
 				}
 				if(txtEau.getText().trim().equals(""))
 				{
 					errorMessexercise+="Le champ departement ne doit pas etre vide! \n";
 				}
 				
 				if(errorMessexercise.length()==0)
 				{
 					return true;
 				}
 				else
 				{
 					Alert alert=new Alert(AlertType.ERROR);
 					alert.setTitle("Champs manquants");
 					alert.setHeaderText("Completer les champs manquants");
 					alert.setContentText(errorMessexercise);
 					alert.showAndWait();
 					return false;
 				}
 					
 		}
 		
 		//Afficher les statistiques
 	    @FXML
 	    void handleStats()
 	    {
 	        try {
 	            FXMLLoader loader= new FXMLLoader(Main.class.getResource("ExerciseStat.FXML"));
 	            AnchorPane pane = loader.load();
 	            Scene scene = new Scene(pane);
 	            ExerciseStat exercisestat=loader.getController();
 	            exercisestat.SetStats(CalorieData);
 	            Stage stage=new Stage();
 	            stage.setScene(scene);
 	            stage.setTitle("Statistiques");
 	            stage.show();
 	        } catch (IOException e) {
 	            e.printStackTrace();
 	        }
 	    }
 		
 		
 		// SAUVEGARDE DE DONNEES
 		
 		//Recuperer le chemin (path) des fichiers si ca existe
 		public File getCalorieFilePath()
 		{
 				Preferences prefs = Preferences.userNodeForPackexercise(Main.class);
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
 				Preferences prefs = Preferences.userNodeForPackexercise(Main.class);
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
 				CalorieData.clear();
 				CalorieData.addAll(wrapper.getcalorie());
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
 				wrapper.setcalorie(CalorieData);
 				
 				m.marshal(wrapper,  file);
 				
 				// Sauvegarde dans le registre
 				setCalorieFilePath(file);
 				// Donner le titre du fichier sauvegarde
 				Stexercise pStexercise=(Stexercise) calorieTable.getScene().getWindow();
 				pStexercise.setTitle(file.getName());
 				
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
 			getCalorieData().clear();
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

