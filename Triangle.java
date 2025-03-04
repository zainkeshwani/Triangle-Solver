package TriangleSolver;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    
    protected double a = -1, b = -1, c = -1; // sides
    protected double A = -1, B = -1, C = -1; // angles
    protected double base = -1, height = -1; 
    protected boolean isBaseHeight, isRight;

    // Constructor with three sides
    public Triangle(double a, double b, double c) {
        if (!validTriangleS(a, b, c)) {
            throw new InvalidException("side(s) a and/or b and/or c");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.isBaseHeight = false;
        
        this.isRight = triangleTypeA().equals("Right");
        
        //we can find the angles of the triangle if they are not given to us
        double[] angles = getExpectedAngles();
        this.A = angles[0];
        this.B = angles[1];
        this.C = angles[2];
    }

    // Constructor with sides and angles
    public Triangle(double a, double b, double c, double A, double B, double C) {
        this(a, b, c); // call constructor for three sides for code reusability
        if (!validTriangleA(A, B, C)) {
            throw new InvalidException("angle(s) A and/or B and/or C");
        }
        
        double[] angles = getExpectedAngles();
        
        if(angles[0] != A || angles[1] != B || angles[2] != C) {
        	throw new InvalidException("angle(s) A and/or B and/or C do not align with expected values");
        }
        
        this.A = A;
        this.B = B;
        this.C = C;
    }
    
    // Constructor with base and height
    public Triangle(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new InvalidException("base and/or height");
        }
        this.base = base;
        this.height = height;
        this.isBaseHeight = true;
    }

    // Validate sides of the triangle
    protected boolean validTriangleS(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a;
    }

    // Validate angles of the triangle
    protected boolean validTriangleA(double A, double B, double C) {
        return A > 0 && B > 0 && C > 0 && A + B + C == 180.0;
    }

    // Calculate angles if only given sides
    protected double[] getExpectedAngles() {
        double[] angles = new double[3];
        angles[0] = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c)));
        angles[1] = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c)));
        angles[2] = Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2 * a * b)));
        return angles;
    }
    
    // Calculate the area of the triangle
    public double area() {
        if (isBaseHeight) {
            return (base * height) / 2;
        } else {
            double s = (a + b + c) / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }
    }

    // Calculate the perimeter of the triangle
    public double perimeter() {
        return isBaseHeight ? -1 : a + b + c;
    }

    // Determine the type of the triangle by angles
    public String triangleTypeA() {
        if (a == -1 || b == -1 || c == -1) {
            return "Need all three sides for triangle type calculation";
        }
        double angleC = Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2 * a * b)));
        if (Math.abs(angleC - 90) < 1e-9) {
            return "Right";
        } else if (angleC < 90) {
            return "Acute";
        } else {
            return "Obtuse";
        }
    }

    // Determine the type of the triangle by sides
    public String triangleTypeS() {
        if (a == -1 || b == -1 || c == -1) {
            return "Need all three sides for triangle type calculation";
        }
        if (a == b && b == c) {
            return "Equilateral";
        } else if (a == b || b == c || a == c) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        list.add("a=" + this.a);
        list.add("b=" + this.b);
        list.add("c=" + this.c);
        list.add("A=" + this.A);
        list.add("B=" + this.B);
        list.add("C=" + this.C);
        list.add("base=" + this.base);
        list.add("height=" + this.height);
        return String.join(", ", list);
    }

    public static void main(String[] args) {
        Triangle t1 = new Triangle(3, 4, 5);
        System.out.println(t1.toString());
        System.out.println("Area: " + t1.area());
        System.out.println("Perimeter: " + t1.perimeter());
        System.out.println("Type by angles: " + t1.triangleTypeA());
        System.out.println("Type by sides: " + t1.triangleTypeS());
    }
}

class InvalidException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public InvalidException() {
        super();
    }
    
    public InvalidException(String s) {
        super("Input was an invalid number for " + s + ". Please correct and re-run the program.");
    }
}
