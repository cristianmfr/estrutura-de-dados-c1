// Feito por: Cristian Menezes de Freitas
// Turma: 2HC2
// Repositório: https://github.com/cristianmfr/estrutura-de-dados-c1

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class CustomerType {
    private String name;
    private String code;
    private double multiplier;
    private int baseValue;

    public CustomerType(String name, String code, double multiplier, int baseValue) {
        this.name = name;
        this.code = code;
        this.multiplier = multiplier;
        this.baseValue = baseValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }

    @Override
    public String toString() {
        return "Role{name='" + name + "', code=" + code + ", baseValue=" + baseValue + "}";
    }
}

class Customer {
    private String name;
    private String typeCode;
    private double energyConsumption;

    public Customer(String name, String typeCode, double energyConsumption) {
        this.name = name;
        this.typeCode = typeCode;
        this.energyConsumption = energyConsumption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String roleCode) {
        this.typeCode = roleCode;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(double balance) {
        this.energyConsumption = balance;
    }

    @Override
    public String toString() {
        return "Cliente: '" + name + "', Código do tipo de cliente: " + typeCode + ", Consumo de energia: " + energyConsumption;
    }
}

public class ExercicioDois {
    private static List<CustomerType> customerTypes = new ArrayList<>();

    static {
        customerTypes.add(new CustomerType("Industrial", "I", 0.68, 34));
        customerTypes.add(new CustomerType("Comercial", "C", 0.37, 45));
        customerTypes.add(new CustomerType("Residencial", "R", 0.77, 12));
    }

    public static double calcBalance(Customer customer) throws Exception {
        CustomerType userType = customerTypes.stream()
                .filter(type -> Objects.equals(type.getCode(), customer.getTypeCode()))
                .findFirst()
                .orElseThrow(() -> new Exception("Tipo de cliente não encontrado"));

        return userType.getMultiplier() * customer.getEnergyConsumption() + userType.getBaseValue();
    }

    public static String getTypeName(String typeCode) throws Exception {
        return customerTypes.stream()
                .filter(type -> Objects.equals(type.getCode(), typeCode))
                .map(CustomerType::getName)
                .findFirst()
                .orElseThrow(() -> new Exception("Tipo de cliente inválido"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            System.out.println("Cliente " + (i + 1) + ":");
            System.out.print("Digite o nome do cliente: ");
            String name = scanner.next();

            System.out.print("Digite o código tipo de cliente ([I] Industrial, [C] Comercial, [R] Residencial): ");
            String customerTypeCode = scanner.next();

            System.out.print("Digite o consumo de energia do cliente: ");
            double energyConsumption = scanner.nextDouble();

            customers.add(new Customer(name, customerTypeCode.toUpperCase(), energyConsumption));
            System.out.println();
        }

        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s |\n", "Nome", "Tipo", "Consumo", "Valor Final");
        System.out.println("-------------------------------------------------------------------------");

        for (Customer customer : customers) {
            try {
                String typeName = getTypeName(customer.getTypeCode());
                double newEnergyConsumption = calcBalance(customer);
                System.out.printf("| %-15s | %-15s | %-15.2f | %-15.2f |\n",
                        customer.getName(), typeName, customer.getEnergyConsumption(), newEnergyConsumption);
            } catch (Exception e) {
                System.out.printf("| %-15s | %-15s | %-15s | %-15s |\n",
                        customer.getName(), "Erro", "Erro", "Erro");
            }
        }

        System.out.println("-------------------------------------------------------------------------");
        scanner.close();
    }
}