package edu.berkeley.cs61b.proj0.model;

import edu.berkeley.cs61b.proj0.model.Particle;
import edu.berkeley.cs61b.proj0.model.ParticleFlavor;
import edu.berkeley.cs61b.proj0.service.ParticleSimulator;

import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParticle {

    @Test
    public void testConstructor() {
        Particle fp = new Particle(ParticleFlavor.FIRE);
        assertEquals(fp.getFlavor(), ParticleFlavor.FIRE);
        assertEquals(fp.getLifespan(), 10);

        Particle sp = new Particle(ParticleFlavor.SAND);
        assertEquals(sp.getFlavor(), ParticleFlavor.SAND);
        assertEquals(sp.getLifespan(), -1);
    }

    @Test
    public void testColor() {
        Particle emptyParticle = new Particle(ParticleFlavor.EMPTY);
        assertEquals(Color.BLACK, emptyParticle.color());

        Particle sandParticle = new Particle(ParticleFlavor.SAND);
        assertEquals(Color.YELLOW, sandParticle.color());

        Particle barrierParticle = new Particle(ParticleFlavor.BARRIER);
        assertEquals(Color.GRAY, barrierParticle.color());

        Particle waterParticle = new Particle(ParticleFlavor.WATER);
        assertEquals(Color.BLUE, waterParticle.color());

        Particle fountainParticle = new Particle(ParticleFlavor.FOUNTAIN);
        assertEquals(Color.CYAN, fountainParticle.color());

        Particle plantParticle = new Particle(ParticleFlavor.PLANT);
        assertEquals(new Color(0, 255, 0), plantParticle.color());

        Particle fireParticle = new Particle(ParticleFlavor.FIRE);
        assertEquals(new Color(255, 0, 0), fireParticle.color());

        Particle flowerParticle = new Particle(ParticleFlavor.FLOWER);
        assertEquals(new Color(255, 141, 161), flowerParticle.color());
    }

    @Test
    public void testMoveInto() {
        Particle particle_a = new Particle(ParticleFlavor.FIRE);
        particle_a.setLifespan(10);
        ;
        Particle particle_b = new Particle(ParticleFlavor.EMPTY);
        particle_b.setLifespan(-1);

        particle_a.moveInto(particle_b);

        assertEquals(ParticleFlavor.EMPTY, particle_a.getFlavor());
        assertEquals(-1, particle_a.getLifespan());

        assertEquals(ParticleFlavor.FIRE, particle_b.getFlavor());
        assertEquals(10, particle_b.getLifespan());
    }

    @Test
    public void testFall() {
        // Arrange: Initialize a small 2x2 simulator
        ParticleSimulator sim = new ParticleSimulator(2, 2);

        // --- Scenario 1: Fall into Empty Space ---
        // Setup: Place SAND at (0, 1) and ensure (0, 0) is EMPTY
        // Note that 0, 0 is the bottom left, and 0, 1 is the top left.
        sim.getParticles()[0][1] = new Particle(ParticleFlavor.SAND);
        sim.getParticles()[0][0] = new Particle(ParticleFlavor.EMPTY);

        // Get real neighbors for the particle at (0, 1)
        Map<Direction, Particle> neighbors1 = sim.getNeighbors(0, 1);

        // Act: Tell the particle at (0, 1) to fall
        sim.getParticles()[0][1].fall(neighbors1);

        // Assert:
        // 1. Old position (0, 1) should now be EMPTY
        assertEquals(
                ParticleFlavor.EMPTY,
                sim.getParticles()[0][1].getFlavor());

        // 2. New position (0, 0) should now be SAND
        assertEquals(
                ParticleFlavor.SAND,
                sim.getParticles()[0][0].getFlavor());

        // --- Scenario 2: Blocked by Barrier ---
        // Setup: Place SAND at (1, 1) and BARRIER at (1, 0)
        sim.getParticles()[1][1] = new Particle(ParticleFlavor.SAND);
        sim.getParticles()[1][0] = new Particle(ParticleFlavor.BARRIER);

        // Get real neighbors for the particle at (1, 1)
        Map<Direction, Particle> neighbors2 = sim.getNeighbors(1, 1);

        // Act: Tell the particle at (1, 1) to fall
        sim.getParticles()[1][1].fall(neighbors2);

        // Assert:
        // 1. Position (1, 1) stays SAND (blocked)
        assertEquals(
                ParticleFlavor.SAND,
                sim.getParticles()[1][1].getFlavor());

        // 2. Position (1, 0) stays BARRIER
        assertEquals(
                ParticleFlavor.BARRIER,
                sim.getParticles()[1][0].getFlavor());
    }

}