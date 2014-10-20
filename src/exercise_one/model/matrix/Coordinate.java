package exercise_one.model.matrix;

public class Coordinate implements Comparable
{

    private int x;
    private int y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

//    public int compareTo(Object o)
//    {
//        if(this.y > ((Coordinate)o).y) return 1;
//        else if(this.y <= ((Coordinate)o).y && this.x > ((Coordinate)o).x) return 1;
//        else if(this.y == ((Coordinate)o).y && this.x == ((Coordinate)o).x) return 0;
//        else return -1;
//    }
    @Override
    public int compareTo(Object o)
    {
        Coordinate coordinate = (Coordinate) o;
        int x, y;
        if (this.y != coordinate.y)
        {
            y = this.y;
            x = coordinate.y;
        }
        else
        {
            y = this.x;
            x = coordinate.x;
        }
        if (y < x)
        {
            return -1;
        }
        else if (y == x)
        {
            return 0;
        }
        else
        {
            return 1;
        }

    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }
        if (!(o instanceof Coordinate))
        {
            return false;
        }

        Coordinate other = (Coordinate) o;
        if (this.x != other.x)
        {
            return false;
        }
        if (this.y != other.y)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Koordinate [x = " + x + ", y = " + y + "] ";
    }
}
