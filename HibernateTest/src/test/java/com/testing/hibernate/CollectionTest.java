package com.testing.hibernate;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.testing.hibernate.Address;
import com.testing.hibernate.UserWithMultipleAddress;

import junit.framework.TestCase;


/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from the {@code basic}
 * example, the only difference being the use of annotations to supply the metadata instead of
 * Hibernate mapping files.
 *
 * @author Steve Ebersole
 */
public class CollectionTest extends TestCase {

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

    // create a couple of events...

    UserWithMultipleAddress user = new UserWithMultipleAddress();
    user.setUserName("Test1");

    Address address = new Address();
    String city1 = "City 1";

    address.setCity(city1);
    address.setStreet("Street 1");

    Address address2 = new Address();
    String city2 = "City 2";

    address2.setCity(city2);
    address2.setStreet("Street 2");

    user.addAddress(address);
    user.addAddress(address2);

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(user);
    session.getTransaction().commit();
    session.close();

    // now lets pull events from the databaset and list them
    session = sessionFactory.openSession();
    session.beginTransaction();

    UserWithMultipleAddress userFromDb =
        (UserWithMultipleAddress) session.get(UserWithMultipleAddress.class, 1);

    System.out.println(userFromDb);

    assertEquals(2, userFromDb.getAddresses().size());

    session.getTransaction().commit();
    session.close();

  }
}
