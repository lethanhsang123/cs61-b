import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
                    String.format("Particle at x=%s, y=%s should be EMPTY", x, y)
            );
        }
    }
}

}
