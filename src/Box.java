import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box
{
    private static Box myBox = null;
    private final int width;
    private final int height;
    private final List<Particle> particles;

    private Box(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.particles = new ArrayList<>();
    }

    public static Box getInstance(int width, int height)
    {
        if (Box.myBox == null)
        {
            Box.myBox = new Box(width, height);
        }
        else
        {
            throw new IllegalStateException("Object was already created!");
        }
        return Box.myBox;
    }

    public void addParticle(Particle particle)
    {
        particles.add(particle);
    }

    public void removeParticle(Particle particle)
    {
        particles.remove(particle);
    }

    public List<Particle> getParticles()
    {
        return particles;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean isInside(int x, int y)
    {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    public boolean hasParticle(int x, int y)
    {
        for (Particle particle : particles)
        {
            if (particle.getX() == x && particle.getY() == y)
                return true;
        }
        return false;
    }

    public void moveParticles()
    {
        for (Particle particle : particles)
        {
            particle.move();
        }
    }

    public void handleCollisions()
    {
        List<Particle> newParticles = new ArrayList<>();
        for (int i = 0; i < particles.size(); i++)
        {
            Particle particle1 = particles.get(i);
            for (int j = i + 1; j < particles.size(); j++)
            {
                Particle particle2 = particles.get(j);
                if (particle1.collidesWith(particle2))
                {
                    newParticles.add(Particle.createRandomParticle(Box.myBox));
                }
            }
        }
        particles.addAll(newParticles);
    }

    private int getRandomX()
    {
        Random random = new Random();
        return random.nextInt(width + 1);
    }

    private int getRandomY()
    {
        Random random = new Random();
        return random.nextInt(height + 1);
    }
}
