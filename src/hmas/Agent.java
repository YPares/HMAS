package hmas;

import java.util.Collections;
import java.util.Random;


public abstract class Agent
{
    // Filters agents: keeps those which collide with the 'this'.
    private abstract class CollisionFilter extends Filter<Agent>
    {
        private Agent self;

        CollisionFilter(Agent self)
        { this.self = self; }

        boolean pointInMe(Vector v)
        {
            return v.x() >= self.position.x() && v.y() >= self.position.y() &&
                   v.x() <= self.position.x() + self.diagonal.x() &&
                   v.y() <= self.position.y() + self.diagonal.y();
        }
    }

    private World world;
    private int level;
    private Vector position;  // Position is the CENTRE of the pixel
    private Vector diagonal;  // Then, a 1 pixel agent should have a diagonal of (0, 0): it is merely a point, with no size
                              // The coordinates of diagonal MUST be both POSITIVE
    private Filter<Agent> differentFilter, collidesPartiallyFilter,
                          collidesCompletelyFilter, fixedFilter, sameAgentTypeFilter;

    private boolean isFixed;

    private boolean validPosition(Vector p)
    {
        Vector diag = world.imageDiagonal();
        return p.x() >= 0 && p.y() >= 0 &&
               p.x() <= diag.x() && p.y() <= diag.y();
    }

    public Agent(World world, int level, Vector position, Vector diagonal)
    {
        this.world = world;
        this.level = level;
        assert(validPosition(position));
        this.position = position;
        this.diagonal = diagonal;
        final Agent self = this;
        // To keep agents that are different of 'this'
        differentFilter = new Filter<Agent>() {
            public boolean passes(Agent ag){
                return !self.equals(ag);
            }
        };
        // To keep agents that partially collide with 'this'
        collidesPartiallyFilter = new CollisionFilter(this) {
            public boolean passes(Agent ag) {
                return pointInMe(ag.position) || pointInMe(ag.position.add(ag.diagonal)) ||
                       pointInMe(ag.position.add(new Vector(ag.diagonal.x(), 0))) ||
                       pointInMe(ag.position.add(new Vector(0, ag.diagonal.y())));
            }
        };
        // To keep agents that completely collide with 'this' (Four corners are in 'this')
        collidesCompletelyFilter = new CollisionFilter(this) {
            public boolean passes(Agent ag) {
                return pointInMe(ag.position) && pointInMe(ag.position.add(ag.diagonal)) &&
                       pointInMe(ag.position.add(new Vector(ag.diagonal.x(), 0))) &&
                       pointInMe(ag.position.add(new Vector(0, ag.diagonal.y())));
            }
        };
        // To keep agents that are fixed
        fixedFilter = new Filter<Agent>() {
            public boolean passes(Agent ag) {
                return ag.isFixed();
            }
        };
        // To keep agents that have the same class than 'this'
        sameAgentTypeFilter = new Filter<Agent>() {
            public boolean passes(Agent ag) {
                return self.getClass() == ag.getClass();
            }
        };

        isFixed = false;

        this.world.addAgent(level, this);
    }

    public boolean isFixed()
    {
        return this.isFixed;
    }

    public void setFixed()
    {
        this.isFixed = true;
    }
    
    public abstract void step();


    protected boolean getPixel(Vector v)
    {
        return world.image[v.x()][v.y()];
    }

    // Get the agents of the level under 'this' (level N-1)
    protected Iterable<Agent> mySons()
    {
        if(level <= 0)
            return Collections.emptySet();
        return world.agents.get(level - 1);
    }

    // Get the agents of same level than 'this' (level N)
    protected Iterable<Agent> myBrothers()
    {
        return differentFilter.filter(world.agents.get(level));
    }

    // Get the agents of N-1 physically under 'this' (not necessarily completely, maybe just a corner)
    protected Iterable<Agent> partiallyUnderMe()
    {
        return collidesPartiallyFilter.filter(mySons());
    }

    // Get the agents of N-1 physically under 'this'
    protected Iterable<Agent> completelyUnderMe()
    {
        return collidesCompletelyFilter.filter(mySons());
    }

    // Filters agents that are not fixed
    protected Iterable<Agent> fixed(Iterable<Agent> it)
    {
        return fixedFilter.filter(it);
    }

    // Filters agents that are not of the type of 'this'
    protected Iterable<Agent> ofMyType(Iterable<Agent> it)
    {
        return sameAgentTypeFilter.filter(it);
    }

    // Count the elements contained in an iterable
    protected static int count(Iterable<?> it)
    {
        int count = 0;
        for(Object obj : it)
            count += 1;
        return count;
    }

    // Tells whether an iterable is empty
    protected static boolean none(Iterable<?> it)
    {
        return !it.iterator().hasNext();
    }

    // Get the agents of N that overlap with 'this'
    protected Iterable<Agent> collidingWithMe()
    {
        return collidesPartiallyFilter.filter(myBrothers());
    }

    // Move the agent
    protected void move(Vector v)
    {
        Vector newPosition = position.add(v);
        if(validPosition(newPosition))
            position = newPosition;
    }

    protected Vector getPosition()
    {
        return position;
    }

    protected Vector getDiagonal()
    {
        return diagonal;
    }

    protected int getLevel()
    {
        return level;
    }

    protected static Vector randomMove()
    {
        return new Vector(new Random().nextBoolean() ? 1 : -1,
                          new Random().nextBoolean() ? 1 : -1);
    }
}

