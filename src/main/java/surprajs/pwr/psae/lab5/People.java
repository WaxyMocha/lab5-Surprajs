package surprajs.pwr.psae.lab5;

import java.util.ArrayList; //for ArrayLists

public class People{
    private ArrayList<Person> pList;
    
    public People() {
        this.pList = new ArrayList<>();
    }
        
    public void addPerson(String name, String surname, int age, double salary) {
        this.pList.add(new Person(name, surname, age, salary));
    }
    
    public Person getPerson(int index) {
        return this.pList.get(index);
    }
    
    
    public Person getTheOldestPerson() {
        Person ps = this.pList.get(0);
        int maxAge = ps.getAge();
        for (Person p: this.pList) {
            if (p.getAge() > maxAge) {
                maxAge = p.getAge();
                ps = p;
            }
        }
        return ps;
    }
    
    public double getAverageSalary() {
        double totalSalary = 0.0;
        for (Person p: this.pList) {
            totalSalary += p.getSalary();
        }
        return totalSalary/pList.size();
    }
    
    public int getSize() {
        return this.pList.size();
    }
}

