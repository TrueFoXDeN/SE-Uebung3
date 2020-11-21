package org.hbrs.se.ws20;

public class Test {
    public static void main(String[] args) {
        MemberObject m = new MemberObject(10);
        MemberObject m2 = new MemberObject(1);
        MemberObject m3 = new MemberObject(2);

        Container c = Container.getContainerInstance();
        PersistenceStrategy<Member> ps = new PersistenceStrategyStream<>();
        c.setPersistenceStrategy(ps);

        try {
            c.addMember(m);
            c.addMember(m2);
            c.addMember(m3);
        } catch (ContainerException e) {
            e.printStackTrace();
        }

        try {
            c.store();
            c.load();
            c.dump();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

    }
}
