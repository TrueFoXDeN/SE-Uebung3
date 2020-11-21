package org.hbrs.se.ws20;

public class Test {
    public static void main(String[] args) {

        MemberView memberView = new MemberView();
        Container c = Container.getContainerInstance();
        PersistenceStrategy<Member> ps = new PersistenceStrategyStream<>();
        c.setPersistenceStrategy(ps);
        Client cl = new Client();

        try {
            cl.addMember();
            memberView.dump(c.getCurrentList());
            System.out.println("----------------");
            cl.store();
            cl.deleteMember();
            memberView.dump(c.getCurrentList());
            System.out.println("----------------");
            cl.load();
            memberView.dump(c.getCurrentList());
        } catch (ContainerException | PersistenceException e) {
            e.printStackTrace();
        }

    }
}
