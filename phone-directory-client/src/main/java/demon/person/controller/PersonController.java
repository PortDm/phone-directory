package demon.person.controller;

import demon.person.entity.Department;
import demon.person.entity.Person;
import demon.util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class PersonController {

    @FXML private TableView<Person> tvPerson;
    @FXML private TableColumn<Person, String> colId;
    @FXML private TableColumn<Person, String> colFio;
    @FXML private TableColumn<Person, String> colDepartment;
    @FXML private TableColumn<Person, String> colPost;

    @FXML
    private void initialize() {
        initTablePerson();
        loadFromDbPerson();
    }

    private void initTablePerson() {
        colId.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
        colFio.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<Person, String>("nameDepartment"));
        colPost.setCellValueFactory(new PropertyValueFactory<Person, String>("post"));
    }

    @FXML
    private void onBtnCreatePerson() throws IOException {
        FXMLLoader loaderPersonCreate = new FXMLLoader(PersonController.class.getResource("/demon/person/view/person-create-view.fxml"));
        Scene scene = new Scene(loaderPersonCreate.load(), 500, 290);
        Stage stage = new Stage();
        stage.setTitle("Создать сотрудника");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        loadFromDbPerson();
    }

    public void onBtnCreateDepartment() throws IOException {
        FXMLLoader loaderCreateDepartment = new FXMLLoader(PersonController.class.getResource("/demon/person/view/department-create-view.fxml"));
        Scene scene = new Scene(loaderCreateDepartment.load(), 400, 150);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Создать подразделение");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void loadFromDbPerson() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Person> queryPerson = session.createQuery("from Person", Person.class);
            List<Person> persons = queryPerson.getResultList();
            tvPerson.getItems().clear();
            tvPerson.getItems().addAll(persons);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void onBtnGetDepartment(ActionEvent actionEvent) {
        loadFromDbPerson();
    }
}
