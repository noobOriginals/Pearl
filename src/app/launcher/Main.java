package app.launcher;

import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;

import app.core.Perlin;
import app.core.PerlinUtils;
import app.core.SeedPerlin;

public class Main {
    public static void main(String[] args) {
        try {
            genSeedPerlinImage("noob".hashCode(), "perlin.png", 1000, 1000, 80);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void genSeedPerlinImage(long seed, String name, int width, int height, double scale) throws Exception {
        SeedPerlin gen = new SeedPerlin(seed);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	int p = Math.clamp((int)((gen.perlin(x / scale, y / scale, PerlinUtils::fade) + 1) * 0.5 * 255), 0, 255);
            	image.setRGB(x, y, (p << 16) | (p << 8) | p);
            }
        }
        File output = new File(name);
        ImageIO.write(image, "png", output);
        System.out.println("Saved " + name);
    }

    public static void genSeedNeoPerlinImage(long seed, String name, int width, int height, double scale) throws Exception {
        SeedPerlin gen = new SeedPerlin(seed);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	int p = Math.clamp((int)((gen.neoperlin(x / scale, y / scale, PerlinUtils::fade) + 1) * 0.5 * 255), 0, 255);
            	image.setRGB(x, y, (p << 16) | (p << 8) | p);
            }
        }
        File output = new File(name);
        ImageIO.write(image, "png", output);
        System.out.println("Saved " + name);
    }

    @Deprecated
    public static void genPerlinImage(String name, int width, int height, double scale) throws Exception {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	int p = Math.clamp((int)((Perlin.perlin(x / scale, y / scale, PerlinUtils::fade) + 1) * 0.5 * 255), 0, 255);
            	image.setRGB(x, y, (p << 16) | (p << 8) | p);
            }
        }
        File output = new File(name);
        ImageIO.write(image, "png", output);
        System.out.println("Saved " + name);
    }
    @Deprecated
    public static void genColorPerlinImage(String name, int width, int height, double scale) throws Exception {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	double value = Perlin.perlin(x / scale, y / scale, PerlinUtils::fade);
                int thrsh = 256;
                double r = 1.0, g = 1.0, b = 1.0;
                g -= Math.abs(value);
                r -= Math.clamp(value, 0.0, 1.0);
                b += Math.clamp(value, -1.0, 0.0);
                int rf, gf, bf;
                rf = (int)(r * 255);
                gf = (int)(g * 255);
                bf = (int)(b * 255);
                int rgb = (rf << 16) | (gf << 8) | bf;
                if (rf >= thrsh && gf >= thrsh && bf >= thrsh) {
                    rgb = (0 << 16) | (255 << 8) | 0;
                }
                if (x % (int)scale == 0 || y % (int)scale == 0) {
                    rgb = 0;
                }
                image.setRGB(x, y, rgb);
            }
        }
        File output = new File(name);
        ImageIO.write(image, "png", output);
        System.out.println("Saved " + name);
    }
}
