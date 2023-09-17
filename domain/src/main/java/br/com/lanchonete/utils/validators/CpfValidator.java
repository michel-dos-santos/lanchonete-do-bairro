package br.com.lanchonete.utils.validators;

public class CpfValidator {
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static int calculateDigit(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
    private static String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }
    public static boolean isValid(String cpf) {
        if ((cpf==null) || (cpf.length()!=11)) return false;
        cpf = cpf.trim().replace(".", "").replace("-", "");

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        int digito1 = calculateDigit(cpf.substring(0,9), pesoCPF);
        int digito2 = calculateDigit(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) +  digito1 + digito2);
    }
}
