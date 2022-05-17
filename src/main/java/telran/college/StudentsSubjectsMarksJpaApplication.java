package telran.college;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.college.dto.Mark;
import telran.college.dto.Student;
import telran.college.dto.Subject;
import telran.college.service.CollegeService;

@SpringBootApplication
public class StudentsSubjectsMarksJpaApplication {
	//TODO configuration values
	@Value("${app.amount.students: 50}")
	int nStudents;
	@Value("${app.amount.subjects: 10}")
	int nSubjects;
	@Value("${app.amount.marks: 40}")
	int nMarks;
	@Value("${app.offset.student.id: 1000}")
	int offset_student_id;
	@Value("${app.offset.subject.id: 5000}")
	int offset_subject_id;
	/**********************/
	@Autowired
	CollegeService collegeService;
	static Logger LOG = LoggerFactory.getLogger(StudentsSubjectsMarksJpaApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =
				SpringApplication.run(StudentsSubjectsMarksJpaApplication.class, args);
		
	}
	@PostConstruct
	void createDb() {
		LOG.debug("nStudents={} offset_student_id={} ", nStudents, offset_student_id);
		LOG.debug("nSubjects={} offset_subject_id={} ", nSubjects, offset_subject_id, nMarks);
		LOG.debug("nMarks={} ", nMarks);
		createStudents();
		createSubjects();
		createMarks();
		
	}
	private void createMarks() {
		for (int index = 1; index <= nMarks; index++) {
			int studenId = getRandomValue(1, nStudents, offset_student_id);
			int subjectId = getRandomValue(1, nSubjects, offset_subject_id);
			int markValue = getRandomValue(1, 100, 0);
			LOG.debug("studenId={} subjectId={} markValue={}", studenId, subjectId, markValue);
			collegeService.addMark(new Mark(studenId, subjectId, markValue));
		}		
	}
	private void createSubjects() {
		for (long index = 1; index <= nSubjects; index++) {
			Long subjectId = index+offset_subject_id;
			String name = "subject_" + subjectId.toString();
			LOG.debug("id={} name={}", subjectId, name);
			collegeService.addSubject(new Subject(subjectId, name));
		}
		
	}
	void createStudents() {
		for(long index=1; index<=nStudents; index++) {
			Long studentId = index+offset_student_id;
			String name = "student_" + studentId.toString();
			LOG.debug("id={} name={}", studentId, name);
			collegeService.addStudent(new Student(studentId, name));
		}
	}
	
	int getRandomValue(int min, int max, int offset){
		   int range = (max - min) + 1;     
		   return (int)(Math.random() * range) + min + offset;
	}
	

}
