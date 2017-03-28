import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctor_office_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql1 = "DELETE FROM patients *;";
      String sql2 = "DELETE FROM doctors *;";
      con.createQuery(sql1).executeUpdate();
      con.createQuery(sql2).executeUpdate();
    }
  }

  @Test
  public void true_patientClassInstantiates_true() {
    Patient newPatient = new Patient("jimmy", 1);
    assertEquals(true, newPatient instanceof Patient);
  }

  @Test
  public void getobjects_true() {
    Patient newPatient = new Patient("jimmy", 1);
    assertEquals("jimmy", newPatient.getName());
    assertEquals(1, newPatient.getDoctor_id());
  }
}
