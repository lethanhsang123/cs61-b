import java.util.Map;
import java.awt.Color;

public class Particle {
    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                   ParticleFlavor.PLANT, PLANT_LIFESPAN,
                   ParticleFlavor.FIRE, FIRE_LIFESPAN);

    private ParticleFlavor flavor;
    private int lifespan;


    /**
     * Praticle constructor
     * @param flavor
     */
    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        this.lifespan = switch(flavor) {
            case FLOWER, PLANT, FIRE -> LIFESPANS.getOrDefault(flavor, -1);
            case null, default -> -1;
        };
    }


    /**
     * Get Particle's color
     * @return color
     */
    public Color color() {
        return switch(this.flavor) {
            case SAND: yield Color.YELLOW;
            case BARRIER: yield Color.GRAY;
            case WATER: yield Color.BLUE;
            case FOUNTAIN: yield Color.CYAN;
            case PLANT: yield new Color(0, 255, 0);
            case FIRE: yield new Color(255, 0, 0);
            case FLOWER: yield new Color(255, 141, 161);
            case EMPTY: yield Color.BLACK;
            case null, default: yield null;
        };
    }

    public void moveInto(Particle other) {
        other.setFlavor(this.flavor);
        other.setLifespan(this.lifespan);
        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }


    // getters
    public ParticleFlavor getFlavor() {
        return this.flavor;
    }

    public int getLifespan() {
        return this.lifespan;
    }

    // setters
    public void setFlavor(ParticleFlavor flavor) {
        this.flavor = flavor;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

}