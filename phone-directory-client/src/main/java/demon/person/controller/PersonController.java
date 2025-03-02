package demon.person.controller;

import demon.person.entity.Person;
import demon.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
//        loadFromDbPerson();
    }

    private void initTablePerson() {
        colId.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
        colFio.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<Person, String>("department"));
        colPost.setCellValueFactory(new PropertyValueFactory<Person, String>("post"));
    }

    @FXML
    private void onBtnCreatePerson() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Person person = new Person();
            person.setLastName("Портных");
            person.setFirstName("Дмитрий");
            person.setSecondName("Викторович");
            person.setDepartment("инновационный отдел");
            person.setPost("старший инженер");
            person.setPhoneNumber("701");
            person.setPhoneIpNumber("2701");

            session.beginTransaction();
            session.persist(person);
            session.getTransaction().commit();
            session.close();

            loadFromDbPerson();
        }
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
}
