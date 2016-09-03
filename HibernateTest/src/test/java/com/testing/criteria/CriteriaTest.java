package com.testing.criteria;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.testing.hibernate.Event;
import com.testing.hibernate.Event_;

import junit.framework.TestCase;


/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from the {@code basic}
 * example, the only difference being the use of annotations to supply the metadata instead of
 * Hibernate mapping files.
 *
 * @author Steve Ebersole
 */
public class CriteriaTest extends TestCase {

  private SessionFactory sessionFactory;
  private Session session;
  String title = "Our very first event!";

  @Override
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    // configures settings from hibernate.cfg.xml
    final StandardServiceRegistry registry =
        new StandardServiceRegistryBuilder().configure().build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

      // create a couple of events...
      Event eventToAdd = new Event(title, new Date());
      Event eventToAdd2 = new Event("Event 2", new Date());

      session = sessionFactory.openSession();
      session.beginTransaction();
      session.save(eventToAdd);
      session.save(eventToAdd2);
      session.getTransaction().commit();
      session.close();

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

  // https://docs.jboss.org/hibernate/orm/5.0/userguide/html_single/Hibernate_User_Guide.html#criteria

  @SuppressWarnings({"unchecked"})
  public void testFrom() {

    // now lets pull events from the databaset and list them
    session = sessionFactory.openSession();
    session.beginTransaction();

    CriteriaBuilder builder = session.getCriteriaBuilder();

    CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
    Root<Event> root = criteria.from(Event.class);
    criteria.select(root);
    criteria.where(builder.equal(root.get(Event_.title), title));


    List<Event> events = session.createQuery(criteria).getResultList();

    session.getTransaction().commit();
    session.close();

    assertEquals(title, events.get(0).getTitle());
  }

}
