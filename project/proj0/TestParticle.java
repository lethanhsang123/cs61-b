import org.junit.jupiter.api.Test;

import java.awt.Color;
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
        particle_a.setLifespan(10);;
        Particle particle_b = new Particle(ParticleFlavor.EMPTY);
        particle_b.setLifespan(-1);

        particle_a.moveInto(particle_b);

        assertEquals(ParticleFlavor.EMPTY, particle_a.getFlavor());
        assertEquals(-1, particle_a.getLifespan());

        assertEquals(ParticleFlavor.FIRE, particle_b.getFlavor());
        assertEquals(10, particle_b.getLifespan());
    }

}