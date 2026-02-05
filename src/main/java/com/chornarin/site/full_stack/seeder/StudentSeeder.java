// package com.chornarin.site.full_stack.seeder;

// import java.util.List;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.chornarin.site.full_stack.models.Students;
// import com.chornarin.site.full_stack.repository.StudentRepository;


// @Component
// public class StudentSeeder implements CommandLineRunner {
//     private final StudentRepository studentRepository;

//     public StudentSeeder(StudentRepository studentRepository){
//         this.studentRepository = studentRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         // seed data 
//         Students students1  = Students
//             .builder()
//             .firstName("Raksa")
//             .lastName("chichi")
//             .email("chicdddshiraska@gmail.com")
//             .build();

//         Students students2  = Students
//             .builder()
//             .firstName("Narin")
//             .lastName("chi")
//             .email("chichira@gmail.com")
//             .build();

//         Students students3  = Students
//             .builder()
//             .firstName("Narinchir")
//             .lastName("chidsf")
//             .email("chichidsdfra@gmail.com")
//             .build();

//         studentRepository.saveAll(List.of(students1, students2, students3));
        
//     }

// }
