package app.core;

import java.util.Random;

public class SeedPerlin {
    private final Random rand;
    private final int[] PERMUTATION;
    private final Vec2[] GRADIENTS;

    private int[] p;

    public SeedPerlin(long seed) {
        rand = new Random(seed);
        PERMUTATION = new int[256];
        GRADIENTS = new Vec2[256];
        for (int i = 0; i < 256; i++) {
            PERMUTATION[i] = i;
            double angle = Math.toRadians(i * 360.0 / 256.0);
            GRADIENTS[i] = new Vec2(Math.sin(angle), Math.cos(angle));
        }
        for (int i = 255; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int t = PERMUTATION[i];
            PERMUTATION[i] = PERMUTATION[j];
            PERMUTATION[j] = t;
        }
        for (int i = 255; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Vec2 t = GRADIENTS[i];
            GRADIENTS[i] = GRADIENTS[j];
            GRADIENTS[j] = t;
        }

        p = new int[512];
        for (int i = 0; i < 256; i++) {
            p[i + 256] = p[i] = PERMUTATION[i];
        }
    }

    public double perlin(double x, double y, PerlinUtils.Fade fade) {
        int xidx = (int)x & 255;
        int yidx = (int)y & 255;

        int aa = p[p[xidx] + yidx];
        int ba = p[p[xidx + 1] + yidx];
        int ab = p[p[xidx] + yidx + 1];
        int bb = p[p[xidx + 1] + yidx + 1];

        double xdiff = x - (int)x;
        double ydiff = y - (int)y;

        double d0 = grad(aa, xdiff, ydiff);
        double d1 = grad(ba, xdiff - 1.0, ydiff);
        double d2 = grad(ab, xdiff, ydiff - 1.0);
        double d3 = grad(bb, xdiff - 1.0, ydiff - 1.0);

        return PerlinUtils.lerp(PerlinUtils.lerp(d0, d1, fade.fade(xdiff)), PerlinUtils.lerp(d2, d3, fade.fade(xdiff)), fade.fade(ydiff));
    }

    public double neoperlin(double x, double y, PerlinUtils.Fade fade) {
        int xidx = (int)x & 255;
        int yidx = (int)y & 255;

        int aa = p[p[xidx] + yidx];
        int ba = p[p[xidx + 1] + yidx];
        int ab = p[p[xidx] + yidx + 1];
        int bb = p[p[xidx + 1] + yidx + 1];

        double xdiff = x - (int)x;
        double ydiff = y - (int)y;

        double d0 = PerlinUtils.hashgrad(aa, xdiff, ydiff);
        double d1 = PerlinUtils.hashgrad(ba, xdiff - 1.0, ydiff);
        double d2 = PerlinUtils.hashgrad(ab, xdiff, ydiff - 1.0);
        double d3 = PerlinUtils.hashgrad(bb, xdiff - 1.0, ydiff - 1.0);

        return PerlinUtils.lerp(PerlinUtils.lerp(d0, d1, fade.fade(xdiff)), PerlinUtils.lerp(d2, d3, fade.fade(xdiff)), fade.fade(ydiff));
    }

    public double grad(int hash, double x, double y) {
        return GRADIENTS[hash].dot(new Vec2(x, y));
    }

    public int[] getPermutationTable() {
        return PERMUTATION;
    }
    public Vec2[] getGradients() {
        return GRADIENTS;
    }
}
