public class Cell {
    public int numRows, numCols, r, c, b;
    public Cell below, right, block;
    public Integer value;
    public List<Integer> possibleValues;

    public String toString()
    {
        if (value == null)
        {

            String res = " ";
            for (int i = 0; i < String.valueOf(numRows * numCols).length(); i++)
            {
                res += "-";
            }
            res += " ";
            return res;
        }
        return " " + String.format("%" + String.valueOf(numRows * numCols).length() + "d", value).replace(" ", "0")
                + " ";
    }

    /*
     * Don't change anything above this line
     */

    public Cell(int nR, int nC, String inp)
    {
        possibleValues = new List<>();
        numRows = nR;
        numCols = nC;

        if (inp.equals("-") == false)
        {
            setVal(Integer.parseInt(inp));
            value = Integer.parseInt(inp);
        }
        else
        {
            value = null;
            for (Integer i = 1; i <= nR * nC; i++)
            {
                possibleValues.append(i);
            }
        }
    }

    public void removeVal(int val)
    {
        if (possibleValues != null)
        {
            Integer rm = (Integer) val;
            possibleValues.remove(rm);
        }
    }

    public void setVal(int val)
    {
        value = val;
        possibleValues = null;
    }
}
