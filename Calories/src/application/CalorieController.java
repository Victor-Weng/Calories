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
    private ComboBox<String> cboEau;

    @FXML
    private BarChart<Calorie, Integer> barChart;
    
    @FXML
    private ComboBox<String> cboSexe;

    @FXML
    private TextField txtPoids;

    @FXML
    private Button btnClear;

    @FXML
    private ComboBox<Double> cboExercise;

    @FXML
    private TableColumn<Calorie, Double> JourColumn;

    @FXML
    private TableColumn<Calorie, Double> caloriesColumn;

    @FXML
    private TableColumn<Calorie, Double> exerciseColumn;
    
    @FXML
    private TableColumn<Calorie, Double> eauColumn;

    @FXML
    private TableColumn<Calorie, Double> legumeColumn;

    @FXML
    private TextField txtCalories;

    @FXML
    private ComboBox<Double> cboLegumes;

    @FXML
    private Label lblUser;

    @FXML
    private Label lblDay;

    @FXML
    private TableView<Calorie> caloriesTable;

    @FXML
    private TextField txtAge;

    @FXML
    private Button btnEffacer;

    @FXML
    private ComboBox<Double> cboJour;

    @FXML
    private Button btnModifier;

    @FXML
    private ComboBox<String> cboProfil;

    @FXML
    private Button btnAjouter;

    @FXML
    private Label lblWeek;

    @FXML
    private TextField txtNom;

    @FXML
    void ajouter(ActionEvent event) {

    }

    @FXML
    void updateEtudiant(ActionEvent event) {

    }

    @FXML
    void deleteEtudiant(ActionEvent event) {

    }

    @FXML
    void clearFields(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
		{
			// TODO Auto-generated method stub
			
		}
    
    
}

