package app.core;

public class Vec2 {
    public double x, y;
    public Vec2() {
        x = y = 0.0;
    }
    public Vec2(double d) {
        x = y = d;
    }
    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vec2 neg() {
        return new Vec2(-x, -y);
    }
    public Vec2 add(Vec2 v) {
        return new Vec2(x + v.x, y + v.y);
    }
    public Vec2 sub(Vec2 v) {
        return new Vec2(x - v.x, y - v.y);
    }
    public Vec2 mul(Vec2 v) {
        return new Vec2(x * v.x, y * v.y);
    }
    public Vec2 div(Vec2 v) {
        return new Vec2(x / v.x, y / v.y);
    }
    public Vec2 add(double d) {
        return new Vec2(x + d, y + d);
    }
    public Vec2 sub(double d) {
        return new Vec2(x - d, y - d);
    }
    public Vec2 mul(double d) {
        return new Vec2(x * d, y * d);
    }
    public Vec2 div(double d) {
        return new Vec2(x / d, y / d);
    }
    public double dot(Vec2 v) {
        return x * v.x + y * v.y;
    }
    public double len() {
        return Math.sqrt(x * x + y * y);
    }
    public double lenSq() {
        return x * x + y * y;
    }
    public Vec2 normalize() {
        double len = this.len();
        if (len == 0.0) {
            return new Vec2(0.0, 0.0);
        }
        return new Vec2(x / len, y / len);
    }
    public void copy(Vec2 other) {
        if (other == null) {
            throw new RuntimeException("copy() method argument \"other\" cannot be a null reference.");
        }
        this.x = other.x;
        this.y = other.y;
    }
    @Override
    public String toString() {
        return String.format("%d\n%d", x, y);
    }
}
