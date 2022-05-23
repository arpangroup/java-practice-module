import com.arpangroup.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTest {
    public List<Employee> employees ;
    public List<Integer> numbers;
    public Map<String, Integer> map;

    @BeforeEach
    void init() {
        employees = Arrays.asList(
                new Employee(1, "Ram", "IT", 5000),
                new Employee(2, "Shyam", "IT", 10000),
                new Employee(3, "Laxman", "TESTING", 15000),
                new Employee(4, "John", "UI", 20000),
                new Employee(5, "Bob", "QA", 25000),
                new Employee(6, "Peter", "DEVOPS", 30000),
                new Employee(7, "Rahul", "DEVOPS", 35000),
                new Employee(8, "Ram", "DEVOPS", 40000)
        );

        numbers = Arrays.asList(3,2,5,1,4,1,2);

        map = new HashMap<>();
        map.put("book", 2);
        map.put("Eagle", 5);
        map.put("cat", 3);
        map.put("dog", 4);
        map.put("Xapple", 1);
    }

    @Test
    public void test_sliceOfListUsingStream(){
        employees.stream()
                .limit(3)
                .map(e -> e.getId() + "==>" +e.getName())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    @Test
    public void test_duplicate_Using_Frequency(){
        Set<Integer> duplicateNumbers = numbers.stream()
                .filter(i -> Collections.frequency(numbers, i) > 1)
                .collect(Collectors.toSet());
        System.out.println(duplicateNumbers);
    }

    @Test
    public void test_duplicate_Using_GroupingBy(){
        Set<String> duplicates = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .filter(s -> s.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println(duplicates);
    }

    @Test
    public void test_duplicate_Using_Set_And_Stream(){
        System.out.println("ORIGINAL: " + numbers);
        Set<Integer> set = new HashSet<>();

        numbers.stream()
                .filter(i -> !set.add(i))
                .collect(Collectors.toSet())
                .forEach(System.out::println);
    }


    @Test
    public void test_getAllEmployeeInADepartmentUsingPartitionBy(){
        Map<Boolean, Long> it = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getDepartment().equalsIgnoreCase("IT"), Collectors.counting()));
        System.out.println(it);

    }

    @Test
    public void test_getAllEmployeeInADepartmentUsingGroupBy(){
        Map<String, Long> deptCountMap =  employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.counting()));
        System.out.println(deptCountMap);

        Map<String, List<Employee>> deptMap =  employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        deptMap.entrySet().forEach(System.out::println);
    }

    @Test
    public void test_groupEmployeeBasedOnDepartmentAndMapTheEmployeeNameToUppercase(){
        Map<String, List<Object>> deptMap =  employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(e -> e.getName().toUpperCase(), Collectors.toList()) ));

        System.out.println(deptMap);
    }

    @Test
    public void test_countTheEmployeeBasedOnDept(){
        Map<String, Long> deptCountMap =  employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.counting()));
        System.out.println(deptCountMap);
    }

    @Test
    public void test_printMaxEmployeeSalary(){
        // sort the employee based on salary
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .map(e -> e.getId() + ": " +e.getName() + "==>" + e.getSalary())
                .forEach(System.out::println);


       Employee emp =  employees.stream().max((o1, o2) -> (int) (o1.getSalary() - o2.getSalary())).get();
       System.out.println(emp);
    }


    @Test
    public void test_sortEmployeeBasedOnTheirSalaryInDESC(){
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).forEach(System.out::println);
    }


    @Test
    public void test_printMaxSalaryOfEmployeeFromEachDept(){
        Map<String, Optional<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                                Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))
                        )
                );
        collect.entrySet().forEach(k -> System.out.println(k.getKey() + "==>" + k.getValue()));
    }


    @Test
    public void test_fetchTop3SalariedEmployees(){
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).forEach(System.out::println);
    }


    @Test
    public void test_fetchAllEmployeeHavingSalaryLessThenThirdHighestSalary(){
    }


    @Test
    public void test_sortHashMap_By_Value(){
        System.out.println("ORIGINAL: " + map);
        List<Map.Entry<String, Integer>> collect = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

    }

    @Test
    public void test_No_Of_Occurance_Of_Words_In_Given_String(){
        String str = "Welcome to code decode and code decode"; // o/p: {code= 2, and=1, to=1, decode=2}
        List<Map.Entry<String, Long>> collect = Arrays.stream(str.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

        System.out.println(collect);
    }

}
