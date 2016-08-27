package com.testing.inheritance.defaultway;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.testing.inheritance.defaultway.Cat;
import com.testing.inheritance.defaultway.Dog;
import com.testing.inheritance.defaultway.Owner;

import junit.framework.TestCase;

/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from the {@code basic}
 * example, the only difference being the use of annotations to supply the metadata instead of
 * Hibernate mapping files.
 *
 * @author Steve Ebersole
 */
public class InheritanceDefaultWayTest extends TestCase {

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

  public void testBasicUsage() {

    // Create
    Owner owner = new Owner();
    owner.setName("Owner");

    // Create
    Dog dog = new Dog();
    dog.setName("dog");
    dog.setPersonality("dog personality");

    Cat cat = new Cat();
    cat.setName("cat");
    cat.setPersonality("cat personality");

    // Create
    owner.getPetList().add(dog);
    owner.getPetList().add(cat);

    // Session - Save
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(owner);
    session.save(dog);
    session.save(cat);

    session.getTransaction().commit();
    session.close();

    // Session - Fetch
    session = sessionFactory.openSession();
    session.beginTransaction();

    session.getTransaction().commit();
    session.close();

  }
}
