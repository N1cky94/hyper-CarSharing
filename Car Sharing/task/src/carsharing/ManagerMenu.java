package carsharing;

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
            System.out.println("Company list:");
            companies.forEach(System.out::println);

        } catch (SqlRuntimeException sre) {
            System.out.println("The company list is empty!");
        }
    }

    private static void createCompany() {

    }
}
