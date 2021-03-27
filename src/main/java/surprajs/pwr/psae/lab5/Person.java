package surprajs.pwr.psae.lab5;

import java.util.Random;

public class Person {
    private String name, surname;
    private int age;
    private double salary;
    
    
    public Person(String name, String surname, int age, double salary) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return this.name;    
    }
    
    public String getSurname() {
        return this.surname;    
    }
    
    public int getAge() {
        return this.age;
    }
    
    public double getSalary() {
        return this.salary;
    }
    
    public static int randAge() {
        return new Random().nextInt(50)+21;
    }
    
    public static double randSalary() {
        return new Random().nextDouble()*10000;
    }
}