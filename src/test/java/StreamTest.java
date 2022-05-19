import com.arpangroup.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public List<Employee> employees ;

    @BeforeEach
    void init() {
        employees = Arrays.asList(
                new Employee(1, "Ram", "IT", 5000),
                new Employee(2, "Shyam", "IT", 10000),
                new Employee(3, "Laxman", "TESTING", 15000),
                new Employee(4, "John", "UI", 20000),
                new Employee(5, "Bob", "QA", 250000),
                new Employee(6, "Peter", "DEVOPS", 30000),
                new Employee(7, "Rahul", "DEVOPS", 35000),
                new Employee(8, "Ram", "DEVOPS", 40000)
        );
    }

    @Test
    public void test_sliceOfListUsingStream(){
        System.out.println(employees);
    }

    @Test
    public void test_duplicateName(){
    }

    @Test
    public void test_getAllEmployeeInADepartmentUsingPartitionBy(){
    }

    @Test
    public void test_getAllEmployeeInADepartmentUsingGroupBy(){
    }

    @Test
    public void test_groupEmployeeBasedOnDepartmentAndMapTheEmployeeNameToUppercase(){
    }

    @Test
    public void test_countTheEmployeeBasedOnDept(){
    }

    @Test
    public void test_printMaxEmployeeSalary(){
    }


    @Test
    public void test_printMaxSalaryOfEmployeeFromEachDept(){
    }

    @Test
    public void test_sortEmployeeBasedOnTheirSalaryInDESC(){
    }

    @Test
    public void test_fetchTop3SalariedEmployees(){
    }


    @Test
    public void test_fetchAllEmployeeHavingSalaryLessThenThirdHighestSalary(){
    }
}
