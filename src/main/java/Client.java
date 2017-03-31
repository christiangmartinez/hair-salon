import org.sql2o.*;

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

  // public int getId() {
  //   return id;
  // }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

}
