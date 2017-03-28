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
    Patient newPatient = new Patient("jimmy");
    assertEquals(true, newPatient instanceof Patient);
  }

  @Test
  public void getobjects_true() {
    Patient newPatient = new Patient("jimmy");
    assertEquals("jimmy", newPatient.getName());
    assertEquals(0, newPatient.getDoctor_id());
  }

  @Test
  public void getAllPatients_true() {
    Patient patient1 = new Patient("Jimmy");
    Patient patient2 = new Patient("timmy");
    patient1.save();
    patient2.save();
    assertTrue(patient1.equals(Patient.all().get(0)));
    assertTrue(patient2.equals(Patient.all().get(1)));
  }
  @Test
  public void getPatientId_true() {
    Patient patient1 = new Patient("Jimmy");
    patient1.save();
    assertTrue(Patient.all().get(0).getId() > 0);
  }
  @Test
  public void findPatient_true() {
    Patient patient1 = new Patient("Jimmy");
    Patient patient2 = new Patient("timmy");
    patient1.save();
    patient2.save();
    assertTrue(Patient.find(patient2.getId()).equals(patient2));
  }

}
