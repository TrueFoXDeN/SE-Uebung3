package org.hbrs.se.ws20;

import java.util.LinkedList;

public class Container {
    private LinkedList<Member> list = new LinkedList<>();


    public void addMember(Member member) throws ContainerException{

        if (!contains(member.getID())) {
            list.add(member);
        } else {
            throw new ContainerException("Das Member-Objekt mit der ID ["+member.getID()+"] ist bereits vorhanden!");

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

    /**
     * Welche Nachteile ergeben sich aus ihrer Sicht für ein solchen
     * Fehler- handling gegenüber einer Lösung mit Exceptions?
     *
     * Beim Vergleichen von Strings können leicht Fehler auftreten.
     * Jeder unterschied im Vergleichs-String führt auch zu fehlerhaften Prüfung.
     * String vergleich mit == sind von gleicher Speicheradresse abhängig,
     * daher sollte die Rückgabe mit der equals-Methode erfolgen.
     */





    public void dump() {
        for(Member m : list){
            System.out.println(m);
        }
    }

    public int size() {
        return list.size();
    }
}
