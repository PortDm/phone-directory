import demon.person.entity.Department;
import demon.person.entity.Person;
import demon.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.Objects;

public class PhoneDirectory extends Application {
    public void start(Stage stage) throws IOException {
        for(int i=0; i<5; i++) {
            testPerson();
        }

        FXMLLoader loaderMain = new FXMLLoader(PhoneDirectory.class.getResource("demon/common/view/main-view.fxml"));
        AnchorPane paneMain = loaderMain.load();

        AnchorPane apWrap = (AnchorPane) paneMain.getChildren()
                .filtered(node -> Objects.equals(node.getId(), "apWrap"))
                .getFirst();
        FXMLLoader loaderPerson = new FXMLLoader(PhoneDirectory.class.getResource("demon/person/view/person-view.fxml"));
        apWrap.getChildren().add(loaderPerson.load());

        Scene sceneMain = new Scene(paneMain, 700, 400);
        stage.setTitle("Phone Directory");
        stage.setScene(sceneMain);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void testPerson() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Department department = new Department();
            department.setNameDepartment("отдел иноваций");
            department.setDescription("лучший отдел");

            session.persist(department);

            Person person = new Person();
            person.setLastName("Портных");
            person.setFirstName("Дмитрий");
            person.setSecondName("Викторович");
            person.setDepartment(department);
            person.setPost("Начальник");
            person.setPhoneNumber("701");
            person.setPhoneIpNumber("7001");

            session.persist(person);
            session.getTransaction().commit();
        }
    }
}

