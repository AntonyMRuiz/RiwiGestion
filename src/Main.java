import controller.CoderController;
import controller.CompanyController;
import controller.ContractController;
import controller.VacancyController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int optionManager = 0, optionCRUD = 0, optionSearch = 0;

        do {
            optionManager = Integer.parseInt(JOptionPane.showInputDialog("""
            1. Coder Manager
            2. Company Manager
            3. Vacancy Manager
            4. Contract Manager
            5. Exit
            
            Enter your option:
            """));

            switch (optionManager) {
                case 1:
                    do {
                        optionCRUD = Integer.parseInt(JOptionPane.showInputDialog("""
                        1. Create Coder
                        2. Search Coder
                        3. Delete Coder
                        4. Update Coder
                        5. Exit
                        
                        Enter your option:
                        """));

                        switch (optionCRUD) {
                            case 1:
                                CoderController.create();
                                break;

                            case 2:

                                do {
                                    optionSearch = Integer.parseInt(JOptionPane.showInputDialog("""
                                    1. Show All Coder
                                    2. Filter Coder
                                    3. Exit
                                    
                                    Enter your option:
                                    """));

                                    switch (optionSearch) {
                                        case 1:
                                            CoderController.showAll();
                                            break;

                                        case 2:
                                            CoderController.filter();
                                            break;
                                    }
                                } while (optionSearch != 3);
                                break;

                            case 3:
                                CoderController.delete();
                                break;

                            case 4:
                                CoderController.update();
                                break;
                        }
                    } while (optionCRUD != 5);
                    break;
                case 2:
                    do {
                        optionCRUD = Integer.parseInt(JOptionPane.showInputDialog("""
                        1. Create Company
                        2. Search Company
                        3. Delete Company
                        4. Update Company
                        5. Exit
                        
                        Enter your option:
                        """));

                        switch (optionCRUD) {
                            case 1:
                                CompanyController.create();
                                break;

                            case 2:

                                do {
                                    optionSearch = Integer.parseInt(JOptionPane.showInputDialog("""
                                    1. Show All Company
                                    2. Filter Company
                                    3. Exit
                                    
                                    Enter your option:
                                    """));

                                    switch (optionSearch) {
                                        case 1:
                                            CompanyController.showAll();
                                            break;

                                        case 2:
                                            CompanyController.filter();
                                            break;
                                    }
                                } while (optionSearch != 3);
                                break;

                            case 3:
                                CompanyController.delete();
                                break;

                            case 4:
                                CompanyController.update();
                                break;
                        }
                    } while (optionCRUD != 5);
                    break;
                case 3:
                    do {
                        optionCRUD = Integer.parseInt(JOptionPane.showInputDialog("""
                        1. Create Vacancy
                        2. Search Vacancy
                        3. Delete Vacancy
                        4. Update Vacancy
                        5. Exit
                        
                        Enter your option:
                        """));

                        switch (optionCRUD) {
                            case 1:
                                VacancyController.create();
                                break;

                            case 2:

                                do {
                                    optionSearch = Integer.parseInt(JOptionPane.showInputDialog("""
                                    1. Show All Vacancy
                                    2. Filter Vacancy
                                    3. Exit
                                    
                                    Enter your option:
                                    """));

                                    switch (optionSearch) {
                                        case 1:
                                            VacancyController.showAll();
                                            break;

                                        case 2:
                                            VacancyController.filter();
                                            break;
                                    }
                                } while (optionSearch != 3);
                                break;

                            case 3:
                                VacancyController.delete();
                                break;

                            case 4:
                                VacancyController.update();
                                break;
                        }
                    } while (optionCRUD != 5);
                    break;
                case 4:
                    do {
                        optionCRUD = Integer.parseInt(JOptionPane.showInputDialog("""
                        1. Create Contract
                        2. Search Contract
                        3. Delete Contract
                        4. Update Contract
                        5. Exit
                        
                        Enter your option:
                        """));

                        switch (optionCRUD) {
                            case 1:
                                ContractController.create();
                                break;

                            case 2:

                                do {
                                    optionSearch = Integer.parseInt(JOptionPane.showInputDialog("""
                                    1. Show All Contract
                                    2. Filter Contract
                                    3. Exit
                                    
                                    Enter your option:
                                    """));

                                    switch (optionSearch) {
                                        case 1:
                                            ContractController.showAll();
                                            break;

                                        case 2:
                                            ContractController.filter();
                                            break;
                                    }
                                } while (optionSearch != 3);
                                break;

                            case 3:
                                ContractController.delete();
                                break;

                            case 4:
                                ContractController.update();
                                break;
                        }
                    } while (optionCRUD != 5);
                    break;
            }
        } while (optionManager != 5);

        JOptionPane.showMessageDialog(null, "Thank you for using our application!");
    }

}