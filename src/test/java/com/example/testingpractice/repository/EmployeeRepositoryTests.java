package com.example.testingpractice.repository;

import com.example.testingpractice.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Andrei")
                .lastName("Shumov")
                .email("verygool@yandex.ru").build();

    }

    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        //given
//        Employee employee = Employee.builder()
//                .firstName("Andrei")
//                .lastName("Shumov")
//                .email("verygool@yandex.ru").build();

        //when
        Employee savedEmployee = employeeRepository.save(employee);

        //then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenEmployessList_whenFindAll_thenReturnEmployeeList() {
        //given
//        Employee employee = Employee.builder()
//                .firstName("Andrei")
//                .lastName("Shumov")
//                .email("verygool@yandex.ru").build();
        Employee employee2 = Employee.builder()
                .firstName("Andrei2")
                .lastName("Shumov2")
                .email("verygool2@yandex.ru").build();
        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        //when
        List<Employee> savedEmployees = employeeRepository.findAll();


        //then
        assertThat(savedEmployees).isNotNull();
        assertThat(savedEmployees.size()).isEqualTo(2);

    }

    @Test
    public void givenEmployee_whenFindById_thenReturnEmployee() {
        //given
//        Employee employee = Employee.builder()
//                .firstName("Andrei")
//                .lastName("Shumov")
//                .email("verygool@yandex.ru").build();
        employeeRepository.save(employee);

        //when
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

        //then
        assertThat(savedEmployee).isNotNull();

    }

       @Test
           public void givenEmployee_whenFindByEmail_thenReturnEmployee() {
               //given
//           Employee employee = Employee.builder()
//                   .firstName("Andrei")
//                   .lastName("Shumov")
//                   .email("verygool@yandex.ru").build();
         employeeRepository.save(employee);
               //when
            Employee savedEmployee =employeeRepository.findByEmail(employee.getEmail()).get();
               //then
            assertThat(savedEmployee).isNotNull();

           }
              @Test
                  public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() {
                      //given
//                  Employee employee = Employee.builder()
//                          .firstName("Andrei")
//                          .lastName("Shumov")
//                          .email("verygool@yandex.ru").build();
                 employeeRepository.save(employee);

                      //when
                    Employee savedEmployee =employeeRepository.findById(employee.getId()).get();
                    savedEmployee.setEmail("updateEmail");
                    savedEmployee.setFirstName("Andreas");
                    Employee updatedEmployee =employeeRepository.save(savedEmployee);
                      //then
                    assertThat(updatedEmployee.getFirstName()).isEqualTo("Andreas");
                    assertThat(updatedEmployee.getEmail()).isEqualTo("updateEmail");
                  }
                     @Test
                         public void givenEmloyee_whenDelete_thenRemoveEmployee() {
                             //given
//                         Employee employee = Employee.builder()
//                                 .firstName("Andrei")
//                                 .lastName("Shumov")
//                                 .email("verygool@yandex.ru").build();
                       employeeRepository.save(employee);

                             //when
                         employeeRepository.delete(employee);
                         Optional<Employee> employeeOptional= employeeRepository.findById(employee.getId());

                             //then
                         assertThat(employeeOptional).isEmpty();

                         }
                            @Test
                                public void givenEmployee_whenFindByJPQL_thenReturnEmployee() {
                                    //given
//                                Employee employee = Employee.builder()
//                                        .firstName("Andrei")
//                                        .lastName("Shumov")
//                                        .email("verygool@yandex.ru").build();
                               employeeRepository.save(employee);
                                    //when
                                Employee savedEmployee=employeeRepository.findByJPQL("Andrei","Shumov");
                                    //then
                                assertThat(employee.getId()).isEqualTo(savedEmployee.getId());
                                }

    @Test
    public void givenEmployee_whenFindByNativeSQL_thenReturnEmployee() {
        //given
//        Employee employee = Employee.builder()
//                .firstName("Andrei")
//                .lastName("Shumov")
//                .email("verygool@yandex.ru").build();
        employeeRepository.save(employee);
        //when
        Employee savedEmployee=employeeRepository.findByNativeSQL("Andrei","Shumov");
        //then
        assertThat(employee.getId()).isEqualTo(savedEmployee.getId());
    }
}
