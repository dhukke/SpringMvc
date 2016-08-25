package com.testing.onetomany;

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
public class OneToManyTest extends TestCase {

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
    UserOneToMany user = new UserOneToMany();
    user.setUserName("Test1");

    // Create
    VehicleOneToMany vehicle = new VehicleOneToMany();
    vehicle.setVehicleName("car");
    
    VehicleOneToMany vehicle2 = new VehicleOneToMany();
    vehicle2.setVehicleName("bus");

    // Associate
    user.getVehicle().add(vehicle);
    user.getVehicle().add(vehicle2);
    vehicle.setUserOneToMany(user);
    vehicle2.setUserOneToMany(user);

    // Session - Save
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(user);
    session.save(vehicle);
    session.save(vehicle2);
    session.getTransaction().commit();
    session.close();

    // Session - Fetch
    session = sessionFactory.openSession();
    session.beginTransaction();



    session.getTransaction().commit();
    session.close();

  }
}
