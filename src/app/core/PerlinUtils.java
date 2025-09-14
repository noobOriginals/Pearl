package app.core;

public class PerlinUtils {
    public static double hashgrad(int hash, double x, double y) {
        switch (hash & 15) {
	        case 0b0110: return x + y;
	        case 0b1001: return x - y;
	        case 0b0101: return - x + y;
	        case 0b0011: return - x - y;
	        case 0b0100: return y + x;
	        case 0b1011: return y - x;
	        case 0b1110: return - y + x;
	        case 0b0111: return - y - x;
	        case 0b1000: return x + 0;
	        case 0b1101: return x - 0;
	        case 0b1111: return - x + 0;
	        case 0b0010: return - x - 0;
	        case 0b0000: return y + 0;
	        case 0b0001: return y - 0;
	        case 0b1100: return - y + 0;
	        case 0b1010: return - y - 0;
	        default: return 0;
	    }
    }

    public static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    public static double smoothstep(double t) {
        return t * t * (3 - 2 * t);
	}
    public static double fade(double t) {
            return t * t * t * (t * (t * 6 - 15) + 10);
    }
    public static double cosi(double t) {
    	return (1 - Math.cos(Math.PI * t)) / 2.0;
    }
    public static double quartic(double t) {
    	return t * t * t * (2 - t);
    }

    @FunctionalInterface
	public interface Fade {
		public double fade(double t);
	}
}
