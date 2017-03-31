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

}
