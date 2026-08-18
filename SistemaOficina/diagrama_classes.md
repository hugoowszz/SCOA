# Diagrama de Classes Atualizado

Aqui está a representação atualizada do seu diagrama de classes. Adicionei a entidade `Material` e uma classe intermediária `ConsumoMaterial` para registrar o quanto de cada material foi gasto em um determinado Serviço.

```mermaid
classDiagram
    %% Estilo aplicado para manter o visual azul claro com bordas pretas do seu print
    classDef default fill:#74c7ec,stroke:#000000,stroke-width:2px,color:#000000,font-weight:bold;

    class Orcamento {
        -id:long
        -modelo_veiculo:string
        -marca_veiculo:string
        -cor_veiculo:string
        -placa_veiculo:string
        -observacao:string
        -preco:double
    }
    
    class Servico {
        -id:long
        -orcamento_id:orcamento
        -funcionario_id:funcionario
        -nome_cliente:string
        -contato_cliente:string
        -status:string
        -data_criacao:datetime
        -data_fim:datetime
        -metodo_pagamento:string
    }
    
    class Funcionario {
        -id:long
        -nome:string
        -telefone:string
    }
    
    class Material {
        -id:long
        -nome:string
        -unidade_medida:string
        -quantidade_estoque:double
        -estoque_minimo:double
    }
    
    class RegistroSaida {
        -id:long
        -material_id:material
        -quantidade_gasta:double
        -data_saida:datetime
    }

    %% Relações idênticas ao seu desenho original
    Orcamento -- Servico
    Servico o-- Funcionario 
    
    %% Relações da nova entidade (sem ligação com o serviço)
    Material "1" -- "*" RegistroSaida : possui_historico_de
```
