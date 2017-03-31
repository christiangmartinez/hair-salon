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

}
