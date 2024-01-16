package com.blackdotan.elephant.common.function;

import com.blackdotan.elephant.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/6/6.
 */
public class CollectionHelperTest {
    private List<Employee> arr=  new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        arr.add(new Employee(1,"1", null));
        arr.add(new Employee(2,"2", 1));
        arr.add(new Employee(3,"3", 1));
        arr.add(new Employee(4,"4", 1));
        arr.add(new Employee(5,"5", 2));
        arr.add(new Employee(6,"6", 2));
        arr.add(new Employee(7,"7", 3));
        arr.add(new Employee(8,"8", 3));
        arr.add(new Employee(9,"9", 4));
        arr.add(new Employee(10,"10", 4));


    }

    @Test
    public void testTotreeAndToarr() {
        List<Employee> totree = CollectionHelper.totree(arr, new IPredicate<Employee, Employee>() {
            @Override
            public Boolean test(Employee employee, Employee employee2) {
                return employee.getId().equals(employee2.getParent());
            }
        }, new IFunction<Employee, Employee>() {
            @Override
            public void apply(Employee employee, Employee employee2) {
                employee.getChildren().add(employee2);
            }
        }, new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getParent() == null;
            }
        });
        System.out.println(JsonUtils.parserObj2String(totree));

        List<Employee> toarr = CollectionHelper.toarr(totree, new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return !employee.getChildren().isEmpty();
            }
        }, new Function<Employee, List<Employee>>() {
            @Override
            public List<Employee> apply(Employee employee) {
                return employee.getChildren();
            }
        });
        System.out.println(JsonUtils.parserObj2String(toarr));
    }



    class Employee {
        private Integer id;
        private String name;
        private Integer parent;
        private List<Employee> children = new ArrayList<>();

        public Employee(Integer id, String name, Integer parent) {
            this.id = id;
            this.name = name;
            this.parent = parent;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getParent() {
            return parent;
        }

        public void setParent(Integer parent) {
            this.parent = parent;
        }

        public List<Employee> getChildren() {
            return children;
        }

        public void setChildren(List<Employee> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", parent=" + parent +
                    ", children=" + children +
                    '}';
        }
    }
}
