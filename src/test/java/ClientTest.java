import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane");
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getters_returnClientVariables_clientInfo() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane");
    assertEquals("Chulo", testClient.getName());
    assertEquals("555-555-5555", testClient.getPhoneNumber());
    assertEquals("123 Chulo lane", testClient.getAddress());
  }

  @Test
  public void equals_returnsTrueifValuesSame() {
    Client firstClient = new Client("Chulo", "555-555-5555", "123 Chulo lane");
    Client secondClient = new Client("Chulo", "555-555-5555", "123 Chulo lane");
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesClientToDatabase_true() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane");
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToClient() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane");
    testClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(testClient.getId(), savedClient.getId());
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Chulo", "555-555-5555", "123 Chulo lane");
    firstClient.save();
    Client secondClient = new Client("Valentina", "999-999-9999", "123 Chulo lane");
    secondClient.save();
    assertTrue(Client.all().get(0).equals(firstClient));
    assertTrue(Client.all().get(1).equals(secondClient));
  }

}
