/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class LoginController implements Initializable {

   @FXML
   TextField username;
   @FXML
   PasswordField password;
   
   public void login(Event e){
       try {
            if(username.getText().trim().matches("[aA]dmin") && password.getText().equals("123")){
                Parent root = FXMLLoader.load(getClass().getResource("/resturant/MainMenu.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Main");
                
                Rectangle2D rd = Screen.getPrimary().getVisualBounds();
                stage.setX( (rd.getWidth()-stage.getWidth())/2);
                stage.setY( (rd.getHeight()-stage.getHeight())/2);
            }
            else {
                JOptionPane.showMessageDialog(null,"Sorry, your username or password was incorrect. Please double-check your username and password.");
            }
       } 
       catch (Exception ex) {
           
       }
   } 
    
   public void exit(){
       Platform.exit();
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
