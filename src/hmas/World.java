package hmas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;


// The origin (0, 0) is the UPPER left corner
// The boolean array 'image' is 'column then row' (x then y)
public class World
{
    boolean[][] image;
    ArrayList< HashSet<Agent> > agents;

    public World(String imageFile) throws java.io.IOException
    {
        this(ImageOpener.urlToBoolArray(imageFile));
    }

    public World(boolean[][] image)
    {
        this.image = image;
    }

    public void step()
    {
        for(HashSet<Agent> set : agents)
            for(Agent ag : set)
                ag.step();
    }

    public Vector imageDiagonal()
    {
        return new Vector(image.length - 1,
                          image[0].length - 1);
    }

    public Iterable<Agent> getLevel(int level)
    {
        if(level < agents.size())
            return agents.get(level);
        return Collections.emptySet();
    }

    public void displayImage()
    {
        ImageOpener.displayBoolArray(image);
    }

    public void displayLevel(int level)
    {
        char[][] arr = imageToCharArray();
        char lev = Character.forDigit(level, 10);
        for(Agent ag : getLevel(level))
        {
            Vector p = ag.getPosition();
            Vector d = ag.getDiagonal();
            for(int x=p.x(); x<=p.x()+d.x(); x++)
                for(int y=p.y(); y<=p.y()+d.y(); y++)
                    if(x >= 0 && y >= 0 &&
                       x < image.length &&
                       y < image[0].length)
                    arr[x][y] = lev;
        }
        displayCharArray(arr);
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

    private char[][] imageToCharArray()
    {
        char[][] arr = new char[image.length][image[0].length];
        for(int x=0; x<image.length; x++)
            for(int y=0; y<image[0].length; y++)
            {
                arr[x][y] = image[x][y] ? 'X' : ' ';
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

