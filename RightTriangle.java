package TriangleSolver;

public class RightTriangle extends Triangle {
    
    // Constructor with three sides
    public RightTriangle(double a, double b, double c) {
        super(a, b, c);
        if (!isRightTriangle(a, b, c)) {
            throw new InvalidException("side(s) a, b, and/or c do not form a right triangle");
        }
    }
    
    // Constructor with sides and angles
    public RightTriangle(double a, double b, double c, double A, double B, double C) {
        super(a, b, c, A, B, C);
        if (!isRightTriangle(a, b, c)) {
            throw new InvalidException("side(s) a, b, and/or c do not form a right triangle");
        }
    }

    // Constructor with base and height
    public RightTriangle(double base, double height) {
        super(base, height);
        this.isRight = true;
    }

    // Check if the sides form a right triangle
    private boolean isRightTriangle(double a, double b, double c) {
        return Math.abs(a * a + b * b - c * c) < 1e-9 ||
               Math.abs(a * a + c * c - b * b) < 1e-9 ||
               Math.abs(b * b + c * c - a * a) < 1e-9;
    }
    
    public static void main(String[] args) {
        RightTriangle rt1 = new RightTriangle(3, 4, 5);
        System.out.println(rt1.toString());
        System.out.println("Area: " + rt1.area());
        System.out.println("Perimeter: " + rt1.perimeter());
        System.out.println("Type by angles: " + rt1.triangleTypeA());
        System.out.println("Type by sides: " + rt1.triangleTypeS());
    }
}
