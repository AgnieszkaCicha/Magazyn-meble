package pl.agnieszkacicha.magazyn.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.agnieszkacicha.magazyn.database.ProductRepository;
import pl.agnieszkacicha.magazyn.model.AGD;
import pl.agnieszkacicha.magazyn.model.Furniture;
import pl.agnieszkacicha.magazyn.model.Product;
import pl.agnieszkacicha.magazyn.model.User;

import java.util.List;
import java.util.Scanner;

@Component
public class GUI {

    @Autowired
    private IUserRepository userRepository;

    public void login() {
        userRepository.getUserList();
    }

    public void register() {
        userRepository.register("admin", "admin");
    }

    private Scanner scanner = new Scanner(System.in);


        public void showLoginMenu() {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            switch (scanner.nextLine()) {
                case "1":
                    if (showLoginScreen()) {
                        showMainMenu();
                    } else {
                        showLoginMenu();
                    }
                    break;
                case "2":
                    showRegisterScreen();
                    showLoginMenu();
                    break;
                case "3":
                    System.exit(0);
                case "9":
                    showUserList();
                    showLoginMenu();
                default:
                    System.out.println("Nieprawidłowy wybór !!");
                    showLoginMenu();
                    break;
            }
        }

            public void showMainMenu () {
                //metoda statyczna żeby nie trzeba było tworzyć obiektu gdy chcemy sie odwołać do metody
                System.out.println("1. Dodaj produkt");
                System.out.println("2. Wydaj produkt");
                System.out.println("3. Wyświetl produkty");
                System.out.println("4. Wyjście");

                switch (scanner.nextLine()) {
                    case "1":
                        addProduct();
                        showMainMenu();
                        break;
                    case "2":
                        deliverProducts();
                        showMainMenu();
                        break;
                    case "3":
                        showAllProducts();
                        System.out.println("");
                        showMainMenu();
                        break;
                    case "4":
                        System.exit(0); //podajemy kod błędu, kod błędu=0 oznacza brak błędu
                    default:
                        System.out.println("Nieprawidłowy wybór !!");
                        showMainMenu();
                        break;
                }
            }

            private boolean showLoginScreen () {
                System.out.println("Podaj login:");
                String login = scanner.nextLine();
                System.out.println("Podaj haslo:");
                String pass = scanner.nextLine();
                boolean authenticationResult = userRepository.authenticate(login, pass);
                userRepository.authenticate(login,pass);
                if (authenticationResult) {
                    System.out.println("Zalogowano !!");
                } else {
                    System.out.println("Bledne dane !!");
                }
                return authenticationResult;
            }

            private void showRegisterScreen () {
                System.out.println("Podaj login:");
                String login = scanner.nextLine();
                System.out.println("Podaj haslo:");
                String pass = scanner.nextLine();

                boolean registerResult = userRepository.register(login, pass);

                if (registerResult) {
                    System.out.println("Rejestracja udana !!");
                } else {
                    System.out.println("Login zajety !!");
                }
            }

            private void showUserList () {
                List<User> userList = userRepository.getUserList();
                for (User user : userList) {
                    System.out.println(user);
                }
            }

            //wyświetlanie produktów
            private void showAllProducts () {
                List<Product> products = ProductRepository.getInstance().getAllProducts();
                for (Product currentProduct : products) {
                    System.out.println(currentProduct);
                }
            }

            //wydanie produktów
            private void deliverProducts () {
                System.out.print("Podaj kod produktu: ");
                int productToDeliver = Integer.parseInt(scanner.nextLine());
                System.out.print("Podaj ilość sztuk: ");
                int piecesToDeliver = Integer.parseInt(scanner.nextLine());
                boolean success =
                        ProductRepository.getInstance().deliverProduct(productToDeliver, piecesToDeliver);
                if (success) {
                    System.out.println("Wydano produkt !!");
                } else {
                    System.out.println("Nie udało się wydać produktu !!");
                }
            }

            //dodawanie produktu
            private void addProduct () {
                System.out.println("Wprowadź dane produktu.");
                System.out.print("Kod produktu: ");
                int code = Integer.parseInt(scanner.nextLine());
                Product productFromDatabase = ProductRepository.getInstance().findProduct(code);
                if (productFromDatabase != null) {
                    try {
                        System.out.print("Podaj ilość: ");
                        int productPieces = Integer.parseInt(scanner.nextLine());
                        productFromDatabase.setPieces(productPieces + productFromDatabase.getPieces());
                        System.out.println("Dodano produkt !!");
                    } catch (NumberFormatException e) {
                        System.out.println("Nieprawidłowa liczba !!");
                        addProduct();
                    }
                } else {
                    addNewProduct(code);
                }
            }

            private void addNewProduct ( int code){
                System.out.println("1. Mebel");
                System.out.println("2. AGD");
                DataWrapper dataWrapper;
                switch (scanner.nextLine()) {
                    case "1":
                        dataWrapper = readCommonData();
                        Furniture furniture =
                                new Furniture(code, dataWrapper.name,
                                        dataWrapper.pieces, dataWrapper.price);
                        ProductRepository.getInstance().addProductToDatabase(furniture);
                        System.out.println("Produkt dodany !!");
                        break;
                    case "2":
                        dataWrapper = readCommonData();
                        System.out.print("Podaj moc[kW]: ");
                        double power = Double.parseDouble(scanner.nextLine());
                        AGD agd = new AGD(code, dataWrapper.name, dataWrapper.pieces, dataWrapper.price, power);
                        ProductRepository.getInstance().addProductToDatabase(agd);
                        System.out.println("Produkt dodany !!");
                        break;
                    default:
                        System.out.println("Nieprawidłowy wybór !!");
                        addNewProduct(code);
                        break;
                }
            }

            private DataWrapper readCommonData () {
                try {
                    System.out.print("Nazwa produktu: ");
                    String name = scanner.nextLine();
                    System.out.print("Ilość: ");
                    int pieces = Integer.parseInt(scanner.nextLine());
                    System.out.print("Cena produktu[zł]: ");
                    Double price = Double.valueOf((scanner.nextLine()));

                    return new DataWrapper(name, pieces, price);
                } catch (NumberFormatException e) {
                    System.out.println("Niepoprawna liczba !!");
                    return readCommonData();
                }
            }

            static class DataWrapper {
                String name;
                int pieces;
                Double price;

                public DataWrapper(String name, int pieces, Double price) {
                    this.name = name;
                    this.pieces = pieces;
                    this.price = price;
                }
            }

    }