package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.NewPersonViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class NewPersonView implements Initializable {

    @Autowired
    private NewPersonViewModel newPersonViewModel;
    @Autowired
    private TourListViewModel tourListViewModel;
    @FXML
    private Button addTourButton;
    @FXML
    private Button deleteTourButton;
    @FXML
    private Label feedbackText;
    @FXML
    private TextField nameTextField;


    @Override
    public void initialize(URL location, ResourceBundle rb) {
        nameTextField.textProperty().bindBidirectional(newPersonViewModel.nameProperty());
        feedbackText.textProperty().bind(tourListViewModel.selectedTourNameProperty());
        System.out.println("initailized");
    }

    public void submitButtonAction(ActionEvent event) {
        if (nameTextField.getText().isEmpty()) {
            //feedbackText.setText("nothing entered!");
            return;
        }
        newPersonViewModel.addNewPerson();
    }

    public void addTourButtonAction(ActionEvent event) {
        //feedbackText.setText("Add Tour Button pressed!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTourWindow.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTourButtonAction(ActionEvent event) {
        //feedbackText.setText("Delete Tour Button pressed!");
        feedbackText.setText(tourListViewModel.getSelected().getName());
    }
}
