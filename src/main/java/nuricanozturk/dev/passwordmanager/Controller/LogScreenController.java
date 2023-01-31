package nuricanozturk.dev.passwordmanager.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nuricanozturk.dev.passwordmanager.Entity.Log;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.service.LogService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogScreenController
{
    public TableView<Log> logTableView;
    public TableColumn<Log, LocalDateTime> dateColumn;
    public TableColumn<Log, Boolean> successColumn;
    private List<Log> logs = new ArrayList<>();


    public void initialize()
    {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        dateColumn.setCellFactory((column) -> getFormattedDate());
        successColumn.setCellValueFactory(new PropertyValueFactory<>("Success"));
        logTableView.setItems(FXCollections.observableArrayList(logs));
    }

    private TableCell<Log, LocalDateTime> getFormattedDate() {
        return new TableCell<>()
        {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:hh").format(item));
            }
        };
    }

    public void setLogs(List<Log> logs)
    {
        this.logs = logs;
        initialize();
    }
}
