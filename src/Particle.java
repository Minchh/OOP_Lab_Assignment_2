import java.util.Random;

public class Particle
{
    private int x;
    private int y;
    private Direction direction;
    private Box box;

    public Particle(int x, int y, Box box)
    {
        this.x = x;
        this.y = y;
        this.direction = getRandomDirection();
        this.box = box;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void move()
    {
        int newX = x;
        int newY = y;

        switch (direction)
        {
            case NORTH:
                newY--;
                break;
            case NORTH_EAST:
                newY--;
                newX++;
                break;
            case EAST:
                newX++;
                break;
            case SOUTH_EAST:
                newY++;
                newX++;
                break;
            case SOUTH:
                newY++;
                break;
            case SOUTH_WEST:
                newY++;
                newX--;
                break;
            case WEST:
                newX--;
                break;
            case NORTH_WEST:
                newY--;
                newX--;
                break;
        }

        // check if the particle is still inside the box
        if (newX >= 0 && newX <= box.getWidth() && newY >= 0 && newY <= box.getHeight())
        {
            x = newX;
            y = newY;
        }
    }

    public boolean collidesWith(Particle other)
    {
        return x == other.getX() && y == other.getY();
    }

    public static Particle createRandomParticle(Box box)
    {
        Random random = new Random();
        int x = random.nextInt(box.getWidth());
        int y = random.nextInt(box.getHeight());
        return new Particle(x, y, box);
    }

    public Direction getRandomDirection()
    {
        Random random = new Random();
        return Direction.values()[random.nextInt(8)];
    }
}
