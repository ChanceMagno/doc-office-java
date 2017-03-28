import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class DoctorTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctor_office_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql1 = "DELETE FROM doctors *;";
      String sql2 = "DELETE FROM patients *;";
      con.createQuery(sql1).executeUpdate();
      con.createQuery(sql2).executeUpdate();
    }
  }

  @Test
  public void doctor_instantiatesCorrectly_true() {
    Doctor test = new Doctor("john", 1);
    assertEquals(true, test instanceof Doctor);
  }
  @Test
  public void getName_john() {
    Doctor test = new Doctor("john", 1);
    assertEquals("john", test.getName());
  }
  @Test
  public void getSpecialty_id_1() {
    Doctor test = new Doctor("john", 1);
    assertEquals(1, test.getSpecialty_id());
  }
  @Test
  public void getAllDoctors_true() {
    Doctor test1 = new Doctor("john", 1);
    Doctor test2 = new Doctor("sam", 1);
    test1.save();
    test2.save();
    assertTrue(test1.equals(Doctor.all().get(0)));
    assertTrue(test2.equals(Doctor.all().get(1)));
  }
  @Test
  public void getDoctorDBgeneratedID_1 (){
    Doctor test1 = new Doctor("john", 1);
    test1.save();
    assertTrue(Doctor.all().get(0).getId()>0);
  }
  @Test
  public void findDoctor_test2(){
    Doctor test1 = new Doctor("john", 1);
    Doctor test2 = new Doctor("sam", 1);
    test1.save();
    test2.save();
    assertTrue(Doctor.find(test2.getId()).equals(test2));
  }
}
