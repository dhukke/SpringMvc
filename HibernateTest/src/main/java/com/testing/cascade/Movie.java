package com.testing.cascade;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id"))
  private Collection<Genre> genreList = new ArrayList<Genre>();

  public int getId() {
    return id;
  }

  public void setId(int movieId) {
    this.id = movieId;
  }

  public String getName() {
    return name;
  }

  public void setName(String movieName) {
    this.name = movieName;
  }

  public Collection<Genre> getGenreList() {
    return genreList;
  }

  public void setGenreList(Collection<Genre> genreList) {
    this.genreList = genreList;
  }

  @Override
  public String toString() {
    return "Movie [movieId=" + id + ", movieName=" + name + ", genreList=" + genreList + "]";
  }

}
