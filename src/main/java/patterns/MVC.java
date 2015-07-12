package patterns;

public class MVC {

	class Student {
		private String name;

		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}	
	
	class StudentView {
		public void printStudentDetails(String studentName){
		    System.out.println("Student name: " + studentName);
		}
	}
	
	class StudentController {
		private Student model;
		private StudentView view;

		public StudentController(Student model, StudentView view){
			this.model = model;
		    this.view = view;
		}

		public void setStudentName(String name){
			model.setName(name);		
		}

		public String getStudentName(){
			return model.getName();		
		}

		public void updateView(){				
			view.printStudentDetails(model.getName());
		}	
	}
	
	public static void main(String[] args) {
		MVC mvc = new MVC();
		Student model = retrieveStudentFromDatabase(mvc);
		StudentView view = mvc.new StudentView();
		StudentController controller = mvc.new StudentController(model, view);
		controller.updateView();
		controller.setStudentName("John");
		controller.updateView();
	}
	
	private static Student retrieveStudentFromDatabase(MVC mvc) {
		Student student = mvc.new Student();
	    student.setName("Robert");
	    return student;
	}

}
