package aop;

public class Student {
    String nameSurname;
    int course;
    double avgGrade;

    public Student(String nameSurname,
                   int corse,
                   double avgGrade) {
        this.nameSurname = nameSurname;
        this.course = corse;
        this.avgGrade = avgGrade;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nameSurname='" + nameSurname + '\'' +
                ", corse=" + course +
                ", avgGrade=" + avgGrade +
                '}';
    }
}
