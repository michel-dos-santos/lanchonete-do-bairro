package br.com.lanchonete.model;

import java.util.Arrays;

public abstract class LogCode {
    public static LogCodeInfo INFO;
    public static LogCodeDebug DEBUG;
    public static LogCodeWarn WARN;
    public static LogCodeError ERROR;

    public enum LogCodeInfo {
        _0001("Iniciando o processo de criação do cliente no sistema"),
        _0002("Validando a consistência dos dados do cliente"),
        _0003("Cliente criado com sucesso"),
        _0004("Identificando o cliente com base no CPF"),
        _0005("Cliente identificado com sucesso"),
        _0006("Iniciando o processo de criação do produto no sistema"),
        _0007("Produto criado com sucesso"),
        _0008("Validando a consistência dos dados do produto"),
        _0009("Iniciando o processo de criação da categoria no sistema"),
        _0010("Categoria criada com sucesso"),
        _0011("Validando a consistência dos dados da categoria"),
        _0012("Iniciando o processo de listagem das categorias"),
        _0013("Listagem das categorias executada com sucesso"),
        _0014("Iniciando o processo de atualização do produto no sistema"),
        _0015("Produto atualizado com sucesso"),
        _0016("Iniciando o processo de exclusão do produto no sistema"),
        _0017("Produto excluido com sucesso"),
        _0018("Iniciando o processo de atualização de campos do produto no sistema"),
        _0019("Iniciando o processo de busca dos produtos pela categoria no sistema"),
        _0020("Produtos encontrados com sucesso"),
        _0021("Validando a consistência dos dados do pedido"),
        _0022("Iniciando o processo de checkout do pedido no sistema"),
        _0023("Pedido criado com sucesso"),
        _0024("Iniciando o processo de fazer a cobrança do pedido no sistema"),
        _0025("Cobrança do pedido criado com sucesso"),
        _0026("Iniciando o processo de busca dos pedidos com base no status no sistema"),
        _0027("Pedidos encontrados com sucesso"),
        _0028("Iniciando o processo de notificação da cobrança do pedido para o hub de pagamento"),
        _0029("Notificação da cobrança do pedido para o hub de pagamento realizada com sucesso"),
        _0030("Enviando a cobrança para o sistema do MercadoPago"),
        _0031("Envio da cobrança para o sistema do MercadoPago realizada com sucesso"),
        _0032("Enviando a cobrança para o sistema do Itaú"),
        _0033("Envio da cobrança para o sistema do Itaú realizada com sucesso"),
        _0034("Recebendo informação da cobrança do sistema do Itaú"),
        _0035("Recebido informação da cobrança com sucesso"),
        _0036("Atualizado informação da cobrança com sucesso"),
        _0037("Recebendo informação da cobrança do sistema do Mercado Pago"),
        _0038("Buscando dados do meu pedido com base no id"),
        _0039("Meu pedido encontrado com sucesso"),
        _0040("Buscando status do pagamento do meu pedido com base no id"),
        _0041("Status do pagamento do meu pedido encontrado com sucesso"),
        _0042("Atualizando status do pedido com base no id"),
        _0043("Status do pedido atualizado com sucesso"),
        _0044("Buscando pedidos para listagem do monitor"),
        _0045("Pedidos para listagem do monitor encontrado com sucesso"),
        ;

        private String description;

        LogCodeInfo(String description) {
            this.description = description;
        }

        public static LogCodeInfo get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeDebug {
        _0001("Template");

        private String description;

        LogCodeDebug(String description) {
            this.description = description;
        }

        public static LogCodeDebug get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeWarn {
        _0001("Template");

        private String description;

        LogCodeWarn(String description) {
            this.description = description;
        }

        public static LogCodeWarn get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeError {
        _0001("Template");

        private String description;

        LogCodeError(String description) {
            this.description = description;
        }

        public static LogCodeError get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }
}
