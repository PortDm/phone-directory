package demon.person.controller;

import demon.person.entity.Department;
import demon.util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.hibernate.Session;

import java.util.Optional;

public class DepartmentCreateController {

    @FXML private Button btnCancelDepartment;
    @FXML private TextField tfNameDepartment;
    @FXML private TextField tfDescription;


    public void onBtnCancelDepartment() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Внимание");
        alert.setHeaderText("Введенные данные будут потеряны");
        alert.setContentText("Продолжить?");
        Optional<ButtonType> win = alert.showAndWait();
        if (win.isPresent() && win.get() == ButtonType.OK) {
            btnCancelDepartment.getScene().getWindow().hide();
        }
    }


    public void onBtnCreateDepartment(ActionEvent actionEvent) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Department department = new Department();
            department.setNameDepartment(tfNameDepartment.getText().trim());
            department.setDescription(tfDescription.getText().trim());

            session.beginTransaction();
            session.persist(department);
            session.getTransaction().commit();

            btnCancelDepartment.getScene().getWindow().hide();
        }
    }


}
