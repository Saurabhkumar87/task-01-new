import java.util.ArrayList;
import java.util.Scanner;

class student {
   private int id;
   private String name;
   private double marks;

   public student(int id , String name , double marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
   }
   public int getId(){
    return id;

   }
   public String getname(){
    return name;
   }
   public double getmarks(){
return marks;
   }
   public void setname(String name){
    this.name = name;
   }
   public void setmarks(double marks){
    this.marks = marks;
   }
   @Override
   public String toString() {
    return "student [id=" + id + ", name=" + name + ", marks=" + marks + "]";
   }
   
}


public class studentMgmt {
  private static ArrayList<student> students = new ArrayList<>();
  private static Scanner sc = new Scanner(System.in);
  
  public static void main(String[] args) {
    while(true){
        System.out.println("\n---- Student mgmt system --");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch(choice){
            case 1: addStudent(); break;
            case 2: viewStudents(); break;
            case 3: updateStudent(); break;
            case 4: deleteStudent(); break;
            case 5: System.out.println("Exiting..."); return;
            default: System.out.println("Invalid choice. Try again.");
        }

    }
  }
  private static void addStudent(){
    System.out.println(" enter id ");
    int id = Integer.parseInt(sc.nextLine());

    System.out.print("Enter Name: ");
    String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = Double.parseDouble(sc.nextLine());

        students.add(new student(id, name, marks));
        System.out.println("Student added successfully.");
  }
  private static void viewStudents() {
    if (students.isEmpty()) {
        System.out.println("No students found.");
        return;
    }

    for (student s : students) {
        System.out.println(s);
    }
}

private static void updateStudent() {
    System.out.print("Enter Student ID to update: ");
    int id = Integer.parseInt(sc.nextLine());

    for (student s : students) {
        if (s.getId() == id) {
            System.out.print("Enter new name: ");
            s.setname(sc.nextLine());

            System.out.print("Enter new marks: ");
            s.setmarks(Double.parseDouble(sc.nextLine()));

            System.out.println("Student updated.");
            return;
        }
    }

    System.out.println("Student not found.");
}
private static void deleteStudent() {
    System.out.print("Enter Student ID to delete: ");
    int id = Integer.parseInt(sc.nextLine());

    for (student s : students) {
        if (s.getId() == id) {
            students.remove(s);
            System.out.println("Student deleted.");
            return;
        }
    }

    System.out.println("Student not found.");
}
}
