package demon.person.controller;

import demon.person.entity.Department;
import demon.person.entity.Person;
import demon.util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class PersonController {

    @FXML
    private TableView<Person> tvPerson;
    @FXML
    private TableColumn<Person, String> colId;
    @FXML
    private TableColumn<Person, String> colFio;
    @FXML
    private TableColumn<Person, String> colDepartment;
    @FXML
    private TableColumn<Person, String> colPost;

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

    public void onTvMouseClicked(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (mouseEvent.getClickCount() == 2) {
                FXMLLoader loaderPerson = new FXMLLoader(PersonController.class.getResource("/demon/person/view/person-create-view.fxml"));
                Scene scene = new Scene(loaderPerson.load());
                PersonCreateController personCreateController = loaderPerson.getController();
                Person selectPerson = tvPerson.getSelectionModel().getSelectedItem();
                personCreateController.setPerson(selectPerson);
                Stage stage = new Stage();
                stage.setTitle("Редактировать сотрудника");
                stage.setScene(scene);
                stage.showAndWait();
                loadFromDbPerson();
            }
        }
    }

    public void onBtnRemovePerson() {
        Person personRemove = tvPerson.getSelectionModel().getSelectedItem();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(personRemove);
            session.getTransaction().commit();
            loadFromDbPerson();
        }
    }
}
