package edu.berkeley.cs61b.proj0;

import edu.berkeley.cs61b.proj0.model.Particle;
import edu.berkeley.cs61b.proj0.model.Direction;
import edu.berkeley.cs61b.proj0.model.ParticleFlavor;
import edu.berkeley.cs61b.proj0.service.ParticleSimulator;
import edu.princeton.cs.algs4.StdDraw;

public class Main {
    public static void main(String[] args) {
        
        ParticleSimulator particleSimulator = new ParticleSimulator(200, 200);
        StdDraw.setXscale(0, particleSimulator.getWidth());
        StdDraw.setYscale(0, particleSimulator.getHeight());
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        ParticleFlavor nextParticleFlavor = ParticleFlavor.SAND;

        while (true) {        

            // If the user has pressed a key.
            if (StdDraw.hasNextKeyTyped()) {
                // Key the user typed.
                char c = StdDraw.nextKeyTyped();
                nextParticleFlavor = ParticleSimulator.LETTER_TO_PARTICLE.getOrDefault(c, ParticleFlavor.SAND);
            }

            if (StdDraw.isMousePressed()) {
                int x = (int) StdDraw.mouseX();
                int y = (int) StdDraw.mouseY();
                if (particleSimulator.validIndex(x, y)) {
                    particleSimulator.getParticles()[x][y] = new Particle(nextParticleFlavor);
                }
            }

            particleSimulator.tick();
            particleSimulator.drawParticles();
            StdDraw.show();
            StdDraw.pause(5);
        }

    }
}
