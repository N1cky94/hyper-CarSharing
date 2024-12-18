package carsharing.menus;

import carsharing.services.CompanyService;
import carsharing.db.SqlRuntimeException;
import carsharing.models.Company;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class ManagerMenu {
    private static CompanyService service;
    public static void show() {
        service = CompanyService.getInstance();
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println();
            System.out.println("""
                    1. Company list
                    2. Create a company
                    0. Back""");
            int choice = KeyboardUtil.readInt();

            switch (choice) {
                case 1 -> listCompany();
                case 2 -> createCompany();
                case 0 -> isLoggedIn = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void listCompany() {
        System.out.println();
        try {

            List<Company> companies = service.getCompanies();

            if (companies.isEmpty()) {
                System.out.println("The company list is empty!");
                return;
            }

            System.out.println("Choose a company:");
            companies.forEach(System.out::println);
            System.out.println("0. Back");

            int choice = KeyboardUtil.readInt();
            if (choice == 0) {
                return;
            }

            Company company = service.getCompany(choice);
            System.out.println(company.getName());
            // todo: some logic for the new menu

        } catch (SqlRuntimeException sre) {
            System.out.println("The company list is empty!");
        }
    }

    private static void createCompany() {
        System.out.println();
        System.out.println("Enter the company name:");
        String name = KeyboardUtil.readLine();

        service.createCompany(name);
        System.out.println("The company was created!");
    }
}
