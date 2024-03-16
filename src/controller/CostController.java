package controller;
import java.util.List;
import model.Book;
import model.User;


public class CostController {

    public static double calculateTotalCost(List<Book> books, List<User> staff) {
        double totalCost = 0.0;

        // Calculate cost of books
        for (Book book : books) {
            totalCost += book.getOrgPrice() * book.getQuantity();
        }

        // Calculate total employee salaries
        for (User user : staff) {
            totalCost += user.getEmployeeSalary();
        }

        return totalCost;
    }
}

