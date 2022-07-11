import java.util.Scanner;

public class TrabalhoFinal {
    public static Scanner scan = new Scanner(System.in);

    public static int informaQtdClientes() {
        System.out.print("Informe a quantidade de clientes: ");
        int qtdClientes = scan.nextInt();
        return qtdClientes;
    }

    public static void armazenaDados(String nomes[], String telefones[], int tipos[], int minutos[]) {
        for (int i = 0; i < nomes.length; i++) {
            System.out.print("\nDados do " + (i + 1) + "o. cliente:");
            scan.nextLine();
            System.out.print("\nNome: ");
            nomes[i] = scan.nextLine();
            System.out.print("Telefone: ");
            telefones[i] = scan.nextLine();
            System.out.print("Tipo: ");
            tipos[i] = scan.nextInt();
            System.out.print("Minutos: ");
            minutos[i] = scan.nextInt();
        }
    }

    public static void lePrecos (double precos[][]) {
        System.out.println("\nInforme o preco basico e excedente de cada tipo de conta:");
        for (int i = 0; i < precos.length; i++) {
            System.out.printf("Tipo %d: ", i);
            precos[i][0] = scan.nextDouble();
            precos[i][1] = scan.nextDouble();
        }
    }

    public static void calculaValorDaConta(int minutos[], double precos[][], double valorDaConta[], int tipos[]) {
        for (int i = 0; i < valorDaConta.length; i++) {
            if (minutos[i] <= 90) {
                if (tipos[i] == 0) {
                    valorDaConta[i] = precos[0][0];
                } else if (tipos[i] == 1) {
                    valorDaConta[i] = precos[1][0];
                } else {
                    valorDaConta[i] = precos[2][0];
                }
                
            } else {
                if (tipos[i] == 0) {
                    valorDaConta[i] = (minutos[i] - 90) * precos[0][1] + (precos[0][0]);
                } else if (tipos[i] == 1) {
                    valorDaConta[i] = (minutos[i] - 90) * precos[1][1] + (precos[1][0]);
                } else {
                    valorDaConta[i] = (minutos[i] - 90) * precos[2][1] + (precos[2][0]);
                }
            }
        }
    }

    public static void relatorioClientes (String nomes[], String telefones[], int tipos[], int minutos[], double valorDaConta[]) {
        for (int i = 0; i < nomes.length; i++) {
            System.out.printf("%s, %s, Tipo %d, Minutos: %d, Conta = R$ %.2f\n", nomes[i], telefones[i], tipos[i], minutos[i], valorDaConta[i]);
        }
    }

    public static void receitaTotal (double valorDaConta[]) {
        double receitaTotal = 0;

        for (int i = 0; i< valorDaConta.length; i++) {
            receitaTotal += valorDaConta[i];
        }

        System.out.printf("\nReceita total da empresa: %.2f\n", receitaTotal);
    }

    public static void contaMaisBarata (double valorDaConta[]) {
        double maisBarata = valorDaConta[0];
        for (int i = 0; i < valorDaConta.length; i++) {
            if (valorDaConta[i] < maisBarata) {
                maisBarata = valorDaConta[i];
            }
        }
        System.out.printf("Conta mais barata = R$ %.2f\n", maisBarata);
    }

    public static void consumoMedio (int tipos[], int minutos[]) {
        int cont = 0, soma = 0;
        double consumoMedio = 0.0;
        for (int i = 0; i < tipos.length; i++) {
            if (tipos[i] == 1) {
                cont++;
                soma += minutos[i];
                consumoMedio =  (double) soma / cont;
            }
        }
        System.out.printf("Consumo médio de clientes tipo 1: %.1f pulsos\n", consumoMedio);
    }

    public static void acimaDe120 (int minutos[], String nomes[]) {
        System.out.println("Clientes que consumiram acima de 120 pulsos:"); 
        for (int i = 0; i < minutos.length; i++) {
            if (minutos[i] > 120) {
                System.out.printf("%s, Minutos: %d\n", nomes[i], minutos[i]);
            }
        }
    }

    public static void calculaPorcentagem (int tipos[]) {
        double porcentagem = 0.0, cont = 0.0;
        for (int i = 0; i < tipos.length; i++) {
            if (tipos[i] == 2) {
                cont++;
            }  
        }
        porcentagem = (cont / tipos.length) * 100.0;
        System.out.printf("Porcentagem de clientes do tipo 2: %.1f%%\n", porcentagem);
    }

    public static void menuDeOpcoes(String nomes[], String telefones[], int tipos[], int minutos[], double valorDaConta[]) {
        int opcao = 0;
        while (opcao != 7) {  
            System.out.println("\n---------------- MENU DE OPÇÕES ----------------");
            System.out.println("1) Relatorio de clientes");
            System.out.println("2) A receita total");
            System.out.println("3) Conta foi mais barata");
            System.out.println("4) Consumo medio de clientes tipo 1");
            System.out.println("5) Clientes que consumiram acima de 120 pulsos");
            System.out.println("6) A porcentagem de clientes tipo 2");
            System.out.println("7) Sair");
            System.out.println("-----------------------------------------------\n");
            System.out.print("Informe uma opção: ");
            opcao = scan.nextInt();
  
            switch (opcao) {
                case 1:
                    relatorioClientes(nomes, telefones, tipos, minutos, valorDaConta);
                    break;
                case 2:
                    receitaTotal(valorDaConta);
                    break;
                case 3:
                    contaMaisBarata(valorDaConta);
                    break;
                case 4:
                    consumoMedio(tipos, minutos);
                    break;
                case 5:
                    acimaDe120(minutos, nomes);
                    break;
                case 6:
                    calculaPorcentagem(tipos);
                    break;
                case 7:
                    System.out.println("\nFIM DO PROGRAMA!");
                    break;
                default:
                    System.out.println("\nDigite uma opção válida!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        int n = informaQtdClientes();
        String nomes[] = new String[n];
        String telefones[] = new String[n];
        int tipos[] = new int[n];
        int minutos[] = new int[n];
        double precos[][] = new double[3][2];
        double valorDaConta[] = new double[n];

        armazenaDados(nomes, telefones, tipos, minutos);
        lePrecos(precos);
        calculaValorDaConta(minutos, precos, valorDaConta, tipos);
        menuDeOpcoes(nomes, telefones, tipos, minutos, valorDaConta);
    }
}
