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

    // Makes a World out of an image file
    public World(String imageFile) throws java.io.IOException
    {
        this(ImageOpener.urlToBoolArray(imageFile));
    }

    // Makes a World out of a boolean array that represents an image
    public World(boolean[][] image)
    {
        this.image = image;
        agents = new TreeMap< Integer, HashSet<Agent> >();
    }

    // Advance every agent in the world of one step, starting by level 0
    public void stepAll()
    {
        for(HashSet<Agent> set : agents.values())
            for(Agent ag : set)
                if(!ag.isFixed())
                    ag.step();
    }

    // Advance of one step a specific level of agents
    public void stepLevel(int level)
    {
        for(Agent ag : getLevel(level))
            if(!ag.isFixed())
                ag.step();
    }

    // Gets the diagonal of an image, from top left pixel to bottom right pixel (i.e. 'its size - Vector(1, 1)')
    public Vector imageDiagonal()
    {
        return new Vector(image.length - 1,
                          image[0].length - 1);
    }

    // Gets the agents inside a specific level
    public Iterable<Agent> getLevel(int level)
    {
        if(agents.containsKey(level))
            return agents.get(level);
        return Collections.emptySet();
    }

    // Displays the bool array of the world
    public void displayImage()
    {
        ImageOpener.displayBoolArray(image);
    }

    // Returns a letter for level (A for level 0, B for level 1...)
    // This letter is uppercase if fixed is true
    private static char getLevelRepr(int level, boolean fixed)
    {
        return (char)(((int)(fixed ? 'A' : 'a')) + level);
    }

    // Displays the bool array of the world and a specific level of agents
    public void displayLevel(int level)
    {
        char[][] arr = imageToCharArray();
        addLevelToDisplay(level, arr);
        displayCharArray(arr);
    }

    // Displays the bool array of the world and some levels of agents, such as levels[0] is displayed on top
    public void displayLevels(int[] levels)
    {
        char[][] arr = imageToCharArray();
        for(int i=levels.length-1; i>=0; i--)
            addLevelToDisplay(levels[i], arr);
        displayCharArray(arr);
    }

    private void addLevelToDisplay(int level, char[][] arr)
    {
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
    }

    // Generates a valid random position in the world
    public Vector randomPosition()
    {
        return new Vector(new Random().nextInt(image.length), new Random().nextInt(image[0].length));
    }

    // Adds an agent at a specific level
    void addAgent(int level, Agent ag)
    {
        if(!agents.containsKey(level))
        {
            agents.put(level, new HashSet<Agent>());
        }
        agents.get(level).add(ag);
    }

    // Turns an image into a CharArray.
    // Different of ImageOpener.displayBoolArray in that it doesn't print anything, it only builds a char[][]
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

    // Displays an array of char
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

