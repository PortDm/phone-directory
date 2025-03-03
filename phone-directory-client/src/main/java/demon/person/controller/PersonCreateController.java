package demon.person.controller;

import demon.person.entity.Department;
import demon.person.entity.Person;
import demon.util.ComboboxUtil;
import demon.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.SearchableComboBox;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PersonCreateController {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfSecondName;
    @FXML
    private SearchableComboBox<Department> cbDepartment;
    @FXML
    private TextField tfPost;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private TextField tfPhoneIpNumber;

    private boolean isEditable = false;
    private Person person;

    public void initialize() {
        setCbDepartment();
    }

    private void setCbDepartment() {
        loadFromDbDepartment();
        cbDepartment.getItems().addAll(listDepartments);
        cbDepartment.setConverter(new ComboboxUtil());
    }

    public void onBtnCreatePerson() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (!isEditable) {
                person = new Person();
            } else {
                person = session.get(Person.class, person.getId());
            }


            person.setLastName(tfLastName.getText().trim());
            person.setFirstName(tfFirstName.getText().trim());
            person.setSecondName(tfSecondName.getText().trim());
            person.setDepartment(cbDepartment.getSelectionModel().getSelectedItem());
            person.setPost(tfPost.getText().trim());
            person.setPhoneNumber(tfPhoneNumber.getText().trim());
            person.setPhoneIpNumber(tfPhoneIpNumber.getText().trim());

            session.beginTransaction();
            session.persist(person);
            session.getTransaction().commit();

            btnCancel.getScene().getWindow().hide();
        }

    }

    public void onBtnCancel() {
        Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION);
        confirmCancel.setTitle("Внимание");
        confirmCancel.setHeaderText("Введенные данные будут потеряны");
        confirmCancel.setContentText("Продолжить?");
        Optional<ButtonType> result = confirmCancel.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            btnCancel.getScene().getWindow().hide();
        }
    }

    List<Department> listDepartments;

    private void loadFromDbDepartment() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Department> queryDepartments = session.createQuery("from Department", Department.class);
            listDepartments = queryDepartments.getResultList();
            session.getTransaction().commit();
        }
    }

    public void setPerson(Person personEditable) {
        btnCreate.setText("Редактировать");
        isEditable = true;
        person = personEditable;

        tfLastName.setText(person.getLastName());
        tfFirstName.setText(person.getFirstName());
        tfSecondName.setText(person.getSecondName());
        tfPost.setText(person.getPost());
        tfPhoneNumber.setText(person.getPhoneNumber());
        tfPhoneIpNumber.setText(person.getPhoneIpNumber());
        cbDepartment.getSelectionModel().select(person.getDepartment());
    }
}
