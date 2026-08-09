package edu.berkeley.cs61b.proj0.service;

import java.util.HashMap;
import java.util.Map;

import edu.berkeley.cs61b.proj0.model.Direction;
import edu.berkeley.cs61b.proj0.model.Particle;
import edu.berkeley.cs61b.proj0.model.ParticleFlavor;
import edu.princeton.cs.algs4.StdDraw;

public class ParticleSimulator {

        public static final Map<Character, ParticleFlavor> LETTER_TO_PARTICLE = Map.of(
            's', ParticleFlavor.SAND,
            'b', ParticleFlavor.BARRIER,
            'w', ParticleFlavor.WATER,
            'p', ParticleFlavor.PLANT,
            'f', ParticleFlavor.FIRE,
            '.', ParticleFlavor.EMPTY,
            'n', ParticleFlavor.FOUNTAIN,
            'r', ParticleFlavor.FLOWER
    );

    private Particle[][] particles;
    private int width;
    private int height;

    public ParticleSimulator(int width, int height) {
        this.width = width;
        this.height = height;

        // particles initializtion
        this.initParticles();
    }

    private void initParticles() {
        this.particles = new Particle[this.width][this.height];
        for (int i = 0; i < this.particles.length; i++) {
            for (int j = 0; j < this.particles[i].length; j++) {
                this.particles[i][j] = new Particle(ParticleFlavor.EMPTY);
            }
        }
    }

    public void drawParticles() {
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                StdDraw.setPenColor(particles[x][y].color());
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
    }

    public boolean validIndex(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    public Map<Direction, Particle> getNeighbors(int x, int y) {
        Particle sentinel = new Particle(ParticleFlavor.BARRIER);
        Map<Direction, Particle> neighbors = new java.util.HashMap<>();
        // Up
        if (validIndex(x, y + 1)) {
            neighbors.put(Direction.UP, this.particles[x][y + 1]);
        } else {
            neighbors.put(Direction.UP, sentinel);
        }
        // Down
        if (validIndex(x, y - 1)) {
            neighbors.put(Direction.DOWN, this.particles[x][y - 1]);
        } else {
            neighbors.put(Direction.DOWN, sentinel); 
        }
        // Left
        if (validIndex(x - 1, y)) {
            neighbors.put(Direction.LEFT, this.particles[x - 1][y]);
        } else {
            neighbors.put(Direction.LEFT, sentinel);
        }
        // Right
        if (validIndex(x + 1, y)) {
            neighbors.put(Direction.RIGHT, this.particles[x + 1][y]);
        } else {
            neighbors.put(Direction.RIGHT, sentinel);
        }
        return neighbors;
    }

    public void tick() {
        for (int i = 0; i < particles.length; i++) {
            for (int j = 0; j < particles[i].length; j++) {
                particles[i][j].fall(getNeighbors(i, j));
            }
        }
    }

    @Override
    public String toString() {
        // 1. Build a reverse map to look up characters by Flavor
        Map<ParticleFlavor, Character> flavorToChar = new HashMap<>();
        for (Map.Entry<Character, ParticleFlavor> entry : LETTER_TO_PARTICLE.entrySet()) {
            flavorToChar.put(entry.getValue(), entry.getKey());
        }

        StringBuilder sb = new StringBuilder();

        // Have to iterate from the top so that
        // the top particles are shown first.
        for (int y = height - 1; y >= 0; y -= 1) {
            for (int x = 0; x < width; x += 1) {
                Particle p = particles[x][y];
                sb.append(flavorToChar.get(p.getFlavor()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // getters
    public Particle[][] getParticles() {
        return this.particles;
    }

    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
}
