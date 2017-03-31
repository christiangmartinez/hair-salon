import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Stylist {
  private int id;
  private String name;
  private String phoneNumber;

  public Stylist(String name, String phoneNumber) {
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName())
        && this.getPhoneNumber().equals(newStylist.getPhoneNumber())
        && this.getId() == newStylist.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, phoneNumber) VALUES (:name, :phoneNumber)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("phoneNumber", phoneNumber)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name, phoneNumber FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id = :id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClient() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylistId = :id";
      return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Client.class);
    }
  }

  public void update(String name, String phoneNumber) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name, phoneNumber = :phoneNumber WHERE id = :id";
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("phoneNumber", phoneNumber)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
