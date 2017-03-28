import org.sql2o.*;
import java.util.List;

public class Doctor {
  private String name;
  private int specialty_id;
  private int id;

  public Doctor(String name, int specialty_id) {
    this.name = name;
    this.specialty_id = specialty_id;
  }

  public String getName(){
    return name;
  }

  public int getSpecialty_id(){
    return specialty_id;
  }

  public int getId(){
    return id;
  }

  public static List<Doctor> all(){
    try(Connection con= DB.sql2o.open()){
      String sql = "SELECT * FROM doctors";
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }

  public void save(){
    try(Connection con= DB.sql2o.open()){
      String sql = "INSERT INTO doctors (name, specialty_id)  VALUES (:name, :specialty_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("specialty_id", this.specialty_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Doctor find(int id){
    try(Connection con= DB.sql2o.open()){
      String sql = "SELECT * FROM doctors WHERE id=:id";
      Doctor newDoctor = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Doctor.class);
        return newDoctor;
    }
  }

  @Override
  public boolean equals(Object otherDoctor){
  if (!(otherDoctor instanceof Doctor)) {
    return false;
  } else {
    Doctor newDoctor = (Doctor) otherDoctor;
    return this.getName().equals(newDoctor.getName()) && this.getId()==newDoctor.getId();
    }
  }






}
