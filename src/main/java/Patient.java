import org.sql2o.*;
import java.util.List;

public class Patient {
  private String name;
  private int doctor_id;
  private int id;

  public Patient(String name) {
    this.name = name;
    this.doctor_id = 0;
  }

  public String getName(){
    return name;
  }

  public int getDoctor_id() {
    return doctor_id;
  }

  public int getId(){
    return id;
  }

  public static List<Patient> all(){
    try(Connection con= DB.sql2o.open()){
      String sql = "SELECT * FROM patients";
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  public void save(){
    try(Connection con= DB.sql2o.open()){
      String sql = "INSERT INTO patients (name, doctor_id) VALUES (:name, :doctor_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("doctor_id", this.doctor_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Patient find(int id){
    try(Connection con= DB.sql2o.open()){
      String sql = "SELECT * FROM patients WHERE id=:id";
      Patient newPatient = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Patient.class);
      return newPatient;
    }
  }

  @Override
  public boolean equals(Object otherPatient){
  if (!(otherPatient instanceof Patient)) {
    return false;
  } else {
    Patient newPatient = (Patient) otherPatient;
    return this.getName().equals(newPatient.getName()) && this.getId() == newPatient.getId();
    }
  }
}
