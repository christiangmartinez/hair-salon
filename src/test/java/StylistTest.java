import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Gertrude", "222-222-2222");
    assertTrue(testStylist instanceof Stylist);
  }

  @Test
  public void getters_returnStylistVariables_stylistInfo() {
    Stylist testStylist = new Stylist("Gertrude", "222-222-2222");
    assertEquals("Gertrude", testStylist.getName());
    assertEquals("222-222-2222", testStylist.getPhoneNumber());
  }

  @Test
  public void equals_returnsTrueifValuesSame_true() {
    Stylist firstStylist = new Stylist("Gertrude", "222-222-2222");
    Stylist secondStylist = new Stylist("Gertrude", "222-222-2222");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesStylistToDatabase_true() {
    Stylist testStylist = new Stylist("Gertrude", "222-222-2222");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void save_assignsIdToStylist() {
    Stylist testStylist = new Stylist("Gertrude", "222-222-2222");
    testStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(testStylist.getId(), savedStylist.getId());
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Gertrude", "222-222-2222");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Gunther", "777-777-7777");
    secondStylist.save();
    assertEquals(secondStylist, Stylist.find(secondStylist.getId()));
  }


}
