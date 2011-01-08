package hmas;


public class Vector
{
    private int _x;
    private int _y;

    public Vector(int x, int y)
    {
        this._x = x;
        this._y = y;
    }

    public int x()
    {
        return _x;
    }

    public int y()
    {
        return _y;
    }

    public Vector add(Vector v)
    {
        return new Vector(_x + v._x, _y + v._y);
    }

    public Vector sub(Vector v)
    {
        return new Vector(_x - v._x, _y - v._y);
    }

    // Dot product ("produit cartésien")
    public int dot(Vector v)
    {
        return _x*v._x + _y*v._y;
    }
}

