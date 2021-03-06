package fun.peri.arithmetic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private long id;
    private String name;
    private double age;
    private double score;
    private String classNum;

    public Student(String name, double age, double score, String classNum) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.classNum = classNum;
    }

    @Override
    public String toString() {
        return name + age + score + classNum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(age);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((classNum == null) ? 0 : classNum.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        temp = Double.doubleToLongBits(score);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (Double.doubleToLongBits(age) != Double.doubleToLongBits(other.age))
            return false;
        if (classNum == null) {
            if (other.classNum != null)
                return false;
        } else if (!classNum.equals(other.classNum))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name)) {
            return false;
        }
        return Double.doubleToLongBits(score) == Double.doubleToLongBits(other.score);
    }


}