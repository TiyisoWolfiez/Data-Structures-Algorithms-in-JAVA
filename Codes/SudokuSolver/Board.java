public class Board {
    private int numRows, numCols;
    private Cell cells[], rows[], cols[], blocks[];

    public String toString()
    {
        String res = "";
        for (int r = 0; r < numRows * numCols; r++)
        {
            if (r % numRows == 0)
            {
                res += horizLine() + "\n";
            }
            for (int c = 0; c < numRows * numCols; c++)
            {
                if (c % numCols == 0)
                {
                    res += "|";
                }
                res += cells[r * numRows * numCols + c];
            }
            res += "|\n";
        }

        res += horizLine();
        return res;
    }

    public String horizLine()
    {
        String res = "";
        for (int i = 0; i < numRows + 1 + (numRows * numCols * (String.valueOf(numRows * numCols).length() + 2)); i++)
        {
            res += "-";
        }
        return res;
    }

    public void testLinks()
    {
        System.out.println("Rows forward");

        for (int r = 0; r < numRows * numCols; r++)
        {
            System.out.print("Row " + r + "\t");
            Cell ptr = rows[r];
            while (ptr != null)
            {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.right;
            }
            System.out.println();
        }

        System.out.println("Cols forward");

        for (int c = 0; c < numRows * numCols; c++)
        {
            System.out.print("Col " + c + "\t");
            Cell ptr = cols[c];
            while (ptr != null)
            {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.below;
            }
            System.out.println();
        }

        System.out.println("Blocks");
        for (int b = 0; b < numRows * numCols; b++)
        {
            System.out.print("Block " + b + "\t");
            Cell ptr = blocks[b];
            while (ptr != null)
            {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.block;
            }
            System.out.println();
        }
    }

    public void testCells()
    {
        System.out.println("Cell\tCoord\tBlock\ttoString");
        for (Cell c : cells)
        {
            System.out.println(indexOf(c) + "\t(" + c.r + "," + c.c + ")\t" + c.b + "\t" + c);
        }
    }

    public int indexOf(Cell c)
    {
        for (int i = 0; i < numRows * numRows * numCols * numCols; i++)
        {
            if (cells[i].equals(c))
            {
                return i;
            }
        }
        return -1;
    }

    public Cell cellAt(int r, int c)
    {
        if (r < 0 || r >= numRows * numCols || c < 0 || c >= numRows * numCols)
        {
            return null;
        }
        return cells[r * numRows * numCols + c];
    }

    /*
     * Don't change anything above this line
     */

    public Board(int r, int c, String boardString)
    {
        numRows = r;
        numCols = c;
        cells = new Cell[numRows * numCols * numRows * numCols];
        String[] cellValues = boardString.split(" ");
        int index = 0, size = numRows * numCols;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (!cellValues[index].contains("-"))
                {
                    String value = (String)cellValues[index];
                    cells[index++] = new Cell(r, c, value);
                }
                else
                {
                    cells[index++] = new Cell(r, c, "-");
                }
            }
        }
        setLinks();
    }

    public void setLinks()
    {
        rows = new Cell[numRows * numCols];
        cols = new Cell[numRows * numCols];
        blocks = new Cell[numRows * numCols];
        final int setH = (numRows % numCols)/12;

        for(int r = setH; r < numRows * numCols; r++)
        {
            for(int c = setH; c < numRows * numCols; c++)
            {
                Cell cellAtt = cellAt(r, c);
                cellAtt.c = c;
                cellAtt.r = r;
            }
        }

        int s = setH;
        for(int t = setH; t < numRows * numCols * numRows * numCols; t = t + numRows * numCols, s++)
        {
            cols[s] = cells[s];
            rows[s] = cells[t];
        }
        s = setH;
        int e = setH;
        for(int t = setH; t < numRows * numCols * numRows * numCols; t = t + numCols, s++)
        {
            e = e + 1 + setH;
            blocks[s] = cells[t];
            if(!(numRows != e))
            {
                e = setH;
                t = t + numRows * numCols * (numRows - 1) + e;
            }
        }

        for(int r = setH; r < numRows * numCols * numRows * numCols; r = r + numRows * numCols)
        {
            for(int c = setH; c < numRows * numCols; c = c + 1)
            {
                int l = c + r + setH;
                if((c + 1) < numRows * numCols)
                {
                    cells[l].right = cells[l + 1];
                }
                else
                {
                    cells[l].right = null;
                }
            }
        }

        for(int c = setH; c < numRows * numCols; c = c + 1)
        {
            for(int k = setH; k < numRows * numCols * numRows * numCols; k = k + numRows * numCols)
            {
                if((k + c + numRows * numCols) >= numRows * numCols * numRows * numCols)
                {
                    cells[k + c].below = null;
                }
                else
                {
                    cells[k + c].below = cells[k + c + numRows * numCols];
                }
            }
        }

        int cr = setH, bn = setH, tn = setH;
        boolean tb = false;
        for(int c = setH; c < numRows * numCols * numRows * numCols; c = c + numCols, bn++)
        {
            int tc = c;
            for(int tr = setH; tr < numRows; tr++, tc = tc + numCols * (numRows - 1))
            {
                if(tb == true)
                {
                    cells[tn].block = cells[tc];
                    tb = false;
                }
                for(int tc2 = setH; tc2 < numCols; tc2++)
                {
                    Cell temp = cells[tc];
                    temp.b = bn;
                    if((tc2 == numCols - 1) && (tr == numRows - 1))
                    {
                        temp.block = null;
                    }
                    else
                    {
                        if(tc2 == (numCols - 1))
                        {
                            tb = true;
                            tn = tc + setH;
                        }
                        else
                        {
                            temp.block = cells[tc + 1];
                        }
                    }
                    tc = tc + 1 + setH;
                }
            }
            cr = cr + 1 + setH;
            if(!(cr != numRows))
            {
                c = c + numRows * numCols * (numRows - 1);
                cr = setH;
            }
        }
        // setH = setH + 1;
    }

    public void fullProp()
    {
        int k = 0;
        while(k < (numRows * numCols * numRows * numCols))
        {
            Cell c = cells[k];
            propCell(c);
            k = k + 1;
        }
    }

    public void propCell(Cell cell)
    {
        if (cell.value == null)
        {
            return;
        }
        int size = numCols * numRows;
        for(int r = 0; r < size; r++)
        {
            for(int c = 0; c < size; c++)
            {
                Cell cellAtt = cellAt(r, c);
                if(cellAtt.b == cell.b)
                {
                    cellAtt.removeVal(cell.value);
                }
                if(cellAtt.c == cell.c)
                {
                    cellAtt.removeVal(cell.value);
                }
                if(cellAtt.r == cell.r)
                {
                    cellAtt.removeVal(cell.value);
                }
            }
        }
    }

    public void solve()
    {
        int count = 0;
        while (soleCandidate() || uniqueCandidate() || duplicateCells())
        {
            count++;
        }
        System.out.println("Number of moves: " + count);
    }

    public boolean soleCandidate()
    {
        int size = numCols * numRows;
        for(int r = 0; r < size; r++)
        {
            for(int c = 0; c < size; c++)
            {
                Cell cellAtt = cellAt(r, c);
                if(cellAtt.possibleValues != null && cellAtt.possibleValues.length == 1)
                {
                    Integer val = cellAtt.possibleValues.head.data;
                    cellAtt.setVal(val);
                    this.propCell(cellAtt);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean uniqueCandidate()
    {
        int size = numCols * numRows;
        for (Cell row : rows)
        {
            int[] counts = new int[numRows * numCols];
            for(int i = 0; i < size; i++){
                counts[i] = 0;
            }
            Cell rowPtr = row;
            while (rowPtr != null)
            {
                if (rowPtr.possibleValues != null)
                {
                    Node<Integer> nodePtr = rowPtr.possibleValues.head;
                    while (nodePtr != null)
                    {
                        counts[nodePtr.data - 1] += 1;
                        nodePtr = nodePtr.next;
                    }
                }
                rowPtr = rowPtr.right;
            }
            for (int i = 0; i < numRows * numCols; i++)
            {
                if (counts[i] == 1)
                {
                    rowPtr = row;
                    while (rowPtr != null)
                    {
                        if (rowPtr.possibleValues != null && rowPtr.possibleValues.contains(i + 1))
                        {
                            rowPtr.setVal(i + 1);
                            propCell(rowPtr);
                        }
                        rowPtr = rowPtr.right;
                    }
                    return true;
                }
            }
        }

        for (Cell col : cols)
        {
            int[] counts = new int[numRows * numCols];
            for(int i = 0; i < size; i++){
                counts[i] = 0;
            }
            Cell colPtr = col;
            while (colPtr != null)
            {
                if (colPtr.possibleValues != null)
                {
                    Node<Integer> nodePtr = colPtr.possibleValues.head;
                    while (nodePtr != null)
                    {
                        counts[nodePtr.data - 1] += 1;
                        nodePtr = nodePtr.next;
                    }
                }
                colPtr = colPtr.below;
            }
            for (int i = 0; i < numRows * numCols; i++)
            {
                if (counts[i] == 1)
                {
                    colPtr = col;
                    while (colPtr != null)
                    {
                        if (colPtr.possibleValues != null && colPtr.possibleValues.contains(i + 1))
                        {
                            colPtr.setVal(i + 1);
                            propCell(colPtr);
                        }
                        colPtr = colPtr.below;
                    }
                    return true;
                }
            }
        }

        for (Cell block : blocks)
        {
            int[] counts = new int[numRows * numCols];
            for(int i = 0; i < size; i++){
                counts[i] = 0;
            }
            Cell blockPtr = block;
            while (blockPtr != null)
            {
                if (blockPtr.possibleValues != null)
                {
                    Node<Integer> nodePtr = blockPtr.possibleValues.head;
                    while (nodePtr != null)
                    {
                        counts[nodePtr.data - 1] += 1;
                        nodePtr = nodePtr.next;
                    }
                }
                blockPtr = blockPtr.block;
            }
            for (int i = 0; i < numRows * numCols; i++)
            {
                if (counts[i] == 1)
                {
                    blockPtr = block;
                    while (blockPtr != null)
                    {
                        if (blockPtr.possibleValues != null && blockPtr.possibleValues.contains(i + 1))
                        {
                            blockPtr.setVal(i + 1);
                            propCell(blockPtr);
                        }
                        blockPtr = blockPtr.block;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean duplicateCells()
    {
        for (Cell row : rows)
        {
            Cell rowPtr = row;
            while (rowPtr != null)
            {
                if (rowPtr.possibleValues != null && rowPtr.possibleValues.length == 2)
                {
                    Cell secondPtr = rowPtr.right;
                    while (secondPtr != null)
                    {
                        if (rowPtr.possibleValues.equals(secondPtr.possibleValues))
                        {
                            Cell thirdPtr = row;
                            boolean change = false;
                            while (thirdPtr != null)
                            {
                                if (!thirdPtr.equals(secondPtr) && !thirdPtr.equals(rowPtr) && thirdPtr != secondPtr && thirdPtr != rowPtr && thirdPtr.possibleValues != null)
                                {
                                    change = (change || thirdPtr.possibleValues.remove(rowPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.right;
                            }
                            if (change)
                            {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.right;
                    }
                }
                rowPtr = rowPtr.right;
            }
        }

        for (Cell col : cols)
        {
            Cell colPtr = col;
            while (colPtr != null)
            {
                if (colPtr.possibleValues != null && colPtr.possibleValues.length == 2)
                {
                    Cell secondPtr = colPtr.below;
                    while (secondPtr != null)
                    {
                        if (colPtr.possibleValues.equals(secondPtr.possibleValues))
                        {
                            Cell thirdPtr = col;
                            boolean change = false;
                            while (thirdPtr != null)
                            {
                                if (!thirdPtr.equals(secondPtr) && !thirdPtr.equals(colPtr) && thirdPtr != secondPtr && thirdPtr != colPtr && thirdPtr.possibleValues != null)
                                {
                                    change = (change || thirdPtr.possibleValues.remove(colPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.below;
                            }
                            if (change)
                            {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.below;
                    }
                }
                colPtr = colPtr.below;
            }
        }

        for (Cell block : blocks)
        {
            Cell blockPtr = block;
            while (blockPtr != null)
            {
                if (blockPtr.possibleValues != null && blockPtr.possibleValues.length == 2)
                {
                    Cell secondPtr = blockPtr.right;
                    while (secondPtr != null)
                    {
                        if (blockPtr.possibleValues.equals(secondPtr.possibleValues))
                        {
                            Cell thirdPtr = block;
                            boolean change = false;
                            while (thirdPtr != null)
                            {
                                if (!thirdPtr.equals(secondPtr) && !thirdPtr.equals(blockPtr) && thirdPtr != secondPtr && thirdPtr != blockPtr && thirdPtr.possibleValues != null)
                                {
                                    change = (change || thirdPtr.possibleValues.remove(blockPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.block;
                            }
                            if (change)
                            {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.block;
                    }
                }
                blockPtr = blockPtr.block;
            }
        }
        return false;
    }
}
