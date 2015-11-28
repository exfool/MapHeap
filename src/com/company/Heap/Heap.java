package com.company.Heap;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap <T extends Comparable<T>>
{
    private ArrayList<T> data;
    private int          size;
    // comparator for Min Heap
    public Comparator<T> comparator = new Comparator<T>()
    {
        @Override
        public int compare(T o1, T o2)
        {
            return o1 == null ? 1 : o2 == null ? -1 : o1.compareTo(o2) == 0 ? -1 : o1.compareTo(o2);
        }
    };

    /**
     * Heap with default comparator
     * comparator used compateTo method
     */
    public Heap()
    {
        this.data = new ArrayList<T>();
        this.size = 0;
    }

    /**
     * Constructor without ensure capacity
     *
     * @param comparator for compare elements T
     */
    public Heap(Comparator<T> comparator)
    {
        this.data = new ArrayList<T>();
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * Constructor with ensure capacity
     *
     * @param maxSize    capacity of array
     * @param comparator for compare elements T
     */
    public Heap(int maxSize, Comparator<T> comparator)
    {
        this.data = new ArrayList<T>(maxSize);
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * Bulk heap creating
     *
     * @param arrayList array for insertion
     */
    public Heap(ArrayList<T> arrayList, Comparator<T> comparator)
    {
        this.comparator = comparator;
        this.data = arrayList; //
        size = arrayList.size();

        for (int i = size / 2 + 1; i >= 0; i--)
        {
            siftDown(i);
        }
    }

    /**
     * Get parent position by position of child
     *
     * @param position right's or left's cild position
     * @return parent position
     */
    private int parent(int position)
    {
        return (position > 0 ? position < size ? (position - 1) / 2 : -1 : -1);
    }

    /**
     * Get left child by position in array
     *
     * @param position of parent
     * @return position of left's child
     */
    private int leftChild(int position)
    {
        return 2 * position + 1;
    }

    /**
     * Get right child by position in array
     *
     * @param position of parent
     * @return position of right's child
     */
    private int rightChild(int position)
    {
        return 2 * position + 2;
    }


    /**
     * Get data by position with checking
     *
     * @param position of data
     * @return element of data
     */
    private T get(int position)
    {
        if (position < 0 || position >= size)
        {
            return null;
        } else
        {
            return data.get(position);
        }
    }

    /**
     * Swap element in data by position
     *
     * @param fpos first position
     * @param spos second position
     */
    private void swap(int fpos, int spos)
    {
        T first = data.get(fpos);
        data.set(fpos, data.get(spos));
        data.set(spos, first);
    }

    /**
     * Sift Down heap
     *
     * @param position upper position
     */
    private void siftDown(int position)
    {
        if ((get(leftChild(position)) == null && get(rightChild(position)) == null) || get(position) == null)
        {
            return;
        } else if (comparator.compare(get(leftChild(position)), get(rightChild(position))) < 0 &&
                   comparator.compare(get(leftChild(position)), get(position)) < 0)
        {

            swap(position, leftChild(position));
            siftDown(leftChild(position));

        } else if (comparator.compare(get(rightChild(position)), get(leftChild(position))) < 0 &&
                   comparator.compare(get(rightChild(position)), get(position)) < 0)
        {

            swap(position, rightChild(position));
            siftDown(rightChild(position));

        }
    }

    /**
     * Sift Up heap
     *
     * @param position of down position
     */
    private void siftUp(int position)
    {
        if (get(parent(position)) == null || get(position) == null || position == 0)
        {
            return;
        }
        if (comparator.compare(get(position), get(parent(position))) < 0)
        {
            swap(position, parent(position));
            siftUp(parent(position));
        }
    }

    /**
     * Inserting the element to Heap
     *
     * @param element for inserting
     */
    public void insert(T element)
    {
        data.add(size++, element);
        siftUp(size - 1);

    }


    /**
     * Get root element and delete them
     *
     * @return root T element
     */
    public T removeTop()
    {
        T popped = data.get(0);
        data.set(0, data.get(size - 1));
        size--;
        siftDown(0);
        return popped;
    }

    /**
     * Show top element without delete
     *
     * @return get top T element
     */
    public T showTop()
    {
        return data.get(0);
    }


    /**
     * Sorting by heap
     *
     * @return sorted ArrayList T
     */
    public ArrayList<T> getSorted()
    {
        ArrayList<T> sortedResult = new ArrayList<T>();
        int          count        = size;
        for (int i = 0; i < count; i++)
        {
            sortedResult.add(i, this.removeTop());
        }
        return sortedResult;
    }


}
