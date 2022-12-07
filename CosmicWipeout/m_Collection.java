package CosmicWipeout;
import java.util.ArrayList;
import java.util.Iterator;

public class m_Collection<T> implements Iterable<T> {
    private ArrayList<T> objects;

    //purpose: construct and initialize m_Collection object.
    //inputs: self - the m_Collection object being created
    //outputs: none.
    //assumptions: none.
    public m_Collection(){
        this.objects = new ArrayList<T>();
    }

    //purpose: add new object to ArrayList.
    //inputs: newObject - T added to objects ArrayList.
    //outputs: none.
    //assumptions: none.
    public void add(T newObject) {
        objects.add(newObject);
    }

    //purpose: gets size of objects ArrayList.
    //inputs: none.
    //outputs: returns size of objects ArrayList.
    //assumptions: none.
    public int size() {
        return objects.size();
    }

    //purpose: remove object from the ArrayList.
    //inputs: index - index to remove object from.
    //outputs: none.
    //assumptions: none.
    public void remove(int index){
        objects.remove(index);
    }

    //purpose: checks whether objects ArrayList is empty.
    //inputs: none.
    //outputs: returns whether objects ArrayList is empty.
    //assumptions: none.
    public boolean isEmpty() {
        return objects.isEmpty();
    }

    //purpose: gets object from Arraylist.
    //inputs: index - int specifying which index needed object is at.
    //outputs: returns object from specified index in objects ArrayList.
    //assumptions: none.
    public T get(int index) {
        return objects.get(index);
    }

    //purpose: checks whether objects ArrayList contains specified object.
    //inputs: object - T that is being checked for.
    //outputs: returns whether objects ArrayList contains specified object.
    //assumptions: none.
    public boolean contains(T object){
        return objects.contains(object);
    }

    //purpose: creates a copy of self with no duplicate objects.
    //inputs: none.
    //outputs: returns copy of self with no duplicate objects.
    //assumptions: none.
    public m_Collection<T> getUnique(){
        m_Collection<T> uniqueObjects = new m_Collection<T>();
        for (T object : objects){
            if (!uniqueObjects.contains(object))
                uniqueObjects.add(object);
        }
        return uniqueObjects;
    }

    //purpose: construct and initialize CollectionIterator object.
    //inputs: self - the CollectionIterator object being created
    //outputs: none.
    //assumptions: none.
    public Iterator<T> iterator() {
        return new CollectionIterator();
    }
    private class CollectionIterator implements Iterator<T> {
        private int index = 0;

        //purpose: checks whether iterating m_Collection has objects remaining.
        //inputs: none.
        //outputs: returns whether iterating m_Collection has objects remaining.
        //assumptions: none.
        public boolean hasNext() {
            return index < size();
        }

        //purpose: iterates to next object in m_Collection.
        //inputs: none.
        //outputs: returns next object T in m_Collection.
        //assumptions: none.
        public T next() {
            return get(index++);
        }

    }
}