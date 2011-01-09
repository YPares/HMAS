package hmas;

import java.util.ArrayList;
import java.util.HashSet;


// The origin (0, 0) is the UPPER left corner
// The boolean array 'image' is 'column then row'
public class World
{
    boolean[][] image;
    ArrayList< HashSet<Agent> > agents;

    public World(boolean[][] image)
    {
        this.image = image;
    }

    void addAgent(int level, Agent ag)
    {
        int maxLevel = agents.size()-1;
        if(level > maxLevel)
        {
            agents.ensureCapacity(level+1);
            for(int i=maxLevel+1; i<=level; i++)
                agents.set(i, new HashSet<Agent>());
        }
        agents.get(level).add(ag);
    }

    public Iterable<Agent> getLevel(int level)
    {
        if(level < agents.size())
            return agents.get(level);
        return new HashSet<Agent>();
    }
}

