package telran.college.entities;
import javax.persistence.*;
@Entity
@Table(name="marks")
public class MarkEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
long id;
	int mark;
	@ManyToOne
	StudentEntity student;
	@ManyToOne
	SubjectEntity subject;
	public MarkEntity() {
	}
	public MarkEntity(int mark, StudentEntity student, SubjectEntity subject) {
		this.mark = mark;
		this.student = student;
		this.subject = subject;
	}
	public long getId() {
		return id;
	}
	public int getMark() {
		return mark;
	}
	public StudentEntity getStudent() {
		return student;
	}
	public SubjectEntity getSubject() {
		return subject;
	}
	
}
