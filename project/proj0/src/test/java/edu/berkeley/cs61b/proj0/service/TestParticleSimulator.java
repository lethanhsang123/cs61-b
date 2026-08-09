package edu.berkeley.cs61b.proj0.service;

import org.junit.jupiter.api.Test;

import edu.berkeley.cs61b.proj0.model.Direction;
import edu.berkeley.cs61b.proj0.model.Particle;
import edu.berkeley.cs61b.proj0.model.ParticleFlavor;
import edu.berkeley.cs61b.proj0.service.ParticleSimulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestParticleSimulator {

    @Test
    public void testConstructor_initializesEmptyGrid_usingIndices() {
        int w = 50;
        int h = 60;
        ParticleSimulator simulator = new ParticleSimulator(w, h);

        // 1. Verify the outer array length (Width)
        assertEquals(w, simulator.getParticles().length);

        // 2. Iterate using Integer Indices
        for (int x = 0; x < w; x++) {

            // Verify the inner array length (Height) for this column
            assertEquals(h, simulator.getParticles()[x].length);

            for (int y = 0; y < simulator.getHeight(); y++) {
                Particle particle = simulator.getParticles()[x][y];

                // Verify the particle is not null
                assertNotNull(particle);

                // Verify the particle is initialized to EMPTY
                assertEquals(
                        ParticleFlavor.EMPTY,
                        particle.getFlavor(),
                        String.format("Particle at x=%s, y=%s should be EMPTY", x, y));
            }
        }
    }

    @Test
    public void testValidIndex() {
        // Arrange: Create a grid of 10x20
        int width = 10;
        int height = 20;
        ParticleSimulator sim = new ParticleSimulator(width, height);

        // Assert: Valid Indices (Inside bounds)
        assertTrue(sim.validIndex(0, 0)); // Bottom-Left Corner
        assertTrue(sim.validIndex(9, 19)); // Top-Right Corner (width-1, height-1)
        assertTrue(sim.validIndex(5, 10)); // Middle

        // Assert: Invalid Indices (Outside bounds)
        assertFalse(sim.validIndex(-1, 0)); // Negative X
        assertFalse(sim.validIndex(0, -1)); // Negative Y
        assertFalse(sim.validIndex(10, 0)); // X == Width (Off by one)
        assertFalse(sim.validIndex(0, 20)); // Y == Height (Off by one)
        assertFalse(sim.validIndex(100, 100)); // Far out of bounds
    }

    @Test
    public void testTick_updatesParticlesBottomUp() {
        // Arrange: Create a tall, narrow grid (1 wide, 3 high)
        // Coordinates: (0,0) is bottom, (0,2) is top
        ParticleSimulator sim = new ParticleSimulator(1, 3);

        // Setup a stack of sand with a gap at the bottom
        sim.getParticles()[0][0] = new Particle(ParticleFlavor.EMPTY); // Bottom
        sim.getParticles()[0][1] = new Particle(ParticleFlavor.SAND); // Middle
        sim.getParticles()[0][2] = new Particle(ParticleFlavor.SAND); // Top

        // Act: Run one simulation step
        sim.tick();

        // Assert: Both particles should have moved down one step

        // 1. The bottom spot (0,0) catches the first falling sand
        assertEquals(
                ParticleFlavor.SAND,
                sim.getParticles()[0][0].getFlavor());

        // 2. The middle spot (0,1) catches the second falling sand
        // (If the loop ran top-down, this would be EMPTY because the top sand
        // would have been blocked)
        assertEquals(
                ParticleFlavor.SAND,
                sim.getParticles()[0][1].getFlavor());

        // 3. The top spot (0,2) should now be empty
        assertEquals(
                ParticleFlavor.EMPTY,
                sim.getParticles()[0][2].getFlavor());
    }

    private ParticleSimulator fromBoardString(String board) {
        String[] lines = board.trim().split("\\n");
        int height = lines.length;
        int width = lines[0].trim().length();

        ParticleSimulator sim = new ParticleSimulator(width, height);

        for (int i = 0; i < height; i++) {
            String line = lines[i].trim();
            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);
                int y = height - 1 - i;
                ParticleFlavor flavor = ParticleSimulator.LETTER_TO_PARTICLE.get(c);
                sim.getParticles()[x][y] = new Particle(flavor);
            }
        }
        return sim;
    }

    @Test
    public void testTickVisual() {
        // Arrange: A 3x5 grid with sand (s) suspended over empty space (d)
        // and a barrier (b) at the bottom.
        String initialBoard = """
                s.s
                s.s
                ...
                ...
                bbb
                """;

        ParticleSimulator sim = fromBoardString(initialBoard);

        // Act: Run 1 tick
        sim.tick();

        String expectedAfter1Tick = """
                ...
                s.s
                s.s
                ...
                bbb
                """;

        // Assert: Verify state after 1 tick
        assertEquals(
                expectedAfter1Tick.trim(),
                sim.toString().trim());

        // Act: Run 2nd tick
        sim.tick();

        String expectedAfter2Ticks = """
                ...
                ...
                s.s
                s.s
                bbb
                """;

        // Assert: Verify state after 2 ticks
        assertEquals(
                expectedAfter2Ticks.trim(),
                sim.toString().trim());
    }

}
