import java.util.Scanner;  //подключаем внутреннюю библиотеку Scanner

public class Main   //Объявляем основной класс
{
    private static int minIncome = 200000;  //переменная, которая может быть использована только в данном классе, мин доход
    private static int maxIncome = 900000;  //переменная, которая может быть использована только в данном классе, макс доход

    private static int officeRentCharge = 140000; //переменная, которая может быть использована только в данном классе,аренда офиса
    private static int telephonyCharge = 12000; //переменная, которая может быть использована только в данном классе, абонетская плата за тел
    private static int internetAccessCharge = 7200; //переменная, которая может быть использована только в данном классе, интернет

    private static int assistantSalary = 45000; //переменная, которая может быть использована только в данном классе, зп ассистента
    private static int financeManagerSalary = 90000;//переменная, которая может быть использована только в данном классе, зп fm

    private static double mainTaxPercent = 0.24; //переменная, которая может быть использована только в данном классе, коэф основного налога
    private static double managerPercent = 0.15; //переменная, которая может быть использована только в данном классе, коэф для зп менеджера

    private static double minInvestmentsAmount = 100000; //переменная, которая может быть использована только в данном классе, мин инвестиции

    public static void main(String[] args) //основной метод
    {
        while(true) // цикл, пока истина, исполняется код в цикле. так как внутри не переменная то исполняется всегда
        {
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");  //вывод сообщения
            int income = (new Scanner(System.in)).nextInt(); //переменная, экземпляр класса Scanner,  считывает ввод в консоли

            if(!checkIncomeRange(income)) {   //если метод checkIncomeRange возвращает истину, возвращаемся на первый шаг цикла(вывод текста),
                                              // иначе продолжается выполнение кода ниже
                continue;
            }

            double managerSalary = income * managerPercent; //переменная вычисляет зп менеджера
            double pureIncome = income - managerSalary -
                calculateFixedCharges();                   //переменная вычисляет чистый доход: доход-зп менеджера-результат метода  calculateFixedCharges
            double taxAmount = mainTaxPercent * pureIncome; //переменная вычисляет налог
            double pureIncomeAfterTax = pureIncome - taxAmount; // переменная вычисляет чистый доход за вычетом налога

            boolean canMakeInvestments = pureIncomeAfterTax >=   //переменная проверяет остаются ли средства для инвестций
                minInvestmentsAmount;

            System.out.println("Зарплата менеджера: " + managerSalary);  //вывод зп менеджера
            System.out.println("Общая сумма налогов: " +
                (taxAmount > 0 ? taxAmount : 0));   //вывод общ суммы налога: проверка если налоги больше 0, то выводим налог, иначе 0
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет")); // если переменная canMakeInvestments возвращает true, то выводим да, иначе нет
            if(pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");  // если переменная pureIncome меньше 0 то выводим сообщение
            }
        }
    }

    private static boolean checkIncomeRange(int income)  // метод возвращает булево значение,
    // проверяет если введенное значение дохода меньше минимального то ложь, если больше максимального, то ложь, иначе истина
    {
        if(income < minIncome)
        {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if(income > maxIncome)
        {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges()  // метод возвращает сумму обязателных платежей
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
