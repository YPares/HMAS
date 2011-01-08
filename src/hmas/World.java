package hmas;


// The origin (0, 0) is the UPPER left corner
// The boolean array 'image' is 'column then row'
public class World
{
    protected boolean[][] image;

    public World(boolean[][] image)
    {
        this.image = image;
    }
}

