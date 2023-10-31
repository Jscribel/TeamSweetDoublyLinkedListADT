package adts;

import java.util.Iterator;

import interfaces.ListInterface;

public class DLLList implements ListInterface<E>, Iterable<E>{
    protected DLLNode<E> head;
    protected DLLNode<E> tail;
    protected int numberElements;
    protected boolean found;
    protected DLLNode<E> location;

    //Helper
    private void find(E element){
        found = false;
        location = head;
        while(location != null){
            if(location.getData().equals(element)){
                found = true;
                return;
            }
            location = location.getNext();
        }
    }

    //Getters
    @Override
    public E get(int index){
        if(numberElements < index)//if out of bounds, returns null
            return null;
        DLLNode<E> nodeTemp = head;
        for(int i = 0; i < index; i++){
            nodeTemp = nodeTemp.getNext();
        }
        return nodeTemp.getData();
    }
    @Override
    public E get(E element){
        find(element);
        if(found)
            return location.getData();
        return null;
    }

    //Editors
    @Override
    public boolean remove(E element){
        find(element);
        if(found){
            if(head.getData().equals(element)){//if the head's data matches the element, the head will be set to the next node and the new node's previous will be set to null
                head = head.getNext();
                head.setPrev(null);
            }
            else if(tail.getData().equals(element)){//if the tail's data matches the element, the tail will be set to the node before the tail and that node's next will be set to null
                tail = tail.getPrev();
                tail.setNext(null);
            }
            else{
                location.getPrev().setNext(location.getNext());
                location.getNext().setPrev(location.getPrev());
            }
            numberElements--;
        }
        return found;
    }
    @Override
    public void add(E element){
        DLLNode<E> nodeNew = new DLLNode<E>(element);
        if(head == null){
            head = nodeNew;
        }
        else{
            tail.setNext(nodeNew);
            nodeNew.setPrev(tail);
        }
        tail = nodeNew;
        numberElements++;
    }

    //Information
    @Override
    public boolean isEmpty(){
        return head == null;
    }
    @Override
    public int size(){
        return numberElements;
    }
    @Override
    public boolean contains(E element){
        find(element);
        return found;
    }

    //Misc
    @Override
    public Iterator<E> iterator(){
        return null;
    }
    @Override
    public String toString(){
        StringBuilder stringCoversion = new StringBuilder("[");
        DLLNode<E> pointer = head;
        while(pointer != null){
            if(stringCoversion.length() > 1)
                stringCoversion.append(", ");
            stringCoversion.append(pointer.getData());
            pointer = pointer.getNext();
        }
        stringCoversion.append("]");
        return stringCoversion.toString();
    }
    
}
