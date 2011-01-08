package hmas;

import java.util.Collection;
import java.util.HashSet;


public abstract class Agent
{
    private World world;
    private Vector position;
    private Vector diagonal;
    
    public Agent(World world, Vector position, Vector diagonal)
    {
        this.world = world;
        this.position = position;
        this.diagonal = diagonal;
    }

    public abstract boolean isFixed();
    public abstract void    step();

    protected boolean getPixel(int x, int y)
    {
        return world.image[x][y];
    }

    public Collection<Agent> getSons()
    {
        return new HashSet<Agent>();
    }

    public Collection<Agent> getBrothers()
    {
        return new HashSet<Agent>();
    }

    public Collection<Agent> isOver()
    {
        return new HashSet<Agent>();
    }

    public Collection<Agent> collidesWith()
    {
        return new HashSet<Agent>();
    }

    public void move(Vector v)
    {
        position = position.add(v);
    }
}

