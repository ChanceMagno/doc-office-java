import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("doctors", Doctor.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("doctors", Doctor.all());
      model.put("template", "templates/doctors.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/doctors", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      int specialty = Integer.parseInt(request.queryParams("specialty"));
      Doctor newDoctor = new Doctor (name, specialty);
      newDoctor.save();
      String url = String.format("/doctors");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/patients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("doctors", Doctor.class);
      model.put("patients", Patient.all());
      model.put("template", "templates/patients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/patients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      int yourDoctorID = Integer.parseInt(request.queryParams("doctorId"));
      // Doctor yourDoctor = Doctor.find(yourDoctorID);
      // model.put("doctor", yourDoctor);
      Patient newPatient = new Patient (name);
      newPatient.setDoctorId(yourDoctorID);
      newPatient.save();
      String url = String.format("/patients");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
