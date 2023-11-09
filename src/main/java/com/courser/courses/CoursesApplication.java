package com.courser.courses;

import com.courser.courses.models.*;
import com.courser.courses.models.enums.CourseShift;
import com.courser.courses.models.enums.Role;
import com.courser.courses.models.subclass.Admin;
import com.courser.courses.models.subclass.Student;
import com.courser.courses.models.subclass.Teacher;
import com.courser.courses.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class CoursesApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(StudentRepository studentRepository, AdminRepository adminRepository, CourseRepository courseRepository, StudentCourseRepository studentCourseRepository, TeacherRepository teacherRepository, TeacherCourseRepository teacherCourseRepository) {
		return (args) -> {
			Admin admin = new Admin("admin admin", "admin@mindhub.com", passwordEncoder.encode("admin123"), Role.ADMIN, true, "IT");
			adminRepository.save(admin);

			Student student1 = new Student("Melba Morel", "melba@mindhub.com", passwordEncoder.encode("MelBA98!"), Role.STUDENT ,true, "I am a Computer Science major");
			Student student2 = new Student("Alex Fulsch", "alexfu@mindhub.com", passwordEncoder.encode("a"), Role.STUDENT ,true, "I love tech!");

			studentRepository.save(student1);
			studentRepository.save(student2);

			Teacher teacher1 = new Teacher("Mary Jones", "mary@gmail.com", passwordEncoder.encode("mary123"), Role.TEACHER ,true, "Computer Science");
			Teacher teacher2 = new Teacher("James White", "ames@gmail.com", passwordEncoder.encode("James3902"), Role.TEACHER ,true,  "Web Development");

			teacherRepository.save(teacher1);
			teacherRepository.save(teacher2);

			Course course1 = new Course("React", "20 weeks", "React JS course", "Mondays and Fridays from 19 to 21", LocalDate.now(), LocalDate.now().plusWeeks(20), 30, 20, CourseShift.NIGHT, "IT", true);
			Course course2 = new Course("Vue", "15 weeks", "Vue course", "Tuesdays and Thursdays from 10 to 12", LocalDate.now(), LocalDate.now().plusWeeks(15), 40, 28, CourseShift.MORNING, "IT", true);

			courseRepository.save(course1);
			courseRepository.save(course2);

			StudentCourse studentCourse1 = new StudentCourse(true);
			StudentCourse studentCourse2 = new StudentCourse(false);

			student1.addStudentCourse(studentCourse1);
			student2.addStudentCourse(studentCourse2);

			course1.addStudentCourse(studentCourse1);
			course2.addStudentCourse(studentCourse2);

			studentCourseRepository.save(studentCourse1);
			studentCourseRepository.save(studentCourse2);

			TeacherCourse teacherCourse1 = new TeacherCourse(true);
			TeacherCourse teacherCourse2 = new TeacherCourse(false);

			teacher1.addTeacherCourse(teacherCourse1);
			teacher2.addTeacherCourse(teacherCourse2);

			course1.addTeacherCourse(teacherCourse1);
			course2.addTeacherCourse(teacherCourse2);

			teacherCourseRepository.save(teacherCourse1);
			teacherCourseRepository.save(teacherCourse2);

		};
	}
}
