// Trabalho feito por: Cristian Menezes de Freitas
// OBS: A tarefa/trabalho foi feito sozinho sem a ajuda de terceiros.
//

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Role {

  private String name;
  private int code;
  private double multiplier;

  public Role(String name, int code, double multiplier) {
    this.name = name;
    this.code = code;
    this.multiplier = multiplier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public double getMultiplier() {
    return multiplier;
  }

  public void setMultiplier(double multiplier) {
    this.multiplier = multiplier;
  }

  @Override
  public String toString() {
    return (
      "Cargo: '" +
      name +
      "', Código do cargo: " +
      code +
      ", Multiplicador de salário (%): " +
      multiplier
    );
  }
}

class User {

  private String name;
  private int roleCode;
  private double balance;

  public User(String name, int roleCode, double balance) {
    this.name = name;
    this.roleCode = roleCode;
    this.balance = balance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(int roleCode) {
    this.roleCode = roleCode;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return (
      "Usuário: " +
      name +
      "', Código da função" +
      roleCode +
      ", Salário inicial: " +
      balance
    );
  }
}

public class ExercicioUm {

  private static List<Role> roles = new ArrayList<>();

  static {
    roles.add(new Role("Gerente", 101, 0.1));
    roles.add(new Role("Engenheiro", 102, 0.2));
    roles.add(new Role("Técnico", 103, 0.3));
  }

  public static double calcBalance(User user) throws Exception {
    Role role = roles
      .stream()
      .filter(r -> r.getCode() == user.getRoleCode())
      .findFirst()
      .orElse(null);

    if (role == null) {
      return user.getBalance() * 0.05 + user.getBalance();
    } else {
      return user.getBalance() * role.getMultiplier() + user.getBalance();
    }
  }

  public static String findRoleNome(User user) throws Exception {
    Role role = roles
      .stream()
      .filter(r -> r.getCode() == user.getRoleCode())
      .findFirst()
      .orElse(null);

    if(role == null) {
      return "N/A";
    } else {
      return role.getName();
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite seu nome: ");
    String name = scanner.next();

    System.out.print("Digite o código do seu cargo (função): ");
    int roleCode = scanner.nextInt();

    System.out.print("Digite seu salário inicial: ");
    double balance = scanner.nextDouble();

    User user = new User(name, roleCode, balance);

    try {
      double newBalance = calcBalance(user);
      System.out.println(
        "----------------------------------------------------------------------"
      );
      System.out.printf(
        "| %-20s | %-20s | %-20s |\n",
        "Usuário",
        "Cargo",
        "Novo Salário"
      );
      System.out.println(
        "----------------------------------------------------------------------"
      );
      System.out.printf(
        "| %-20s | %-20s | %-20.2f |\n",
        user.getName(),
        findRoleNome(user),
        newBalance
      );
      System.out.println(
        "----------------------------------------------------------------------"
      );
    } catch (Exception e) {
      e.printStackTrace();
    }

    scanner.close();
  }
}
