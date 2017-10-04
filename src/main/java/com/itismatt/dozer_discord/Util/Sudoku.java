package com.itismatt.dozer_discord.Util;


public class Sudoku
{
    private static final int M_SIZE = 9;

    private static final int M_BLANK = 0;


    private int[][] m_puzzle = new int[M_SIZE][M_SIZE];


    private Point m_point = new Point();



    public Sudoku()
    {
        m_point.x = 0;
        m_point.y = 0;
    }


    public void getPuzzle(String puzzleString)
    {
        if(puzzleString.equals(""))
        {
            m_point.x = 0;
            m_point.y = 0;
            return;
        }

        m_puzzle[(int)m_point.y][(int)m_point.x] = Integer.valueOf(puzzleString.substring(0 , 1));

        m_nextPoint();

        getPuzzle(puzzleString.substring(1));
    }


    public String puzzleString()
    {
        String str = "";

        for(int i = 0; i < M_SIZE; i ++)
        {
            for(int j = 0; j < M_SIZE; j ++)
            {
                str += Integer.toString(m_puzzle[i][j]) + " ";
            }

            str += "\n";
        }

        return str;
    }


    public void printPuzzle()
    {
        for(int i = 0; i < M_SIZE; i ++)
        {
            for(int j = 0; j < M_SIZE; j ++)
            {
                System.out.print(m_puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }


    public boolean solvePuzzle()
    {
        int localX;
        int localY;

        for(int i = 1; i <= 9; i ++)
        {
            localX = (int)m_point.x;
            localY = (int)m_point.y;

            if(!m_inBounds())
            {
                return true;
            }

            if(m_puzzle[localY][localX] == M_BLANK)
            {
                if(m_checkRow(i) && m_checkColumn(i) && m_checkGroup(i))
                {
                    m_puzzle[localY][localX] = i;

                    m_nextPoint();

                    if(solvePuzzle())
                    {
                        return true;
                    }
                    else
                    {
                        m_point.x = localX;
                        m_point.y = localY;

                        m_puzzle[localY][localX] = M_BLANK;
                    }
                }
            }
            else
            {
                m_nextPoint();
                i = 0;
            }
        }

        return false;
    }


    private void m_nextPoint()
    {
        if(m_point.x == M_SIZE - 1)
        {
            m_point.x = 0;
            m_point.y ++;
        }
        else
        {
            m_point.x++;
        }
    }


    private boolean m_inBounds()
    {
        if(m_point.x >= M_SIZE || m_point.y >= M_SIZE)
        {
            return false;
        }

        return true;
    }


    private boolean m_checkRow(final int VALUE)
    {
        for(int i = 0; i < M_SIZE; i ++)
        {
            if(m_puzzle[(int)m_point.y][i] == VALUE)
            {
                return false;
            }
        }

        return true;
    }


    private boolean m_checkColumn(final int VALUE)
    {
        for(int i = 0; i < M_SIZE; i ++)
        {
            if(m_puzzle[i][(int)m_point.x] == VALUE)
            {
                return false;
            }
        }

        return true;
    }


    private boolean m_checkGroup(final int VALUE)
    {
        int groupRow = (int)m_point.y / 3;
        int groupColumn = (int)m_point.x / 3;

        for(int i = 3 * groupRow; i < 3 * (groupRow + 1); i ++)
        {
            for(int j = 3 * groupColumn; j < 3 * (groupColumn + 1); j ++)
            {
                if(m_puzzle[i][j] == VALUE)
                {
                    return false;
                }
            }
        }

        return true;
    }
}