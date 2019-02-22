package fun.peri.arithmetic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircleRadius {
    double radius;

    public CircleRadius() {
    }

    public CircleRadius(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

}
