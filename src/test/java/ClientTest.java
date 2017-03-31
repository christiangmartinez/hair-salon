import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getters_returnClientVariables_clientInfo() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    assertEquals("Chulo", testClient.getName());
    assertEquals("555-555-5555", testClient.getPhoneNumber());
    assertEquals("123 Chulo lane", testClient.getAddress());
  }

  @Test
  public void equals_returnsTrueifValuesSame() {
    Client firstClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    Client secondClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesClientToDatabase_true() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToClient() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    testClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(testClient.getId(), savedClient.getId());
  }

  @Test
  public void save_savesStylistIdIntoDatabase_true() {
    Stylist testStylist = new Stylist("Gertrude", "222-222-2222");
    testStylist.save();
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    firstClient.save();
    Client secondClient = new Client("Valentina", "999-999-9999", "123 Chulo lane", 1);
    secondClient.save();
    assertTrue(Client.all().get(0).equals(firstClient));
    assertTrue(Client.all().get(1).equals(secondClient));
  }

  @Test
  public void find_returnsClientsWithSameId_secondClient() {
    Client firstClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    firstClient.save();
    Client secondClient = new Client("Valentina", "999-999-9999", "123 Chulo lane", 1);
    secondClient.save();
    assertEquals(secondClient, Client.find(secondClient.getId()));
  }

  @Test
  public void update_updatesClientInfo() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    testClient.save();
    testClient.update("Chulito", "555-555-5555", "123 Chulo lane");
    assertEquals("Chulito", Client.find(testClient.getId()).getName());
  }

  @Test
  public void delete_deletesClient() {
    Client testClient = new Client("Chulo", "555-555-5555", "123 Chulo lane", 1);
    testClient.save();
    int testClientId = testClient.getId();
    testClient.delete();
    assertEquals(null, Client.find(testClientId));
  }

}
