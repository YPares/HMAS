package hmas;

import java.util.HashSet;


public abstract class Agent
{
    private World world;
    private int level;
    private Vector position;
    private Vector diagonal;
    private Filter<Agent> differentOfMe;
    private Filter<Agent> collidesWithMe;

    public Agent(World world, int level, Vector position, Vector diagonal)
    {
        this.world = world;
        this.level = level;
        this.world.addAgent(level, this);
        this.position = position;
        this.diagonal = diagonal;
        final Agent self = this;
        differentOfMe = new Filter<Agent>() {
            public boolean passes(Agent ag){
                return !self.equals(ag);
            }
        };
        collidesWithMe = new Filter<Agent>() {
            public boolean passes(Agent ag){
                return true;
            }
        };
    }

    public abstract boolean isFixed();
    public abstract void    step();

    protected boolean getPixel(int x, int y)
    {
        return world.image[x][y];
    }

    protected Iterable<Agent> getSons()
    {
        if(level <= 0)
            return new HashSet<Agent>();
        return world.agents.get(level - 1);
    }

    protected Iterable<Agent> getBrothers()
    {
        return differentOfMe.filter(world.agents.get(level));
    }

    protected Iterable<Agent> isOver()
    {
        return collidesWithMe.filter(getSons());
    }

    protected Iterable<Agent> collidesWith()
    {
        return collidesWithMe.filter(getBrothers());
    }

    protected void move(Vector v)
    {
        position = position.add(v);
    }
}

