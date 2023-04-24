package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.AddTourWindowViewModel;
import at.fhtw.swen2.tutorial.service.dto.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;



@Component
@Scope("prototype")
public class AddTourWindowView implements Initializable {

    public javafx.scene.control.Label titleLabel;

    @Autowired
    private AddTourWindowViewModel addTourWindowViewModel;


    @FXML
    private TextField tourNameTextField;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private ChoiceBox transportModeCheckBox;

    @FXML
    private Button confirmTourButton;

    @FXML
    private Button cancelButton;

    public AddTourWindowView(AddTourWindowViewModel addTourWindowViewModel) {
        this.addTourWindowViewModel = addTourWindowViewModel;
    }

    public void initialize(URL location, ResourceBundle rb) {
        tourNameTextField.textProperty().bindBidirectional(addTourWindowViewModel.nameStringProperty());
        fromTextField.textProperty().bindBidirectional(addTourWindowViewModel.fromStringProperty());
        toTextField.textProperty().bindBidirectional(addTourWindowViewModel.toStringProperty());
        descriptionTextField.textProperty().bindBidirectional(addTourWindowViewModel.descriptionStringProperty());
    }

    public void confirmTourButtonAction(ActionEvent event)  {
        Tour tour = Tour.builder()
                .name(tourNameTextField.getText())
                .description(fromTextField.getText())
                .from(toTextField.getText())
                .to(descriptionTextField.getText())
                .distance(33.)
                .estimatedTime(33.)
                .transportType("foot")
                .routeInformation("link")
                .build();
        addTourWindowViewModel.addTour(tour);

    }


}
