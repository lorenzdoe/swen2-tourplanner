package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.TourLogListViewModel;
import at.fhtw.swen2.tutorial.service.dto.TourLog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
public class TourLogListView implements Initializable{

    @Autowired
    public TourLogListViewModel tourLogListViewModel;

    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;
    @Override
    public void initialize(URL location, ResourceBundle rb){
        tableView.setItems(tourLogListViewModel.getTourLogListListItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn id = new TableColumn("ID");
        id.setMinWidth(20);
        id.setMaxWidth(50);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn comment = new TableColumn("COMMENT");
        comment.setCellValueFactory(new PropertyValueFactory("comment"));
        TableColumn date = new TableColumn("DATE");
        date.setCellValueFactory(new PropertyValueFactory("dateTime"));
        TableColumn difficulty = new TableColumn("DIFFICULTY");
        difficulty.setCellValueFactory(new PropertyValueFactory("difficulty"));
        tableView.getColumns().addAll(id, comment, date, difficulty);

        dataContainer.getChildren().add(tableView);
        tourLogListViewModel.initList();

        tableView.setRowFactory(tv -> {
            TableRow<TourLog> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    TourLog rowData = row.getItem();
                    tourLogListViewModel.select(rowData);
                }
            });
            return row;
        });


    }

}
