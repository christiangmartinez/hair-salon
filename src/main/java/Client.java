import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
  private int id;
  private String name;
  private String phoneNumber;
  private String address;

  public Client(String name, String phoneNumber, String address) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName())
        && this.getPhoneNumber().equals(newClient.getPhoneNumber())
        && this.getAddress().equals(newClient.getAddress());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, phoneNumber, address) VALUES (:name, :phoneNumber, :address)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("phoneNumber", this.phoneNumber)
      .addParameter("address", this.address)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, phoneNumber, address FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id = :id";
      Client client = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String name, String phoneNumber, String address) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, phoneNumber = :phoneNumber, address = :address WHERE id = :id";
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("phoneNumber", phoneNumber)
      .addParameter("address", address)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id;";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }

}
