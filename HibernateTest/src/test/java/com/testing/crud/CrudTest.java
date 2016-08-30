package com.testing.crud;

import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import junit.framework.TestCase;


/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from the {@code basic}
 * example, the only difference being the use of annotations to supply the metadata instead of
 * Hibernate mapping files.
 *
 * @author Steve Ebersole
 */
public class CrudTest extends TestCase {

  private SessionFactory sessionFactory;

  @Override
  protected void setUp() throws Exception {

    // Allows to connect to H2 via browser through http://localhost:8082/
    // http://stackoverflow.com/questions/7309359/view-content-of-h2-or-hsqldb-in-memory-database
    Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();

    // A SessionFactory is set up once for an application!
    // configures settings from hibernate.cfg.xml
    final StandardServiceRegistry registry =
        new StandardServiceRegistryBuilder().configure().build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      // The registry would be destroyed by the SessionFactory, but we had trouble building the
      // SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Override
  protected void tearDown() throws Exception {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }

  @SuppressWarnings({"unchecked"})
  public void testBasicUsage() {

    Session session = sessionFactory.openSession();
    session.beginTransaction();

    // Add
    for (int i = 0; i < 10; i++) {

      Person person = new Person();
      person.setName("perso" + i);
      session.save(person);
    }

    // Assert add
    List<Person> persons = new ArrayList<Person>();

    for (int i = 0; i < 10; i++) {

      persons.add((Person) session.get(Person.class, i));
    }

    assertEquals(10, persons.size());

    Person personFromDb = (Person) session.get(Person.class, 1);

    // Assert get
    assertEquals(1, personFromDb.getId());

    // Assert update

    personFromDb.setName("UPDATED");
    session.update(personFromDb);

    assertEquals("UPDATED", personFromDb.getName());

    // Assert delete
    session.delete(personFromDb);
    personFromDb = (Person) session.get(Person.class, 1);
    assertEquals(null, personFromDb);

    session.getTransaction().commit();
    session.close();

  }
}
