package hmas;

import java.util.Collection;


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

    public abstract Collection<Agent> getSons();
    public abstract Collection<Agent> getBrothers();
    public abstract Collection<Agent> isOver();
    public abstract Collection<Agent> collidesWith();

    public abstract void move(Vector v);
}

