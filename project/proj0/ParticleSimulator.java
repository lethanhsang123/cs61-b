import edu.princeton.cs.algs4.StdDraw;

public class ParticleSimulator {
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

    public static void main(String[] args) {
        
        ParticleSimulator particleSimulator = new ParticleSimulator(150, 150);
        StdDraw.setXscale(0, particleSimulator.width);
        StdDraw.setYscale(0, particleSimulator.height);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        ParticleFlavor nextParticleFlavor = ParticleFlavor.SAND;

        while (true) {        
            if (StdDraw.isMousePressed()) {
                int x = (int) StdDraw.mouseX();
                int y = (int) StdDraw.mouseY();
                particleSimulator.particles[x][y] = new Particle(nextParticleFlavor);
            }

            particleSimulator.drawParticles();
            StdDraw.show();
            StdDraw.pause(5);
        }

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
