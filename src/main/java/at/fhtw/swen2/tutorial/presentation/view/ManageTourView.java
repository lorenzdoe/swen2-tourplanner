package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.ManageTourViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.TourListViewModel;
import at.fhtw.swen2.tutorial.service.dto.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@Slf4j
public class ManageTourView implements Initializable {

    @Autowired
    private ManageTourViewModel manageTourViewModel;
    @Autowired
    private TourListViewModel tourListViewModel;
    @FXML
    private Button addTourButton;
    @FXML
    private Label selectedTour;
    @FXML
    private Button searchTourButton;
    @FXML
    private TextField searchTourField;


    @Override
    public void initialize(URL location, ResourceBundle rb) {
        selectedTour.textProperty().bind(tourListViewModel.selectedTourNameProperty());
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
        if(tourListViewModel.getSelected() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Tour selected");
            alert.setContentText("Please select a Tour to delete");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("You are about to delete " + selectedTour.getText());
        alert.setContentText("Cannot be undone. Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            tourListViewModel.deleteSelectedTour();
        }
    }
}