package hmas;

public class Vector
{
    private int x;
    private int y;

    public Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector v)
    {
        return new Vector(x + v.x, y + v.y);
    }

    public Vector sub(Vector v)
    {
        return new Vector(x - v.x, y - v.y);
    }

    // Dot product ("produit cartésien")
    public int dot(Vector v)
    {
        return x*v.x + y*v.y;
    }
}

