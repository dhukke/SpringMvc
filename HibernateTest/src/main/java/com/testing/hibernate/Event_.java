package com.testing.hibernate;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Event.class)
public class Event_ {
  public static volatile SingularAttribute<Event, Long> id;
  public static volatile SingularAttribute<Event, String> title;
  public static volatile SingularAttribute<Event, Date> date;

}
