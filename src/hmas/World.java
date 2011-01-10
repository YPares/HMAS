package hmas;

import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Random;

// The origin (0, 0) is the UPPER left corner
// The boolean array 'image' is 'column then row' (x then y)
public class World
{
    boolean[][] image;
    TreeMap< Integer, HashSet<Agent> > agents;

    public World(String imageFile) throws java.io.IOException
    {
        this(ImageOpener.urlToBoolArray(imageFile));
    }

    public World(boolean[][] image)
    {
        this.image = image;
        agents = new TreeMap< Integer, HashSet<Agent> >();
    }

    public void stepAll()
    {
        for(HashSet<Agent> set : agents.values())
            for(Agent ag : set)
                if(!ag.isFixed())
                    ag.step();
    }

    public void stepLevel(int level)
    {
        for(Agent ag : getLevel(level))
            if(!ag.isFixed())
                ag.step();
    }

    public Vector imageDiagonal()
    {
        return new Vector(image.length - 1,
                          image[0].length - 1);
    }

    public Iterable<Agent> getLevel(int level)
    {
        if(agents.containsKey(level))
            return agents.get(level);
        return Collections.emptySet();
    }

    public void displayImage()
    {
        ImageOpener.displayBoolArray(image);
    }

    private static char getLevelRepr(int level, boolean fixed)
    {
        return (char)(((int)(fixed ? 'A' : 'a')) + level);
    }

    public void displayLevel(int level)
    {
        char[][] arr = imageToCharArray();
        for(Agent ag : getLevel(level))
        {
            Vector p = ag.getPosition();
            Vector d = ag.getDiagonal();
            for(int x=p.x(); x<=p.x()+d.x(); x++)
                for(int y=p.y(); y<=p.y()+d.y(); y++)
                    if(x >= 0 && y >= 0 &&
                       x < image.length &&
                       y < image[0].length)
                    arr[x][y] = getLevelRepr(level, ag.isFixed());
        }
        displayCharArray(arr);
    }

    public Vector randomPosition()
    {
        return new Vector(new Random().nextInt(image.length), new Random().nextInt(image[0].length));
    }

    void addAgent(int level, Agent ag)
    {
        if(!agents.containsKey(level))
        {
            agents.put(level, new HashSet<Agent>());
        }
        agents.get(level).add(ag);
    }

    private char[][] imageToCharArray()
    {
        char[][] arr = new char[image.length][image[0].length];
        for(int x=0; x<image.length; x++)
            for(int y=0; y<image[0].length; y++)
            {
                arr[x][y] = image[x][y] ? '.' : ' ';
            }
        return arr;
    }

    private static void displayCharArray(char[][] arr)
    {
        for (int y=0; y < arr[0].length; y++) {
            for (int x=0; x < arr.length; x++) {
                 System.out.print(arr[x][y]);   
            }
            System.out.println("");
        }
    }
}

