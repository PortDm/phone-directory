package demon.util;

import demon.person.entity.Department;
import javafx.util.StringConverter;

public class ComboboxUtil extends StringConverter<Department> {
        @Override
        public String toString(Department street) {
            return street == null ? null : street.getNameDepartment();
        }

        @Override
        public Department fromString(String string) {
            return null;
        }
}


//    private void setCellFactory(SearchableComboBox<Department> cbDepartment) {
//        cbDepartment.setCellFactory(p -> new ListCell<Department>() {
//            @Override
//            protected void updateItem(Department item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item != null && !empty) {
//                    setText(item.getNameDepartment());
//                } else {
//                    setText(null);
//                }
//            }
//        });
//    }