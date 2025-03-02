package demon.phone.controller;

import demon.person.entity.Person;
import demon.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class PhoneController {

    @FXML private TableView<Person> tvPhone;
    @FXML private TableColumn<Person, String> colId;
    @FXML private TableColumn<Person, String> colPerson;
    @FXML private TableColumn<Person, String> colPhoneNumber;
    @FXML private TableColumn<Person, String> colPhoneIpNumber;

    @FXML
    private void initialize() {
        initTablePhone();
        loadFromDbPerson();
    }

    private void initTablePhone() {
        colId.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
        colPerson.setCellValueFactory(new PropertyValueFactory<Person, String>("fioShort"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));
        colPhoneIpNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneIpNumber"));
    }

    private void loadFromDbPerson() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Person> queryPerson = session.createQuery("from Person", Person.class);
            List<Person> persons = queryPerson.getResultList();
            tvPhone.getItems().clear();
            tvPhone.getItems().addAll(persons);
        }
    }
}
