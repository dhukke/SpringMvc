package com.testing.hql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.testing.hibernate.Event;

import junit.framework.TestCase;


/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from the {@code basic}
 * example, the only difference being the use of annotations to supply the metadata instead of
 * Hibernate mapping files.
 *
 * @author Steve Ebersole
 */
public class HqlTest extends TestCase {

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

  @SuppressWarnings({"unchecked"})
  public void testFrom() {

    // now lets pull events from the databaset and list them
    session = sessionFactory.openSession();
    session.beginTransaction();

    Query<Event> query = session.createQuery("from Event");
    List<Event> resultList = query.getResultList();

    session.getTransaction().commit();
    session.close();

    assertEquals(2, resultList.size());
  }

  @SuppressWarnings({"unchecked"})
  public void testWhere() {

    // now lets pull events from the databaset and list them
    session = sessionFactory.openSession();
    session.beginTransaction();

    long id = 1;

    Query<Event> query = session.createQuery("from Event where id = :id");

    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put("id", id);
    query.setProperties(properties);

    List<Event> resultList = (List<Event>) query.getResultList();

    session.getTransaction().commit();
    session.close();

    assertEquals(title, resultList.get(0).getTitle());
  }

  @SuppressWarnings({"unchecked"})
  public void testSelect() {

    // now lets pull events from the databaset and list them
    session = sessionFactory.openSession();
    session.beginTransaction();

    long id = 1;

    Query<String> query = session.createQuery("select title from Event where id = :id");
    query.setParameter("id", id);

    List<String> resultList = query.getResultList();

    session.getTransaction().commit();
    session.close();

    assertEquals(title, resultList.get(0));
  }

  @SuppressWarnings({"unchecked"})
  public void testPagination() {

    // now lets pull events from the databaset and list them
    session = sessionFactory.openSession();
    session.beginTransaction();

    String title3 = "Event 3";
    String title5 = "Event 5";
    Event eventToAdd3 = new Event(title3, new Date());
    Event eventToAdd4 = new Event("Event 4", new Date());
    Event eventToAdd5 = new Event(title5, new Date());
    Event eventToAdd6 = new Event("Event 6", new Date());

    session.save(eventToAdd3);
    session.save(eventToAdd4);
    session.save(eventToAdd5);
    session.save(eventToAdd6);

    Query<String> query = session.createQuery("select title from Event");
    query.setFirstResult(2);
    query.setMaxResults(3);

    List<String> resultList = query.getResultList();

    session.getTransaction().commit();
    session.close();

    assertEquals(title3, resultList.get(0));
    assertEquals(title5, resultList.get(2));
  }

  @SuppressWarnings({"unchecked"})
  public void testNamedQuery() {

    // now lets pull events from the databaset and list them
    session = sessionFactory.openSession();
    session.beginTransaction();

    long id = 1;

    Query<Event> query = session.createNamedQuery("Event.byId");
    query.setParameter("id", id);

    List<Event> resultList = (List<Event>) query.getResultList();

    session.getTransaction().commit();
    session.close();

    assertEquals(title, resultList.get(0).getTitle());
  }

  @SuppressWarnings({"unchecked"})
  public void testNamedNativeQuery() {

    // now lets pull events from the databaset and list them
    session = sessionFactory.openSession();
    session.beginTransaction();

    String titleParameter = title;

    Query<Event> query = session.createNamedQuery("Event.byTitle");
    query.setParameter("title", titleParameter);

    List<Event> resultList = (List<Event>) query.getResultList();

    session.getTransaction().commit();
    session.close();

    assertEquals(title, resultList.get(0).getTitle());
  }
}
