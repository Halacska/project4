/*
 * Ilyés Antal
 */
package hu.unideb.inf.szekfoglalo_maven_fx.view;

import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import org.junit.Test;
import static org.loadui.testfx.Assertions.verifyThat;
import org.loadui.testfx.GuiTest;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 *
 * @author ilyes
 */
public class ControllerTest extends GuiTest{

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            return parent;
        } catch (IOException ex) {
            // TODO ...
        }
        return parent;
    }
    
    
    
    @Test
    public void sessionTest(){
        TextField username = find("#username");
        username.setText("Anti");
        //verifyThat("#username", hasText("Anti"));
        
        PasswordField password = find("#password");
        password.setText("jelszo");
        //verifyThat("#password", hasText("jelszo"));
        
        Button login = find("#login");
        click(login);
        
        click("OK");
        
        verifyThat("#username", hasText(""));
        
        username.setText("Anti");
        password.setText("jelszo");
        Button register = find("#register");
        click(register);
        
        click(login);
        
        click("OK");
        
        username.setText("Anti");
        password.setText("jelszo");
        
        click(login);
        
        Button endgame = find("#endgame");
        click(endgame);
        
        Label tab = find("Movies");
        click(tab);
        
        Button signout = find("#logout_btn");
        click(signout);
        
        username.setText("Anti");
        password.setText("jelszo");
        
        click(login);
        
        click(endgame);
        
        GridPane gp = find("#gp");
        
        click(gp.getChildren().get(23));
        click(gp.getChildren().get(24));
        click(gp.getChildren().get(25));
        click("Save");
        
        click(gp.getChildren().get(24));
        click(gp.getChildren().get(25));
        click("Delete");
        
        click(signout);
    }
}
