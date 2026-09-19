package edu.berkeley.cs61b.proj0.service;

import org.junit.jupiter.api.Test;

import edu.berkeley.cs61b.proj0.model.Direction;
import edu.berkeley.cs61b.proj0.model.Particle;
import edu.berkeley.cs61b.proj0.model.ParticleFlavor;
import edu.berkeley.cs61b.proj0.service.ParticleSimulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Test
    public void testTickWithFlow() {
        // Arrange:
        // Col 0: Stacked Sand (s) on Barrier -> Should be Stable
        // Col 2: Water (w) on Barrier -> Should Flow
        // Col 4: Sand (s) in Air -> Should Fall
        String startState = """
                s...s
                s.w..
                bbbbb
                """;

        // Possibility 1: Water stays put (or moves Right then Left)
        // Sand falls.
        String expectStay = """
                s....
                s.w.s
                bbbbb
                """;

        // Possibility 2: Water flows Left.
        // Sand falls.
        String expectLeft = """
                s....
                sw..s
                bbbbb
                """;

        // Possibility 3: Water flows Right ONCE (Right then Stay).
        // Sand falls.
        String expectRightSingle = """
                s....
                s..ws
                bbbbb
                """;

        // Possibility 4: Water flows Right TWICE (Right then Right).
        // Water ends up under the Sand (at 4,1), blocking the Sand at (4,2).
        String expectRightDouble = """
                s...s
                s...w
                bbbbb
                """;

        int countStay = 0;
        int countLeft = 0;
        int countRightSingle = 0;
        int countRightDouble = 0;

        // Act: Run 1000 simulations
        for (int i = 0; i < 1000; i++) {
            ParticleSimulator sim = fromBoardString(startState);
            sim.tick();

            String result = sim.toString().trim();

            if (result.equals(expectStay.trim())) {
                countStay += 1;
            } else if (result.equals(expectLeft.trim())) {
                countLeft += 1;
            } else if (result.equals(expectRightSingle.trim())) {
                countRightSingle += 1;
            } else if (result.equals(expectRightDouble.trim())) {
                countRightDouble += 1;
            } else {
                throw new AssertionError("Unexpected board state:\n" + result);
            }
        }

        // Assert:

        // 1. Left (~33%): > 240 is safe.
        assertTrue(
                countLeft > 240,
                "Expected more than 240 left-flow results, but got " + countLeft);

        // 2. Stay (~44%):
        // 1/3 (Stay) + 1/9 (Right-then-Left) = 4/9.
        assertTrue(
                countStay > 240,
                "Expected more than 240 stay results, but got " + countStay);

        // 3. Right Single (~11%):
        // 1/3 (Right) * 1/3 (Stay) = 1/9.
        assertTrue(
                countRightSingle > 50,
                "Expected more than 50 right-single results, but got "
                        + countRightSingle);

        // 4. Right Double (~11%):
        // 1/3 (Right) * 1/3 (Right) = 1/9.
        assertTrue(
                countRightDouble > 50,
                "Expected more than 50 right-double results, but got "
                        + countRightDouble);
    }

    @Test
    public void testFallingWaterDoesNotFlow() {
        // Arrange:
        // Water (w) suspended in the center.
        // It has empty space below it (so it MUST fall).
        // It has empty space to the sides (so it COULD flow, if logic was wrong).
        String startState = """
                ...
                .w.
                ...
                bbb
                """;

        // Expected Behavior:
        // The water drops exactly one spot (to the center bottom).
        // It should NOT move Left or Right after falling.
        String expectedState = """
                ...
                ...
                .w.
                bbb
                """;

        for (int i = 0; i < 100; i++) {
            ParticleSimulator sim = fromBoardString(startState);
            sim.tick();

            String result = sim.toString().trim();

            assertEquals(
                    expectedState.trim(),
                    result);
        }
    }

    @Test
    public void testGrow() {
        String startState = """
                ...
                .p.
                bbb
                """.trim();

        // The list of REQUIRED growth outcomes
        List<String> expectedGrowthStates = new ArrayList<>();

        expectedGrowthStates.add("""
                ...
                .p.
                bbb
                """.trim()); // no growth

        expectedGrowthStates.add("""
                ...
                pp.
                bbb
                """.trim()); // Left

        expectedGrowthStates.add("""
                .p.
                .p.
                bbb
                """.trim()); // Up

        expectedGrowthStates.add("""
                pp.
                .p.
                bbb
                """.trim()); // Up + Left

        expectedGrowthStates.add("""
                ...
                .pp
                bbb
                """.trim()); // Right

        expectedGrowthStates.add("""
                ..p
                .pp
                bbb
                """.trim()); // Right + Up

        expectedGrowthStates.add("""
                .p.
                .pp
                bbb
                """.trim()); // Up, Right (fall)

        expectedGrowthStates.add("""
                .pp
                .pp
                bbb
                """.trim()); // Right, Up, Left

        // --- ACT ---
        Set<String> observedStates = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            ParticleSimulator sim = fromBoardString(startState);
            sim.tick();

            observedStates.add(sim.toString().trim());
        }

        // --- ASSERT 1: CHECK FOR MISSING STATES ---
        for (String expected : expectedGrowthStates) {
            assertTrue(
                    observedStates.contains(expected),
                    """
                            Test Failed: A required growth state was never observed.
                            Missing State:
                            %s
                            """.formatted(expected));
        }

        // --- ASSERT 2: CHECK FOR UNEXPECTED (INVALID) STATES ---

        // Create a "White List" of all valid outcomes (Growth + No Change)
        Set<String> validStates = new HashSet<>(expectedGrowthStates);

        for (String observed : observedStates) {
            assertTrue(
                    validStates.contains(observed),
                    """
                            Test Failed: An invalid/impossible state was generated.
                            Unexpected State:
                            %s
                            """.formatted(observed));
        }
    }
}
