# ${{ values.name }}

${{ values.description }}

Repo de **deploy** (gitops) do `${{ values.name }}` — par do repo de **código**
`tourinho-labs/${{ values.name }}` (Fastify + CI que builda a imagem). Aqui o app
é deployado **consumindo o arquétipo Helm central `${{ values.archetype }}@2.0.0`**
(`oci://ghcr.io/tourinhom/charts`) — por versão, sem copiar o chart, com a `image`
apontando pro que o CI do repo de código publica.

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
