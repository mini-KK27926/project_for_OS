import java.util.Scanner;

// Класс, представляющий банковский счет
class BankAccount {
    private double balance;
    // Конструктор класса BankAccount, устанавливающий начальный баланс
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }
    // Метод для получения текущего баланса
    public double getBalance() {
        return balance;
    }
    // Метод для внесения средств на счет
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Неверная сумма для внесения.");
        }
    }
    // Метод для снятия средств со счета
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Неверная сумма для снятия или недостаточно средств на счете.");
        }
    }
}
// Класс, представляющий банкомат
class ATM {
    private BankAccount account;
    // Конструктор класса ATM, принимающий объект BankAccount
    public ATM(BankAccount account) {
        this.account = account;
    }
    // Метод для отображения меню банкомата
    public void displayMenu() {
        System.out.println("1. Показать баланс");
        System.out.println("2. Внести средства");
        System.out.println("3. Снять средства");
        System.out.println("4. Выйти");
    }
    // Метод для обработки выбранной опции
    public void processOption(int option) {
        Scanner scanner = new Scanner(System.in);

        switch (option) {
            case 1:
                System.out.println("Баланс: " + account.getBalance());
                break;
            case 2:
                System.out.print("Введите сумму для внесения: ");
                if (scanner.hasNextDouble()) {
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Средства внесены успешно.");
                } else {
                    System.out.println("Некорректный ввод. Необходимо ввести число.");
                    scanner.next(); // Очистка буфера ввода
                }
                break;
            case 3:
                System.out.print("Введите сумму для снятия: ");
                if (scanner.hasNextDouble()) {
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                } else {
                    System.out.println("Некорректный ввод. Необходимо ввести число.");
                    scanner.next(); // Очистка буфера ввода
                }
                break;
            case 4:
                System.out.println("Выход из банкомата. До свидания!");
                System.exit(0);
                break;
            default:
                System.out.println("Неверная опция. Попробуйте снова.");
        }
    }
}
// Главный класс программы
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Инициализация банковского счета
        System.out.print("Введите начальный баланс: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Некорректный ввод. Необходимо ввести число.");
            scanner.next(); // Очистка буфера ввода
        }
        double initialBalance = scanner.nextDouble();
        BankAccount bankAccount = new BankAccount(initialBalance);

        // Инициализация банкомата
        ATM atm = new ATM(bankAccount);

        // Бесконечный цикл для взаимодействия с банкоматом
        while (true) {
            // Отображение меню
            atm.displayMenu();
            // Запрос опции у пользователя
            System.out.print("Выберите опцию (1-4): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод. Необходимо ввести число от 1 до 4.");
                scanner.next(); // Очистка буфера ввода
            }
            int option = scanner.nextInt();

            // Обработка выбранной опции
            atm.processOption(option);
        }
    }
}