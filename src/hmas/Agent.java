package hmas;

import java.util.Collection;
import java.util.HashSet;


public abstract class Agent
{
    private World world;
    private Vector position;
    private Vector diagonal;
    
    public Agent(World world, int level, Vector position, Vector diagonal)
    {
        this.world = world;
        this.world.addAgent(level, this);
        this.position = position;
        this.diagonal = diagonal;
    }

    public abstract boolean isFixed();
    public abstract void    step();

    protected boolean getPixel(int x, int y)
    {
        return world.image[x][y];
    }

    protected Collection<Agent> getSons()
    {
        return new HashSet<Agent>();
    }

    protected Collection<Agent> getBrothers()
    {
        return new HashSet<Agent>();
    }

    protected Collection<Agent> isOver()
    {
        return new HashSet<Agent>();
    }

    protected Collection<Agent> collidesWith()
    {
        return new HashSet<Agent>();
    }

    protected void move(Vector v)
    {
        position = position.add(v);
    }
}

