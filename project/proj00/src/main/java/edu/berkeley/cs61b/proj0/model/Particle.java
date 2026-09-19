package edu.berkeley.cs61b.proj0.model;

import java.util.Map;
import java.awt.Color;
import edu.princeton.cs.algs4.StdRandom;

public class Particle {
    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS = Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
            ParticleFlavor.PLANT, PLANT_LIFESPAN,
            ParticleFlavor.FIRE, FIRE_LIFESPAN);

    private ParticleFlavor flavor;
    private int lifespan;

    /**
     * Praticle constructor
     * 
     * @param flavor
     */
    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        this.lifespan = getLifespanForFlavor(flavor);
    }

    /**
     * Get Particle's color
     * 
     * @return color
     */
    public Color color() {
        return switch (this.flavor) {
            case SAND -> Color.YELLOW;
            case BARRIER -> Color.GRAY;
            case WATER -> Color.BLUE;
            case FOUNTAIN -> Color.CYAN;
            case PLANT -> {
                double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
                int g = 120 + (int) Math.round((255 - 120) * ratio);
                yield new Color(0, g, 0);
            }
            case FIRE -> {
                double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
                int r = (int) Math.round(255 * ratio);
                yield new Color(r, 0, 0);
            }
            case FLOWER -> {
                double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
                int r = 120 + (int) Math.round((255 - 120) * ratio);
                int g = 70 + (int) Math.round((141 - 70) * ratio);
                int b = 80 + (int) Math.round((161 - 80) * ratio);
                yield new Color(r, g, b);
            }
            case EMPTY -> Color.BLACK;
            case null, default -> null;
        };
    }

    public void moveInto(Particle other) {
        other.setFlavor(this.flavor);
        other.setLifespan(this.lifespan);
        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors) {
        Particle down = neighbors.get(Direction.DOWN);
        if (down != null && ParticleFlavor.EMPTY.equals(down.getFlavor())) {
            this.moveInto(neighbors.get(Direction.DOWN));
        }
    }

    public void action(Map<Direction, Particle> neighbors) {
        if (this.flavor == ParticleFlavor.EMPTY) {
            return;
        }

        if (this.flavor != ParticleFlavor.BARRIER) {
            this.fall(neighbors);
        }

        if (this.flavor == ParticleFlavor.WATER) {
            this.flow(neighbors);
        }

        if (this.flavor == ParticleFlavor.PLANT || this.flavor == ParticleFlavor.FLOWER) {
            this.grow(neighbors);
        }

        if (this.flavor == ParticleFlavor.FIRE) {
            this.burn(neighbors);
        }

    }

    public void flow(Map<Direction, Particle> neighbors) {
        int choice = StdRandom.uniformInt(3);
        Particle left = neighbors.get(Direction.LEFT);
        Particle right = neighbors.get(Direction.RIGHT);
        if (choice == 0 && left != null && ParticleFlavor.EMPTY.equals(left.getFlavor())) {
            this.moveInto(left);
        } else if (choice == 1 && right != null && ParticleFlavor.EMPTY.equals(right.getFlavor())) {
            this.moveInto(right);
        }
    }

    public void grow(Map<Direction, Particle> neighbors) {
        int choice = StdRandom.uniformInt(10);
        switch (choice) {
            case 0: {
                Particle up = neighbors.get(Direction.UP);
                if (up != null && ParticleFlavor.EMPTY.equals(up.getFlavor())) {
                    up.setFlavor(this.flavor);
                    up.setLifespan(getLifespanForFlavor(this.flavor));
                }
                break;
            }
            case 1: {
                Particle left = neighbors.get(Direction.LEFT);
                if (left != null && ParticleFlavor.EMPTY.equals(left.getFlavor())) {
                    left.setFlavor(this.flavor);
                    left.setLifespan(getLifespanForFlavor(this.flavor));
                }
                break;
            }
            case 2: {
                Particle right = neighbors.get(Direction.RIGHT);
                if (right != null && ParticleFlavor.EMPTY.equals(right.getFlavor())) {
                    right.setFlavor(this.flavor);
                    right.setLifespan(getLifespanForFlavor(this.flavor));
                }
                break;
            }
            case 3, 4, 5, 6, 7, 8, 9: {
                break;
            }
        }
    }

    public void decrementLifespan() {
        if (this.lifespan > 0) {
            this.lifespan--;
        }
        if (this.lifespan == 0) {
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }

    private Integer getLifespanForFlavor(ParticleFlavor flavor) {
        return LIFESPANS.getOrDefault(flavor, -1);
    }

    public void burn(Map<Direction, Particle> neighbors) {
    for (Particle neighbor : neighbors.values()) {
        if (neighbor != null
                && (neighbor.getFlavor() == ParticleFlavor.PLANT
                    || neighbor.getFlavor() == ParticleFlavor.FLOWER)) {

            if (StdRandom.uniformInt(100) < 40) {
                neighbor.setFlavor(ParticleFlavor.FIRE);
                neighbor.setLifespan(FIRE_LIFESPAN);
            }
        }
    }
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