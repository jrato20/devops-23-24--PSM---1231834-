package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void getId() {
        Employee employee = new Employee("Frodo", "Baggins", "Lord of the Rings", 30, "frodo_baggins@gmail.com");
        long idExpected = 123;
        employee.setId(idExpected);
        assertEquals(idExpected, employee.getId());
    }

    @Test
    void setId() {
        Employee employee = new Employee("Frodo", "Baggins", "Lord of the Rings", 30, "frodo_baggins@gmail.com");
        long idExpected = 15;
        employee.setId(idExpected);
        assertEquals(idExpected, employee.getId());
    }

    @Test
    void getFirstName() {
        Employee employee = new Employee("Bilbo", "Baggins", "Slytherin", 18, "bilbo_baggins@gmail.com");
        String nameExpected = "Bilbo";
        assertEquals(nameExpected, employee.getFirstName());
    }

    @Test
    void setFirstName() {
        Employee employee = new Employee("Sirius", "Black", "Slytherin", 18, "sirius_black@gmail.com");
        String nameExpected = "Padfoot";
        employee.setFirstName(nameExpected);
        assertEquals(nameExpected, employee.getFirstName());
    }

    @Test
    void getLastName() {
        Employee employee = new Employee("Legolas", "Greenleaf", "Prince of the Woodland Realm", 1000000, "legolas_greenleaf@gmail.com");
        String nameExpected = "Greenleaf";
        assertEquals(nameExpected, employee.getLastName());
    }

    @Test
    void setLastName() {
        Employee employee = new Employee("Legolas", " Greenleaf", "Prince of the Woodland Realm", 1000000, "legolas_greenleaf@gmail.com");
        String nameExpected = "Sindar Elf";
        employee.setLastName(nameExpected);
        assertEquals(nameExpected, employee.getLastName());
    }

    @Test
    void getDescription() {
        Employee employee = new Employee("Severus", "Snape", "Slytherin", 18, "severus_snape@gmail.com");
        String descriptionExpected = "Slytherin";
        assertEquals(descriptionExpected, employee.getDescription());
    }

    @Test
    void setDescription() {
        Employee employee = new Employee("Severus", "Snape", "Slytherin", 18, "severus_snape@gmail.com");
        String descriptionExpected = "Gryffindor";
        employee.setDescription(descriptionExpected);
        assertEquals(descriptionExpected, employee.getDescription());
    }

    @Test
    void getJobYears() {
        Employee employee = new Employee("Gimli", "son of Glóin", "Member of the Fellowship of the Ring.", 120, "gimli_gloin@gmail.com");
        int jobYearsExpected = 120;
        assertEquals(jobYearsExpected, employee.getJobYears());
    }

    @Test
    void setJobYears() {
        Employee employee = new Employee("Gimli", "son of Glóin", "Member of the Fellowship of the Ring.", 120, "gimli_gloin@gmail.com");
        int jobYearsExpected = 500;
        employee.setJobYears(jobYearsExpected);
        assertEquals(jobYearsExpected, employee.getJobYears());
    }

    @Test
    void getEmail() {
        Employee employee = new Employee("Gimli", "son of Glóin", "Member of the Fellowship of the Ring.", 120, "frodo_baggins@gmail.com");
        String emailExpected = "frodo_baggins@gmail.com";
        assertEquals(emailExpected, employee.getEmail());
    }

    @Test
    void setEmail() {
        Employee employee = new Employee("Gimli", "son of Glóin", "Member of the Fellowship of the Ring.", 120, "gimli_gloin@gmail.com");
        String emailExpected = "gimli_gloin@gmail.com";
        employee.setEmail(emailExpected);
        assertEquals(emailExpected, employee.getEmail());
    }

    @Test
    void testToString() {
        Employee employee = new Employee("Jack", "Sparrow", "Captain", 33, "jack_sparrow@gmail.com");
        long idExpected = 17;
        employee.setId(idExpected);
        String expected = "Employee{id=17, firstName='Jack', lastName='Sparrow', description='Captain', jobYears='33', email='jack_sparrow@gmail.com'}";
        String result = employee.toString();
        assertEquals(expected, result);
    }

    @Test
    void testEquals() {
        Employee employee1 = new Employee("Fitzwilliam", "Darcy", "Novel", 30, "fitzwilliam_darcy@gmail.com");
        Employee employee2 = new Employee("Elizabeth", "Bennet", "Novel", 20, "elizabeth_darcy@gmail.com");
        Employee employee3 = new Employee("Fitzwilliam", "Darcy", "Novel", 30, "fitzwilliam_darcy@gmail.com");

        assertTrue(employee1.equals(employee3));
        assertFalse(employee1.equals(employee2));
    }

    @Test
    void testHash() {
        Employee employee1 = new Employee("Fitzwilliam", "Darcy", "Novel", 30, "fitzwilliam_darcy@gmail.com");
        Employee employee2 = new Employee("Elizabeth", "Bennet", "Novel", 20, "elizabeth_darcy@gmail.com");
        Employee employee3 = new Employee("Fitzwilliam", "Darcy", "Novel", 30, "fitzwilliam_darcy@gmail.com");

        assertEquals(employee1.hashCode(), employee3.hashCode());
        assertNotEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    void firstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(null, "Targaryen", "Khaleesi", 10, "elizabeth_darcy@gmail.com"));
    }

    @Test
    void firstNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("", "Targaryen", "Khaleesi", 10, "elizabeth_darcy@gmail.com"));
    }

    @Test
    void lastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Daenerys", null, "Khaleesi", 10, "elizabeth_darcy@gmail.com"));
    }

    @Test
    void lastNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Daenerys", "", "Khaleesi", 10, "elizabeth_darcy@gmail.com"));
    }

    @Test
    void descriptionNull() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Daenerys", "Targaryen", null, 10, "elizabeth_darcy@gmail.com"));
    }

    @Test
    void descriptionEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Daenerys", "Targaryen", "", 10, "elizabeth_darcy@gmail.com"));
    }

    @Test
    void jobYearsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Daenerys", "Targaryen", "Khaleesi", -10, "elizabeth_darcy@gmail.com"));
    }

    @Test
    void emailEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Joni", "Mouse", "RatLand", 24, ""));
    }

    @Test
    void emailNull() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Joni", "Mouse", "BigRat", 1000, null));

    }

    @Test
    void EmailInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Daenerys", "Targaryen", "Khaleesi", 10, "elizabeth_darcygmail.com"));
    }
}

