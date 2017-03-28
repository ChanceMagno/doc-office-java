import org.sql2o.*;

public class Patient {
  private String name;
  private int doctor_id;
  private int id;

  public Patient(String name, int doctor_id) {
    this.name = name;
    this.doctor_id = doctor_id;
  }

  public String getName(){
    return name;
  }

  public int getDoctor_id() {
    return doctor_id;
  }

}
