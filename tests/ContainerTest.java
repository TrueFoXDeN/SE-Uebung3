import org.hbrs.se.ws20.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    MemberView memberView = new MemberView();

    Container c = Container.getContainerInstance();

    MemberObject[] member = new MemberObject[3];

    @BeforeEach
    void setUp() {
        PersistenceStrategy<Member> ps = new PersistenceStrategyStream<>();
        c.setPersistenceStrategy(ps);
        for (int i = 0; i < member.length; i++) {
            member[i] = new MemberObject(i);
        }

    }

    @AfterEach
    void tearDown() {
        c.clearList();
    }

    @Test
    void addMember() {
        for (int i = 0; i < member.length; i++) {
            try {
                c.addMember(member[i]);
                assertEquals(i + 1, c.size());

            } catch (ContainerException e) {
                e.printStackTrace();
            }
        }
        try {
            c.addMember(null);
            assertThrows(ContainerException.class, () -> {c.addMember(member[0]);});
        } catch (ContainerException e) {
            e.printStackTrace();
        }

    }

    @Test
    void deleteMember() {
        for (int i = 0; i < member.length; i++) {
            try {
                c.addMember(member[i]);
            } catch (ContainerException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < member.length; i++) {
            assertEquals("Member (ID = ["+i+"]) deleted", c.deleteMember(i));
            assertEquals(member.length - 1 - i, c.size());
        }

        assertEquals("Member (ID = [0]) not found", c.deleteMember(0));
    }

    @Test
    void store() {

        for (int i = 0; i < member.length; i++) {
            try {
                c.addMember(member[i]);
            } catch (ContainerException e) {
                e.printStackTrace();
            }
        }

        assertDoesNotThrow(() -> {
            c.store();
        });

    }

    @Test
    void load() {

        for (int i = 0; i < member.length; i++) {
            try {
                c.addMember(member[i]);
            } catch (ContainerException e) {
                e.printStackTrace();
            }
        }

        try {
            c.store();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        assertDoesNotThrow(() -> {
            c.load();
        });

        assertNotEquals(null, c.getCurrentList());
        assertEquals(member.length, c.size());

        for (int i = 0; i < member.length; i++) {
            assertEquals("Member (ID = ["+i+"]) deleted", c.deleteMember(i));
            assertEquals(member.length - 1 - i, c.size());
        }

    }

}