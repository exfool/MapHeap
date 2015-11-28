package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class HeapTest
{
    public Random              rnd        = new Random();
    public int                 maxSize    = 12;
    public ArrayList<Integer>  data       = new ArrayList<Integer>();
    public Comparator<Integer> comparator = new Comparator<Integer>()
    {
        @Override
        public int compare(Integer o1, Integer o2)
        {
            return o1 == null ? 1 : o2 == null ? -1 : o1.compareTo(o2) == 0 ? -1 : o1.compareTo(o2);
        }
    };
    public Heap<Integer>       mp         = new Heap<Integer>();

    @org.junit.Before
    public void setUp() throws Exception
    {
        for (int i = 0; i < maxSize; i++)
        {
            int buff = rnd.nextInt(maxSize);
            data.add(buff);
            mp.insert(buff);
        }
        //mp = new MapHeap<Integer>(data, comparator); // uncomment for test for build heap
        data.sort(comparator);
    }

    @org.junit.Test
    public void testGetTop() throws Exception
    {
        Assert.assertEquals(data.get(0), mp.showTop());
    }

    @org.junit.Test
    public void testRemoveTop() throws Exception
    {
        for (int i = 0; i < maxSize; i++)
        {
            Assert.assertEquals(data.get(i), mp.removeTop());
        }
    }

    @Test
    public void testGetSorted() throws Exception
    {
        ArrayList<Integer> heapData = mp.getSorted();
        System.out.print("HEAP      : ");
        System.out.println(heapData);
        System.out.print("RIGHT DATA: ");
        System.out.println(data);

        int count = data.size();
        for (int i = 0; i < count; i++)
        {
            Assert.assertEquals(data.get(i), heapData.get(i));
        }
    }
}