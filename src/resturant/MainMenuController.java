/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Mndour
 */
public class MainMenuController implements Initializable {

    @FXML
    TextField numMeals;
    @FXML
    TextField numDrinks;
    
    @FXML
    Pane Drinks;
    @FXML
    Pane Meals;
    
    
    // M insert
    @FXML 
    ComboBox typeM;
    @FXML
    TextField numM;
    @FXML
    TextField nameM;
    @FXML
    TextField costM;
    @FXML
    Label doneM;
    
    // D insert   
    @FXML 
    ComboBox typeD;
    @FXML
    TextField numD;
    @FXML
    TextField nameD;
    @FXML
    TextField cstD;
    @FXML
    Label doneD;
    
    //M
    @FXML 
    TableView<Meal> tableM;
    @FXML 
    TableColumn<Meal,Integer> numCM;
    @FXML 
    TableColumn<Meal,String> nameCM;
    @FXML 
    TableColumn<Meal,String> typeCM;
    @FXML 
    TableColumn<Meal,Integer> costCM;
    //D
    @FXML 
    TableView<Drink> tableD;
    @FXML 
    TableColumn<Drink,Integer> numCD;
    @FXML 
    TableColumn<Drink,String> nameCD;
    @FXML 
    TableColumn<Drink,String> typeCD;
    @FXML 
    TableColumn<Drink,Integer> costCD;
    @FXML
    TextField searchM;
    @FXML
    TextField searchD;
    
    ObservableList<Meal>listM=FXCollections.observableArrayList();
    ObservableList<Drink>listD=FXCollections.observableArrayList();
    
    int indexM=-1,indexD=-1;
    
    public void enterd(Event e){
        ((Button) e.getSource()).setScaleX(1.3);
        ((Button) e.getSource()).setScaleY(1.3);
        
    }
    public void exited(Event e){
        ((Button) e.getSource()).setScaleX(1);
        ((Button) e.getSource()).setScaleY(1);
    }

    
    public void Drinks(){
       Drinks.setVisible(true);
       Meals.setVisible(false);
    }
    public void Meals(){
       Drinks.setVisible(false);
       Meals.setVisible(true);

       
    }  


    public void InsertMeals(){
   
        if(numM.getText().equals("")||nameM.getText().equals("")|| typeM.getSelectionModel().isEmpty()  ||costM.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please complete all fields.");
            return;
        }
        
        int num=Integer.parseInt(numM.getText());
        String name = nameM.getText();
        String type = typeM.getSelectionModel().getSelectedItem().toString();
        int cost =  Integer.parseInt(costM.getText());
        
        // find if this element is exist
        boolean findIndex=false;  
        for (int i = 0; i < listM.size(); i++) {
           if( num==listM.get(i).id && name.equals(listM.get(i).name) && type.equals(listM.get(i).type) &&  cost==listM.get(i).cost){
               findIndex=true;
               break;
           }
        }

        if(!findIndex){
            listM.add(new Meal(num, name, type, cost));
            doneM.setText("Addition completed");
            doneM.setVisible(true);
            numMeals.setText(Integer.parseInt(numMeals.getText())+1+ "");
            clearM();
        }
        else {
            JOptionPane.showMessageDialog(null, "Already exsist");
        }
    }
   
  
     
    public void getSelectedMeals(){
        // get index
        indexM = tableM.getSelectionModel().getSelectedIndex();
       
        if(indexM<=-1)return;
        
        numM.setText(numCM.getCellData(indexM).toString());
        nameM.setText(nameCM.getCellData(indexM).toString());
        typeM.getSelectionModel().select(typeCM.getCellData(indexM));
        costM.setText(costCM.getCellData(indexM).toString());
    }
    
    public void updataMeals(){
        if(numM.getText().equals("")||nameM.getText().equals("")||typeM.getSelectionModel().isEmpty() ||costM.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please complete all fields.");
            return;
        }
        int num=Integer.parseInt(numM.getText());
        String name = nameM.getText();
        String type = typeM.getSelectionModel().getSelectedItem().toString();
        int cost =  Integer.parseInt(costM.getText());
        
        
        {
            listM.set(indexM , new Meal(num, name, type, cost));
            doneM.setText("Modification completed");
            doneM.setVisible(true);
        }

      
    }
    
   public void deleteMeals(){
    if(listM.size()==0){
        JOptionPane.showMessageDialog(null, "Meal menu is empty !");
        return;
    }   
    if(indexM==-1){
        JOptionPane.showMessageDialog(null, "Please select a meal");
        return;
    }
    
    
    doneM.setText("Deletion is completed");
    doneM.setVisible(true);
    listM.remove(indexM);
    numMeals.setText(listM.size()+"");
    indexM=-1;
    clearM();
    
   }
    
    
    public void clearM(){
        numM.clear();
        nameM.clear();
        typeM.getSelectionModel().select(-1);
        costM.clear();
    }
    
    
    
    public void InsertDrinks(){
        
        if(numD.getText().equals("")||nameD.getText().equals("")|| typeD.getSelectionModel().isEmpty()  ||cstD.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please complete all fields.");
            return;
        }     
        
        int num=Integer.parseInt(numD.getText());
        String name = nameD.getText();
        String type = typeD.getSelectionModel().getSelectedItem().toString();
        int cost =  Integer.parseInt(cstD.getText());
    
        // find if this element is exist
        boolean findIndex=false;     
        for (int i = 0; i < listD.size(); i++) {
           if( num==listD.get(i).id && name.equals(listD.get(i).name) && type.equals(listD.get(i).type) &&  cost==listD.get(i).cost){
               findIndex=true;
               break;
           }
        }
        if(!findIndex){
            listD.add(new Drink(num, name, type, cost));
            doneD.setText("Addition completed");
            doneD.setVisible(true);
            numDrinks.setText(Integer.parseInt(numDrinks.getText())+1+ "");
        }
        else {
            JOptionPane.showMessageDialog(null, "Already exsist");
        }

    }
         
         
      public void getSelectedDrinks(){
        // get index
        indexD = tableD.getSelectionModel().getSelectedIndex();
       
        if(indexD<=-1)return;
        
        numD.setText(numCD.getCellData(indexD).toString());
        nameD.setText(nameCD.getCellData(indexD).toString());
        typeD.getSelectionModel().select(typeCD.getCellData(indexD));
        cstD.setText(costCD.getCellData(indexD).toString());
    }
    
    public void updateDrinks(){
        if(numD.getText().equals("")||nameD.getText().equals("")||typeD.getSelectionModel().isEmpty() ||cstD.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please complete all fields.");
            return;
        }
        int num=Integer.parseInt(numD.getText());
        String name = nameD.getText();
        String type = typeD.getSelectionModel().getSelectedItem().toString();
        int cost =  Integer.parseInt(cstD.getText());
        
        
        {
            listD.set(indexD , new Drink(num, name, type, cost));
            doneD.setText("Modification completed");
            doneD.setVisible(true);
        }

      
    }
    
    
  public void deleteDrinks(){
    if(listD.size()==0){
        JOptionPane.showMessageDialog(null, "Drinks menu is empty !");
        return;
    }   
    if(indexD==-1){
        JOptionPane.showMessageDialog(null, "Please select a Drink");
        return;
    }
    
    
    doneD.setText("Deletion is completed");
    doneD.setVisible(true);
    listD.remove(indexD);
    numDrinks.setText(listD.size()+"");
    indexD=-1;
    clearD();
    
   }
       
     public void clearD(){
        numD.clear();
        nameD.clear();
        typeD.getSelectionModel().select(-1);
        cstD.clear();
    }
     

    public void searchMeals(){
        
        searchM.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
              if(searchM.textProperty().get().isEmpty()){
                  tableM.setItems(listM);
                  return;
              }
              
               ObservableList<Meal>listMCopy=FXCollections.observableArrayList();
               ObservableList<TableColumn<Meal,?>> column=tableM.getColumns();
                
               for(int row = 0; row < listM.size() ; row++)
                   for(int col = 0 ; col < column.size() ; col++){
                       TableColumn colVar = column.get(col);
                       String cellValue = colVar.getCellData(listM.get(row)).toString();
                       cellValue = cellValue.toLowerCase();
                       if(cellValue.contains(searchM.getText().toLowerCase()) && cellValue.startsWith(searchM.getText().toLowerCase())){
                           listMCopy.add(listM.get(row));
                           break;
                       }
                   }
               tableM.setItems(listMCopy);
            }
        });
        
    } 
     
        public void searchDrinks(){
        
        searchD.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
              if(searchD.textProperty().get().isEmpty()){
                  tableD.setItems(listD);
                  return;
              }
              
               ObservableList<Drink>listDCopy=FXCollections.observableArrayList();
               ObservableList<TableColumn<Drink,?>> column=tableD.getColumns();
                
               for(int row = 0; row < listD.size() ; row++)
                   for(int col = 0 ; col < column.size() ; col++){
                       TableColumn colVar = column.get(col);
                       String cellValue = colVar.getCellData(listD.get(row)).toString();
                       cellValue = cellValue.toLowerCase();
                       if(cellValue.contains(searchD.getText().toLowerCase()) && cellValue.startsWith(searchD.getText().toLowerCase())){
                           listDCopy.add(listD.get(row));
                           break;
                       }
                   }
               tableD.setItems(listDCopy);
            }
        });
        
    }
       
        
    
     public void logOut(Event e){
         try {
                Parent root = FXMLLoader.load(getClass().getResource("/resturant/login.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login");
                
                Rectangle2D rd = Screen.getPrimary().getVisualBounds();
                stage.setX( (rd.getWidth()-stage.getWidth())/2);
                stage.setY( (rd.getHeight()-stage.getHeight())/2);               
         } catch (Exception ee) {
         }
       
     }   
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // M table
       numCM.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("id"));
       nameCM.setCellValueFactory(new PropertyValueFactory<Meal,String>("name"));
       typeCM.setCellValueFactory(new PropertyValueFactory<Meal,String>("type"));
       costCM.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("cost"));
       

       listM.add(new Meal(1, "طاجن موزة ضاني", "الأطباق الرئيسية", 199));
       listM.add(new Meal(2, "حمام محشي مشوي", "الأطباق الرئيسية", 199));
       listM.add(new Meal(3, "الوليمة", "الأطباق الرئيسية", 210));
       listM.add(new Meal(4, "شيش طاووق", "الأطباق الرئيسية", 105));
       listM.add(new Meal(5, "فرخة مشوية", "أحسن الأطباق", 90));
       listM.add(new Meal(6, "كباب وكفتة", "أحسن الأطباق", 240));
       listM.add(new Meal(7, "ضلع ضاني مشوي", "أحسن الأطباق", 210));
       listM.add(new Meal(8, "حمام محشي مقلي", "أحسن الأطباق", 100));
       listM.add(new Meal(9, "شوربة الملك فارووق", "الشوربة", 70));
       listM.add(new Meal(10, "سلطة خضراء", "سلطة", 20));
       listM.add(new Meal(11, "سلطة  فتوش", "سلطة", 45));
       listM.add(new Meal(12, "ملوخية", "المقبلات الساخنة", 45));
       listM.add(new Meal(13, "سمبوسك لحم مفروم", "المقبلات الساخنة", 64));
       listM.add(new Meal(14, "طاجن ورق عنب بالكوارع", "المقبلات الساخنة", 110));
       listM.add(new Meal(15, "فراخ بون بون", "المقبلات الساخنة", 66));
       listM.add(new Meal(16, "طبق مقبلات ساخنة كومبو", "المقبلات الساخنة", 115));
       listM.add(new Meal(17, "سجق", "المقبلات الساخنة", 90));
       listM.add(new Meal(18, "ساندوتش كباب", "ساندوتشات", 70));
       listM.add(new Meal(19, "ساندوتش طرب", "ساندوتشات", 45));
       listM.add(new Meal(20, "ساندوتش كباب و كفتة", "ساندوتشات", 78));
       listM.add(new Meal(21, "ساندوتش كبدة مشوي على الفحم", "ساندوتشات", 73));
       listM.add(new Meal(22, "ساندوتش شيش طاووق", "ساندوتشات", 65));
       listM.add(new Meal(23, "ساندوتش كومبو", "ساندوتشات", 20));
       listM.add(new Meal(24, "طاجن ورق عنت باللحم القري", "طواجن", 159));

       tableM.setItems(listM);
      
       // D table
       
       numCD.setCellValueFactory(new PropertyValueFactory<Drink,Integer>("id"));
       nameCD.setCellValueFactory(new PropertyValueFactory<Drink,String>("name"));
       typeCD.setCellValueFactory(new PropertyValueFactory<Drink,String>("type"));
       costCD.setCellValueFactory(new PropertyValueFactory<Drink,Integer>("cost"));
       
       listD.add(new Drink(1, "شاي أخضر", "مشروبات ساخنة", 15));
       listD.add(new Drink(2, "قهوة", "مشروبات ساخنة", 15));
       listD.add(new Drink(3, "اناناس و روزماري", "مشروبات باردة", 39));
       listD.add(new Drink(4, "بلو لاجون", "مشروبات باردة", 34));
       listD.add(new Drink(5, "شاش خوخ مثلج", "مشروبات باردة", 30));
       listD.add(new Drink(6, "عصير ليمون بالنعناع", "عصائر فريش", 22));
       listD.add(new Drink(7, "عصير برتقال", "عصائر فريش", 32));
       listD.add(new Drink(8, "عصير سوبيا", "عصائر فريش", 29));
       listD.add(new Drink(9, "سفن أب", "مشروبات غازية", 15));
       listD.add(new Drink(10, "ميرندا برتقال", "مشروبات غازية", 15));
       listD.add(new Drink(11, "ميرندا تفاح", "مشروبات غازية", 15));
       listD.add(new Drink(12, "فيروز", "مشروبات غازية", 15));
       listD.add(new Drink(13, "بريل", "مشروبات غازية", 15));
       
       
       tableD.setItems(listD);
       
       //// 
       
      
        numDrinks.setText(listD.size()+"");
        numMeals.setText (listM.size()+"");
        
        ObservableList listM = FXCollections.observableArrayList("الأطباق الرئيسية","الشوربة","سلطة","ساندوتشات","مشويات","الستيك","طواجن","أصناف جانبية","أحسن الأطباق","المقبلات الساخنة","المقبلات الباردة","الباربيكيو");
        typeM.setItems(listM);
        
        ObservableList listD = FXCollections.observableArrayList("مشروبات ساخنة","مشروبات باردة","عصائر فريش","مشروبات غازية");
        typeD.setItems(listD);
    }

     
    
}
