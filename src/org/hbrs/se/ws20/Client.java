package org.hbrs.se.ws20;

public class Client {
    private MemberObject m = new MemberObject(10);
    private MemberObject m2 = new MemberObject(1);
    private MemberObject m3 = new MemberObject(2);

    private Container c = Container.getContainerInstance();

    public Client(){

    }

    public void addMember() throws ContainerException {
        c.addMember(m);
        c.addMember(m2);
        c.addMember(m3);
    }

    public void deleteMember(){
        c.deleteMember(m.getID());
        c.deleteMember(m2.getID());
        c.deleteMember(m3.getID());
    }

    public void store() throws PersistenceException {
        c.store();
    }

    public void load() throws PersistenceException {
        c.load();
    }
}
