package org.hbrs.se.ws20;

import java.util.LinkedList;
import java.util.List;

public class Container{
    private LinkedList<Member> list = new LinkedList<>();
    private PersistenceStrategy<Member> persistenceStrategy;
    private MemberView memberView = new MemberView();

    private static Container container;
    private Container(){}

    public static Container getContainerInstance(){
        if(container == null){
            container = new Container();
            return container;
        }else{
            return container;
        }
    }

    public void store() throws PersistenceException{
        persistenceStrategy.save(list);
    }

    public void load() throws PersistenceException{
        list = (LinkedList<Member>) persistenceStrategy.load();
    }

    public void addMember(Member member) throws ContainerException{
        if(member != null){
            if (!contains(member.getID())) {
                list.add(member);
            } else {
                throw new ContainerException("Das Member-Objekt mit der ID ["+member.getID()+"] ist bereits vorhanden!");

            }
        }

    }

    private boolean contains(int id) {
        for(Member m : list){
            if (m.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String deleteMember(int id) {
        Member toDelete = null;
        for(Member m : list){
            if (m.getID().equals(id)) {
                toDelete = m;
            }
        }
        if(toDelete != null){
            list.remove(toDelete);
            return "Member (ID = ["+id+"]) deleted";
        }

        return "Member (ID = ["+id+"]) not found";

    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    /**
     * Welche Nachteile ergeben sich aus ihrer Sicht für ein solchen
     * Fehler- handling gegenüber einer Lösung mit Exceptions?
     *
     * Beim Vergleichen von Strings können leicht Fehler auftreten.
     * Jeder unterschied im Vergleichs-String führt auch zu fehlerhaften Prüfung.
     * String vergleich mit == sind von gleicher Speicheradresse abhängig,
     * daher sollte die Rückgabe mit der equals-Methode erfolgen.
     */

    public void clearList(){
        list.clear();
    }

    public List<Member> getCurrentList(){
        return list;
    }

    public int size() {
        return list.size();
    }
}
