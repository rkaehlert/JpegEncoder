package exercise_one.filter;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.model.color.Colormodel;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;

public class FilterReductionByM extends Filter
{
    private static final int OFFSET_ROW = 2;
    private static final int OFFSET_COL = 2;

    @Override
    public TreeMap<Coordinate, Colormodel> filter(TreeMap<Coordinate, Colormodel> pixel)
    {
        TreeMap<Coordinate, Colormodel> returnValue = new TreeMap<Coordinate, Colormodel>();
        YCbCr ycbcr = null;
        Coordinate coordinate = null;
        for (Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet())
        {
            coordinate = (Coordinate) entry.getKey();
            ycbcr = (YCbCr) entry.getValue();
            if (this.isValidRow(coordinate.getY()))
            {
                if (this.isValidCol(coordinate.getX()))
                {
                    returnValue.put(coordinate, ycbcr);
                }
            }
        }
        return returnValue;
    }

    private boolean isValidRow(int row)
    {
        return (row % OFFSET_ROW) == 0;
    }

    private boolean isValidCol(int column)
    {
        return (column % OFFSET_COL) == 0;
    }

    @Override
    public void reset()
    {

    }

}
