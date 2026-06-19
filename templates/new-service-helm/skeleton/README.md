# ${{ values.name }}

${{ values.description }}

Deploy do app **consumindo o arquétipo Helm central `${{ values.archetype }}@2.0.0`**
(`oci://ghcr.io/tourinhom/charts`) — por versão, sem copiar o chart.

## Como funciona

- **`Chart.yaml`** declara a dependência do arquétipo por versão. O arquétipo traz
  os defaults da plataforma (labels + governança, securityContext, resources, probes).
- **`values.yaml`** carrega só o específico do app (`team`, `system`, `image`).
- **`argocd/application.yaml`** registra no Argo; ele autodetecta o `Chart.yaml`,
  puxa o arquétipo do OCI e renderiza.
- **`renovate.json`** abre PR quando sai versão nova do arquétipo — atualização
  central via pin-por-versão.

## Day-2

```bash
helm dependency update .   # resolve o arquétipo do OCI
helm template . | less     # vê o YAML final renderizado
```

Mudar comportamento = editar `values.yaml` (o que o arquétipo expõe) ou subir a
`version` da dependência. Sem `if` em template, sem cópia de chart.
