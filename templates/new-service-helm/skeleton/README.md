# ${{ values.name }} — deploy (gitops)

Repo de **deploy** do `${{ values.name }}`, **gerenciado por máquina** — par do
repo de **código** `tourinho-labs/${{ values.name }}`. **O dev não edita aqui**:
ele mexe no `service.yaml` do repo de código, e o CI materializa este repo.

O app é deployado **consumindo o arquétipo Helm central `${{ values.archetype }}`**
(`oci://ghcr.io/tourinhom/charts`) por versão, sem copiar o chart.

## Estrutura (multi-ambiente)

```
Chart.yaml              dep <arquétipo>@versão
values.yaml             params COMUNS (do service.yaml do dev)
values/
  dev.yaml              image do dev   (+ overrides do env)
  hml.yaml              image do hml
  prod.yaml             image do prod
argocd/application.yaml uma Application por env (dev/hml/prod), com
                        valueFiles [values.yaml, values/<env>.yaml]
renovate.json           bump do arquétipo
```

## Quem escreve o quê (ninguém à mão)

| Camada | Arquivo | Quem | Como |
|--------|---------|------|------|
| params comuns | `values.yaml` | **CI config-sync** | dev edita `service.yaml` no código → push |
| image por ambiente | `values/<env>.yaml` | **CI / Kargo** | promoção dev → hml → prod (gated) |
| versão do arquétipo | `Chart.yaml` | **Renovate** | PR quando sai versão nova do template |

## Promoção de ambiente (Kargo-ready)

Cada ambiente tem sua Application e seu `values/<env>.yaml`. A promoção avança a
`image` de um env pro próximo (`values/dev.yaml` → `values/hml.yaml` → `…/prod.yaml`);
o Argo sincroniza só o ambiente alterado. Os três nascem com a imagem inicial; o
Kargo passa a gatekeepar a promoção das versões novas.
