package demon.util;

import demon.person.entity.Department;
import javafx.util.StringConverter;

public class ComboxUtil extends StringConverter<Department> {

        @Override
        public String toString(Department street) {
            return street == null ? null : street.getNameDepartment();
        }

        @Override
        public Department fromString(String string) {
            return null;
        }
    
}
