package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      Car car1 = new Car("BMW 520", 10);
      Car car2 = new Car("Lada", 2107);

      userService.add(new User("Dima", "Dmitriev", "dmitriev@mail.ru", car1));
      userService.add(new User("Pavel", "Pavlov", "pavlov@mail.ru", car2));

      // получаем пользователей
      userService.listUsers().forEach(System.out::println);

      User user = userService.getUserByCar("Lada", 2107);

      System.out.println("---------------------------------------------------------------------");
      System.out.println("\u001B[32m" + user + "\u001B[0m");

      context.close();
   }
}
