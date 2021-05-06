package application;


import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class CalorieStat {

     @FXML
        private BarChart<String, Integer> barChart;

        @FXML
        private CategoryAxis xAxis;

        private ObservableList<String> intervalCalories=FXCollections.observableArrayList();

       @FXML
       private void initialize()
       {
           intervalCalories.add("0-10");
           intervalCalories.add("10-20");
           intervalCalories.add("20-30");
           intervalCalories.add("30-40");
           intervalCalories.add("40-50");
           intervalCalories.add("50-60");
           xAxis.setCategories(intervalCalories);
}


       public void SetStats(List<Calorie> calories)
       {
           //Compter les etudiants appartenant a la meme tranche d'age

           int[] ageCounter= new int[6]; //Tableau pour les nombres des tranches d'age

           for(Calorie e:calories)
           {
               double age = e.getCalorie();

               if(age<=10)
                   ageCounter[0]++;
               else
                   if(age<=20)
                       ageCounter[1]++;
                   else
                       if(age<=30)
                           ageCounter[2]++;
                       else
                           if(age<=40)
                               ageCounter[3]++;
                           else
                               if(age<=50)
                                   ageCounter[4]++;
                               else
                                   ageCounter[5]++;
           }
           XYChart.Series<String, Integer> series=new XYChart.Series<>();
           series.setName("ages"); // legende pour le graphique

           //creation du graphique
           for(int i=0 ; i<ageCounter.length;i++)
           {
               series.getData().add(new XYChart.Data<>(intervalCalories.get(i), ageCounter[i]));

           }
           barChart.getData().add(series);
       }

}