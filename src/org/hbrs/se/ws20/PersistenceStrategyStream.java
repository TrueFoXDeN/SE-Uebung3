package org.hbrs.se.ws20;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {
    @Override
    public void openConnection() throws PersistenceException {

    }

    @Override
    public void closeConnection() throws PersistenceException {

    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException  {
        try {
            FileOutputStream fos = new FileOutputStream("save.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List l = member;
            oos.writeObject(l);
            oos.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.SaveFailure, "IOException occurred.");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException  {

        try {
            FileInputStream fis = new FileInputStream("save.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                Object obj = ois.readObject();
                List<Member> l = null;
                if(obj instanceof List<?>){
                    l = (List<Member>)obj;
                }
                ois.close();
                return l;
            } catch (ClassNotFoundException e) {
                throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "ClassNotFoundException occurred.");
            }

        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "IOException occurred.");
        }

    }
}
